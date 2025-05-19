package com.drvr1.applogic.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Visit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    protected String ip;

    @Column
    protected LocalDateTime visitDateTime;

    @Column
    protected String typeOfVisit; // Could be a register, a redirect or random visit from outside/bot

    public Visit() {}

    public Visit(String ip, LocalDateTime visitDateTime, String typeOfVisit) {
        this.ip = ip;
        this.visitDateTime = visitDateTime;
        this.typeOfVisit = typeOfVisit;
    }
}
