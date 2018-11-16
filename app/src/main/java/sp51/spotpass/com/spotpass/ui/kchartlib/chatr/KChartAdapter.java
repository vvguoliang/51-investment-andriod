package sp51.spotpass.com.spotpass.ui.kchartlib.chatr;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import sp51.spotpass.com.spotpass.ui.kchartlib.BaseKChartAdapter;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.DateUtil;

/**
 * 数据适配器
 * Created by tifezh on 2016/6/18.
 */

@SuppressWarnings("deprecation")
public class KChartAdapter extends BaseKChartAdapter<KLineEntity> {

    @Override
    public Date getDate(int position) {
        try {
            String s = getItem(position).Date;
            Date date = new Date(Long.parseLong(s));
//            switch (getIstime(s)) {
//                case "yyyyMMddHHmmss":
//                    date.setYear(Integer.parseInt(s.substring(0, 4)) - 1900);
//                    date.setMonth(Integer.parseInt(s.substring(4, 6)) - 1);
//                    date.setDate(Integer.parseInt(s.substring(6, 8)));
//                    date.setHours(Integer.parseInt(s.substring(8, 10)));
//                    date.setMinutes(Integer.parseInt(s.substring(10, 12)));
//                    date.setSeconds(Integer.parseInt(s.substring(12, s.length())));
//                    break;
//                case "yyyyMMdd":
//                    date.setYear(Integer.parseInt(s.substring(0, 4)) - 1900);
//                    date.setMonth(Integer.parseInt(s.substring(4, 6)) - 1);
//                    date.setDate(Integer.parseInt(s.substring(6, 8)));
//                    break;
//                case "yyyy-MM-dd HH:mm:ss":
//                    date.setYear(Integer.parseInt(s.substring(0, 4)) - 1900);
//                    date.setMonth(Integer.parseInt(s.substring(5, 7)) - 1);
//                    date.setDate(Integer.parseInt(s.substring(8, 10)));
//                    date.setHours(Integer.parseInt(s.substring(11, 13)));
//                    date.setMinutes(Integer.parseInt(s.substring(14, 16)));
//                    date.setSeconds(Integer.parseInt(s.substring(17, s.length())));
//                    break;
//                case "yyyy-MM-dd":
//                    date.setYear(Integer.parseInt(s.substring(0, 4)) - 1900);
//                    date.setMonth(Integer.parseInt(s.substring(5, 7)) - 1);
//                    date.setDate(Integer.parseInt(s.substring(8, 10)));
//                    break;
//                case "yyyy-MM-dd HH:mm":
//                    date.setYear(Integer.parseInt(s.substring(0, 4)) - 1900);
//                    date.setMonth(Integer.parseInt(s.substring(5, 7)) - 1);
//                    date.setDate(Integer.parseInt(s.substring(8, 10)));
//                    date.setHours(Integer.parseInt(s.substring(11, 13)));
//                    date.setMinutes(Integer.parseInt(s.substring(14, 16)));
//                    break;
//            }
            return date;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getIstime(String time) {
        String a1 = "[0-9]{4}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}[0-9]{2}";//yyyyMMddHHmmss
        String a2 = "[0-9]{4}[0-9]{2}[0-9]{2}";//yyyyMMdd
        String a3 = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}:[0-9]{2}";//yyyy-MM-dd HH:mm:ss
        String a4 = "[0-9]{4}-[0-9]{2}-[0-9]{2}";//yyyy-MM-dd
        String a5 = "[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}";//yyyy-MM-dd  HH:mm
        time = time.trim();
        boolean datea1 = Pattern.compile(a1).matcher(time).matches();
        if (datea1) {
            return "yyyyMMddHHmmss";
        }
        boolean datea2 = Pattern.compile(a2).matcher(time).matches();
        if (datea2) {
            return "yyyyMMdd";
        }
        boolean datea3 = Pattern.compile(a3).matcher(time).matches();
        if (datea3) {
            return "yyyy-MM-dd HH:mm:ss";
        }
        boolean datea4 = Pattern.compile(a4).matcher(time).matches();
        if (datea4) {
            return "yyyy-MM-dd";
        }
        boolean datea5 = Pattern.compile(a5).matcher(time).matches();
        if (datea5) {
            return "yyyy-MM-dd HH:mm";
        }
        return "";
    }

}
