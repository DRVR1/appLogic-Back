package com.drvr1.applogic.Controllers;

import com.drvr1.applogic.Entities.Interaction;
import com.drvr1.applogic.Services.InteractionService;
import com.drvr1.applogic.Services.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class InteractionController {

    @Autowired
    InteractionService interactionService;

    @Autowired
    RequestService requestService;

    @PostMapping("/createInteraction")
    public ResponseEntity<?> createInteraction(HttpServletRequest request, @RequestBody Interaction interaction){
        interaction.setIp(requestService.getClientIp(request));
        return ResponseEntity.ok(interactionService.createInteraction(interaction));
    }

    @PostMapping("/deleteInteraction")
    public ResponseEntity<?> deleteInteraction(HttpServletRequest request, @RequestBody Interaction interaction){
        try{
            interactionService.deleteInteraction(interaction);
            return ResponseEntity.ok("Done");
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting interaction");
        }
    }
}
