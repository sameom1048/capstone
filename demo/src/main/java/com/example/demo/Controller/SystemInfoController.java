package com.example.demo.Controller;

import com.example.demo.Model.GPU;
import com.example.demo.Sevice.GPUService;
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

// CPU, GPU, RAM 정보 컨트롤러
@CrossOrigin
@RestController
public class SystemInfoController {

    @Autowired //Bean으로 등록된 클래스들을 스프링을 시작할 때 (서버를 켤 때)자동으로 주입
    private GPUService gpuService;

    @GetMapping("/gpu/data")
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

    @GetMapping("/api/gpulist")
    public ResponseEntity<List<GPU>> findAll() {
        return new ResponseEntity<>(gpuService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/api/gpu/{id}")
    public ResponseEntity<GPU> findById(@PathVariable Long id) {
        Optional<GPU> optionalUser = gpuService.findById(id);
        if (optionalUser.isPresent()) {
            return new ResponseEntity<>(optionalUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/api/gpu")
    public ResponseEntity<GPU> save(@RequestBody GPU gpu) {
        System.out.println("successfully response!");
        return new ResponseEntity<>(gpuService.save(gpu), HttpStatus.CREATED);
    }
    @DeleteMapping("/api/gpu/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        gpuService.deleteById(id);
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

