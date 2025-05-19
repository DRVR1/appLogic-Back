package com.drvr1.applogic.Services;

import com.drvr1.applogic.Entities.AppVersion;
import com.drvr1.applogic.Repositories.AppVersionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppVersionService {
    @Autowired
    AppVersionRepository appVersionRepository;

    public AppVersion createAppVersion(AppVersion appVersion){
        AppVersion appVersion1 = AppVersion
                .builder()
                .moddedApp(appVersion.getModdedApp())
                .architectures(appVersion.getArchitectures())
                .postDateTime(LocalDateTime.now())
                .mirrorList(appVersion.getMirrorList())
                .version(appVersion.getVersion())
                .build();
        return appVersionRepository.save(appVersion1);
    }

    public ResponseEntity<?> deleteAppVersion(AppVersion appVersion){
        // buscar app
        long id = appVersion.getId();
        Optional<AppVersion> appVersionOptional = appVersionRepository.findById(id);
        // si no existe, error
        try{
            if (appVersionOptional.isPresent()){
                appVersionRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.OK).body("Deleted sucessfully!");
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found");
            }
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Some internal error, please check the server");
        }

    }

    public AppVersion getLastAppVersion(long moddedAppId){
        try{
            ArrayList<AppVersion> appVersionArrayList =  new ArrayList<>(appVersionRepository.findAllByModdedApp_IdOrderByPostDateTimeDesc(moddedAppId));
            return appVersionArrayList.getFirst();
        }catch (Exception e){
            return AppVersion.builder().build();
        }

    }

    public List<AppVersion> readAllAppVersion(String packageName){
        return appVersionRepository.findAllByModdedApp_PackageNameOrderByPostDateTimeDesc(packageName);
    }
}
