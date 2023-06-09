package com.example.demo.Controller;

import ch.qos.logback.core.util.SystemInfo;
import com.example.demo.Model.User;
import com.example.demo.Sevice.UserService;
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

@CrossOrigin
@RestController
public class HelloWorldController {

    @Autowired //Bean으로 등록된 클래스들을 스프링을 시작할 때 (서버를 켤 때)자동으로 주입
    private UserService userService;

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

//    @GetMapping("/cpu")
//    public String getCpuInfo() {
//        OperatingSystemMXBean osBean = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
//        String cpuName = osBean.getProcessor().getProcessorIdentifier().getName();
//        int cpuCount = osBean.getAvailableProcessors();
//        return "CPU Name: " + cpuName + ", CPU Count: " + cpuCount;
//    }

    @GetMapping("/api/userlist")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/api/signup/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/signup")
    public ResponseEntity<User> save(@RequestBody User user) {
        System.out.println("successfully response!");
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/signup/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        userService.deleteById(id);
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

