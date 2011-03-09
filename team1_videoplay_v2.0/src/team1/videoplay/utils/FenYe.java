package team1.videoplay.utils;

import java.util.List;

public class FenYe {
	public static final int pageSize=8;//每页的对象数量
	private int page;//页码
	private int pagecount;//全部页数
	private List list;//返回的对象集合
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPagecount() {
		return pagecount;
	}
	public void setPagecount(int pagecount) {
		this.pagecount = pagecount;
	}
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}

	
}
