package com.drvr1.applogic.Services;

import com.drvr1.applogic.Entities.Visit;
import com.drvr1.applogic.Repositories.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    @Autowired
    VisitRepository visitRepository;

    public Visit altaVisit(Visit visit){
        return visitRepository.save(visit);
    }
}
