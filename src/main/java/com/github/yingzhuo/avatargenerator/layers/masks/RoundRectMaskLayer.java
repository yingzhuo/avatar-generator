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
package com.github.yingzhuo.avatargenerator.layers.masks;

import com.github.yingzhuo.avatargenerator.AvatarInfo;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoundRectMaskLayer extends AbstractMaskLayer {

    private final double percent;

    public RoundRectMaskLayer() {
        this(0.5);
    }

    public RoundRectMaskLayer(double percent) {
        this.percent = percent;
    }

    @Override
    protected BufferedImage buildMask(AvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.setColor(Color.white);
        g2.fillRoundRect(0, 0, width, height, (int) (width * percent), (int) (height * percent));
        g2.dispose();
        return mask;
    }
}
