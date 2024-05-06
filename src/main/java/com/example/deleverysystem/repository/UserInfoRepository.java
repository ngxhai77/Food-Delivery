package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {


}