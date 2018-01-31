package com.meiyou.meetyoucostdemo;

import com.meiyou.meetyoucostplugin.Cost;

/**
 * Author: ice
 * Date: 1/30/18 17:56.
 */

public class TestController {
    private static TestController instance;

    @Cost
    public static TestController getInstance(){
        if(instance==null){
            instance = new TestController();
        }
        return  instance;
    }
}

