package org.wangqian.introduction.tools.utils.tree;


public class Node {

    //节点编号
    public String id;

    //父节点编号
    public String parentId;

    //页面路径
    public String index;

    //页面名称
    public String title;

    //页面备注
    public String remark;

    private Children children = new Children();


    // 先序遍历，拼接JSON字符串
    @Override
    public String toString() {
        String result = "{"
                + " 'id' : '" + id + "'"
                + ", 'index' : '" + index + "'"
                + ", 'title' : '" + title + "'"
                + ", 'remark' : '" + remark + "'";

        if (children != null && children.getSize() != 0) {
            result += ", 'subs' : " + children.toString();
        } else {
            result += ", 'leaf' : true";
        }

        return result + "}";
    }

    // 兄弟节点横向排序
    public void sortChildren() {
        if (children != null && children.getSize() != 0) {
            children.sortChildren();
        }
    }

    // 添加孩子节点
    public void addChild(Node node) {
        this.children.addChild(node);
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

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Children getChildren() {
        return children;
    }

    public void setChildren(Children children) {
        this.children = children;
    }
}
