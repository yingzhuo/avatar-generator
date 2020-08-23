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

import java.awt.image.BufferedImage;

public class ResizeLayer implements Layer {

    private int width;
    private int height;

    public ResizeLayer(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public BufferedImage apply(AvatarInfo avatarInfo, BufferedImage src) {
        return AvatarUtils.resizeImage(src, width, height);
    }
}
