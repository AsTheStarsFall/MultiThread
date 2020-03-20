package com.tianhy.multithread;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void tt(){
        Map<String,String> map = new HashMap<>();

        map.put("EA","va1");
        map.put("FB","va2");

        System.out.println(map.entrySet());

//        String s ="Ea";
//
//        String d ="FB";
//
//        String c = "AD";
//
//        System.out.println(s.hashCode());
//
//        System.out.println(d.hashCode());
//
//        System.out.println(c.hashCode());

    }
}
