package com.choongang.frombirth.controller;


import com.choongang.frombirth.command.UserVO;
import com.choongang.frombirth.common.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class HomeRestController {

    @Autowired
    private HomeService homeService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public HomeRestController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/registUser")
    public ResponseEntity<String> insertEmployee(@ModelAttribute UserVO userVO) {
        System.out.println(userVO);
        try {
            String encodedPassword = bCryptPasswordEncoder.encode(userVO.getPassword());
            userVO.setPassword(encodedPassword); // 암호화된 비밀번호 설정

            int isRegistered = homeService.registerUser(userVO);

            if (isRegistered==0) {
                return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("User registration failed", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Data processing failed.");
        }
    }
}
