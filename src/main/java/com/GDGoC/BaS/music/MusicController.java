package com.GDGoC.BaS.music;

import static org.springframework.http.HttpStatus.OK;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/musics")
@RequiredArgsConstructor
public class MusicController {

    private final GeminiService geminiService;

    @GetMapping("/recommend")
    public ResponseEntity<MusicDto> recommendMusic(@RequestParam String mood, @RequestParam String genre) {
        String raw = geminiService.getRecommendedSong(mood, genre);

        String[] parts = raw.split(" - ");
        String title = parts.length > 0 ? parts[0].trim() : "Unknown";
        String artist = parts.length > 1 ? parts[1].trim() : "Unknown";

        return ResponseEntity
                .status(OK)
                .body(new MusicDto(title, artist));
    }
}
