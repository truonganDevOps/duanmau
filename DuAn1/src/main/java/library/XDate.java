/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package library;

import java.text.*;
import java.util.Date;


public class XDate {
    
    static final SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy");
    
    //chuyển String date -> Date theo pattern
    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                formater.applyPattern(pattern[0]);
            }
            if (date == null) {
                return XDate.now();
            }
            return formater.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    
    //chuyển từ dạng Date -> String theo pattern[0]
    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            formater.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = XDate.now();
        }
        return formater.format(date);
    }
    
    //return ngày hiện tại
    public static Date now() {
        return new Date();
    }
    
    // add days to date -> return date
    public static Date addDays(Date date, int days) {
        date.setTime(date.getTime() + days*24*60*60*1000);
        return date;
    }
    
    //add days vào ngày hiện tại và return
    public static Date add(int days) {
        Date now = XDate.now();
        now.setTime(now.getTime() + days*24*60*60*1000);
        return now;
    }
    
}
