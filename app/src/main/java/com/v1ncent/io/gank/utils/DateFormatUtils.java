package com.v1ncent.io.gank.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by v1ncent on 2015/9/8. create
 */
public class DateFormatUtils {
    public static void main(String[] args) {
//        System.out.println(getTodayDate());
//        System.out.println(getTheDayBefore(getTodayDate()));
//
//        System.out.println(getWeek(getTodayDate()));
//        System.out.println(getWeek(getTheDayBefore(getTodayDate())));
        getNoWorkFirstDate();
    }

    /**
     * 获取今天的日期
     *
     * @return
     */
    public static String getTodayDate() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        return simpleDateFormat.format(date);
    }

    /**
     * 获取非工作日的7天
     *
     * @return
     */
    public static List<String> getNoWorkFirstDate() {
        List<String> closeTo7thDays = new ArrayList<>();
        String tempDate;
        tempDate = getTodayDate();
//        System.out.println("first day-->"+tempDate);
        for (int i = 0; i < 24; i++) {
            if (getWeek(tempDate)) {
                closeTo7thDays.add(tempDate);
            }
            tempDate = getTheDayBefore(tempDate);
//            System.out.println("tempDate-->"+tempDate);
            if (closeTo7thDays.size() == 10) {
                break;
            }
        }
        return closeTo7thDays;
    }

    /**
     * 获取指定日期的前一天
     *
     * @param the_day
     * @return
     */
    public static String getTheDayBefore(String the_day) {
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = format.parse(the_day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        date = calendar.getTime();   //得到前一天的时间

        return format.format(date);
    }

    /**
     * 获取指定日期的前一天
     *
     * @param the_day
     * @return
     */
    public static String getTheDayBeforeNum(String the_day) {
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        try {
            date = format.parse(the_day);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();  //得到日历
        calendar.setTime(date);//把当前时间赋给日历
        calendar.add(Calendar.DAY_OF_MONTH, -1);  //设置为前一天
        date = calendar.getTime();   //得到前一天的时间

        return format.format(date);
    }

    /**
     * 根据日期取得星期几
     *
     * @param date2Week
     * @return
     */
    public static boolean getWeek(String date2Week) {
        String week;
        boolean isWeekDay;
        DateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        Date date = null;
        try {
            date = format.parse(date2Week);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        week = weeks[week_index];
        if (week.equals("星期日") ||
                week.equals("星期六")) {
            isWeekDay = false;
        } else {
            isWeekDay = true;
        }
        return isWeekDay;
    }



}
