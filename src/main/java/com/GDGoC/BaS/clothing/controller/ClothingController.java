package com.GDGoC.BaS.clothing.controller;

import static org.springframework.http.HttpStatus.OK;

import com.GDGoC.BaS.clothing.dto.MyClothesDto;
import com.GDGoC.BaS.clothing.service.ClothingService;
import com.GDGoC.BaS.user.domain.User;
import com.GDGoC.BaS.user.service.UserService;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clothes")
@RequiredArgsConstructor
public class ClothingController {

    private final UserService userService;
    private final ClothingService clothingService;

    @GetMapping("/my")
    public ResponseEntity<MyClothesDto> getMyClothes(Principal principal) {
        User user = userService.getUserOrException(Long.valueOf(principal.getName()));
        return ResponseEntity
                .status(OK)
                .body(clothingService.getMyClothes(user));
    }
}
