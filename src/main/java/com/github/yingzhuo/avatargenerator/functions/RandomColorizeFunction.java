/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.avatargenerator.functions;

import com.github.yingzhuo.avatargenerator.Avatar;
import com.github.yingzhuo.avatargenerator.AvatarInfo;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import java.awt.*;
import java.util.List;

public class RandomColorizeFunction implements Avatar.ColorizeFunction {

    private final List<Color> colors;
    private final Color whiteColor;
    private final Color blackColor;

    public RandomColorizeFunction() {
        this(AvatarUtils.defaultColors, Color.BLACK, Color.WHITE);
    }

    public RandomColorizeFunction(List<Color> colors, Color blackColor, Color whiteColor) {
        this.colors = colors;
        this.whiteColor = whiteColor;
        this.blackColor = blackColor;
    }

    @Override
    public Color colorize(AvatarInfo avatarInfo, String element) {
        Color backColor = colors.get((int) (avatarInfo.getCode() % colors.size()));
        return AvatarUtils.getComplementColor(backColor, blackColor, whiteColor);
    }

}
