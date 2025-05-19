package com.drvr1.applogic.DTOs;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmallModdedAppDTO {
    private long id;
    private String title;
    private String iconUrl;
    private String imageUrl;
    private String packageName;
    private String description;
    // From versions
    private String lastVersion;
    // From Comments
    private int comments;
    //From interactions
    private int likes;
    private int dislikes;
}


// title,image,icon, version, packageName, likes, dislikes, comments