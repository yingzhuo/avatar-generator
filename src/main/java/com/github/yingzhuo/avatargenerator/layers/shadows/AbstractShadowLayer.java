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
import com.github.yingzhuo.avatargenerator.layers.Layer;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractShadowLayer implements Layer {

    private boolean first;

    protected AbstractShadowLayer(boolean first) {
        super();
        this.first = first;
    }

    @Override
    public BufferedImage apply(AvatarInfo avatarInfo, BufferedImage src) {
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        if (first) {
            g2.drawImage(buildShadow(avatarInfo, src), 0, 0, null);
        }
        g2.drawImage(src, 0, 0, null);
        if (!first) {
            g2.drawImage(buildShadow(avatarInfo, src), 0, 0, null);
        }
        g2.dispose();
        return dest;
    }

    protected abstract BufferedImage buildShadow(AvatarInfo avatarInfo, BufferedImage src);

}
