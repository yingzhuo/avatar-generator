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
package com.github.yingzhuo.avatargenerator.layers.others;

import com.github.yingzhuo.avatargenerator.AvatarInfo;
import com.github.yingzhuo.avatargenerator.layers.Layer;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class RandomColorPaintLayer implements Layer {

    private final List<Color> colors;
    private final Color whiteColor;
    private final Color blackColor;

    public RandomColorPaintLayer() {
        this(AvatarUtils.defaultColors, Color.BLACK, Color.WHITE);
    }

    public RandomColorPaintLayer(List<Color> colors, Color blackColor, Color whiteColor) {
        this.colors = colors;
        this.whiteColor = whiteColor;
        this.blackColor = blackColor;
    }

    @Override
    public BufferedImage apply(AvatarInfo avatarInfo, BufferedImage src) {
        Color backColor = colors.get((int) (avatarInfo.getCode() % colors.size()));
        Color foreColor = AvatarUtils.getComplementColor(backColor, blackColor, whiteColor);

        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.setPaint(backColor);
        g2.fillRect(0, 0, width, height);
        g2.drawImage(AvatarUtils.fillColorImage(src, foreColor), 0, 0, null);
        g2.dispose();
        return dest;
    }
}
