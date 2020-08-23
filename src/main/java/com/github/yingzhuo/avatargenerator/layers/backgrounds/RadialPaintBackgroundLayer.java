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

import java.awt.*;
import java.awt.image.BufferedImage;

public class RadialPaintBackgroundLayer extends AbstractPaintBackgroundLayer {

    private final Color inColor;
    private final Color outColor;

    public RadialPaintBackgroundLayer() {
        this(new Color(0xE2A6FF), new Color(0xC58BFF));
    }

    public RadialPaintBackgroundLayer(Color inColor, Color outColor) {
        this.inColor = inColor;
        this.outColor = outColor;
    }

    @Override
    protected Paint buildPaint(AvatarInfo avatarInfo, BufferedImage bufferedImage) {
        int width = bufferedImage.getWidth();
        int height = bufferedImage.getHeight();
        return new RadialGradientPaint(width / 2, height / 2, (int) Math.sqrt(width * width + height * height) / 2, new float[]{0.0f, 0.75f}, new Color[]{inColor, outColor});
    }
}
