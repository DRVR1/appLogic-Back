package com.drvr1.applogic.Repositories;

import com.drvr1.applogic.Entities.AppVersion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppVersionRepository extends JpaRepository<AppVersion, Long> {
    List<AppVersion> findAllByModdedApp_IdOrderByPostDateTimeDesc(long id);
    List<AppVersion> findAllByModdedApp_PackageNameOrderByPostDateTimeDesc(String packageName);
}
