package com.drvr1.applogic.Services;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;


@Service
public class RequestService {

    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        System.out.println("Returnind ip address: " + ip);
        return ip;
    }


    public Boolean isTrustedUser(String ip){
        return true;
    }

}
