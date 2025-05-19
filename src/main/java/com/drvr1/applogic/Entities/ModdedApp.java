package com.drvr1.applogic.Entities;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModdedApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    protected String packageName;

    @Column
    protected String title;

    @Column(length = 1000)
    protected String description;

    @Column(length = 1000)
    protected String imageUrl;

    @Column(length = 1000)
    String iconUrl;

    @Column
    public boolean removed;
}
