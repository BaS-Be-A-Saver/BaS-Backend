package com.GDGoC.BaS.music;

import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Transactional
public class GeminiService {

    @Value("${gemini.api-key}")
    private String apiKey;

    public String getRecommendedSong(String mood, String genre) {
        RestTemplate restTemplate = new RestTemplate();

        String prompt = String.format(
                "분위기는 '%s', 장르는 '%s'인 노래를 하나만 추천해줘. 곡 제목 - 아티스트 형식으로 알려줘. 예를들어 '사랑 - 최유리' 처럼. 다른 말은 아무것도 붙이지 말고 저렇게만 대답해줘. 매번 다른 곡으로 추천해주고, 곡이름이랑 가수 모두 정확히 알려줘.",
                mood, genre
        );

        Map<String, Object> message = Map.of("parts", List.of(Map.of("text", prompt)));
        Map<String, Object> requestBody = Map.of("contents", List.of(message));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(getApiUrl(), HttpMethod.POST, request, Map.class);

        if (response.getStatusCode() == OK) {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            return (String) parts.get(0).get("text");
        }

        return "추천 결과를 가져올 수 없습니다.";
    }

    private String getApiUrl() {
        return "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + apiKey;
    }
}
