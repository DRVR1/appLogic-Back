package com.drvr1.applogic.Services;

import com.drvr1.applogic.DTOs.SmallModdedAppDTO;
import com.drvr1.applogic.Entities.ModdedApp;
import com.drvr1.applogic.Repositories.ModdedAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ModdedAppService {

    @Autowired
    ModdedAppRepository moddedAppRepository;

    @Autowired
    CommentService commentService;

    @Autowired
    InteractionService interactionService;

    @Autowired
    AppVersionService appVersionService;

    public ModdedApp createModdedApp(ModdedApp moddedApp) {
        if(moddedApp.getImageUrl() == null){
            moddedApp.setImageUrl("");
        }
        ModdedApp moddedApp1 = ModdedApp.builder()
                .packageName(moddedApp.getPackageName())
                .title(moddedApp.getTitle())
                .description(moddedApp.getDescription())
                .imageUrl(moddedApp.getImageUrl())
                .iconUrl(moddedApp.getIconUrl())
                .removed(false)
                .build();
        return moddedAppRepository.save(moddedApp1);
    }

    public ResponseEntity<?> updateModdedApp(ModdedApp moddedApp) {
        long id = moddedApp.getId();
        Optional<ModdedApp> optionalModdedApp = moddedAppRepository.findById(id);
        if (optionalModdedApp.isPresent()) {
            ModdedApp moddedApp1 = optionalModdedApp.get();
            moddedApp1.setPackageName(moddedApp.getPackageName());
            moddedApp1.setTitle(moddedApp.getTitle());
            moddedApp1.setDescription(moddedApp.getDescription());
            moddedApp1.setImageUrl(moddedApp.getImageUrl());
            moddedApp1.setIconUrl(moddedApp.getIconUrl());
            moddedApp1.setRemoved(moddedApp.isRemoved());
            return ResponseEntity.status(HttpStatus.OK).body(moddedAppRepository.save(moddedApp1));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("The app you tried to update was not found.");
        }
    }

    public ResponseEntity<?> deleteModdedApp(Long id) {
        Optional<ModdedApp> app = moddedAppRepository.findById(id);
        if (app.isPresent()) {
            ModdedApp app1 = app.get();
            app1.setRemoved(true);
            return ResponseEntity.status(HttpStatus.OK).body(moddedAppRepository.save(app1));
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("app with that id was not found");
        }
    }

    public List<ModdedApp> readAllModdedApp(){
        return moddedAppRepository.findAll();
    }

    public List<SmallModdedAppDTO> readAllSmallModdedApp(){
        List<ModdedApp> moddedAppList = moddedAppRepository.findByRemovedIsFalse();
        ArrayList<SmallModdedAppDTO> smallModdedAppDTOList = new ArrayList<>();
        for(ModdedApp moddedApp : moddedAppList){
            System.out.println("adding: " + moddedApp.getTitle());
            SmallModdedAppDTO smallModdedAppDTO = SmallModdedAppDTO.builder()
                    .id(moddedApp.getId())
                    .title(moddedApp.getTitle())
                    .iconUrl(moddedApp.getIconUrl())
                    .packageName(moddedApp.getPackageName())
                    .imageUrl(moddedApp.getImageUrl())
                    .description(moddedApp.getDescription())
                    .comments(commentService.getAmmountCommentsByModdedAppId(moddedApp.getId()))
                    .likes(interactionService.getInteractionAmmountByModdedAppId(moddedApp.getId(),"LIKE"))
                    .dislikes(interactionService.getInteractionAmmountByModdedAppId(moddedApp.getId(), "DISLIKE"))
                    .lastVersion(appVersionService.getLastAppVersion(moddedApp.getId()).getVersion())
                    .build();
            smallModdedAppDTOList.add(smallModdedAppDTO);
        }
        return smallModdedAppDTOList;
    }

    public SmallModdedAppDTO readSmallModdedApp(String packageName){
        Optional<ModdedApp> moddedAppOptional = moddedAppRepository.findByPackageName(packageName);
        if(moddedAppOptional.isPresent()){
            ModdedApp moddedApp = moddedAppOptional.get();
            SmallModdedAppDTO smallModdedAppDTO = SmallModdedAppDTO.builder()
                    .id(moddedApp.getId())
                    .title(moddedApp.getTitle())
                    .iconUrl(moddedApp.getIconUrl())
                    .packageName(moddedApp.getPackageName())
                    .imageUrl(moddedApp.getImageUrl())
                    .description(moddedApp.getDescription())
                    .comments(commentService.getAmmountCommentsByModdedAppId(moddedApp.getId()))
                    .likes(interactionService.getInteractionAmmountByModdedAppId(moddedApp.getId(),"LIKE"))
                    .dislikes(interactionService.getInteractionAmmountByModdedAppId(moddedApp.getId(), "DISLIKE"))
                    .lastVersion(appVersionService.getLastAppVersion(moddedApp.getId()).getVersion())
                    .build();
            return smallModdedAppDTO;
        }
        return null;
    }

    public List<SmallModdedAppDTO> readAllSmallModdedAppByTitle(String title){
        List<ModdedApp> moddedAppList = moddedAppRepository.findByRemovedIsFalseAndTitleContainingIgnoreCase(title);
        ArrayList<SmallModdedAppDTO> smallModdedAppDTOList = new ArrayList<>();
        for(ModdedApp moddedApp : moddedAppList){
            SmallModdedAppDTO smallModdedAppDTO = SmallModdedAppDTO.builder()
                    .id(moddedApp.getId())
                    .title(moddedApp.getTitle())
                    .iconUrl(moddedApp.getIconUrl())
                    .packageName(moddedApp.getPackageName())
                    .imageUrl(moddedApp.getImageUrl())
                    .comments(commentService.getAmmountCommentsByModdedAppId(moddedApp.getId()))
                    .likes(interactionService.getInteractionAmmountByModdedAppId(moddedApp.getId(),"LIKE"))
                    .dislikes(interactionService.getInteractionAmmountByModdedAppId(moddedApp.getId(), "DISLIKE"))
                    .lastVersion(appVersionService.getLastAppVersion(moddedApp.getId()).getVersion())
                    .build();
            smallModdedAppDTOList.add(smallModdedAppDTO);
        }
        return smallModdedAppDTOList;
    }

    public ModdedApp getByPackageName(String packageName){
        return moddedAppRepository.findByPackageName(packageName).get();
    }

}
