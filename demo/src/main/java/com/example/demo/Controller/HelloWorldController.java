package com.example.demo.Controller;

import com.example.demo.Model.UserInfo;
import com.example.demo.Sevice.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//기본 홈 컨트롤러
@CrossOrigin
@RestController
public class HelloWorldController {

    @Autowired //Bean으로 등록된 클래스들을 스프링을 시작할 때 (서버를 켤 때)자동으로 주입
    private UserInfoService userInfoService;

    @GetMapping("/api/data")
    public Map<String, String> getCPUInfo() {
        OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();

        Map<String, String> data = new HashMap<>();
        data.put("Arch", osBean.getArch());
        System.out.println(osBean.getArch());
        data.put("AvailableProcessors", String.valueOf(osBean.getAvailableProcessors()));
        data.put("Name", osBean.getName());
        System.out.println(osBean.getName());
        data.put("Version", osBean.getVersion());

        return data;
    }


    @GetMapping("/api/userlist")
    public ResponseEntity<List<UserInfo>> findAll() {
        return new ResponseEntity<>(userInfoService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/api/insert/{id}")
    public ResponseEntity<UserInfo> findById(@PathVariable Long id) {
        Optional<UserInfo> optionalUser = userInfoService.findById(id);
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/insert")
    public ResponseEntity<UserInfo> save(@RequestBody UserInfo userInfo) {
        System.out.println("successfully response!");
        return new ResponseEntity<>(userInfoService.save(userInfo), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/insert/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userInfoService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
//        if (userService.existsByEmail(signupRequest.getEmail())) {
//            return new ResponseEntity<>(new ApiResponse(false, "Email address already in use!"), HttpStatus.BAD_REQUEST);
//        }
//
//        User user = new User(signupRequest.getEmail(), signupRequest.getPassword());
//
//        userService.save(user);
//
//        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully"));
//    }
}

