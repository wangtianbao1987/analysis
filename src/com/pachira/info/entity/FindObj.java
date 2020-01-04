package com.pachira.info.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: 王添宝
 * @date 2019-11-13 15:04
 */
@Getter
@Setter
public class FindObj implements Cloneable {
	private String type;
	private String typeName;
	private RecognizeText rtext;
	private RecogniItem startItem;
	private RecogniItem endItem;
	private int startIndex;
	private int endIndex;
	@Override
	public String toString() {
		return "FindObj [type=" + type + ", typeName=" + typeName + ", rtext=" + rtext + ", startItem=" + startItem
				+ ", endItem=" + endItem + ", startIndex=" + startIndex + ", endIndex=" + endIndex + "]\n";
	}
}
