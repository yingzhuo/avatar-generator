/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.avatargenerator.layers.masks;

import com.github.yingzhuo.avatargenerator.AvatarInfo;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RoundRectMaskLayer extends AbstractMaskLayer {

    private final double percent;

    public RoundRectMaskLayer() {
        this(0.5);
    }

    public RoundRectMaskLayer(double percent) {
        this.percent = percent;
    }

    @Override
    protected BufferedImage buildMask(AvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        BufferedImage mask = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = mask.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        g2.setColor(Color.white);
        g2.fillRoundRect(0, 0, width, height, (int) (width * percent), (int) (height * percent));
        g2.dispose();
        return mask;
    }
}
