package com.drvr1.applogic.Repositories;

import com.drvr1.applogic.Entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findByModdedApp_Id(long id);
    List<Comment> findByModdedApp_PackageName(String packageName);
    List<Comment> findByIpAndModdedApp_Id(String ip, long moddedAppId);
}
