package com.utils;

import java.util.ArrayList;
import java.util.List;

public class GridModel {
	private List rows = new ArrayList();
	private Long total = Long.valueOf(0L);

	public List getRows() {
		return this.rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}
}
