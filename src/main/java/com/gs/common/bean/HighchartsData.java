package com.gs.common.bean;

import com.gs.common.util.DateFormatUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by xiao-kang on 2017/5/8.
 */
public class HighchartsData {
    public  static final String[] strDay = new String[]{"01","02","03","04","05","06","07","08","09",
            "10","11","12","13","14","15","16","17","18","19","20",
            "21","22","23","24","25","26","27","28","29","30","31"};

    public  static final String[] strQuarter = new String[]{"第一季度","第二季度","第三季度","第四季度"};

    public  static final  String[] strMonth = new String[]{"01月","02月","03月","04月","05月","06月","07月","08月","09月",
            "10月","11月","12月"};

    public  static  String[] strYear;
    public  static  int yearLen;
    public  static double[] doubleYearInType;
    public  static double[] doubleYearOutType;

    public  static String[] strWeek;
    public  static int weekLen;
    public  static double[] doubleWeekInType;
    public  static double[] doubleWeekOutType;

    public  static double[] doubleMonthInType = new double[12];
    public  static double[] doubleMonthOutType = new double[12];

    public  static double[] doubleDayInType = new double[31];
    public  static double[] doubleDayOutType = new double[31];

    public  static double[] doubleQuarterInType = new double[4];
    public  static double[] doubleQuarterOutType = new double[4];

    public  static int len;

    public static String dateFormat(Date date, String type){
        String str = DateFormatUtil.defaultFormat(date);
        String time = null;
        if(type.equals("day")){
            time = str.substring(8,10);
        }else if(type.equals("month")){
            time = str.substring(5,8);
        }else if(type.equals("quarter")){
            time = str.substring(5,7);
        }else if(type.equals("year")){
            time = str.substring(0,4);
        }
        return time;
    }

    public static String dateFormat(Date date){
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    // 获取周报表数据
    public static void getWeek(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strWeek[j].equals(strs[k])) {
                    if (inOut.equals("incoming")) {
                        doubleWeekInType[j] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleWeekOutType[j] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleWeekInType[j] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleWeekOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取年报表数据
    public static void getYear(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strYear[j].equals(strs[k])) {
                    if (inOut.equals("incoming")) {
                        doubleYearInType[j] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleYearOutType[j] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleYearInType[j] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleYearOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取季度报表数据
    public static void getQuarter(String[] strs,double[] doubles,String inOut){
        for(int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strs[k].equals("01") || strs[k].equals("02") || strs[k].equals("03")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[0] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[0] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleQuarterInType[0] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleQuarterOutType[0] = doubles[k];
                    }
                } else if (strs[k].equals("04") || strs[k].equals("05") || strs[k].equals("06")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[1] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[1] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleQuarterInType[1] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleQuarterOutType[1] = doubles[k];
                    }
                } else if (strs[k].equals("07") || strs[k].equals("08") || strs[k].equals("09")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[2] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[2] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleQuarterInType[2] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleQuarterOutType[2] = doubles[k];
                    }
                } else if (strs[k].equals("10") || strs[k].equals("11") || strs[k].equals("12")) {
                    if (inOut.equals("incoming")) {
                        doubleQuarterInType[3] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleQuarterOutType[3] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleQuarterInType[3] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleQuarterOutType[3] = doubles[k];
                    }
                }
            }

        }
    }

    // 获取月报表数据
    public static void getMonth(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if (strMonth[j].equals(strs[k])) {
                    if (inOut.equals("incoming")) {
                        doubleMonthInType[j] = doubles[k];
                    } else if (inOut.equals("outgoing")) {
                        doubleMonthOutType[j] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleMonthInType[j] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleMonthOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    // 获取日报表数据
    public  static void getDay(String[] strs,double[] doubles,String inOut) {
        for (int j = 0; j < len; j++) {
            for (int k = 0; k < strs.length; k++) {
                if(strDay[j].equals(strs[k])){
                    if(inOut.equals("incoming")){
                        doubleDayInType[j] = doubles[k];
                    }else if(inOut.equals("outgoing")){
                        doubleDayOutType[j] = doubles[k];
                    }else if(inOut.equals("service")){
                        doubleDayInType[j] = doubles[k];
                    }else if(inOut.equals("maintenance")){
                        doubleDayOutType[j] = doubles[k];
                    }
                }
            }
        }
    }

    /*
    *
    * 获取选中的时间是本年的第几周
    * */
    public static int getWeek(String time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int weekOfMonth = calendar.get(Calendar.WEEK_OF_YEAR);
        return weekOfMonth;
    }


    /*
    * 获取选择的开始年和结束年的所有
    * */
    public static void setStrYear(String start,String end){
        String strStartTime = start.substring(0,4);
        String strEndTime = end.substring(0,4);
        int startTime = Integer.valueOf(strStartTime);
        int endTime = Integer.valueOf(strEndTime);
        yearLen =  endTime - startTime+1;
        strYear = new String[yearLen];
        for(int i = 0; i < strYear.length; i++){
            strYear[i] = String.valueOf(startTime);
            startTime++;
        }
    }
    /*
       * 获取选择的开始周和结束周的所有
       * */
    public static void setStrWeek(String start,String end){
        int starkWeek =  getWeek(start);
        int endWeek = getWeek(end);
        weekLen =  endWeek - starkWeek+1;
        strWeek = new String[weekLen];
        for(int i = 0; i < strWeek.length; i++){
            strWeek[i] = "第"+ String.valueOf(starkWeek) + "周";
            starkWeek++;
        }

    }
}
