package com.shuyao.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
	/** 时间格式(yyyy-MM)*/
    public final static String DATE_MONTH_PATTERN = "yyyy-MM";
	public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }
    
    /** 
     *  
     * @param minDate 最小时间  2015-01 
     * @param maxDate 最大时间 2015-10 
     * @return 日期集合 格式为 年-月 
     * @throws Exception 
     */  
    public static List<String> getMonthBetween(String minDate, String maxDate) {  
        ArrayList<String> result = new ArrayList<String>();  
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_MONTH_PATTERN);//格式化为年月  
  
        Calendar min = Calendar.getInstance();  
        Calendar max = Calendar.getInstance();  
  
        try {
	        	if (StringUtils.isBlank(maxDate)) {
	        		maxDate = sdf.format(new Date());
	        	}
            max.setTime(sdf.parse(maxDate));  
            max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
        	    if (StringUtils.isBlank(minDate)) {
        	    	min.setTime(sdf.parse(maxDate));
        	     	min.add(Calendar.MONTH, -24);
        	    } else {
        	      	min.setTime(sdf.parse(minDate));
        			min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);  
        	    } 
        } catch (ParseException e) {
			return null;
		}  
        Calendar curr = min;  
        while (curr.before(max)) {  
         result.add(sdf.format(curr.getTime()));  
         curr.add(Calendar.MONTH, 1);  
        }  
  
        return result;  
    }
}
