package com.drvr1.applogic.Controllers;

import com.drvr1.applogic.Entities.Visit;
import com.drvr1.applogic.Login.LoginRequest;
import com.drvr1.applogic.Login.LoginResponse;
import com.drvr1.applogic.Security.JwtIssuer;
import com.drvr1.applogic.Security.UserPrincipal;
import com.drvr1.applogic.Services.RequestService;
import com.drvr1.applogic.Services.VisitService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.drvr1.applogic.Security.*;
import org.springframework.security.core.GrantedAuthority;


import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final JwtIssuer issuer = new JwtIssuer();
    private final AuthenticationManager authenticationManager;

    @Autowired
    RequestService requestService;

    @Autowired
    VisitService visitService;

    @PostMapping("/auth/login")
    public LoginResponse login(@RequestBody @Validated LoginRequest request, HttpServletRequest servletRequest){
        Visit visit = new Visit(requestService.getClientIp(servletRequest), LocalDateTime.now(),"login");
        visitService.altaVisit(visit);
        if(requestService.isTrustedUser(requestService.getClientIp(servletRequest))){
            var authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            var principal = (UserPrincipal) authentication.getPrincipal();

            var roles = principal.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .toList();


            var token = issuer.issue(principal.getUserId(),principal.getEmail(), roles);
            return new LoginResponse(token,roles.getFirst(),principal.getUserId());
        }
        return new LoginResponse("access","denied",Long.parseLong("1"));
    }

}
