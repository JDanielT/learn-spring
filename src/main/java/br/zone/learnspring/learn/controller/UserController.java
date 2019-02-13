package br.zone.learnspring.learn.controller;

import br.zone.learnspring.learn.model.User;
import br.zone.learnspring.learn.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity list(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @PostMapping("/register")
    public ResponseEntity save(@RequestBody @Valid User user, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().body(getFieldsErrors(errors));
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    private Map<Object, String> getFieldsErrors(Errors errors){
        return errors.getFieldErrors()
                .stream()
                .collect(Collectors.toMap( erro -> erro.getField(), erro -> erro.getDefaultMessage()));
    }

}
