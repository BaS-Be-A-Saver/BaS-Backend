package com.GDGoC.BaS.waterdrop;

import static com.GDGoC.BaS.waterdrop.Reason.GOAL;
import static com.GDGoC.BaS.waterdrop.Reason.PRESENT;

import com.GDGoC.BaS.shower.UserRecord;
import com.GDGoC.BaS.shower.UserRecordRepository;
import com.GDGoC.BaS.user.User;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WaterdropService {

    private final WaterdropHistoryRepository waterdropHistoryRepository;
    private final UserRecordRepository userRecordRepository;

    public void collectWaterdrop(User user, WaterdropCollectDto request) {
        validateRequest(user, request);

        user.addWaterdrop(request.amount());

        WaterdropHistory waterdropHistory = WaterdropHistory.builder()
                .amount(request.amount())
                .reason(request.reason())
                .user(user)
                .build();
        waterdropHistoryRepository.save(waterdropHistory);
    }

    private void validateRequest(User user, WaterdropCollectDto request) {
        LocalDate today = LocalDate.now();

        if (request.reason().equals(PRESENT)) {
            if (waterdropHistoryRepository.existsByUserAndReasonAndCreatedDate(user, PRESENT, today)) {
                throw new IllegalStateException("오늘 이미 출석으로 물방울을 획득했습니다.");
            }
        }

        if (request.reason().equals(GOAL)) {
            if (waterdropHistoryRepository.existsByUserAndReasonAndCreatedDate(user, GOAL, today)) {
                throw new IllegalStateException("오늘 이미 목표 달성으로 물방울을 획득했습니다.");
            }

            UserRecord userRecord = userRecordRepository.findByUserAndCreatedDate(user, today)
                    .orElseThrow(() -> new IllegalStateException("오늘 샤워 기록이 없습니다."));
            if (userRecord.getDuration() > userRecord.getGoal()) {
                throw new IllegalStateException("목표 시간을 초과하여 GOAL 보상을 받을 수 없습니다.");
            }
        }
    }
}
