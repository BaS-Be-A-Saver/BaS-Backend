package com.GDGoC.BaS.booth.service;

import com.GDGoC.BaS.booth.dto.BoothUserCreateDto;
import com.GDGoC.BaS.booth.repository.BoothRepository;
import com.GDGoC.BaS.booth.domain.BoothUser;
import com.GDGoC.BaS.booth.repository.BoothUserRepository;
import com.GDGoC.BaS.booth.domain.Booth;
import com.GDGoC.BaS.booth.dto.BoothCreateDto;
import com.GDGoC.BaS.user.domain.User;
import java.security.SecureRandom;
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
}
