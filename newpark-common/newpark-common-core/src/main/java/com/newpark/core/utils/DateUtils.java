package com.newpark.core.utils;


import com.newpark.base.exception.BizException;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;
import java.util.*;


/**
 * 日期处理
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN2 = "yyyy/MM/dd";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    public final static String DT_8 = "yyyy-MM-dd'T'HH:mm:ss";

    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 获取到时间戳 s
     * @return
     */
    public static Long getTimes(){
        return new Date().getTime()/1000;
    }

    /**
     * 2019-11-25T15:25:54+08:00 ==> 2019-11-25 15:25:54
     *
     * @param dateStr
     * @return
     */
    public static String format_8(String dateStr) {
        try {
            Date date = DateUtils.parse(dateStr, DT_8);
            SimpleDateFormat df1 = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
            Date date1 = df1.parse(date.toString());
            DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return df2.format(date1);
        } catch (Exception e) {
            return null;
        }
    }

    public static String formatPattern(Date date) {
        return format(date, DATE_TIME_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    public static Date formatDate(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            String format = format(date, pattern);
            try {
                return df.parse(format);
            } catch (ParseException e) {
                throw new BizException("时间转换异常！");
            }
        }
        return null;
    }

    public static Date parse(String date) {
        return parse(date, DATE_TIME_PATTERN);
    }

    /**
     * 将日期字符串转换为日期类型
     *
     * @param date    日期字符串
     * @param pattern 转换模式
     * @return 返回日期类型时间
     */
    public static Date parse(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            throw new BizException("时间转换异常！");
        }
    }

    /**
     * 将日期字符串转换为指定格式的日期字符串
     *
     * @param date    日期字符串
     * @param pattern 转换模式
     * @return 返回日期类型时间
     */
    public static String format(String date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_PATTERN);
        try {
            Date parse = sdf.parse(date);
            return format(parse, pattern);
        } catch (ParseException e) {
            throw new BizException("时间转换异常！");
        }
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd）
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 日期字符串
     */
    public static String formatDate(Date date, Object... pattern) {
        if (date == null) {
            return null;
        }
        String formatDate;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }


    /**
     * 获取某天的开始时间
     *
     * @param dateTime 字符串日期
     * @return 某天的开始时间
     */
    public static String getBeginTime(String dateTime) {
        if (StringUtils.isNotEmpty(dateTime)) {
            Date date = parse(dateTime, DATE_PATTERN);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf("00"));
            calendar.set(Calendar.MINUTE, Integer.valueOf("00"));
            calendar.set(Calendar.SECOND, Integer.valueOf("00"));
            return formatPattern(calendar.getTime());
        }
        return "";
    }

    /**
     * 获取某天的结束时间
     *
     * @param dateTime 字符串日期
     * @return 某天的结束时间
     */
    public static String getEndTime(String dateTime) {
        if (StringUtils.isNotEmpty(dateTime)) {
            Date date = parse(dateTime, DATE_PATTERN);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            return formatPattern(calendar.getTime());
        }
        return "";
    }

    /**
     * 获取某天0点0分
     * @param date
     * @return
     */
    public static Date getZeroToday(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }
    public static Date setDateTime(Date date, Date time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DATE);


        Calendar start = Calendar.getInstance();
        start.setTime(time);
        int hour = start.get(Calendar.HOUR_OF_DAY);
        int minute = start.get(Calendar.MINUTE);
        int second = start.get(Calendar.SECOND);
        start.set(year, month, day, hour, minute, second);
        return start.getTime();
    }


    /**
     * 获取date最后的时间
     * date转为datetime  2021-01-31 00:00:00--->2021-01-31 23:59:59
     */
    public static Date getLastDateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        return calendar.getTime();
    }
    /**
     * 日期往后增加天数
     *
     * @param date    日期
     * @param addDays 天数
     * @return 增多天数后的日期
     */
    public static Date addDay(Date date, int addDays) {
        if (date == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, addDays);
        return calendar.getTime();
    }

    /**
     * 让当前日期加n天
     *
     * @param today   当前日期
     * @param addDays 增加的天数
     * @return 转换结果
     */
    public static String addNDay(String today, int addDays) {
        Date mondayDate = parse(today, DATE_PATTERN);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mondayDate);
        calendar.add(Calendar.DAY_OF_MONTH, addDays);
        return DateUtils.format(calendar.getTime());
    }

    /**
     * 获取本周的星期一的日期
     *
     * @param date 当前日期(yyyy-MM-dd)
     * @return 本周的星期一的日期
     */
    public static Date setCurrWeekToMonday(Date date) {
        Calendar calendar = Calendar.getInstance();
        // 设置为当前时间
        calendar.setTime(date);
        // 设置一周的第一天为星期一
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0) {
            day_of_week = 7;
            calendar.add(Calendar.DATE,-day_of_week+1);
        }
        // 判断当前算的日期是否是周日，如果是则减一天计算周六的，否则会出问题，计算到下一周去了
        if (1 == day) {
            // 减去一天，即星期六
            calendar.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 将当前日期设置为当前周的第一天，即星期一
        calendar.add(Calendar.DATE, calendar.getFirstDayOfWeek() - day);

        return calendar.getTime();
    }

    /**
     * 获取某分钟的开始时间
     *
     * @param date 日期
     * @return
     */
    public static String getBeginSecond(Date date) {
        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, Integer.valueOf("00"));
            return formatPattern(calendar.getTime());
        }
        return null;
    }

    /**
     * 获取某分钟的结束时间
     *
     * @param date 日期
     * @return
     */
    public static String getEndSecond(Date date) {
        if (date != null) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.SECOND, 59);
            return formatPattern(calendar.getTime());
        }
        return null;
    }


    /**
     * 日期增加分钟数
     *
     * @param date   日期
     * @param minute 分钟数
     * @return
     */
    public static Date addMinute(Date date, int minute) {
        if (date == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return calendar.getTime();
    }

    /**
     * 获取两个日期之间的天数
     *
     * @param date1 开始日期 "yyyy-MM-dd"
     * @param date2 结束日期 "yyyy-MM-dd"
     * @return 获取两个日期之间的天数
     */
    public static int countDays(String date1, String date2) {
        Date d1 = parse(date1, DATE_PATTERN);
        Date d2 = parse(date2, DATE_PATTERN);
        int days = (int) ((d2.getTime() - d1.getTime()) / (1000 * 3600 * 24));
        return days + 1;
    }

    /**
     * 获取两个日期之间的日期集合
     *
     * @param date1 开始日期 "yyyy-MM-dd"
     * @param date2 结束日期 "yyyy-MM-dd"
     * @return 获取两个日期之间的日期集合
     */
    public static List<String> returnTwoDateDayList(String date1, String date2) {
        int days = countDays(date1, date2);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < days; i++) {
            list.add(format(addDay(parse(date1, DATE_PATTERN), i)));
        }
        return list;
    }

    /**
     * 将秒数转换为日时分秒，
     */
    public static String secondToTime(long second) {
        //转换小时
        long hours = second / 3600;
        //剩余秒数
        second = second % 3600;
        //转换分钟
        long minutes = second / 60;
        //剩余秒数
        second = second % 60;
        return String.format("%02d", hours) + ":" + String.format("%02d", minutes) + ":" + String.format("%02d", second);
    }

    /**
     * 将时间转换为秒
     *
     * @param time "48:13:35"
     * @return 返回秒数
     */
    public static int timeToSecond(String time) {
        if (StringUtils.isNotEmpty(time)) {
            String[] split = time.split(":");
            Integer hour = Integer.valueOf(split[0]);
            Integer minute = Integer.valueOf(split[1]);
            Integer second = Integer.valueOf(split[2]);
            return hour * 3600 + minute * 60 + second;
        }
        return 0;
    }

    /**
     * 获取某个月第一天的开始时间
     *
     * @param dateTime 字符串日期
     * @return 某个月第一天的开始时间
     */
    public static String getMonthBeginTime(String dateTime) {
        if (StringUtils.isNotEmpty(dateTime)) {
            dateTime = dateTime.concat("-1");
            Date date = parse(dateTime, DATE_PATTERN);
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf("00"));
            calendar.set(Calendar.MINUTE, Integer.valueOf("00"));
            calendar.set(Calendar.SECOND, Integer.valueOf("00"));
            return formatPattern(calendar.getTime());
        }
        return null;
    }

    /**
     * 获取当月1号
     */
    public static String getMonthBeginTimeTow(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return formatPattern(calendar.getTime());
    }
    /**
     * 获取当月1号 返回日期类型
     */
    public static Date getMonthBeginTimeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取某个月最后一天
     *
     * @param dateTime 字符串日期
     * @return 某个月最后一天
     */
    public static String getMonthEndTime(String dateTime) {
        if (StringUtils.isNotEmpty(dateTime)) {
            String[] date = dateTime.split("-");
            Calendar calendar = new GregorianCalendar();
            calendar.set(Calendar.YEAR, Integer.valueOf(date[0]));
            calendar.set(Calendar.MONTH, Integer.valueOf(date[1]) - 1);
            int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DAY_OF_MONTH, lastDay);
            calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf("23"));
            calendar.set(Calendar.MINUTE, Integer.valueOf("59"));
            calendar.set(Calendar.SECOND, Integer.valueOf("59"));
            return formatPattern(calendar.getTime());
        }
        return null;
    }

    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime   当前时间
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return true:在区间内 false:不在区间内
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime() || nowTime.getTime() == endTime.getTime()) {
            return true;
        }
        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        return date.after(begin) && date.before(end);
    }

    /**
     * 获取两个日期之间的日期对应的月周次
     *
     * @param beginDate 开始日期 "2019-01-01"
     * @param endDate   结束日期 "2019-12-31"
     * @return 返回结果
     */
    public static LinkedHashMap<String, Object> getWeekOfMonthTwoDate(String beginDate, String endDate) {
        List<String> dayList = returnTwoDateDayList(beginDate, endDate);
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        Calendar calendar = Calendar.getInstance();
        dayList.forEach(d -> {
            Date date = parse(d, DATE_PATTERN);
            calendar.setTime(date);
            map.put(d, calendar.get(Calendar.WEEK_OF_MONTH));
        });
        return map;
    }

    /**
     * 获取两个日期之间的月周次
     *
     * @param beginDate 开始日期 "2019-01-01"
     * @param endDate   结束日期 "2019-12-31"
     * @return 返回结果
     */
    public static List<LinkedHashMap<String, Object>> getWeeksOfTwoDate(String beginDate, String endDate) {
        return getWeeksOfTwoDateByScale(beginDate, endDate, 4);
    }

    /**
     * 按照指定的比例计算两个日期之间的月周次
     *
     * @param beginDate 开始日期 "2019-01-01"
     * @param endDate   结束日期 "2019-12-31"
     * @param scale     周黄金比例
     * @return 返回结果
     */
    public static List<LinkedHashMap<String, Object>> getWeeksOfTwoDateByScale(String beginDate, String endDate, int scale) {
        List<String> dayList = returnTwoDateDayList(beginDate, endDate);
        List<LinkedHashMap<String, Object>> list = new ArrayList<>();

        LinkedHashMap<LocalDate, Object> weekOfMonth = new LinkedHashMap<>();
        dayList.forEach(d -> {
            LinkedHashMap<String, Object> map = new LinkedHashMap<>();

            LocalDate localDate = LocalDate.parse(d);
            map.put("date", d);
            map.put("year", localDate.get(ChronoField.YEAR));
            //本月首日
            LocalDate firstDayOfThisMonth = localDate.withDayOfMonth(1);
            //本月第一天对应周几
            int i1 = firstDayOfThisMonth.get(ChronoField.DAY_OF_WEEK);
            //本月的末日
            LocalDate lastDayOfThisMonth = localDate.with(TemporalAdjusters.lastDayOfMonth());
            //本月的末日对应星期几
            int i2 = lastDayOfThisMonth.get(ChronoField.DAY_OF_WEEK);

            Calendar calendar = Calendar.getInstance();
            calendar.setFirstDayOfWeek(Calendar.MONDAY);
            calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
            Date date = parse(d, DATE_PATTERN);
            calendar.setTime(date);
            //当月当前周次
            int weeks = calendar.get(Calendar.WEEK_OF_MONTH);
            int actualWeeks = calendar.getActualMaximum(Calendar.WEEK_OF_MONTH);
            //当月共有多少周
            weekOfMonth.put(localDate, actualWeeks);

            if (i1 >= 1 && i1 <= scale) {
                if (weeks <= scale) {
                    map.put("month", localDate.get(ChronoField.MONTH_OF_YEAR));
                    map.put("weeks", weeks);
                } else {
                    if (i2 >= scale) {
                        map.put("month", localDate.get(ChronoField.MONTH_OF_YEAR));
                        map.put("weeks", weeks);
                        //map.put("日期：" + d, "对应月份：" + localDate.get(ChronoField.MONTH_OF_YEAR) + ",对应周次：" + weeks);
                    } else {
                        //下个月
                        LocalDate nextMonth = localDate.plusMonths(1);
                        map.put("month", nextMonth.get(ChronoField.MONTH_OF_YEAR));
                        map.put("weeks", 1);
                        if (nextMonth.get(ChronoField.MONTH_OF_YEAR) == 1) {
                            map.put("year", localDate.plusYears(1).get(ChronoField.YEAR));
                        }
                    }
                }
            } else {
                if (weeks == 1) {
                    //上个月
                    LocalDate lastMonth = localDate.minusMonths(1);
                    map.put("month", lastMonth.get(ChronoField.MONTH_OF_YEAR));
                    //TODO 这里有个问题,如果传入日期集合第一个日期走这里。那么weekOfMonth集合中无法获取上个月共有多少周
                    map.put("weeks", weekOfMonth.get(lastMonth));
                    if (lastMonth.get(ChronoField.MONTH_OF_YEAR) == 12) {
                        map.put("year", localDate.plusYears(-1).get(ChronoField.YEAR));
                    }
                    //当月共有周次需减一周
                    weekOfMonth.put(localDate, actualWeeks - 1);
                } else if (weeks >= 2 && weeks <= 5) {
                    map.put("month", localDate.get(ChronoField.MONTH_OF_YEAR));
                    map.put("weeks", (weeks - 1));
                } else {
                    //下个月
                    LocalDate nextMonth = localDate.plusMonths(1);
                    map.put("month", nextMonth.get(ChronoField.MONTH_OF_YEAR));
                    map.put("weeks", 1);
                    if (nextMonth.get(ChronoField.MONTH_OF_YEAR) == 1) {
                        map.put("year", localDate.plusYears(1).get(ChronoField.YEAR));
                    }
                    //当月共有周次需再减一周
                    weekOfMonth.put(localDate, actualWeeks - 1);
                }
            }
            list.add(map);
        });
        return list;
    }

    /**
     * 获取几个小时前的时间
     */
    public static String getPreHoursAgoTime(Integer preHour) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR, Calendar.HOUR - preHour);
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cal.getTime());
    }

    public static void main(String[] args) throws ParseException {

       /* LocalDate now = LocalDate.now();
        int i = now.get(ChronoField.YEAR);*/
        String begin = 2021 + "-01-01";
        String end = 2021 + "-12-31";

        System.out.println("begin:" + begin + "==end:" + end);
        List<LinkedHashMap<String, Object>> weeksOfTwoDate = getWeeksOfTwoDate(begin, end);
        weeksOfTwoDate.forEach(x -> {
            Set<Map.Entry<String, Object>> entries = x.entrySet();
            Iterator<Map.Entry<String, Object>> iterator = entries.iterator();
            StringBuilder sb = new StringBuilder();
            while (iterator.hasNext()) {
                sb.append(iterator.next()).append(",");
            }
            System.out.println(sb.toString());
        });
    }

}
