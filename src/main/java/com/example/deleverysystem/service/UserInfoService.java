package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.exception.ItemNotFoundException;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;


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


    @Transactional
    public void deleteById(Integer id){
         ApplicationUser applicationUser  = userRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
        applicationUser.getUserInfo().setUserAccount(null);
        UserInfo userInfo = applicationUser.getUserInfo();
        if (userRepository.existsById(id)) {
            userRepository.delete(applicationUser);
        } else {
            throw new EntityNotFoundException("User with id " + id + " does not exist.");
        }

    }

    @Transactional
    public UserInfo update(HttpServletRequest request, UserInfo userInfo){
        try {
            Integer id = tokenService.getIdFromToken(request);
            ApplicationUser applicationUser = userRepository.findById(id)
                    .orElseThrow(() -> new ItemNotFoundException(id));
            UserInfo userInfoToUpdate = applicationUser.getUserInfo();

            userInfoToUpdate.setDisplayName(userInfo.getDisplayName());
            userInfoToUpdate.setEmail(userInfo.getEmail());
            userInfoToUpdate.setPhone(userInfo.getPhone());
            userInfoToUpdate.setAddress(userInfo.getAddress());

            return userInfoRepository.save(userInfoToUpdate);
        } catch (Exception e) {
            // handle exception
            return null;
        }
    }
    //update by id
    @Transactional
    public UserInfo update(Integer id, UserInfo userInfo){
        UserInfo userInfo1 = userInfoRepository.findById(id).orElseThrow(()->new ItemNotFoundException(id));
        userInfo1.setDisplayName(userInfo.getDisplayName());
        userInfo1.setEmail(userInfo.getEmail());
        userInfo1.setPhone(userInfo.getPhone());
        userInfo1.setAddress(userInfo.getAddress());

        return userInfoRepository.save(userInfo1);
    }



}
