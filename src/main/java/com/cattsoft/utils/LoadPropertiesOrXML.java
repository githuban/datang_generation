package com.cattsoft.utils;

/**
 * Description: <br>
 * Date: 2014-5-24 <br>
 * Copyright (c) 2014 CATTSoft <br>
 * 
 * @author zhoulei
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadPropertiesOrXML {
	private static String xmlPath = "/com/cattsoft/files/bing.xml";
	private static String proPath = "/com/cattsoft/files/bing.properties";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		LoadPropertiesOrXML.loadXml();
		LoadPropertiesOrXML.loadProperties();
		LoadPropertiesOrXML.storeToXml();

	}

	public static void loadXml() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = LoadPropertiesOrXML.class.getResourceAsStream(xmlPath);
			prop.loadFromXML(is);// 不能用prop.load(is);
			prop.list(System.out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void loadProperties() {
		Properties prop = new Properties();
		InputStream is = null;
		try {
			is = LoadPropertiesOrXML.class.getResourceAsStream(proPath);
			prop.load(is);
			prop.list(System.out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void storeToXml() {
		Properties prop = new Properties();
		prop.setProperty("one-two", "buckle my shoe");
		prop.setProperty("three-four", "shut the door");
		prop.setProperty("five-six", "pick up sticks");
		prop.setProperty("seven-eight", "lay them straight");
		prop.setProperty("nine-ten", "a big, fat hen");
		try {
			System.out.println(LoadPropertiesOrXML.class.getResource("").getPath().substring(1));
			FileOutputStream fos = new FileOutputStream(
					LoadPropertiesOrXML.class.getResource("").getPath().substring(1)+ "rhyme1.xml");
			prop.storeToXML(fos, "");
			prop.list(System.out);
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
