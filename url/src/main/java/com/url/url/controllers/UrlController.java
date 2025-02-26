package com.url.url.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.url.services.UrlService;

@RestController
@RequestMapping("/api")
public class UrlController {

    @Autowired
    private UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> shortenUrl(@RequestBody Map<String, String> request) {
        String originalUrl = request.get("originalUrl");
        if (originalUrl == null || originalUrl.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "URL cannot be empty"));
        }

        String shortUrl = urlService.shortenUrl(originalUrl);
        Map<String, String> response = new HashMap<>();
        response.put("shortenedUrl",  shortUrl);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getOriginalUrl(@PathVariable String shortUrl) {
        System.out.println("Fetching original URL for: " + shortUrl); 

        Optional<String> originalUrl = urlService.getOriginalUrl(shortUrl);

        if (originalUrl.isPresent()) {
            System.out.println("Found URL: " + originalUrl.get());  
            return ResponseEntity.ok(originalUrl.get());
        } else {
            System.out.println("Short URL not found!");  
            return ResponseEntity.notFound().build();
        }
    }
}



