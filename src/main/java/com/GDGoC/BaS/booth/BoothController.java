package com.GDGoC.BaS.booth;

import static org.springframework.http.HttpStatus.CREATED;

import com.GDGoC.BaS.user.User;
import com.GDGoC.BaS.user.UserService;
import jakarta.validation.Valid;
import java.security.Principal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booths")
@RequiredArgsConstructor
public class BoothController {

    private final UserService userService;
    private final BoothService boothService;

    @PostMapping
    public ResponseEntity<Void> createBooth(Principal principal,
                                            @Valid @RequestBody BoothCreateDto request) {
        User user = userService.getUserOrException(Long.valueOf(principal.getName()));
        boothService.createBooth(user, request);

        return ResponseEntity
                .status(CREATED)
                .build();
    }
}
