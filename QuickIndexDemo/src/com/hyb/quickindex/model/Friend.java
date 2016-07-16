package com.hyb.quickindex.model;

import com.hyb.quickindex.utils.PinYinUtil;

public class Friend implements Comparable<Friend>{
	private String name;
	private String pinyin;
	public Friend() {
		// TODO Auto-generated constructor stub
	}
	public Friend(String name) {
		super();
		this.name = name;
		this.pinyin=PinYinUtil.getPinyin(name);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
		this.pinyin=PinYinUtil.getPinyin(name);
	}
	
	public String getPinyin() {
		return pinyin;
	}
	@Override
	public int compareTo(Friend another) {
		// TODO Auto-generated method stub
		
		return this.pinyin.compareTo(another.getPinyin());
	}
	
}
