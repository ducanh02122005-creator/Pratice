package com.example.prototype1.controller;

import com.example.prototype1.DTOS.UserDto;
import com.example.prototype1.Mapper.UserMapper;
import com.example.prototype1.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class HomePageController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

//    @GetMapping("/home")
//    public String index(){
//        return "HomePage";
//    }
//    request ko thể trả html nên khi muốn trả lại file html thì phải dùng controller
//    @controller sẽ cố trả html nên khi dùng func thì dùng @restController
    @GetMapping("/user")
    public Iterable<UserDto> getAllUser(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id){
        var user = userRepository.findById(id).orElse(null);
        if(user==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userMapper.toDto(user));
    }
}
