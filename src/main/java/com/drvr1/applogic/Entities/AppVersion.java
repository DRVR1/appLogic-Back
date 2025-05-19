package com.drvr1.applogic.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppVersion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modded_app_id", referencedColumnName = "id")
    private ModdedApp moddedApp;

    @Column
    private String architectures;

    @Column
    private LocalDateTime postDateTime;

    @Column
    private String version;

    @Column
    private List<String> mirrorList;
}
