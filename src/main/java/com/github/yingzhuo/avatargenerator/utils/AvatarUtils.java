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
package com.github.yingzhuo.avatargenerator.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public final class AvatarUtils {

    public final static List<Color> defaultColors = Arrays
            .asList(new Color(0x6e1e78), new Color(0x82be00), new Color(0xa1006b), new Color(0x009aa6), new Color(0xcd0037), new Color(0x0088ce), new Color(0xe05206), new Color(0xd52b1e),
                    new Color(0xffb612), new Color(0xd2e100));

    private AvatarUtils() {
    }

    public static void activeAntialiasing(Graphics2D g2) {
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
    }

    public static BufferedImage resizeImage(BufferedImage src, int width, int height) {
        int original_width = src.getWidth();
        int original_height = src.getHeight();

        if (original_width == width && original_height == height) {
            return src;
        }

        double diffComponent = width / (double) height;
        double diffImage = original_width / (double) original_height;

        double diff = diffImage / diffComponent;
        int w = width;
        int h = height;
        if (diff >= 1.0) {
            h = (int) (h / diff);
        } else {
            w = (int) (w * diff);
        }

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.drawImage(src.getScaledInstance(w, h, Image.SCALE_SMOOTH), (width - w) / 2, (height - h) / 2, null);
        g2.dispose();
        return dest;
    }

    public static BufferedImage fillColorImage(BufferedImage src, Color color) {
        int original_width = src.getWidth();
        int original_height = src.getHeight();

        BufferedImage dest = new BufferedImage(original_width, original_height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        g2.setColor(color);
        g2.fillRect(0, 0, original_width, original_height);
        g2.setComposite(AlphaComposite.DstIn);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }

    public static BufferedImage tintImage(BufferedImage src, Color color) {
        int w = src.getWidth();
        int h = src.getHeight();
        BufferedImage dest = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dest.createGraphics();
        g.drawImage(src, 0, 0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0, 0, w, h);
        g.dispose();
        return dest;
    }

    public static BufferedImage planImage(BufferedImage src, int width, int height) {
        int original_width = src.getWidth();
        int original_height = src.getHeight();
        if (original_width == width && original_height == height) {
            return src;
        }

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, (width - original_width) / 2, (height - original_height) / 2, null);
        g2.dispose();
        return dest;
    }

    public static Color getComplementColor(Color color, Color blackColor, Color whiteColor) {
        float[] rgba1 = color.getComponents(null);
        double l = 0.2126 * rgba1[0] + 0.7152 * rgba1[1] + 0.0722 * rgba1[2];
        double ratio = (l + 0.05) / 0.05;
        return ratio > 7 ? blackColor : whiteColor;
    }

    public static BufferedImage toARGBImage(Image src) {
        BufferedImage dest = new BufferedImage(src.getWidth(null), src.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }

    public static Path saveImageInTemp(BufferedImage src) {
        try {
            Path path = Files.createTempFile("image", ".png");
            ImageIO.write(src, "png", path.toFile());
            return path;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
    }

    private static String addZeroes(String ns, int nd) {
        for (int i = 0; i < nd - ns.length(); i++) {
            ns = "0" + ns;
        }
        return ns;
    }

    public static Color extractColor(long code) {
        String n = addZeroes(Long.toHexString(code), 6);
        return new Color(Integer.parseInt(n.substring(0, 6), 16));
    }

    public static float getColorDistance(Color c1, Color c2) {
        float dx = c1.getRed() - c2.getRed();
        float dy = c1.getGreen() - c2.getGreen();
        float dz = c1.getBlue() - c2.getBlue();
        return (float) Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public static Color getComplementaryColor(Color color) {
        return new Color(color.getRGB() ^ 0x00FFFFFF);
    }
}
