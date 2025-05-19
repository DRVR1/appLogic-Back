package com.drvr1.applogic.DTOs;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentDTO {
    private long id;
    private LocalDateTime postedDateTime;
    private String body;
}
