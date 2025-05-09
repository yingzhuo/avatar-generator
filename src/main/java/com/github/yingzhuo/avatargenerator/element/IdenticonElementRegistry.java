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
package com.github.yingzhuo.avatargenerator.element;

import com.github.yingzhuo.avatargenerator.AvatarInfo;
import com.github.yingzhuo.avatargenerator.element.identicon.NineBlockIdenticonRenderer;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class IdenticonElementRegistry extends AbstractImageElementRegistry {

    private final NineBlockIdenticonRenderer nineBlockIdenticonRenderer;

    public IdenticonElementRegistry() {
        this(new NineBlockIdenticonRenderer());
    }

    public IdenticonElementRegistry(NineBlockIdenticonRenderer nineBlockIdenticonRenderer) {
        this.nineBlockIdenticonRenderer = nineBlockIdenticonRenderer;
    }

    @Override
    protected BufferedImage buildImage(AvatarInfo avatarInfo) {
        int code = new BigDecimal(avatarInfo.getCode()).remainder(new BigDecimal(2).pow(32)).intValue();
        int size = Math.min(avatarInfo.getWidth() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2, avatarInfo.getHeight() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2);
        return nineBlockIdenticonRenderer.render(code, size);
    }
}
