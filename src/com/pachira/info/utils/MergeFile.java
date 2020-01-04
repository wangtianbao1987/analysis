package com.pachira.info.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.pachira.info.entity.RecogniItem;
import com.pachira.info.entity.RecognizeText;

/**
 * @author: 王添宝
 * @date 2019-11-18 09:48
 */
public class MergeFile {
    public static void merge(RecognizeText rtext) {
    	// 先根据时间排序
    	List<RecogniItem> items = rtext.getItems();
    	for(int i = 0; i < items.size(); i++) {
    		for (int j = i + 1; j < items.size(); j++) {
    			if(items.get(i).getBeginTime() > items.get(j).getBeginTime()) {
    				RecogniItem temp = items.get(i);
    				items.set(i, items.get(j));
    				items.set(j, temp);
    			}
    		}
    	}
    	// 合并相同的角色
    	Iterator<RecogniItem> it = items.iterator();
    	RecogniItem item0 = it.next();
    	while(it.hasNext()) {
    		RecogniItem item = it.next();
    		if (item0.getRoleName().equals(item.getRoleName())) {
    			item0.setText(item0.getText() + item.getText());
    			it.remove();
    		}else {
    			item0 = item;
    		}
    	}
    	
    	Map<String,StringBuilder> speak = new HashMap<String,StringBuilder>();
    	Map<String,List<RecogniItem>> roleMap = new HashMap<String,List<RecogniItem>>();
    	Map<String,Integer> preIndexMap = new HashMap<String,Integer>();
    	
    	for(int i=0;i<items.size();i++) {
    		RecogniItem item = items.get(i);
    		String roleName = item.getRoleName();
    		List<RecogniItem> rItems = roleMap.get(roleName);
    		if(rItems == null) {
    			rItems = new ArrayList<RecogniItem>();
    			roleMap.put(roleName, rItems);
    			speak.put(roleName, new StringBuilder());
    			preIndexMap.put(roleName, 0);
    		}
    		rItems.add(item);
    		int preIndex = preIndexMap.get(roleName);
    		String text = item.getText();
    		speak.get(roleName).append(text);
    		int endIndex = preIndex + text.length();
    		item.setWordsIndex(new int[] {preIndex, endIndex});
    		preIndexMap.put(roleName, endIndex);
    	}
    	
    	rtext.setSpeak(speak);
    	rtext.setRoleMap(roleMap);
    	
    }
}
