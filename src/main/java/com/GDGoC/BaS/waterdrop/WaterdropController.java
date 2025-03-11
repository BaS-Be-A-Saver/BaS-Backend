package com.GDGoC.BaS.waterdrop;

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
@RequestMapping("/waterdrops")
@RequiredArgsConstructor
public class WaterdropController {

    private final UserService userService;
    private final WaterdropService waterdropService;

    @PostMapping("/collect")
    public ResponseEntity<Void> collectWaterdrop(Principal principal,
                                                 @Valid @RequestBody WaterdropCollectDto request) {
        User user = userService.getUserOrException(Long.valueOf(principal.getName()));
        waterdropService.collectWaterdrop(user, request);
        return ResponseEntity
                .status(CREATED)
                .build();
    }
}
