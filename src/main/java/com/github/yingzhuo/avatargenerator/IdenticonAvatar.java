/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *      _             _                   ____                           _
 *     / \__   ____ _| |_ __ _ _ __      / ___| ___ _ __   ___ _ __ __ _| |_ ___  _ __
 *    / _ \ \ / / _` | __/ _` | '__|____| |  _ / _ \ '_ \ / _ \ '__/ _` | __/ _ \| '__|
 *   / ___ \ V / (_| | || (_| | | |_____| |_| |  __/ | | |  __/ | | (_| | || (_) | |
 *  /_/   \_\_/ \__,_|\__\__,_|_|        \____|\___|_| |_|\___|_|  \__,_|\__\___/|_|
 *
 *  https://github.com/yingzhuo/avatar-generator
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package com.github.yingzhuo.avatargenerator;

import com.github.yingzhuo.avatargenerator.element.IdenticonElementRegistry;
import com.github.yingzhuo.avatargenerator.element.identicon.NineBlockIdenticonRenderer;

public final class IdenticonAvatar {

    private IdenticonAvatar() {
    }

    public static Avatar.AvatarBuilder newAvatarBuilder() {
        return Avatar.newBuilder().elementRegistry(new IdenticonElementRegistry());
    }

    public static Avatar.AvatarBuilder newAvatarBuilder(NineBlockIdenticonRenderer nineBlockIdenticonRenderer) {
        return Avatar.newBuilder().elementRegistry(new IdenticonElementRegistry(nineBlockIdenticonRenderer));
    }
}
