package com.example.deleverysystem.repository;


import com.example.deleverysystem.entity.BlacklistedToken;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface TokenBlacklistRepository extends JpaRepository<BlacklistedToken, Integer>{
    boolean existsByToken(String token);

    @Modifying
    @Query("delete from BlacklistedToken b where b.expirationTime < :expirationTime")
    void deleteByExpirationTimeBefore(@Param("expirationTime") Instant expirationTime);
}
