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
import com.github.yingzhuo.avatargenerator.layers.Layer;
import com.github.yingzhuo.avatargenerator.utils.AvatarUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class AbstractShadowLayer implements Layer {

    private boolean first;

    protected AbstractShadowLayer(boolean first) {
        super();
        this.first = first;
    }

    @Override
    public BufferedImage apply(AvatarInfo avatarInfo, BufferedImage src) {
        BufferedImage dest = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = dest.createGraphics();
        AvatarUtils.activeAntialiasing(g2);
        if (first) {
            g2.drawImage(buildShadow(avatarInfo, src), 0, 0, null);
        }
        g2.drawImage(src, 0, 0, null);
        if (!first) {
            g2.drawImage(buildShadow(avatarInfo, src), 0, 0, null);
        }
        g2.dispose();
        return dest;
    }

    protected abstract BufferedImage buildShadow(AvatarInfo avatarInfo, BufferedImage src);

}
