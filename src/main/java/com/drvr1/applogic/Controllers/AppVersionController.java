package com.drvr1.applogic.Controllers;

import com.drvr1.applogic.DTOs.ResponseMessage;
import com.drvr1.applogic.Entities.AppVersion;
import com.drvr1.applogic.Services.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AppVersionController {
    @Autowired
    AppVersionService appVersionService;

    @PostMapping("/createAppVersion")
    public ResponseEntity<?> createAppVersion(@RequestBody AppVersion appVersion){
        try{
            appVersionService.createAppVersion(appVersion);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Success"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Error uploading app version"));
        }

    }
    @PostMapping("/deleteAppVersion")
    public ResponseEntity<?> deleteAppVersion(@RequestBody AppVersion appVersion){
        try{
            appVersionService.deleteAppVersion(appVersion);
            return ResponseEntity.ok(new ResponseMessage("Success"));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseMessage("Error deleting app version"));
        }
    }

    @GetMapping("/readAllAppVersion")
    public ResponseEntity<?> readAllAppVersion(@RequestParam String packageName){
        return ResponseEntity.ok(appVersionService.readAllAppVersion(packageName));
    }

}
