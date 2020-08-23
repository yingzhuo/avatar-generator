/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.avatargenerator.layers.shadows;

import com.github.yingzhuo.avatargenerator.AvatarInfo;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ScoreShadowLayer extends AbstractShadowLayer {

    private Color shadowColor;

    public ScoreShadowLayer() {
        this(new Color(0, 0, 0, 24));
    }

    public ScoreShadowLayer(Color shadowColor) {
        super(false);

        this.shadowColor = shadowColor;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }

    @Override
    protected BufferedImage buildShadow(AvatarInfo avatarInfo, BufferedImage src) {
        int width = src.getWidth();
        int height = src.getHeight();

        int[] colors = new int[]{shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), shadowColor.getAlpha()};

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height / 2; y++) {
            for (int x = 0; x < width; x++) {
                dest.getRaster().setPixel(x, y, colors);
            }
        }
        return dest;
    }

}
