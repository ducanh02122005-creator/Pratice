package com.example.prototype1.controller;

import com.example.prototype1.DTOS.UserDto;
import com.example.prototype1.Mapper.UserMapper;
import com.example.prototype1.Repository.UserRepository;
import com.example.prototype1.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/home")
public class PageController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //    @GetMapping("/home")
//    public String index(){
//        return "HomePage";
//    }
//    request ko thể trả html nên khi muốn trả lại file html thì phải dùng controller
//    @controller sẽ cố trả html nên khi dùng func thì dùng @restController


    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public Iterable<UserDto> getAllUser(
            @RequestParam(defaultValue = "name") String sort){

        return userService.getAllUser(sort);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> findUserById(@PathVariable int id){
        return userService.getUser(id);
    }
}

