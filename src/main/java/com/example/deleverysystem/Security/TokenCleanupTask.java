package com.example.deleverysystem.Security;


import com.example.deleverysystem.repository.TokenBlacklistRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
public class TokenCleanupTask {


    private final TokenBlacklistRepository tokenBlacklistRepository;

    public TokenCleanupTask(TokenBlacklistRepository tokenBlacklistRepository) {
        this.tokenBlacklistRepository = tokenBlacklistRepository;
    }

    @Scheduled(fixedRate = 86400000) // Run once per day
    public void cleanupTokens() {
        tokenBlacklistRepository.deleteByExpirationTimeBefore(Instant.now());
    }
}
