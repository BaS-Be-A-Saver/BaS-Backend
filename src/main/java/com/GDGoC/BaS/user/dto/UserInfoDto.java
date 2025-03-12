package com.GDGoC.BaS.user.dto;

import com.GDGoC.BaS.user.domain.User;

public record UserInfoDto(
        String nickname,
        String email,
        String skinImage,
        String eyesImage,
        String noseImage,
        String mouthImage
) {
    static public UserInfoDto of(User user) {
        return new UserInfoDto(
                user.getNickname(),
                user.getEmail(),
                user.getSkin().getImage(),
                user.getEye().getImage(),
                user.getNose().getImage(),
                user.getMouth().getImage()
        );
    }
}
