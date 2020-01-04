package com.pachira.info.analysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.pachira.info.entity.FindObj;
import com.pachira.info.entity.RecogniItem;
import com.pachira.info.entity.RecognizeText;
import com.pachira.info.utils.FindObjUtils;
import com.pachira.info.utils.StringUtils;

public class SingleFileAnalysis {
	private RecognizeText rtext;
	private String role0;
	private String role1;
	
	public List<FindObj> findList = new ArrayList<FindObj>();
	
	public SingleFileAnalysis(RecognizeText rtext) {
		this.rtext = rtext;
		Map<String,StringBuilder> speak = rtext.getSpeak();
		String role0_1 = speak.get("R0").toString().replaceAll("[，。！？]", "");
		String role1_1 = speak.get("R1").toString().replaceAll("[，。！？]", "");
		this.role0 = StringUtils.transStr(role0_1);
		this.role1 = StringUtils.transStr(role1_1);
	}
	
	@SuppressWarnings("unchecked")
	public void regex(String type, String typeName, String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m0 = p.matcher(role0);
		Object[] res0 = StringUtils.indexOf(m0);
		List<Integer> indexes = (List<Integer>)res0[0];
		List<String> targets = (List<String>)res0[1];
		Map<String,StringBuilder> speak = rtext.getSpeak();
		Map<String,List<RecogniItem>> roleMap = rtext.getRoleMap();
		FindObjUtils.addFindObj(rtext, type, typeName, roleMap.get("R0"),speak.get("R0").toString(),findList, indexes, targets);
		Matcher m1 = p.matcher(role1);
		Object[] res1 = StringUtils.indexOf(m1);
		indexes = (List<Integer>)res1[0];
		targets = (List<String>)res1[1];
		FindObjUtils.addFindObj(rtext, type, typeName, roleMap.get("R1"), speak.get("R1").toString(),findList, indexes, targets);
	}
	
	public void str(String type, String typeName, String str) {
		String[] strs = 
	}
	
	
	
	
	
	
	public List<FindObj> getFindList() {
		return findList;
	}
	
	
}
