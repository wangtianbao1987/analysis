package com.pachira.info.utils;

import java.util.List;

import com.pachira.info.entity.FindObj;
import com.pachira.info.entity.RecogniItem;
import com.pachira.info.entity.RecognizeText;

public class FindObjUtils {
	public static void addFindObj(RecognizeText rtext, String type, String typeName, List<RecogniItem> items, String source, List<FindObj> finds, List<Integer> indexes, List<String> targets) {
		if(indexes.isEmpty()) {
			return;
		}
		List<Integer> realIndex = StringUtils.getRealIndex(source, indexes);
		
		for(int i=0;i<realIndex.size();i++) {
			int stIdx = realIndex.get(i);
			String target = targets.get(i);
			int edIdx = StringUtils.getEndIndex(source, stIdx, target.length());
			Object[] objs = StringUtils.findItemByWordsIndex(0, items, edIdx);
			RecogniItem startItem = (RecogniItem)objs[1];
			int startIndex = (int)objs[2];
			objs = StringUtils.findItemByWordsIndex((int)objs[0], items, stIdx);
			RecogniItem endItem = (RecogniItem)objs[1];
			int endIndex = (int)objs[2];
			FindObj find = new FindObj();
			find.setRtext(rtext);
			find.setType(type);
			find.setTypeName(typeName);
			find.setStartItem(startItem);
			find.setStartIndex(startIndex);
			find.setEndItem(endItem);
			find.setEndIndex(endIndex);
			finds.add(find);
		}
	}
	
	
}
