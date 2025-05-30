package com.GDGoC.BaS.user.oauth;

import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;
        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleOAuth2Response(oAuth2User.getAttributes());
        }

        String socialId = oAuth2Response.getProvider() + "_" + oAuth2Response.getProviderId();
        String defaultNickname =
                oAuth2Response.getProvider().charAt(0) + "*" + oAuth2Response.getProviderId().substring(2, 10);
        User existedUserOrNull = userRepository.findBySocialId(socialId);
        if (existedUserOrNull == null) {
            userRepository.save(User.createByGoogleOAuth(oAuth2Response.getEmail(), socialId, defaultNickname));
            OAuth2UserDTO oAuth2UserDTO = OAuth2UserDTO.of(
                    defaultNickname, oAuth2Response.getEmail(), socialId);
            return new CustomOAuth2User(oAuth2UserDTO);
        } else {
            OAuth2UserDTO oAuth2UserDTO = OAuth2UserDTO.of(
                    existedUserOrNull.getNickname(), existedUserOrNull.getEmail(), existedUserOrNull.getSocialId());
            return new CustomOAuth2User(oAuth2UserDTO);
        }
    }
}
