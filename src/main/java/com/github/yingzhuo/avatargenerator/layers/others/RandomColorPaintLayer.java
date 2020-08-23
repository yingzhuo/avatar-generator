/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
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
