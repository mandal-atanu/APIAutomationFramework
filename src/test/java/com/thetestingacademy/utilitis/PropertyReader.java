package com.thetestingacademy.utilitis;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    // Responsibility of the class is to give the value of by key

    public static String readkey(String key){
        Properties properties = new Properties();

        try{

            FileInputStream fileInputStream = new FileInputStream("src/test/resources/data.properties");
            properties.load(fileInputStream);

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return properties.getProperty(key);
    }
}
