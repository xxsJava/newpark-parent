package com.newpark.test;

import java.util.Calendar;
import java.util.Scanner;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/23 18:08
 **/
public class CountMoney {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();
        // 获取当前年
        int year = calendar.get(Calendar.YEAR);
        // 获取当前月
        int month = calendar.get(Calendar.MONTH) + 1;

//        countMoney(year,month);
        moneys();
    }

    public static void moneys(){
        int moneySum = 12000;
        int sxMoney = (int) (moneySum*0.8);

        System.out.println("实习期---->"+(sxMoney));
        System.out.println("实际到手---->"+((sxMoney/30)*26));
    }

    public static void countMoney(int year,int month){
        int day1 = 0;
        int dayT = 0;
        //13578 10 12 这几个月份都有31天 获取到上个月底的最后一天
        for(int i = 0; i <= month;i++){

            int tempMonth = month-i ;

            if(tempMonth == 0){
                break;
            }

            //13578 10 12 这几个月份都有31天
            if(tempMonth == 2){
                if( ((year%100!=0 && year%4==0) ||
                        (year%400==0))){
                    System.out.println(year+"是闰年！");
                    day1 += 29;
                }else {
                    day1 += 28;
                }
            }else if(tempMonth<8){
                if(tempMonth%2!=0){
                    day1 += 31;
                }else {
                    day1 += 30;
                }
            }else {
                if(tempMonth%2 == 0){
                    day1 += 31;
                }else {
                    day1 += 30;
                }
            }

            if(month == tempMonth){
                dayT = day1;
            }
        }


        int yearCount = 0 ;
        //计算1900到输入月份的天数
        if(year >= 1900){
            int  tempYear = year-1900;
            //是否是闰年 闰年366 平年365 利用循环判断闰年 假如我们2017 - 2023 我们需要拿到2017-2023中间的年份
            for (int i = 0;i<tempYear;i++){
                if( (((year-i)%100!=0 && (year-i)%4==0) ||
                        ((year-i)%400==0))){
                    ++yearCount;
                    day1 += 366 ;
                }else {
                    day1 +=365 ;
                }
            }
        }

        //计算输入月份的第一天
        //利用总天数-当月的天数 == 上一个月最后一天 + 1 ==当月第一天 % 每个礼拜有7天((day1-dayT)+1)%7
        int dayD = ((day1-dayT)+1)%7;

        String[] monthText = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
        String[] dayArr = new String[dayT + 7];

        //遍历日期
        for(int i = 0;i < monthText.length;i++){
            System.out.print(monthText[i]);
            if(i<monthText.length-1){
                System.out.print("\t");
            }
        }
        //格式
        System.out.println();
        //存入日期 从当月的第一天是 星期几开始存
        for(int i = 0; i < dayT;i++){
            dayArr[dayD + i] = (i+1) + "";
        }

        Integer nullCount = 0;
        Integer dayCount = 0;

        //遍历日期
        for(int i = 0; i < dayArr.length;i++){
            if(i%7 == 0){
                System.out.println();
            }
            //存入字符时由于是从当月第一天是星期几开始存 导致前面没有赋值
            if(dayArr[i] == null){
                System.out.print("\t\t");
            }else {
                System.out.print(dayArr[i]+"\t\t");
            }
        }

    }

}
