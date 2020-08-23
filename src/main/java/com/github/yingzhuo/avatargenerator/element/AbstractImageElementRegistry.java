/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
