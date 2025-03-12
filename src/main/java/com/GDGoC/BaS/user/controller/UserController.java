package com.GDGoC.BaS.user.controller;

import static org.springframework.http.HttpStatus.OK;

import com.GDGoC.BaS.user.dto.LoginResponse;
import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.user.dto.UserDropDto;
import com.GDGoC.BaS.user.dto.UserInfoDto;
import com.GDGoC.BaS.user.service.UserService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@Validated
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody String userId) {
        return ResponseEntity
                .status(OK)
                .body(userService.login(Long.valueOf(userId)));
    }

    @GetMapping("/waterdrop")
    public ResponseEntity<UserDropDto> getUserDrop(Principal principal) {
        User user = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userService.getUserDrop(user));
    }

    @GetMapping("/my")
    public ResponseEntity<UserInfoDto> getUserInfo(Principal principal) {
        User user = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(userService.getUserInfo(user));
    }
}
