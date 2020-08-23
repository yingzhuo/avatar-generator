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

public class LongShadowLayer extends AbstractShadowLayer {

    private Color shadowColor;

    public LongShadowLayer() {
        this(new Color(0, 0, 0, 64));
    }

    public LongShadowLayer(Color shadowColor) {
        super(true);
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

        double n = shadowColor.getAlpha();
        double step = n / (width + height);

        BufferedImage dest = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (isInShade(src, x, y)) {
                    int alpha = (int) (n - ((x + y) * step));
                    int[] colors = new int[]{shadowColor.getRed(), shadowColor.getGreen(), shadowColor.getBlue(), alpha};

                    dest.getRaster().setPixel(x, y, colors);
                }
            }
        }
        return dest;
    }

    private boolean isInShade(BufferedImage src, int x, int y) {
        int tx = x;
        int ty = y;
        int[] colors = new int[4];
        while (true) {
            tx -= 1;
            ty -= 1;
            if (tx < 0 || ty < 0) {
                return false;
            } else {
                src.getRaster().getPixel(tx, ty, colors);
                if (colors[3] > 0) {
                    return true;
                }
            }
        }
    }
}
