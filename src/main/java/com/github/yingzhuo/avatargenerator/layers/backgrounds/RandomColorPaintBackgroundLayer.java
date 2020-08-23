/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.avatargenerator.layers.backgrounds;

import com.github.yingzhuo.avatargenerator.AvatarInfo;
import com.github.yingzhuo.avatargenerator.layers.Layer;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class RandomColorPaintBackgroundLayer implements Layer {

    private List<Color> colors;

    public RandomColorPaintBackgroundLayer() {
        this(AvatarUtils.defaultColors);
    }

    public RandomColorPaintBackgroundLayer(List<Color> colors) {
        super();

        this.colors = colors;
    }

    @Override
    public BufferedImage apply(AvatarInfo avatarInfo, BufferedImage src) {
        Color backColor = colors.get((int) (avatarInfo.getCode() % colors.size()));

        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.setPaint(backColor);
        g2.fillRect(0, 0, width, height);
        g2.drawImage(src, 0, 0, null);
        g2.dispose();
        return dest;
    }
}
