package com.vo;

import java.util.LinkedHashMap;
import java.util.List;

public class TreeNodeVo {

	private String id;                  //节点的 id  
    private String parentId;            //父节点id，java生成树时使用  
    private String text;                //显示的节点文字。  
    private String iconCls;             //节点图标样式    "iconCls":"icon-save",  "iconCls":"icon-ok", 等  
    private String state;               //节点状态， 'open' 或 'closed'，默认是 'open'。当设为 'closed' 时，此节点有子节点，并且将从远程站点加载它们。  
    private boolean checked;            //指示节点是否被选中。  
    private LinkedHashMap<?,?> attributes;    //给一个节点追加的自定义属性。  
    private List<TreeNodeVo> children;        //定义了一些子节点的节点数组。  
    private String url;//扩展属性url  
    private String code;
    private Integer priority;
    private String description;
    private String menutype;
      
    public TreeNodeVo()  
    {  
        //children = new ArrayList();  
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public LinkedHashMap<?, ?> getAttributes() {
		return attributes;
	}

	public void setAttributes(LinkedHashMap<?, ?> attributes) {
		this.attributes = attributes;
	}

	public List<TreeNodeVo> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNodeVo> children) {
		this.children = children;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMenutype() {
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}  
    
	
    
}
