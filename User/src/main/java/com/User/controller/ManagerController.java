package com.User.controller;


import com.User.dto.*;
import com.User.security.CheckSecurity;
import com.User.service.ManagerService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "http://localhost:4200")
public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/getAllManagers")
    //@CheckSecurity(roles = {"ROLE_MANAGER"})
    public ResponseEntity<Page<ManagerDto>> getAllMangers(Pageable pageable) {
        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("{id}/getManager")
    @CheckSecurity (roles={"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> getManager(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(managerService.findById(id),HttpStatus.OK);
    }
    @PostMapping("/registerManager")
    public ResponseEntity<ManagerDto> saveManager(@RequestBody @Valid ManagerCreateDto managerCreateDto) {
        //notifikacija
        return new ResponseEntity<>(managerService.add(managerCreateDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> loginManager(@RequestBody @Valid TokenRequestDto tokenRequestDto) {
        //notifikacija
        return new ResponseEntity<>(managerService.login(tokenRequestDto), HttpStatus.OK);
    }

    @PutMapping("/update/{managerId}")
    public ResponseEntity<String> updateProfile(@PathVariable("managerId") Long managerId, @RequestBody ManagerUdateDto updatedManager) {
        managerService.updateProfile(Long.valueOf(managerId), updatedManager);

        return ResponseEntity.ok("Profile updated successfully.");
    }
}
