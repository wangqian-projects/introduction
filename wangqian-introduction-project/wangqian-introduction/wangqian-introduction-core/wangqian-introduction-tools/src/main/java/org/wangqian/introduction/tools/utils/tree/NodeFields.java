package org.wangqian.introduction.tools.utils.tree;

/**
 * 节点属性
 *
 * @author 卢兴旺
 * @date 2019-09-27
 */
public class NodeFields {

    /**
     * 节点ID
     */
    private String id;

    /**
     * 父节点ID
     */
    public String parentId;

    /**
     * 路径
     */
    private String index;

    /**
     * 标题
     */
    private String title;

    /**
     * 备注
     */
    private String remark;


    /**
     * 构造器
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     */
    public NodeFields(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
    }

    /**
     * 构造器
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     */
    public NodeFields(String id, String parentId, String title) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
    }

    /**
     * 构造器
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     * @param remark   备注
     */
    public NodeFields(String id, String parentId, String title, String remark) {
        this.id = id;
        this.parentId = parentId;
        this.title = title;
        this.remark = remark;
    }

    /**
     * 创建
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     */
    public static NodeFields build(String id, String parentId) {
        return new NodeFields(id, parentId);
    }

    /**
     * 创建
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     */
    public static NodeFields build(String id, String parentId, String title) {
        return new NodeFields(id, parentId, title);
    }

    /**
     * 创建
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     * @param remark   备注
     */
    public static NodeFields build(String id, String parentId, String title, String remark) {
        return new NodeFields(id, parentId, title, remark);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "NodeFields{" +
                "id='" + id + '\'' +
                ", parentId='" + parentId + '\'' +
                ", index='" + index + '\'' +
                ", title='" + title + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
