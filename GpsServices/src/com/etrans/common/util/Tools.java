package com.etrans.common.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;

public class Tools {

	private static int num=0;
	
	/**
	 * ÿ̨�������������10��
	 */
	public static final int maxMessageNum = 5;
	
	/**
	 * ʵʱ����ҳ����ʾ����¼��
	 */
	public static final int realAlarmMaxCount = 50;

//��ڶ������͸���2
	public static Map<String,String> flatMap = new HashMap<String,String>(3);
//�·����Ķ������� ����3
	public static Map<String,String> flatPlatMap = new HashMap<String,String>(10);
//������Դ	
	public static Map<String,String> alarmSourceStrMap = new HashMap<String,String>(3);
///�������� ����1
	public static Map<String,String> alarmTypeMap = new HashMap<String,String>(14);
//���켶��	
	public static Map<String,String> overseeingMap = new HashMap<String,String>(2);
//��·״̬
	public static Map<String,String> linkStatusMap = new HashMap<String,String>(3);
//�ϼ��·�Ӧ����Ϣ���͸���4	
	public static Map<String,String> upAnswerMap = new HashMap<String,String>(3);
	
	static{
		//��ڶ������͸���2
		flatMap.put("1","��ǰ���ӵ��¼�ƽ̨");
		flatMap.put("2","�¼�ƽ̨������һҵ��");
		flatMap.put("3","�¼�ƽ̨��������ҵ��");
		
		//�·����Ķ������� ����3
		flatPlatMap.put("0","�¼�ƽ̨������һƽ̨");
		flatPlatMap.put("1","��ǰ���ӵ��¼�ƽ̨");
		flatPlatMap.put("2","�¼�ƽ̨������һҵ��");
		flatPlatMap.put("3","�¼�ƽ̨��������ҵ��");
		flatPlatMap.put("4","�¼�ƽ̨��������ƽ̨");
		flatPlatMap.put("5","�¼�ƽ̨��������ƽ̨��ҵ��");
		flatPlatMap.put("6","�¼�ƽ̨���������������ƽ̨������ضˣ�");
		flatPlatMap.put("7","�¼�ƽ̨����������ҵ���ƽ̨");
		flatPlatMap.put("8","�¼�ƽ̨�������о�Ӫ����ҵ���ƽ̨");
		flatPlatMap.put("9","�¼�ƽ̨�������зǾ�Ӫ����ҵ���ƽ̨");

		alarmSourceStrMap.put("1","�����ն�");
		alarmSourceStrMap.put("2","��ҵ���ƽ̨");
		alarmSourceStrMap.put("3","�������ƽ̨");
		alarmSourceStrMap.put("9","����");
		
		//�������� ����1
		alarmTypeMap.put("1","���ٱ���");
		alarmTypeMap.put("2","ƣ�ͼ�ʻ����");
		alarmTypeMap.put("3","��������");
		alarmTypeMap.put("4","����ָ�����򱨾�");
		alarmTypeMap.put("5","�뿪ָ�����򱨾�");
		alarmTypeMap.put("6","·�ζ�������");
		alarmTypeMap.put("7","Σ��·�α���");
		alarmTypeMap.put("8","Խ�籨��");
		alarmTypeMap.put("9","����");
		alarmTypeMap.put("10","�پ�");
		alarmTypeMap.put("11","ƫ��·�߱���");
		alarmTypeMap.put("12","�����ƶ�����");
		alarmTypeMap.put("13","��ʱ��ʻ����");
		alarmTypeMap.put("255","��������");
		
		//�����������
		overseeingMap.put("0","����");
		overseeingMap.put("1","һ��");
		
		//��·״̬
		linkStatusMap.put("0","���ӶϿ�");
		linkStatusMap.put("1","��¼�ɹ�");
		linkStatusMap.put("2","��·����δ��¼");
		
		//�ϼ��·�Ӧ����Ϣ���͸���4
		upAnswerMap.put("37385","����������λ��ϢӦ��");
		upAnswerMap.put("37383","���뽻��ָ��������λ��ϢӦ��");
		upAnswerMap.put("37384","ȡ������ָ��������λ��ϢӦ��");
	}
	
	public static String getTermianlState(String gpsInfoMessageState, String userTerminalState) {
		Map<Integer, String> terminalStateMap = new HashMap<Integer, String>();
		// ��ʼ���ն�״̬
		terminalStateMap.put(0, "GPS��λ|GPSδ��λ");
		terminalStateMap.put(1, "��γ|��γ");
		terminalStateMap.put(2, "����|����");
		terminalStateMap.put(3, "��Ӫ״̬|ͣ��״̬");
		terminalStateMap.put(4, "δԤԼ|ԤԼ");
		terminalStateMap.put(8, "ACC��|ACC��");
		terminalStateMap.put(9, "�ճ�|�س�");
		terminalStateMap.put(10, "��·����|��·�Ͽ�");
		terminalStateMap.put(11, "��·����|��·�Ͽ�");
		terminalStateMap.put(12, "���Ž���|���ż���");

		String stateStr = "";
		// �����ն�״̬
		String termainlStateStr = Long.toBinaryString(Long.parseLong(Long.valueOf(gpsInfoMessageState, 16).toString()));
		char[] terminalStateArray = ("00000000000000000000000000000000".substring(0, (32 - termainlStateStr.length())) + termainlStateStr).substring(19, 32).toCharArray();

		for (int s = 0; s < terminalStateArray.length; s++) {
			if (null != terminalStateMap.get(12 - s)) {
				if (userTerminalState.contains("|" + (12 - s) + "=" + String.valueOf(terminalStateArray[s]) + "|")) { // �ж��û��Ƿ��д��ն�״̬
					stateStr += (String.valueOf(terminalStateMap.get(12 - s)).split("\\|")[Integer.parseInt(String.valueOf(terminalStateArray[s]))]) + (s == 12 ? "" : ",");
				}
			}
		}
		return stateStr;
	}

 
	
	/**
	 * �����漴����
	 * 
	 * @param pwd_len ���ɵ�������ܳ���
	 * @return ������ַ���
	 */
	public static String genRandomNum(int pwd_len) {
		// 35����Ϊ�����Ǵ�0��ʼ�ģ�26����ĸ+10������
		final int maxNum = 10;
		int i; // ���ɵ������
		int count = 0; // ���ɵ�����ĳ���
		/*
		 * char[] str = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
		 * 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		 */

		char[] str = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

		StringBuffer pwd = new StringBuffer("");
		Random r = new Random();
		while (count < pwd_len) {
			// �����������ȡ����ֵ����ֹ���ɸ�����

			i = Math.abs(r.nextInt(maxNum)); // ���ɵ������Ϊ36-1

			if (i >= 0 && i < str.length) {
				pwd.append(str[i]);
				count++;
			}
		}

		return pwd.toString();
	}

	
	public static long genRandomLongNum()
	{
	 Random r = new Random(999999);
	 
	 return r.nextLong();
	}
	
	 
	 
	/**
	 * ȡ���ļ���׺
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(pos + 1, fileName.length());
	}

	/**
	 * ȡ���ļ�ǰ׺
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getPrefix(String fileName) {
		int pos = fileName.lastIndexOf(".");
		return fileName.substring(0, pos == -1 ? fileName.length() : pos);
	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static String getFileContext(String filePath) throws IOException {
		StringBuffer stringBuffer = new StringBuffer();
		File file = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			String line;// ��������ÿ�ж�ȡ������
			file = new File(filePath);
			if (!file.exists()) {
				return null;
			}
			inputStreamReader = new InputStreamReader(new FileInputStream(file), "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			line = reader.readLine();// ��ȡ��һ��
			while (line != null) {// ��� line Ϊ��˵��������
				stringBuffer.append(line);// ��������������ӵ� buffer��
				stringBuffer.append("<br>");// ��ӻ��з�
				line = reader.readLine();// ��ȡ��һ��
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
		}
		return stringBuffer.toString();
	}

	/**
	 * �ļ�������
	 * 
	 * @param srcPath
	 * @param diskPath
	 * @throws IOException
	 */
	public static void rename(String srcPath, String diskPath) throws IOException {
		File file = new File(srcPath);
		if (file.exists()) {
			file.renameTo(new File(diskPath));// ����
		}
	}

	/**
	 * ɾ���ļ�
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void deleteFile(String filePath) throws IOException {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();// ɾ���ļ�
		}
	}

	/**
	 * �����ļ�
	 * 
	 * @param filePath
	 * @param fileName
	 * @throws IOException
	 */
	public static void createFile(String filePath, String fileName) throws IOException {
		File file = new File(filePath + "/" + fileName);
		if (!file.exists()) {
			file.createNewFile();
		} 
	}

	/**
	 * �����ļ�����
	 * 
	 * @param filePath
	 * @param fileName
	 * @param list
	 * @param flag ---true ����initKeyҪ����,false����initKey��������
	 * @throws IOException
	 */
	public static void setBossFileContext(String filePath, String fileName, String context) throws IOException {
		File file = new File(filePath + "/" + fileName);
		// �ж��ļ��Ƿ����
		if (!file.exists()) {
			throw new IOException("file is not exists!");
		} else {
			// ����һ�����������
			BufferedWriter bufferedWriter = null;
			try {
				// �����ļ�����ַ���,�ڶ�����Ϊtrue�����ļ�׷����Ϣ����˼
				bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true)));
				bufferedWriter.write(context);
				// ����
				bufferedWriter.newLine();
				bufferedWriter.flush();
			} catch (IOException e) {
				throw e;
			} finally {
				try {
					if (bufferedWriter != null) {
						bufferedWriter.close();
					}
				} catch (IOException e) {
					throw e;
				}
			}
		}
	}

	/**
	 * ��ȡ�ļ�����
	 * 
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public static List<String> getBossFileContext(String filePath) throws IOException {
		List<String> list = new ArrayList<String>();
		File file = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			String line;// ��������ÿ�ж�ȡ������
			file = new File(filePath);
			if (!file.exists()) {
				return null;
			}
			inputStreamReader = new InputStreamReader(new FileInputStream(file));
			reader = new BufferedReader(inputStreamReader);
			line = reader.readLine();// ��ȡ��һ��
			while (line != null) {// ��� line Ϊ��˵��������
				list.add(line);// ��������������ӵ� buffer��
				line = reader.readLine();// ��ȡ��һ��
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (inputStreamReader != null) {
				inputStreamReader.close();
			}
		}
		return list;
	}
	
	/**
	 * ������ ȡ�ַ����ֽڵ�16����ASICC
	 * 
	 * @author lihaiyan
	 * @since Create on 2012-05-02
	 * @version Copyright (c) 2012 by e_trans.
	 */
  public  static String getByteHex(String str) {
		
		String result = "";
		String toASICCString=str;
		try {
			byte[] array = toASICCString.getBytes("GBK");

			for (byte y : array) {
				 String hex = Integer.toHexString(y & 0xFF);
				  if (hex.length() == 1) {
				        hex = '0' + hex;
				      }
				  result+=hex;
			}
		} catch (Exception e) {
		}

		return result;

	}

	/**
	 * �ַ���ת����ʮ�������ַ���
	 */
	public static String String2hex(String str) {
		byte[] b = null;
		if (str != null && !str.equals(""))
			b = str.getBytes();
		else
			return null;
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	/**
	 * ���ַ���ת���ɶ������ַ������Կո����
	 * 
	 * @param str ��ת�����ַ�
	 * @return String �Կո�����Ķ����ƴ�
	 * */
	public static String stringToBinary(String str) {
		char[] strChar = str.toCharArray();
		String result = "";
		for (int i = 0; i < strChar.length; i++) {

			result += Integer.toBinaryString(strChar[i]) + (i == strChar.length - 1 ? "" : " ");
		}
		return result;
	}

	/**
	 * ��������ת��Ϊ�ַ���
	 * 
	 * @param binary ��ת���Ķ����ƴ�
	 * @return String
	 * */
	public static String binaryToString(String binary) {
		StringBuffer suf = new StringBuffer();
		for (int b = 0; b < binary.split(" ").length; b++) {
			int[] temp = binaryToIntArray(binary.split(" ")[b]);
			int sum = 0;
			for (int i = 0; i < temp.length; i++) {
				sum += temp[temp.length - 1 - i] << i;
			}
			suf.append((char) sum);
		}
		return suf.toString();
	}

	/**
	 * ���������ַ���ת��Ϊchar
	 * @param binary ��ת���Ķ������ַ�
	 * @return char
	 * */
	public static char binaryToChar(String binary) {
		int[] temp = binaryToIntArray(binary);
		int sum = 0;
		for (int i = 0; i < temp.length; i++) {
			sum += temp[temp.length - 1 - i] << i;
		}
		return (char) sum;
	}

	/**
	 * ���������ַ���ת����int����
	 * 
	 * @param binary ��ת���Ķ������ַ�
	 * @return int[]
	 * */
	public static int[] binaryToIntArray(String binary) {
		char[] temp = binary.toCharArray();
		int[] result = new int[temp.length];
		for (int i = 0; i < temp.length; i++) {
			result[i] = temp[i] - 48;
		}
		return result;
	}

	/**
	 * �ֽ�����ת����ͼƬ�����
	 * @param bytes ��ת�����ֽ�����
	 * @param fileName ͼƬ���ʱ��ͼƬ����
	 * @param imageType ͼƬ��׺��
	 * @param filePath ͼƬ���·��
	 * @throws IOException 
	 * */
	public static void byteArrayToImage(byte[] bytes, String fileName, String imageType, String filePath) throws IOException {
		String file = filePath + fileName + "." + imageType;
		File f = new File(file);
		if(f.exists())f.delete();
		byte[] buf = bytes;
		FileOutputStream out=null;
		try {
			out = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			throw new FileNotFoundException("�ļ�û�ҵ�"+e.getMessage());
		}
		try {
			out.write(buf);
			out.close();
		} catch (IOException e) {
			throw new IOException("IO�쳣"+e.getMessage());
		}
		
	}

	/**
	 * ʮ�����ַ���ת�����ֽ�����
	 * @param hexString ��ת����ʮ�����ַ���
	 * @return byte����
	 * */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * �ַ�ת�ֽ�byte
	 * @param c ��ת�����ַ�
	 * @return byte
	 * */
	public static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

 
	
	/**
	 * �ַ���תASICC
	 * @param str
	 * @return
	 */
	public static String getASCII(String str)
	{
	 
	 if(StringUtils.isEmpty(str))
		 new RuntimeException("�ַ�������Ϊ��");
	 
	  String result = "";
		try 
		{
			byte[] array = str.getBytes("GBK");

			for (byte y : array) 
			{
				result += Integer.toHexString(y & 0xFF);
			}
		} 
		catch (Exception e) 
		{
		}
	 
	 return result;
	}
	
	/**
	 * ��ȡ������������Integer���ֵ���³�ʼ��Ϊ0
	 * @return
	 */
	public static Integer getNum()
	{
	 
	 if(num >= Integer.MAX_VALUE) num=0;
	
	 return num++;
	}
	
	
	
	public static Integer getRandomNum(int max)
	{
	 
	 Random random = new Random();
	 
	 return random.nextInt(max);
	 
	}
	
	/**
	 * 
	 * @param dateStr
	 * @return �Ƿ�������ʱ��
	 * @throws Exception
	 */
	public static  boolean isNew(String dateStr) throws Exception
	{
		
		Long d=Long.parseLong(dateStr);
		Long now =System.currentTimeMillis();
		if((now - d) <=(30*60*1000)) //30����            	//if((now - d) <=(60*5 *1000))
		{
			return true;
		}
		
		return false;
	}
 
	 
 
	public static SimpleDateFormat getSimpleDateFormat(String formatStr){
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		sdf.setTimeZone(timeZoneChina);
		return sdf;
	}
	private static TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");// ��ȡ�й���ʱ�� 
	
	/**
	 * ����ʱ���ʽ�ַ���ת��Ϊʱ�� yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 * @throws ParseException 
	 */
	public static Date strToDate(String strDate) throws ParseException {
		SimpleDateFormat formatter = getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date strtodate = formatter.parse(strDate);
		return strtodate;
	}
	
	/**
	 * ������ת�����ַ���
	 * 
	 * @param dateTime
	 * @return
	 */
	public static String convertDateToString(Date dateTime) {
		SimpleDateFormat df =getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(dateTime);
	}
	
	/**
	 * ���ظ�ʽ�������ַ���[yyyy-M-dd HH:mm:ss]
	 * @return
	 */
	public static String formatDate(Date date) throws Exception{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-M-dd HH:mm:ss");
		TimeZone timeZoneChina = TimeZone.getTimeZone("Asia/Shanghai");// ��ȡ�й���ʱ��
		sdf.setTimeZone(timeZoneChina);
		
		return sdf.format(date);
	}
	
 
 
	public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // Ŀ¼��ʱΪ�գ�����ɾ��
        return dir.delete();
    }
 
	public static String getNewArray(String[] oldAryStr,int star,int end,String replaceStrSplit){
		StringBuffer nStr = new StringBuffer("");
		for(int i=0;i<oldAryStr.length;i++){
			if(i>=star && i<end)nStr.append(oldAryStr[i]).append(replaceStrSplit);
		}
		String nStr$ = nStr.toString();
		return nStr$.substring(0,nStr$.length()-1);
	}
}
