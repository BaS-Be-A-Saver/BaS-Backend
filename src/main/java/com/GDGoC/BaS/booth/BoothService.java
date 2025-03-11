package com.GDGoC.BaS.booth;

import com.GDGoC.BaS.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoothService {

    private final BoothRepository boothRepository;
    private final BoothUserRepository boothUserRepository;

    public void createBooth(User user, BoothCreateDto request) {
        String code = generateUniCode();

        Booth booth = Booth.builder()
                .name(request.name())
                .code(code)
                .build();
        boothRepository.save(booth);

        BoothUser boothUser = BoothUser.builder()
                .user(user)
                .booth(booth)
                .build();
        boothUserRepository.save(boothUser);
    }
}
