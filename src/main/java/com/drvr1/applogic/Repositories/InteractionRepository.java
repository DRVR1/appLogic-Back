package com.drvr1.applogic.Repositories;

import com.drvr1.applogic.Entities.Interaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long> {
    public Interaction findByIpAndModdedApp_Id(String ip,Long id);

    public List<Interaction> findByModdedApp_Id(long id);
}