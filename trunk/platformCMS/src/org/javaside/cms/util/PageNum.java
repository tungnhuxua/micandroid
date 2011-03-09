package org.javaside.cms.util;

import java.util.ArrayList;
import java.util.List;

public class PageNum {

	public PageNum() {

	}

	/**
	 * 分页页码
	 * 
	 * @param pageNum
	 *            -总页数
	 * @param pageNo
	 *            -当前页码
	 * @return
	 */
	public static List<Integer> getPageList(int pageNum, int pageNo) {
		//	long start = System.currentTimeMillis();
		if (pageNum < 1)
			return null;
		try {
			List<Integer> pageList = new ArrayList<Integer>(15);
			for (int i = 1; i <= 15; i++) {
				Integer midInt = new Integer(i);
				pageList.add(midInt);
			}
			if (pageNum < 15) {
				for (int i = pageNum; i < 15; i++) {
					pageList.remove(pageNum);
				}
				return pageList;
			}
			if (pageNo > 7) {
				pageList.remove(2);
				pageList.add(2, new Integer(-1));
			}
			if ((pageNum - pageNo) > 6) {
				pageList.remove(12);
				pageList.add(12, new Integer(-1));
				pageList.remove(13);
				pageList.add(13, new Integer(pageNum - 1));
				pageList.remove(14);
				pageList.add(14, new Integer(pageNum));
			}
			if (pageNo > 7 && (pageNum - pageNo) > 6) {
				int tmp = pageNo - 4;
				int rtmp = 3;
				for (int i = tmp; i < pageNo + 5; i++) {
					pageList.remove(rtmp);
					pageList.add(rtmp, new Integer(i));
					rtmp++;
				}
			}
			if (pageNo <= 7) {
				int tmp = pageNo + 4;
				for (int i = tmp; i < 12 && pageList.size() > tmp; i++) {
					pageList.remove(tmp);
				}
			}

			if (pageNum - pageNo <= 6) {
				int tmp = pageNo - 4;
				int rtmp = 3;
				for (int i = 15; i > 3 && pageList.size() > rtmp; i--) {
					pageList.remove(rtmp);
				}
				for (int i = tmp; i <= pageNum; i++) {
					pageList.add(rtmp, new Integer(i));
					rtmp++;
				}
			}
			//		long end = System.currentTimeMillis();
			//		System.out.println("pageList => "+(end-start));
			return pageList;
		} catch (Exception e) {
			System.out.print("分页错误");
			System.out.print(e);
			return null;
		}
	}
}