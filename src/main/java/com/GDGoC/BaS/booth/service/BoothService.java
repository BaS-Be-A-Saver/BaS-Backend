package com.GDGoC.BaS.booth.service;

import com.GDGoC.BaS.booth.domain.Booth;
import com.GDGoC.BaS.booth.domain.BoothUser;
import com.GDGoC.BaS.booth.dto.BoothCreateDto;
import com.GDGoC.BaS.booth.dto.BoothDetailDto;
import com.GDGoC.BaS.booth.dto.BoothPreviewDto;
import com.GDGoC.BaS.booth.dto.BoothUserCreateDto;
import com.GDGoC.BaS.booth.dto.BoothUserDto;
import com.GDGoC.BaS.booth.dto.MyBoothsDto;
import com.GDGoC.BaS.booth.repository.BoothRepository;
import com.GDGoC.BaS.booth.repository.BoothUserRepository;
import com.GDGoC.BaS.clothing.domain.Accessory;
import com.GDGoC.BaS.clothing.domain.Head;
import com.GDGoC.BaS.clothing.domain.Towel;
import com.GDGoC.BaS.clothing.domain.UserAccessory;
import com.GDGoC.BaS.clothing.domain.UserHead;
import com.GDGoC.BaS.clothing.domain.UserTowel;
import com.GDGoC.BaS.shower.domain.UserRecord;
import com.GDGoC.BaS.user.domain.User;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class BoothService {

    private final BoothRepository boothRepository;
    private final BoothUserRepository boothUserRepository;

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Integer CODE_LENGTH = 6;

    public void createBooth(User user, BoothCreateDto request) {
        log.info("Booth name before saving: {}", request.name());
        Booth booth = saveBooth(request.name());
        joinUserToBooth(user, booth);
    }

    private Booth saveBooth(String boothName) {
        for (int i = 0; i < 10; i++) { // 10번 시도
            try {
                String code = generateUniCode();
                Booth booth = Booth.builder()
                        .name(boothName)
                        .code(code)
                        .build();
                return boothRepository.save(booth);
            } catch (DataIntegrityViolationException e) {
                log.warn("중복된 코드 발생: 새 코드 생성 후 재시도 (시도 {}번)", i + 1);
            }
        }
        throw new RuntimeException("코드 생성 실패: 너무 많은 충돌 발생");
    }

    private String generateUniCode() {
        StringBuilder code = new StringBuilder(CODE_LENGTH);
        for (int i = 0; i < CODE_LENGTH; i++) {
            code.append(CHARACTERS.charAt(RANDOM.nextInt(CHARACTERS.length())));
        }
        return code.toString();
    }

    private void joinUserToBooth(User user, Booth booth) {
        BoothUser boothUser = BoothUser.builder()
                .user(user)
                .booth(booth)
                .build();
        boothUserRepository.save(boothUser);
    }

    public void joinBooth(User user, BoothUserCreateDto request) {
        Booth booth = boothRepository.findByCode(request.code())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 부스 코드입니다."));

        if (boothUserRepository.existsByUserAndBooth(user, booth)) {
            throw new IllegalStateException("이미 참여한 부스입니다.");
        }

        BoothUser boothUser = BoothUser.builder()
                .user(user)
                .booth(booth)
                .build();
        boothUserRepository.save(boothUser);
    }

    public BoothDetailDto getBoothDetail(User u, Long boothId) {
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 부스가 존재하지 않습니다."));

        List<BoothUser> boothUsers = booth.getBoothUsers();

        LocalDate now = LocalDate.now();

        List<BoothUserDto> boothUserDtos = boothUsers
                .stream()
                .map(boothUser -> {
                    User user = boothUser.getUser();

                    List<UserRecord> records = user.getUserRecords();
                    int todayDuration = records.stream()
                            .filter(r -> r.getCreatedDate().isEqual(now))
                            .map(UserRecord::getDuration)
                            .findFirst()
                            .orElse(0);

                    long successCount = records.stream()
                            .filter(r -> {
                                LocalDate date = r.getCreatedDate();
                                return date.getYear() == now.getYear() && date.getMonth() == now.getMonth();
                            })
                            .filter(r -> r.getDuration() <= r.getGoal())
                            .count();

                    String headImage = user.getUserHeads().stream()
                            .filter(UserHead::getIsEquipped)
                            .findFirst()
                            .map(UserHead::getHead)
                            .map(Head::getImageUrl)
                            .orElse(null);

                    String towelImage = user.getUserTowels().stream()
                            .filter(UserTowel::getIsEquipped)
                            .findFirst()
                            .map(UserTowel::getTowel)
                            .map(Towel::getImageUrl)
                            .orElse(null);

                    String accessoryImage = user.getUserAccessories().stream()
                            .filter(UserAccessory::getIsEquipped)
                            .findFirst()
                            .map(UserAccessory::getAccessory)
                            .map(Accessory::getImageUrl)
                            .orElse(null);

                    return new BoothUserDto(
                            user.getNickname(),
                            user.getGoal(),
                            todayDuration,
                            successCount,
                            user.getSkin().getImage(),
                            user.getEye().getImage(),
                            user.getNose().getImage(),
                            user.getMouth().getImage(),
                            headImage,
                            towelImage,
                            accessoryImage
                    );
                })
                .toList();

        List<UserRecord> monthlyRecords = boothUsers.stream()
                .map(BoothUser::getUser)
                .flatMap(user -> user.getUserRecords().stream())
                .filter(r -> r.getCreatedDate().getYear() == now.getYear() &&
                        r.getCreatedDate().getMonth() == now.getMonth())
                .toList();

        int averageDuration = monthlyRecords.isEmpty() ?
                0 :
                (int) monthlyRecords.stream()
                        .mapToInt(UserRecord::getDuration)
                        .average()
                        .orElse(0);

        return new BoothDetailDto(
                booth.getName(),
                averageDuration,
                booth.getCode(),
                boothUserDtos
        );
    }

    public void leaveBooth(User user, Long boothId) {
        Booth booth = boothRepository.findById(boothId)
                .orElseThrow(() -> new IllegalArgumentException("해당 부스가 존재하지 않습니다."));

        BoothUser boothUser = boothUserRepository.findByUserAndBooth(user, booth)
                .orElseThrow(() -> new IllegalArgumentException("해당 부스에 참여하지 않았습니다."));

        boothUserRepository.delete(boothUser);

        long remainingCount = boothUserRepository.countByBooth(booth);

        if (remainingCount == 0) {
            boothRepository.delete(booth);
        }
    }

    public MyBoothsDto getMyBooths(User user) {
        LocalDate today = LocalDate.now();
        List<Booth> booths = boothRepository.findBoothsByUser(user);

        List<BoothPreviewDto> boothPreviews = booths.stream()
                .map(booth -> BoothPreviewDto.from(booth, today))
                .toList();

        return new MyBoothsDto(boothPreviews);
    }
}
