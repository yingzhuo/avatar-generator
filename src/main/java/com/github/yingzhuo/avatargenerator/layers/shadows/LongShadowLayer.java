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
package com.github.yingzhuo.avatargenerator.layers.shadows;

import com.github.yingzhuo.avatargenerator.AvatarInfo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LongShadowLayer extends AbstractShadowLayer {

    private Color shadowColor;

    public LongShadowLayer() {
        this(new Color(0, 0, 0, 64));
    }

    public LongShadowLayer(Color shadowColor) {
        super(true);
        this.shadowColor = shadowColor;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    @Override
    protected BufferedImage buildShadow(AvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        double n = shadowColor.getAlpha();
        double step = n / (width + height);

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isInShade(src, x, y)) {
                    int alpha = (int) (n - ((x + y) * step));
                    int[] colors = new int[]{shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), alpha};

                    dest.getRaster().setPixel(x, y, colors);
                }
            }
        }
        return dest;
    }

    private boolean isInShade(BufferedImage src, int x, int y) {
        int tx = x;
        int ty = y;
        int[] colors = new int[4];
        while (true) {
            tx -= 1;
            ty -= 1;
            if (tx < 0 || ty < 0) {
                return false;
            } else {
                src.getRaster().getPixel(tx, ty, colors);
                if (colors[3] > 0) {
                    return true;
                }
            }
        }
    }
}
