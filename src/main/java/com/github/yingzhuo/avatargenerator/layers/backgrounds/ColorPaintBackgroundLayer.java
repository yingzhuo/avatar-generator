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

public class ColorPaintBackgroundLayer extends AbstractPaintBackgroundLayer {

    private Color color;

    public ColorPaintBackgroundLayer() {
        this(new Color(0xE2A6FF));
    }

    public ColorPaintBackgroundLayer(Color color) {
        super();

        this.color = color;
    }

    @Override
    protected Paint buildPaint(AvatarInfo avatarInfo, BufferedImage bufferedImage) {
        return color;
    }
}
