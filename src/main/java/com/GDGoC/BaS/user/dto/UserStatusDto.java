package com.GDGoC.BaS.user.dto;

import com.GDGoC.BaS.clothing.domain.Accessory;
import com.GDGoC.BaS.clothing.domain.Head;
import com.GDGoC.BaS.clothing.domain.Towel;
import com.GDGoC.BaS.shower.domain.UserRecord;
import com.GDGoC.BaS.user.domain.User;

public record UserStatusDto(
        Integer goal,
        Integer duration,
        String skinImage,
        String eyesImage,
        String noseImage,
        String mouthImage,
        String headImage,
        String towelImage,
        String accessoryImage
) {
    public static UserStatusDto of(User user, UserRecord userRecord, Head head, Towel towel, Accessory accessory) {
        boolean alreadyShowered = userRecord != null;
        return new UserStatusDto(
                alreadyShowered ? userRecord.getGoal() : user.getGoal(),
                alreadyShowered ? userRecord.getDuration() : null,
                user.getSkin().getImage(),
                user.getEye().getImage(),
                user.getNose().getImage(),
                user.getMouth().getImage(),
                head == null ? null : head.getImageUrl(),
                towel == null ? null : towel.getImageUrl(),
                accessory == null ? null : accessory.getImageUrl()
        );
    }
}
