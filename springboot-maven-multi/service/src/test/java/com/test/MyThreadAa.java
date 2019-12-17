package com.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class MyThreadAa {
    public static void main(String[] args) {

        for (int i = 6; i >= 3; i -= 3) {
            Timestamp startTime = Timestamp.valueOf(LocalDateTime.now().minusMonths(i));
            Timestamp endTime = Timestamp.valueOf(LocalDateTime.now().minusMonths(i - 3));

            System.out.println("startTime = " + startTime + ", endTime = " + endTime);
        }

        // Calendar转为Date
        Date date = DateUtil.date(Calendar.getInstance());
        System.out.println(date);

        // long转为Date
        Date date2 = DateUtil.date(System.currentTimeMillis());
        System.out.println(date2);

        // Date转为Calendar
        Calendar calendar = DateUtil.calendar(date);
        System.out.println(calendar);

        // long转为Calendar
        Calendar calendar2 = DateUtil.calendar(System.currentTimeMillis());
        System.out.println(calendar2);

        Properties properties = new Properties();
        properties.setProperty("password", "123124");
        Console.log(properties);


        String s = "2019-12-07";
        DateTime dateTime = DateUtil.parse(s);
        String dateStr = DateUtil.format(dateTime, "yyyy/MM/dd");
        System.out.println(dateStr);
        String dateStr2 = DateUtil.formatDate(dateTime);
        System.out.println(dateStr2);
        String dateStr3 = DateUtil.formatDateTime(dateTime);
        System.out.println(dateStr3);
        String dateStr4 = DateUtil.formatTime(dateTime);
        System.out.println(dateStr4);

        System.out.println(BigDecimal.valueOf(0.1).add(BigDecimal.valueOf(0.2)).doubleValue());
        System.out.println(new BigDecimal(0.3).doubleValue());
        System.out.println(new BigDecimal(0.1).doubleValue());
        System.out.println(new BigDecimal("0.1").doubleValue());
        System.out.println(new BigDecimal(0.1).add(new BigDecimal(0.2)).doubleValue() == new BigDecimal(0.3).doubleValue());

        int i = -1;
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(i >> 30);
        System.out.println(i << 10);

        int a = -0x2f;//小写十六进制（等价于0x002f）
        System.out.println(Integer.toBinaryString(1111));
        System.out.println(setZero(1111));
        System.out.println(Integer.toBinaryString(getBitValue(10, 11)));

        convert("PAYPALISHIRING", 3);
    }

    public static int setZero(int num) {
        return num & 0xFF;
    }

    public static int getBitValue(int start, int offset) {
        int e = -1 >>> (32 - offset);
        int d = -1 << start;
        return e & d;
    }

    public static String convert(String s, int numRows) {
        StringBuilder sb = new StringBuilder();
        int k = 2 * numRows - 2;
        int cnt = 0;
        int len = s.length();
        for (int i = 0; i < numRows; i++) {
            int index = 1;
            int aaa = i + (index / 2) * k - cnt * ((index + 1) % 2);
            int bbb = 0;
            while (aaa < len) {
                sb.append(s.charAt(aaa));
                index++;
                bbb = i + (index / 2) * k - cnt * ((index + 1) % 2);
                if(bbb == aaa){
                    index++;
                    bbb = i + (index / 2) * k - cnt * ((index + 1) % 2);
                }
                aaa = bbb;
            }
            cnt += 2;
        }
        return sb.toString();
    }

}


