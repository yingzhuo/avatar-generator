/*
 *
 * Copyright 2022-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.github.yingzhuo.avatargenerator;

import com.github.yingzhuo.avatargenerator.element.ElementInfo;
import com.github.yingzhuo.avatargenerator.element.ElementRegistry;
import com.github.yingzhuo.avatargenerator.layers.Layer;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.*;

public final class Avatar implements Serializable {

    private int width = 128;
    private int height = 128;
    private int padding = 0;
    private int margin = 0;

    private ElementRegistry elementRegistry = null;
    private ColorizeFunction colorizeFunction = null;
    private Layer[] layers;

    private Avatar() {
    }

    public static AvatarBuilder newBuilder() {
        return new AvatarBuilder();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPadding() {
        return padding;
    }

    public int getMargin() {
        return margin;
    }

    public BufferedImage create(long code) {
        Random random = new Random(code);
        AvatarInfo avatarInfo = new AvatarInfoImpl(code, random);
        return buildAll(avatarInfo);
    }

    public byte[] createAsPngBytes(long code) {
        BufferedImage src = create(code);
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            ImageIO.write(src, "png", os);
            return os.toByteArray();
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write png for code=" + code, e);
        }
    }

    public String createAsPngBase64EncodedString(long code) {
        return Base64.getEncoder().encodeToString(createAsPngBytes(code));
    }

    public void createAsPngToFile(long code, File file) {
        BufferedImage src = create(code);
        try {
            ImageIO.write(src, "png", file);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to write png for code=" + code, e);
        }
    }

    private BufferedImage buildAll(AvatarInfo avatarInfo) {
        try {
            BufferedImage bufferedImage = buildAvatarImage(avatarInfo);

            int wm = width - margin * 2;
            int hm = height - margin * 2;
            int wmp = wm - padding * 2;
            int hmp = hm - padding * 2;

            bufferedImage = AvatarUtils.resizeImage(bufferedImage, wmp, hmp);
            bufferedImage = AvatarUtils.planImage(bufferedImage, wm, hm);

            if (layers != null && layers.length > 0) {
                for (Layer layer : layers) {
                    bufferedImage = layer.apply(avatarInfo, bufferedImage);
                }
            }

            bufferedImage = AvatarUtils.resizeImage(bufferedImage, wm, hm);
            bufferedImage = AvatarUtils.planImage(bufferedImage, width, height);
            return bufferedImage;
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to build avatar", e);
        }
    }

    private BufferedImage buildAvatarImage(AvatarInfo avatarInfo) throws IOException {
        if (elementRegistry == null) {
            return new BufferedImage(width - (margin + padding) * 2, height - (margin + padding) * 2, BufferedImage.TYPE_INT_ARGB);
        }

        Random random = avatarInfo.getRandom();

        int xmin = Integer.MAX_VALUE, ymin = Integer.MAX_VALUE, xmax = Integer.MIN_VALUE, ymax = Integer.MIN_VALUE;
        List<ImageInfo> imageInfos = new ArrayList<>();
        int groupCount = elementRegistry.getGroupCount(avatarInfo);
        if (groupCount > 0) {
            int d = random.nextInt(groupCount);
            ElementInfo[] elements = elementRegistry.getGroup(avatarInfo, d);
            if (elements != null && elements.length > 0) {
                for (ElementInfo element : elements) {
                    int elementCount = elementRegistry.getElementCount(avatarInfo, element.name);
                    if (elementCount > 0) {
                        int index = random.nextInt(elementCount);
                        BufferedImage bufferedImage = AvatarUtils.toARGBImage(elementRegistry.getElement(avatarInfo, element.name, index));

                        xmin = Math.min(xmin, -bufferedImage.getWidth() / 2 + element.offsetX);
                        xmax = Math.max(xmax, bufferedImage.getWidth() / 2 + element.offsetX);
                        ymin = Math.min(ymin, -bufferedImage.getHeight() / 2 + element.offsetY);
                        ymax = Math.max(ymax, bufferedImage.getHeight() / 2 + element.offsetY);

                        imageInfos.add(new ImageInfo(element.name, bufferedImage, element.offsetX, element.offsetY));
                    }
                }
            }
        }
        int w = xmax - xmin;
        int h = ymax - ymin;

        BufferedImage dest = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);

        for (ImageInfo imageInfo : imageInfos) {
            copyImage(g2, avatarInfo, imageInfo, w, h);
        }

        g2.dispose();

        return dest;
    }

    private void copyImage(Graphics2D g2, AvatarInfo avatarInfo, ImageInfo imageInfo, int width, int height) throws IOException {
        BufferedImage img = imageInfo.image;
        if (colorizeFunction != null) {
            Color color = colorizeFunction.colorize(avatarInfo, imageInfo.element);
            if (color != null) {
                img = AvatarUtils.tintImage(img, color);
            }
        }
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        int x = (width - w) / 2 + imageInfo.offsetX;
        int y = (height - h) / 2 + imageInfo.offsetY;
        g2.drawImage(img, x, y, w, h, null);
    }

    public interface ColorizeFunction {
        public Color colorize(AvatarInfo avatarInfo, String element);
    }

    public static class AvatarBuilder {

        private int width = 128;
        private int height = 128;
        private int padding = 0;
        private int margin = 0;

        private ElementRegistry elementRegistry = null;
        private ColorizeFunction colorizeFunction = null;
        private Layer[] layers;

        private AvatarBuilder() {
            super();
        }

        public AvatarBuilder elementRegistry(ElementRegistry elementRegistry) {
            this.elementRegistry = elementRegistry;
            return this;
        }

        public AvatarBuilder size(int width, int height) {
            this.width = width;
            this.height = height;
            return this;
        }

        public AvatarBuilder padding(int padding) {
            this.padding = padding;
            return this;
        }

        public AvatarBuilder margin(int margin) {
            this.margin = margin;
            return this;
        }

        public AvatarBuilder layers(Layer... layers) {
            this.layers = layers;
            return this;
        }

        public AvatarBuilder color(Color color) {
            return colorizeFunction((c, e) -> color);
        }

        public AvatarBuilder colorizeFunction(ColorizeFunction colorizeFunction) {
            this.colorizeFunction = colorizeFunction;
            return this;
        }

        public Avatar build() {
            Avatar avatar = new Avatar();
            avatar.width = width;
            avatar.height = height;
            avatar.padding = padding;
            avatar.margin = margin;
            avatar.elementRegistry = elementRegistry;
            avatar.colorizeFunction = colorizeFunction;
            avatar.layers = layers != null ? Arrays.copyOf(layers, layers.length) : null;
            return avatar;
        }
    }

    private static class ImageInfo {

        public final String element;
        public final BufferedImage image;
        public final int offsetX;
        public final int offsetY;

        ImageInfo(String element, BufferedImage image, int offsetX, int offsetY) {
            this.element = element;
            this.image = image;
            this.offsetX = offsetX;
            this.offsetY = offsetY;
        }
    }

    private class AvatarInfoImpl implements AvatarInfo {

        private final long code;
        private final Random random;

        public AvatarInfoImpl(long code, Random random) {
            this.code = code;
            this.random = random;
        }

        @Override
        public long getCode() {
            return code;
        }

        @Override
        public Random getRandom() {
            return random;
        }

        @Override
        public int getWidth() {
            return width;
        }

        @Override
        public int getHeight() {
            return height;
        }

        @Override
        public int getPadding() {
            return padding;
        }

        @Override
        public int getMargin() {
            return margin;
        }
    }

}
