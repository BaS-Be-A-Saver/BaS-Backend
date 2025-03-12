package com.GDGoC.BaS.user.service;

import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.user.dto.LoginResponse;
import com.GDGoC.BaS.user.dto.UserDropDto;
import com.GDGoC.BaS.user.dto.UserInfoDto;
import com.GDGoC.BaS.user.oauth.JwtTokenProvider;
import com.GDGoC.BaS.user.oauth.UserAuthentication;
import com.GDGoC.BaS.user.repository.UserRepository;
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
}
