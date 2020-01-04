package com.wtb.test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.pachira.info.analysis.SingleFileAnalysis;
import com.pachira.info.entity.RecognizeText;
import com.pachira.info.utils.ReadXML;
import com.pachira.info.utils.StringUtils;

public class Test01 {
	@Test
	public void test010() {
		List<String> list = new ArrayList<String>();
		list.add("11");
		list.add("22");
		list.add("33");
		list.add("44");
		list.add("55");
		list.add("66");
		list.add("77");
		list.add("88");
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			String str = it.next();
			if("44".equals(str)) {
				it.remove();
			}
		}
		System.out.println(list);
	}
	
	@Test
	public void test011() {
		RecognizeText rtext = ReadXML.readXML(new File("E:\\测试数据\\200files\\20191022030744_22802642_013400192996_1385000.wav.xml"));
		System.out.println(rtext);
	}
	
	@Test
	public void test012() {
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher("你号234啊啊啊333发啊啊啊555那你那23456噢噢噢噢0876");
		Object[] res = StringUtils.indexOf(m);
		System.out.println(res[0]);
		System.out.println(res[1]);
	}
	
	
	@Test
	public void test013() {
		System.out.println(StringUtils.getEndIndex("你好发放，号。东！方？？红啊发红发暗示法", 3, 6));
	}
	
	@Test
	public void test014() {
		RecognizeText rtext = ReadXML.readXML(new File("E:\\测试数据\\200files\\20191022030744_22802642_013400192996_1385000.wav.xml"));
		SingleFileAnalysis sfa = new SingleFileAnalysis(rtext);
		sfa.regex("age", "年龄", "([0-9十两]+.{0,2}岁)|((?<=年龄.{0,2})[0-9十两]+(.?岁)?)");
		System.out.println(sfa.getFindList());
	}
	
	
}
