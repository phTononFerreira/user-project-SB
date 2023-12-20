package com.api.usersproject.cotrollers;

import com.api.usersproject.dtos.UserDTO;
import com.api.usersproject.models.UserModel;
import com.api.usersproject.services.UserServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(originPatterns = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {
    final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser(@RequestBody @Valid UserDTO userDTO) { // @RequestBody: Serve para receber JSON do body / @Valid: Realiza as validações do DTO
        var userModel = new UserModel();
        BeanUtils.copyProperties(userDTO, userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userServices.save(userModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "id") UUID id) {
        Optional<UserModel> userOptional = userServices.findByID(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userOptional.get());
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userServices.findAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id){
        Optional<UserModel> userOptional = userServices.findByID(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }
        userServices.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> editUser(@PathVariable(value = "id") UUID id, @RequestBody @Valid UserDTO userDTO) {
        Optional<UserModel> userOptional = userServices.findByID(id);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado!");
        }

        var userModel = userOptional.get();
        userModel.setName(userDTO.getName());
        userModel.setPassword(userDTO.getPassword());
        userModel.setAddress(userDTO.getAddress());
        userModel.setContact(userDTO.getContact());
        userModel.setEmail(userDTO.getEmail());

        return ResponseEntity.status(HttpStatus.OK).body(userServices.save(userModel));
    }


}
