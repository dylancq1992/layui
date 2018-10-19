package com.vo;

import java.util.List;

public class ChartBarVO {
	
	private List<String> categories; // 图表横坐标显示的内容
	private List<Integer> data; // 数据
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public List<Integer> getData() {
		return data;
	}
	public void setData(List<Integer> data) {
		this.data = data;
	}
	
	

}
