/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.avatargenerator.element;

import com.github.yingzhuo.avatargenerator.AvatarInfo;
import com.github.yingzhuo.avatargenerator.element.identicon.NineBlockIdenticonRenderer;

import java.awt.image.BufferedImage;
import java.math.BigDecimal;

public class IdenticonElementRegistry extends AbstractImageElementRegistry {

    private final NineBlockIdenticonRenderer nineBlockIdenticonRenderer;

    public IdenticonElementRegistry() {
        this(new NineBlockIdenticonRenderer());
    }

    public IdenticonElementRegistry(NineBlockIdenticonRenderer nineBlockIdenticonRenderer) {
        this.nineBlockIdenticonRenderer = nineBlockIdenticonRenderer;
    }

    @Override
    protected BufferedImage buildImage(AvatarInfo avatarInfo) {
        int code = new BigDecimal(avatarInfo.getCode()).remainder(new BigDecimal(2).pow(32)).intValue();
        int size = Math.min(avatarInfo.getWidth() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2, avatarInfo.getHeight() - (avatarInfo.getMargin() + avatarInfo.getPadding()) * 2);
        return nineBlockIdenticonRenderer.render(code, size);
    }
}
