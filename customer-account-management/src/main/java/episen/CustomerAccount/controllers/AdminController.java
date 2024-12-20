package episen.CustomerAccount.controllers;


import back.models.CustomerAccountModel.Admin;
import episen.CustomerAccount.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("Customer/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/loginAdmin")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String userName = credentials.get("userName");
        System.out.println(userName);
        String password = credentials.get("password");
        System.out.println(password);

        Admin admin = adminService.authenticate(userName, password);
        if (admin != null) {
            return ResponseEntity.ok(admin);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }
}
