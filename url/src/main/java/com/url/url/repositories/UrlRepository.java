package com.url.url.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.url.entities.UrlEntity;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {  
    Optional<UrlEntity> findByShortUrl(String shortUrl);
}





