package com.etrans.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** 
 * ���ڹ�����
 * 
 * @author Pomelo(����.)  
 * @version 1.0
 * @since ����ʱ�䣺2013-7-3 ����3:40:14 
 */
public class DateUtil {
	
	/**
	 * �Ƚϵ�һ�������Ƿ���ڵڶ�������
	 * @param firstDateString
	 * @param secondDateString
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static boolean compareTwoDate(String firstDateString,String secondDateString,String format) throws ParseException{
		Date firstDate = convertStringToDate(format,firstDateString);
		Date secondDate=convertStringToDate(format,secondDateString);
		boolean flag=firstDate.after(secondDate);
		return flag;
	}
	
    /**
     * �˷�����������ʱ��ָ���ĸ�ʽ�����ַ���������һ�����ڶ���
     *
     * @param aMask   ���ڸ�ʽ
     * @param strDate ���ڵ��ַ�����ʾ
     * @return һ��ת��������ڶ���
     * @throws java.text.ParseException ���ַ���������ָ���ĸ�ʽʱ
     * @see java.text.SimpleDateFormat
     */
    public static Date convertStringToDate(String aMask, String strDate)
            throws ParseException {
        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);
        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }
        return (date);
    }

    /**
     * 
     * 
     * @param str
     * @return
     * @throws Exception
     */
	public static String formatStr2Date(String str) throws Exception{
		StringBuffer date = new StringBuffer("");
		date.append(str.substring(0,4)+"-")
			.append(str.substring(4,6)+"-")
			.append(str.substring(6,8)+" ")
			.append(str.substring(8,10)+":")
			.append(str.substring(10,12)+":")
			.append(str.substring(12,14));
		return date.toString();
	}
}

