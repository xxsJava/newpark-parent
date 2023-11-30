package com.newpark.test;

import org.junit.Test;

import java.util.Scanner;

/**
 * @Author xxs18
 * @Description
 * @Date 2023/11/22 11:03
 **/
public class Demo {

    public static void main(String[] args) {
//        工资=月薪÷21.75×月计薪天数×(出勤天数比例)
//        月计薪天数=(月出勤天数 + 法定节假日天数)
//        出勤天数比例= 21.75÷(当月应出勤天数+法定节假日天数)
        Double money = 21.75;

        System.out.print("请输入月薪:");
        Scanner in = new Scanner(System.in);
        Integer moneyMonth = in.nextInt();
        System.out.println();

        System.out.print("请输入月出勤天数:");
        Scanner in1 = new Scanner(System.in);
        Integer moneyMonthDay = in1.nextInt();
        System.out.println();

        System.out.print("请输入法定节假日天数:");
        Scanner in2 = new Scanner(System.in);
        Integer moneyLawDay = in2.nextInt();
        System.out.println();

        System.out.print("请输入当月应出勤天数:");
        Scanner in3 = new Scanner(System.in);
        Integer monthDays = in3.nextInt();
        System.out.println();

        //月计薪天数
        Integer monthDay = (moneyMonthDay + moneyLawDay);
        //出勤天数比例
        Double moneys = money / (monthDays+moneyLawDay);

        System.out.println("月计薪天数--->"+monthDay);
        System.out.println("出勤天数比例--->"+moneys);
        System.out.println("工资--->"+(Double)(moneyMonth/money*monthDay*moneys));

    }

}
