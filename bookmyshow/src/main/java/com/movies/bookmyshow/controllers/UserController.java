package com.movies.bookmyshow.controllers;

import com.movies.bookmyshow.dtos.ResponseStatus;
import com.movies.bookmyshow.dtos.SignUpRequestDto;
import com.movies.bookmyshow.dtos.SignUpResponseDto;
import com.movies.bookmyshow.models.User;
import com.movies.bookmyshow.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public SignUpResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        SignUpResponseDto signUpResponseDto = new SignUpResponseDto();
        User user;
        try{
            user=userService.signUp(signUpRequestDto.getEmail(),
                    signUpRequestDto.getPassword());
            signUpResponseDto.setStatus(ResponseStatus.SUCCESS);
            signUpResponseDto.setUserId(user.getId());
        } catch (Exception e) {
            signUpResponseDto.setStatus(ResponseStatus.FAILURE);
        }
        return signUpResponseDto;
    }
}
