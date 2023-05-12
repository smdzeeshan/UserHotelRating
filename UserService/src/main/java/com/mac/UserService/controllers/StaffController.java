package com.mac.UserService.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/staffs")
public class StaffController {
    @GetMapping("")
    public ResponseEntity<List<String>> getStaffs() {
        List<String> list = new ArrayList<>();
        list.add("Zeeshan_Officer");
        list.add("Ravi");
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
