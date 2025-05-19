package com.drvr1.applogic.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Interaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "modded_app_id", referencedColumnName = "id")
    private ModdedApp moddedApp;

    @Column
    private String ip;

    @Column
    private LocalDateTime postDateTime;

    @Column
    private String type;
}
