package com.example.learncode5.controller;

import com.example.learncode5.DTO.UserDTO;
import com.example.learncode5.payload.Response.ResponseObject;
import com.example.learncode5.repository.UserRepository;
import com.example.learncode5.service.UserService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")

public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @GetMapping
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> getUserList(){
        return userService.getUserList();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable long id){
        return userService.deleteUserById(id);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasAuthority('USER') || hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> getUserByUsername(@PathVariable String username){
        return userService.getUserByUsername(username);
    }

    @PostMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<ResponseObject> updateUser(@PathVariable long id, @RequestBody UserDTO userDTO){
        return userService.updateUser(id,userDTO.getFullName(),userDTO.getEmail(),userDTO.getRoles());
    }

//    @GetMapping("item-report/{format}")
//    public ResponseEntity<Resource> getItemReport(@PathVariable String format) throws JRException, IOException {
//
//        byte[] reportContent = UserService.getUserReport(userRepository.findAll(), format);
//
//        ByteArrayResource resource = new ByteArrayResource(reportContent);
//
//        return ResponseEntity.ok()
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .contentLength(resource.contentLength())
//                .header(HttpHeaders.CONTENT_DISPOSITION,
//                        ContentDisposition.attachment()
//                                .filename("item-report." + format)
//                                .build().toString())
//                .body((Resource) resource);
//    }
}
