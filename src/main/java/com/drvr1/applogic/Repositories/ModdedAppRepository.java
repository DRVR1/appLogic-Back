package com.drvr1.applogic.Repositories;

import com.drvr1.applogic.Entities.ModdedApp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModdedAppRepository extends JpaRepository<ModdedApp, Long> {
    List<ModdedApp> findByRemovedIsFalse();
    List<ModdedApp> findByRemovedIsFalseAndTitleContainingIgnoreCase(String title);
    Optional<ModdedApp> findByPackageName(String packageName);
}
