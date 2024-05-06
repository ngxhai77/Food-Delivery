package com.example.deleverysystem.service;

import com.example.deleverysystem.common.ResponseObject;
import com.example.deleverysystem.entity.ApplicationUser;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.exception.ErrorMessage;
import com.example.deleverysystem.repository.UserInfoRepository;
import com.example.deleverysystem.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

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

    public ResponseEntity create(UserInfo userInfo){
         userInfoRepository.save(userInfo);
            return ResponseEntity.ok("User created successfully");
    }

    public UserInfo save(UserInfo userInfo){
        return userInfoRepository.save(userInfo);
    }


    public UserInfo findById(Integer id){
        return userInfoRepository.findById(id).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
    }


//    @Transactional
//    public ResponseObject deleteU(HttpServletRequest request){
//        try {
//            Integer id = tokenService.getIdFromToken(request);
//            ApplicationUser applicationUser = userRepository.findById(id)
//                    .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
//            applicationUser.getUserInfo().setUserAccount(null);
//            UserInfo userInfo = applicationUser.getUserInfo();
//            userInfo.setUserAccount(null);
//            if (userRepository.existsById(id)) {
//                userRepository.delete(applicationUser);
//            return new ResponseObject(HttpStatus.OK.name(), "User deleted successfully",null);
//            } else {
//           return new ResponseObject(HttpStatus.NOT_FOUND.name(), "User Not Found",null  );
//            }
//
//       } catch (HttpClientErrorException e) {
//            return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Delete FAILED",e.getMessage());
//
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }

        @Transactional
        public ResponseEntity<String> deleteU(HttpServletRequest request){
            try {
                Integer id = tokenService.getIdFromToken(request);
                ApplicationUser applicationUser = userRepository.findById(id)
                        .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
                applicationUser.getUserInfo().setUserAccount(null);
                UserInfo userInfo = applicationUser.getUserInfo();
                userInfo.setUserAccount(null);
                if (userRepository.existsById(id)) {
                    userRepository.delete(applicationUser);
                    return ResponseEntity.noContent().build();
                } else {
                    return ResponseEntity.notFound().build();
                }
            } catch (HttpClientErrorException e) {
               // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete FAILED" + e.getMessage() );
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete FAILED" + e.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

//
//         ApplicationUser applicationUser  = userRepository.findById(id).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
//        applicationUser.getUserInfo().setUserAccount(null);
//        UserInfo userInfo = applicationUser.getUserInfo();
//        if (userRepository.existsById(id)) {
//            userRepository.delete(applicationUser);
//            return new ResponseObject(HttpStatus.OK.name(), "User deleted successfully",null);
//        } else {
//           return new ResponseObject(HttpStatus.INTERNAL_SERVER_ERROR.name(), "Delete FAILED",null  );
//        }

    }

    @Transactional
    public ResponseEntity update(HttpServletRequest request, UserInfo userInfo){
        try {
            Integer id = tokenService.getIdFromToken(request);
            ApplicationUser applicationUser = userRepository.findById(id)
                    .orElseThrow(() ->new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
            UserInfo userInfoToUpdate = applicationUser.getUserInfo();

            userInfoToUpdate.setDisplayName(userInfo.getDisplayName());
            userInfoToUpdate.setEmail(userInfo.getEmail());
            userInfoToUpdate.setPhone(userInfo.getPhone());
            userInfoToUpdate.setAddress(userInfo.getAddress());

            userInfoRepository.save(userInfoToUpdate);
            return  ResponseEntity.ok("User updated successfully");
        } catch ( Exception e) {
            // handle exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Update FAILED : " + e.getMessage());
        }
    }
    //update by id
    @Transactional
    public UserInfo update(Integer id, UserInfo userInfo){
        UserInfo userInfo1 = userInfoRepository.findById(id).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
        userInfo1.setDisplayName(userInfo.getDisplayName());
        userInfo1.setEmail(userInfo.getEmail());
        userInfo1.setPhone(userInfo.getPhone());
        userInfo1.setAddress(userInfo.getAddress());

        return userInfoRepository.save(userInfo1);
    }






}
