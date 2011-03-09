package cn.mmbook.platform.model.tag;

import java.io.Serializable;

public class JSONTreeCheckedNode implements Serializable {
	
	private String id;          //节点主键
	private String text;        //节点文本显示
	private String iconCls;     //图标	
	private boolean leaf;       //是否叶子
	private boolean singleClickExpand; //用单击文本展开,默认为双击
	private String arr;         //PID=0&id=1 格式 用于传递参数
	private boolean checked;    //是否被选中
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public boolean isSingleClickExpand() {
		return singleClickExpand;
	}
	public void setSingleClickExpand(boolean singleClickExpand) {
		this.singleClickExpand = singleClickExpand;
	}
	public String getArr() {
		return arr;
	}
	public void setArr(String arr) {
		this.arr = arr;
	}
	public boolean isChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
}