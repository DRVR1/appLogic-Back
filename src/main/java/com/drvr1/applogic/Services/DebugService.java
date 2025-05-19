package com.drvr1.applogic.Services;

public class DebugService {

    public boolean debugMode = true;

    private static DebugService instance;

    private DebugService(){}

    public static DebugService getInstance(){
        if(instance == null){
            return new DebugService();
        }else{
            return instance;
        }
    }
}
