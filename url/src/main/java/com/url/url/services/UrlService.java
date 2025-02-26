package com.url.url.services;


import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.url.url.entities.UrlEntity;
import com.url.url.repositories.UrlRepository;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    public String shortenUrl(String originalUrl) {
        String shortUrl = UUID.randomUUID().toString().substring(0, 8);
        UrlEntity urlEntity = new UrlEntity(originalUrl, shortUrl);
        urlRepository.save(urlEntity);
        return shortUrl;
    }

    public Optional<String> getOriginalUrl(String shortUrl) {
        System.out.println("Searching for shortUrl: " + shortUrl);
        
        Optional<UrlEntity> urlEntity = urlRepository.findByShortUrl(shortUrl);

        if (urlEntity.isPresent()) {
            System.out.println("Original URL found: " + urlEntity.get().getOriginalUrl());
            return Optional.of(urlEntity.get().getOriginalUrl());
        } else {
            System.out.println("Short URL not found in DB.");
            return Optional.empty();
        }
    }
}


