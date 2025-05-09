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

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractImageElementRegistry implements ElementRegistry {

    protected AbstractImageElementRegistry() {
        super();
    }

    @Override
    public int getElementCount(AvatarInfo avatarInfo, String name) {
        return 1;
    }

    @Override
    public Image getElement(AvatarInfo avatarInfo, String name, int index) {
        return buildImage(avatarInfo);
    }

    @Override
    public int getGroupCount(AvatarInfo avatarInfo) {
        return 1;
    }

    @Override
    public ElementInfo[] getGroup(AvatarInfo avatarInfo, int index) {
        return new ElementInfo[]{ElementInfo.of("github")};
    }

    protected abstract BufferedImage buildImage(AvatarInfo avatarInfo);

}
