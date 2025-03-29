package com.GDGoC.BaS.shower.controller;

import static org.springframework.http.HttpStatus.CREATED;

import com.GDGoC.BaS.shower.dto.UserRecordCreateDto;
import com.GDGoC.BaS.shower.service.ShowerService;
import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.user.service.UserService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shower")
@RequiredArgsConstructor
public class ShowerController {

    private final UserService userService;
    private final ShowerService showerService;

    @PostMapping
    public ResponseEntity<Void> shower(Principal principal,
                                       @RequestBody @Valid UserRecordCreateDto request) {
        User user = userService.getUserOrException(Long.valueOf(principal.getName()));
        showerService.shower(user, request);

        return ResponseEntity
                .status(CREATED)
                .build();
    }
}
