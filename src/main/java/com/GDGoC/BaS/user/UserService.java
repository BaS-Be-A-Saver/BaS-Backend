package com.GDGoC.BaS.user;

import com.GDGoC.BaS.user.oauth.JwtTokenProvider;
import com.GDGoC.BaS.user.oauth.UserAuthentication;
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
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("no user found with that userId"));
    }

    @Transactional(readOnly = true)
    public LoginResponse login(Long userId) {
        User user = getUserOrException(userId);

        UserAuthentication userAuthentication = new UserAuthentication(user.getUserId(), null, null);
        String token = jwtTokenProvider.generateToken(userAuthentication);

        return LoginResponse.of(token);
    }
}
