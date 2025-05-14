package com.GDGoC.BaS.booth.dto;

import com.GDGoC.BaS.booth.domain.Booth;
import com.GDGoC.BaS.booth.domain.BoothUser;
import java.time.LocalDate;
import java.util.List;

public record BoothPreviewDto(
        Long boothId,
        String name,
        boolean successful
) {
    public static BoothPreviewDto from(Booth booth, LocalDate today) {
        List<BoothUser> boothUsers = booth.getBoothUsers();

        long totalUsers = boothUsers.size();
        long successUsersToday = boothUsers.stream()
                .map(BoothUser::getUser)
                .map(user -> user.getUserRecords().stream()
                        .filter(r -> r.getCreatedDate().isEqual(today))
                        .findFirst()
                        .map(r -> r.getDuration() <= r.getGoal())
                        .orElse(false))
                .filter(success -> success)
                .count();

        boolean successful = totalUsers > 0 && (double) successUsersToday / totalUsers >= 0.5;

        return new BoothPreviewDto(
                booth.getBoothId(),
                booth.getName(),
                successful
        );
    }
}
