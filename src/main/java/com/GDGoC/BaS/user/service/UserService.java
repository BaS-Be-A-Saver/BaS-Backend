package com.GDGoC.BaS.user.service;

import com.GDGoC.BaS.clothing.domain.Accessory;
import com.GDGoC.BaS.clothing.domain.Head;
import com.GDGoC.BaS.clothing.domain.Towel;
import com.GDGoC.BaS.clothing.domain.UserAccessory;
import com.GDGoC.BaS.clothing.domain.UserHead;
import com.GDGoC.BaS.clothing.repository.UserAccessoryRepository;
import com.GDGoC.BaS.clothing.repository.UserHeadRepository;
import com.GDGoC.BaS.clothing.domain.UserTowel;
import com.GDGoC.BaS.clothing.repository.UserTowelRepository;
import com.GDGoC.BaS.shower.domain.UserRecord;
import com.GDGoC.BaS.shower.repository.UserRecordRepository;
import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.user.dto.LoginResponse;
import com.GDGoC.BaS.user.dto.UserDropDto;
import com.GDGoC.BaS.user.dto.UserInfoDto;
import com.GDGoC.BaS.user.dto.UserStatusDto;
import com.GDGoC.BaS.user.oauth.JwtTokenProvider;
import com.GDGoC.BaS.user.oauth.UserAuthentication;
import com.GDGoC.BaS.user.repository.UserRepository;
import java.time.LocalDate;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRecordRepository userRecordRepository;
    private final UserHeadRepository userHeadRepository;
    private final UserTowelRepository userTowelRepository;
    private final UserAccessoryRepository userAccessoryRepository;

    @Transactional(readOnly = true)
    public User getUserOrException(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with id " + userId + " not found"));
    }

    @Transactional(readOnly = true)
    public LoginResponse login(Long userId) {
        User user = getUserOrException(userId);

        UserAuthentication userAuthentication = new UserAuthentication(user.getUserId(), null, null);
        String token = jwtTokenProvider.generateToken(userAuthentication);

        return LoginResponse.of(token);
    }

    @Transactional(readOnly = true)
    public UserDropDto getUserDrop(User user) {
        return UserDropDto.of(user.getWaterdrop());
    }

    @Transactional(readOnly = true)
    public UserInfoDto getUserInfo(User user) {
        return UserInfoDto.of(user);
    }

    @Transactional(readOnly = true)
    public UserStatusDto getUserStatus(User user) {
        UserRecord userRecord = userRecordRepository.findByUserAndCreatedDate(user, LocalDate.now()).orElse(null);

        UserHead userHead = userHeadRepository.findByUserAndIsEquippedTrue(user).orElse(null);
        Head head = (userHead == null) ? null : userHead.getHead();

        UserTowel userTowel = userTowelRepository.findByUserAndIsEquippedTrue(user).orElse(null);
        Towel towel = (userTowel == null) ? null : userTowel.getTowel();

        UserAccessory userAccessory = userAccessoryRepository.findByUserAndIsEquippedTrue(user).orElse(null);
        Accessory accessory = (userAccessory == null) ? null : userAccessory.getAccessory();

        return UserStatusDto.of(user, userRecord, head, towel, accessory);
    }
}
