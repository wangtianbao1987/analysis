package com.pachira.info.entity;

import java.util.Arrays;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: 王添宝
 * @date 2019-11-13 15:04
 */
@Getter
@Setter
public class RecogniItem {
	// 包含标点的索引
	private int[] wordsIndex;
	private int beginTime;
	private String text;
	private String roleName;
	private RecognizeText rtext;
	@Override
	public String toString() {
		return "RecogniItem [wordsIndex=" + Arrays.toString(wordsIndex) + ", beginTime=" + beginTime + ", text=" + text
				+ ", roleName=" + roleName + "]";
	}
}
