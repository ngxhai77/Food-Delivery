package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.exception.ItemNotFoundException;
import com.example.deleverysystem.repository.UserInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;


    public UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UserInfo> findAll(){
        return userInfoRepository.findAll();
    }

    public UserInfo create(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }

    public UserInfo save(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }


    public UserInfo findById(Integer id){
        return userInfoRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
    }

    public void deleteById(Integer id){
        userInfoRepository.deleteById(id);
    }

    public UserInfo update(Integer id, UserInfo userInfo){
        UserInfo userInfo1 = userInfoRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
        userInfo1.setDisplayName(userInfo.getDisplayName());
        userInfo1.setEmail(userInfo.getEmail());
        userInfo1.setPhone(userInfo.getPhone());
        userInfo1.setAddress(userInfo.getAddress());
        return userInfoRepository.save(userInfo1);
    }




}
