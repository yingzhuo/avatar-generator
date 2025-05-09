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
package com.github.yingzhuo.avatargenerator.layers.backgrounds;

import com.github.yingzhuo.avatargenerator.AvatarInfo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RadialPaintBackgroundLayer extends AbstractPaintBackgroundLayer {

    private final Color inColor;
    private final Color outColor;

    public RadialPaintBackgroundLayer() {
        this(new Color(0xE2A6FF), new Color(0xC58BFF));
    }

    public RadialPaintBackgroundLayer(Color inColor, Color outColor) {
        this.inColor = inColor;
        this.outColor = outColor;
    }

    @Override
    protected Paint buildPaint(AvatarInfo avatarInfo, BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        return new RadialGradientPaint(width / 2, height / 2, (int) Math.sqrt(width * width + height * height) / 2, new float[]{0.0f, 0.75f}, new Color[]{inColor, outColor});
    }
}
