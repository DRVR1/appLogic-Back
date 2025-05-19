package com.drvr1.applogic.Controllers;

import com.drvr1.applogic.Entities.Comment;
import com.drvr1.applogic.Services.CommentService;
import com.drvr1.applogic.Services.RequestService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    RequestService requestService;

    @PostMapping("/createComment")
    public ResponseEntity<?> createComment(@RequestBody Comment comment, HttpServletRequest request){
        comment.setIp(requestService.getClientIp(request));
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @PostMapping("/deleteComment")
    public ResponseEntity<?> deleteComment(@RequestBody Comment comment){
        try{
            commentService.deleteComent(comment);
            return ResponseEntity.ok("Comment deleted!");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some error: " + e);
        }
    }

    @GetMapping("/readAllComments")
    public ResponseEntity<?> readAllComments(@RequestParam String packageName){
        return ResponseEntity.ok(commentService.readAllComments(packageName));
    }
}
