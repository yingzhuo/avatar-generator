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
