package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@RestController
public class HelloWorldController {

    @GetMapping("/api/hello")
    public String test() {
        String cmd = "wmic cpu get name";
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("cmd /c " + cmd);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String l = null;
        StringBuffer sb = new StringBuffer();
        sb.append(cmd);
        String ans = null;
        String [] name = new String[1024];
        try {
            while ((l = r.readLine()) != null) {
                sb.append(l);
                sb.append("\n");
            }
            ans = sb.toString();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            name = ans.split("\n");
            System.out.println(name[2]);
        }
        return name[2];
    }
}

