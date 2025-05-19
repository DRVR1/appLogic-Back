package com.drvr1.applogic.Services;

import com.drvr1.applogic.Entities.Interaction;
import com.drvr1.applogic.Repositories.InteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InteractionService {
    @Autowired
    InteractionRepository interactionRepository;

    public Interaction createInteraction(Interaction interaction){
        //borrar la interaccion de esa ip para esa modded_app_id antes de colocar la siguiente
        try{
            Interaction oldInteraction = interactionRepository.findByIpAndModdedApp_Id(interaction.getIp(),interaction.getModdedApp().getId());
            System.out.println(oldInteraction.toString());
            interactionRepository.deleteById(oldInteraction.getId());
        }catch (Exception e){
            System.out.println("old not found: " + e);
        }
        interaction.setPostDateTime(LocalDateTime.now());
        return interactionRepository.save(interaction);
    }

    public void deleteInteraction(Interaction interaction){
        interactionRepository.deleteById(interaction.getId());
    }

    public List<Interaction> readAllInteractionsByModdedAppId(long moddedAppId){
        return interactionRepository.findByModdedApp_Id(moddedAppId);
    }

    public int getInteractionAmmountByModdedAppId(long moddedAppId,String interactionType){
        ArrayList<Interaction> interactionList = new ArrayList<>(this.readAllInteractionsByModdedAppId(moddedAppId));
        int count = 0;
        for(Interaction interaction : interactionList){
            if(interaction.getType().equals(interactionType)){
                count+=1;
            }
        }
        return count;
    }
}
