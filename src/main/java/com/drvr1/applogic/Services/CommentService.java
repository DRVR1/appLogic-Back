package com.drvr1.applogic.Services;

import com.drvr1.applogic.DTOs.CommentDTO;
import com.drvr1.applogic.Entities.Comment;
import com.drvr1.applogic.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public Comment createComment(Comment comment){
        ArrayList<Comment> oldComments = new ArrayList<Comment>(commentRepository.findByIpAndModdedApp_Id(comment.getIp(),comment.getModdedApp().getId()));
        if(oldComments.size() >= 3){
            return null;
        }
        Comment comment1 = Comment.builder()
                .body(comment.getBody())
                .postedDateTime(LocalDateTime.now())
                .moddedApp(comment.getModdedApp())
                .ip(comment.getIp())
                .build();
        return commentRepository.save(comment1);

    }

    public void deleteComent(Comment comment){
        commentRepository.deleteById(comment.getId());
    }

    public int getAmmountCommentsByModdedAppId(long moddedAppId){
        return commentRepository.findByModdedApp_Id(moddedAppId).size();
    }

    public List<CommentDTO> readAllComments(String moddedAppPackageName){
        ArrayList<Comment> commentArrayList = new ArrayList<>(commentRepository.findByModdedApp_PackageName(moddedAppPackageName));
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for(Comment comment : commentArrayList){
            CommentDTO commentDTO = CommentDTO.builder()
                    .id(comment.getId())
                    .body(comment.getBody())
                    .postedDateTime(comment.getPostedDateTime())
                    .build();
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
