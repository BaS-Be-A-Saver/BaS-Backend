package com.GDGoC.BaS.shower.service;

import com.GDGoC.BaS.shower.domain.UserRecord;
import com.GDGoC.BaS.shower.dto.UserRecordCreateDto;
import com.GDGoC.BaS.shower.repository.UserRecordRepository;
import com.GDGoC.BaS.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ShowerService {

    private final UserRecordRepository userRecordRepository;

    public void shower(User user, UserRecordCreateDto request) {
        UserRecord userRecord = saveUserRecord(user, request);
        updateBoothRecord(user, userRecord);
    }

    private UserRecord saveUserRecord(User user, UserRecordCreateDto request) {
        UserRecord userRecord = UserRecord.builder()
                .duration(request.duration())
                .goal(user.getGoal())
                .user(user)
                .build();
        return userRecordRepository.save(userRecord);
    }

    private void updateBoothRecord(User user, UserRecord userRecord) {
        // User의 BoothUser 다 찾아서, User가 속한 Booth 다 찾기
        // 부스마다 속한 유저 찾고, 모든 유저의 당일 record 찾기
        // 부스마다 totalCount 높이기(booth.updateTotalCount())
        // 부스마다 successCount 높이기(booth.updateSuccessCount())
        // 부스마다 averageRecord 수정
    }
}
