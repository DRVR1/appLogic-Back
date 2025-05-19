package com.drvr1.applogic.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    protected LocalDateTime postedDateTime;

    @Column(length = 600)
    protected String body;

    @Column
    private String ip;

    @ManyToOne
    @JoinColumn(name = "modded_app_id", referencedColumnName = "id")
    private ModdedApp moddedApp;
}
