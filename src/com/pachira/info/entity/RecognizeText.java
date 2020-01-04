package com.pachira.info.entity;

import java.io.File;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: 王添宝
 * @date 2019-11-13 15:03
 */
@Getter
@Setter
public class RecognizeText {
	private File xmlFile;
	private List<RecogniItem> items;
	private Map<String,StringBuilder> speak;
	private Map<String,List<RecogniItem>> roleMap;
}
