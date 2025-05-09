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

public interface ElementRegistry {

    /**
     * @param avatarInfo information of avatar
     * @param name       name of element
     * @return size of element
     */
    int getElementCount(AvatarInfo avatarInfo, String name);

    /**
     * @param avatarInfo information of avatar
     * @param name       name of element
     * @param index      position in element
     * @return a path of element
     */
    Image getElement(AvatarInfo avatarInfo, String name, int index);

    /**
     * @param avatarInfo information of avatar
     * @return size of group
     */
    int getGroupCount(AvatarInfo avatarInfo);

    /**
     * @param avatarInfo information of avatar
     * @param index      position in group
     * @return array of element information
     */
    ElementInfo[] getGroup(AvatarInfo avatarInfo, int index);

}
