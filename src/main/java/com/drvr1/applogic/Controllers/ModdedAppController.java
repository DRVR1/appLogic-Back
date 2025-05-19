package com.drvr1.applogic.Controllers;

import com.drvr1.applogic.DTOs.ResponseMessage;
import com.drvr1.applogic.Entities.ModdedApp;
import com.drvr1.applogic.Entities.Visit;
import com.drvr1.applogic.Services.ModdedAppService;
import com.drvr1.applogic.Services.RequestService;
import com.drvr1.applogic.Services.VisitService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api")
public class ModdedAppController {

    @Autowired
    ModdedAppService moddedAppService;

    @Autowired
    RequestService requestService;

    @Autowired
    VisitService visitService;

    @PostMapping("/createModdedApp")
    public ResponseEntity<?> altaModdedApp(@RequestBody ModdedApp moddedApp, HttpServletRequest request) {
        Visit visit = new Visit(requestService.getClientIp(request), LocalDateTime.now(),"createModdedApp");
        visitService.altaVisit(visit);
        try{
            if (requestService.isTrustedUser(requestService.getClientIp(request))) {
                Long id = moddedApp.getId();
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(moddedAppService.createModdedApp(moddedApp));
            }else{
                return ResponseEntity
                        .status(HttpStatus.FORBIDDEN)
                        .body(null);
            }
        } catch (Exception e) {
            System.out.println("Returning error!");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage("There was some error uploading the app."));
        }

    }

    @PostMapping("/updateModdedApp")
    public ResponseEntity<?> updateModdedApp(@RequestBody ModdedApp moddedApp, HttpServletRequest request) {
        Visit visit = new Visit(requestService.getClientIp(request), LocalDateTime.now(),"updateModdedApp");
        visitService.altaVisit(visit);
        if (requestService.isTrustedUser(requestService.getClientIp(request))) {
            return moddedAppService.updateModdedApp(moddedApp);
        }else{
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Your ip address is not whitelisted");
        }
    }

    @PostMapping("/deleteModdedApp")
    public ResponseEntity<?> deleteModdedApp(@RequestBody ModdedApp moddedApp, HttpServletRequest request) {
        try{
            Visit visit = new Visit(requestService.getClientIp(request), LocalDateTime.now(), "deleteModdedApp");
            visitService.altaVisit(visit);
            if (requestService.isTrustedUser(requestService.getClientIp(request))) {
                Long id = moddedApp.getId();
                moddedAppService.deleteModdedApp(id);
                return ResponseEntity.ok(new ResponseMessage("Sucess"));
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Not allowed ip");
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseMessage("There was some error deleting the app."));
        }
    }

    @GetMapping("/readAllModdedApp")
    public ResponseEntity<?> readAllModdedApp(HttpServletRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(moddedAppService.readAllModdedApp());
    }

    @GetMapping("/readAllSmallModdedAppByTitle")
    public ResponseEntity<?> readAllSmallModdedAppByTitle(HttpServletRequest request, @RequestParam String title){
        return ResponseEntity.ok(moddedAppService.readAllSmallModdedAppByTitle(title));
    }

    @GetMapping("/readAllSmallModdedApp")
    public ResponseEntity<?> readAllSmallModdedApp(HttpServletRequest request){
        return ResponseEntity.ok(moddedAppService.readAllSmallModdedApp());
    }

    @GetMapping("/readSmallModdedApp")
    public ResponseEntity<?> readSmallModdedApp(HttpServletRequest request, @RequestParam String packageName){
        return ResponseEntity.ok(moddedAppService.readSmallModdedApp(packageName));
    }
}