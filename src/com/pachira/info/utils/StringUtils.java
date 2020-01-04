package com.pachira.info.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

import com.pachira.info.entity.RecogniItem;

/**
 * @author: 王添宝
 * @date 2019-11-13 15:33
 */
public class StringUtils {
    public static char[] numSource = new char[] {
            '零','幺','一','二','三','四','五','六','七','八','九'
    };
    public static char[] numTarget = new char[] {
            '0','1','1','2','3','4','5','6','7','8','9'
    };

    public static String transStr(String text) {
        char[] chs = text.toCharArray();
        int j = 0;
        for (int i = 0; i < chs.length; i++) {
            int findIndex = indexOf(numSource, chs[i]);
            if (findIndex == -1) {
                chs[j] = chs[i];
                j++;
            } else {
                if (numTarget[findIndex] != ' ') {
                    chs[j] = numTarget[findIndex];
                    j++;
                }
            }
        }
        return new String(chs, 0, j);
    }
    public static int indexOf(char[] chs, char target) {
        for (int i = 0; i < chs.length; i++) {
            if (target == chs[i]) {
                return i;
            }
        }
        return -1;
    }

    public static List<Integer> indexOf(String source, String target) {
        List<Integer> res = new ArrayList<>();
        int index = 0;
        int newStart = 0;
        while((index = source.indexOf(target)) != -1) {
            res.add(newStart + index);
            newStart = newStart + index + target.length();
            int startIndex = index + target.length();
            if (startIndex >= source.length()) {
                break;
            }
            source = source.substring(startIndex);
        }
        return res;
    }
    
    public static Object[] indexOf(Matcher m) {
    	List<Integer> indexes = new ArrayList<Integer>();
    	List<String> targets = new ArrayList<String>();
    	while(m.find()) {
    		String target = m.group();
    		int index = m.start();
    		targets.add(target);
    		indexes.add(index);
    	}
    	return new List[] {indexes, targets};
    }
    
    public static int getRealIndex(String source, int index) {
    	for(int i=0;i<source.length();i++) {
    		char ch = source.charAt(i);
    		if(ch == '，' || ch == '。' || ch == '！' || ch == '？') {
    			continue;
    		}
    		if(index == i) {
    			return i;
    		}
    	}
    	return index;
    }
    
    public static List<Integer> getRealIndex(String source, List<Integer> indexes) {
    	List<Integer> res = new ArrayList<Integer>();
    	if(indexes.isEmpty()) {
    		return res;
    	}
    	int k = 0;
    	int m = 0;
    	for(int i=0;i<source.length();i++) {
    		char ch = source.charAt(i);
    		if(ch == '，' || ch == '。' || ch == '！' || ch == '？') {
    			continue;
    		}
    		if(indexes.get(k).intValue() == m) {
    			res.add(i);
    			k++;
    			if(k == indexes.size()) {
    				return res;
    			}
    		}
    		m++;
    	}
    	return res;
    }
    
    public static int getEndIndex(String source, int startIndex, int matchLen) {
    	for(; startIndex< source.length() && matchLen>0; startIndex++) {
    		char ch = source.charAt(startIndex);
    		if(ch == '，' || ch == '。' || ch == '！' || ch == '？') {
    			continue;
    		}
    		matchLen--;
    	}
    	return startIndex;
    }
    
    public static Object[] findItemByWordsIndex(int left, List<RecogniItem> items, int wIndex) {
    	int right = items.size() - 1;
    	int mid = 0;
    	while(left <= right) {
    		mid = (left + right) / 2;
    		RecogniItem item = items.get(mid);
    		int[] wordsIndex = item.getWordsIndex();
    		if (wIndex < wordsIndex[0]) {
    			right = mid - 1;
    		} else if(wIndex > wordsIndex[1]) {
    			left = mid + 1;
    		} else {
    			return new Object[] {mid, item, wIndex-wordsIndex[0]};
    		}
    	}
    	return null;
    	
    }
    

}
