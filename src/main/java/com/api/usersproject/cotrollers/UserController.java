package com.api.usersproject.cotrollers;

import com.api.usersproject.dtos.UserDTO;
import com.api.usersproject.models.UserModel;
import com.api.usersproject.services.UserServices;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    final UserServices userServices;

    public UserController(UserServices userServices){
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO){ // @RequestBody: Serve para receber JSON do body / @Valid: Realiza as validações do DTO
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.create(userModel));
    }


}
