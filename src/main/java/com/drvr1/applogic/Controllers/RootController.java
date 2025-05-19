package com.drvr1.applogic.Controllers;

import com.drvr1.applogic.Services.RequestService;
import com.drvr1.applogic.Services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private VisitService visitService;

    @GetMapping("/{path:[^\\\\.]*}") // Matches all paths except those containing a dot (e.g., .js, .css, etc.)
    public String forward() {
        return "forward:/index.html";
    }

    @GetMapping("/apps/*") // Matches all paths except those containing a dot (e.g., .js, .css, etc.)
    public String apps() {
        return "forward:/index.html";
    }

}
