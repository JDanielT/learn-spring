package br.zone.learnspring.learn.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeController {

    @GetMapping
    public ResponseEntity greating(){
        return ResponseEntity.ok(new Greating());
    }

}

@Data
class Greating {
    private String message = "Hello World";
}