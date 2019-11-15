package org.wangqian.introduction.tools.utils.tree;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * 逆向节点
 * 由子节点指向父节点
 *
 * @author 王骞
 * @date 2019-09-27
 */
public class ReverseNode extends NodeFields {

    /**
     * 父节点
     */
    private ReverseNode parent;

    /**
     * 构造器
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     */
    public ReverseNode(String id, String parentId) {
        super(id, parentId);
    }

    /**
     * 构造器
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     */
    public ReverseNode(String id, String parentId, String title) {
        super(id, parentId, title);
    }

    /**
     * 构造器
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     */
    public ReverseNode(String id, String parentId, String title, String remark) {
        super(id, parentId, title, remark);
    }

    /**
     * 构建逆向节点
     *
     * @param indexId  坐标节点ID
     * @param dataList 数据集
     * @param function 在数据集中采集返回 ReverseNode节点字段
     * @param <T>      数据集类型
     * @return
     */
    public static <T> ReverseNode build(String indexId, List<T> dataList, Function<T, ReverseNode> function) {
        if (CollectionUtil.isEmpty(dataList)) {
            return new ReverseNode(indexId, null);
        }
        List<ReverseNode> reverseNodes = new ArrayList<>();
        dataList.forEach(t ->
                reverseNodes.add(function.apply(t))
        );

        HashMap<String, ReverseNode> nodeMap = MapUtil.newHashMap(reverseNodes.size());
        for (ReverseNode reverseNode : reverseNodes) {
            nodeMap.put(reverseNode.getId(), reverseNode);
        }

        // 获取当前节点
        ReverseNode indexNode = nodeMap.get(indexId);
        if (indexNode == null) {
            return new ReverseNode(indexId, null);
        }

        // 组织父节点
        ReverseNode tempNode = indexNode;
        while (true) {
            String parentId = tempNode.getParentId();
            ReverseNode parentNode = nodeMap.get(parentId);
            if (parentNode == null) {
                break;
            }
            tempNode.setParent(parentNode);
            tempNode = parentNode;
        }
        return indexNode;
    }

    /**
     * 构建
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @return ReverseNode
     */
    public static ReverseNode build(String id, String parentId) {
        return new ReverseNode(id, parentId);
    }

    /**
     * 构建
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     * @return ReverseNode
     */
    public static ReverseNode build(String id, String parentId, String title) {
        return new ReverseNode(id, parentId, title);
    }

    /**
     * 构建
     *
     * @param id       节点ID
     * @param parentId 父节点ID
     * @param title    标题
     * @param remark   备注
     * @return ReverseNode
     */
    public static ReverseNode build(String id, String parentId, String title, String remark) {
        return new ReverseNode(id, parentId, title, remark);
    }

    /**
     * 节点执行函数, 以父父节点执行的结果优先
     * 委派父节点去执行函数, 如果父类函数返回值不等于null, 则返回最终结果
     *
     * @param function 函数
     * @return 结果
     */
    public Object doFunctionParentFirst(Function<ReverseNode, Object> function) {
        Object result = null;
        if (this.parent != null) {
            result = parent.doFunctionParentFirst(function);
        }
        if (result == null) {
            result = function.apply(this);
        }
        return result;
    }

    /**
     * 节点执行函数, 以当前节点执行的结果优先
     * 如果当前节点返回的结果为null, 则委派父节点重新执行函数
     *
     * @param function 函数
     * @return 结果
     */
    public Object doFunctionSubFirst(Function<ReverseNode, Object> function) {
        Object result = function.apply(this);
        if (result == null && this.parent != null) {
            result = parent.doFunctionSubFirst(function);
        }
        return result;
    }


    /**
     * 节点执行函数
     *  如果当前节点返回的FunctionResult为false, 则执行父节点函数
     *
     * @param function 函数
     * @return 结果
     */
    public FunctionResult doFunctionToParentIf(Function<ReverseNode, FunctionResult> function) {
        FunctionResult result = function.apply(this);
        if (!result.test && this.parent != null) {
            result = parent.doFunctionToParentIf(function);
        }
        return result;
    }

    /**
     * 函数返回结果
     *
     * @param <T> 结果类型
     */
    public static class FunctionResult<T> {

        /**
         * 是否为想要的结果
         */
        private boolean test;

        /**
         * 具体函数结果
         */
        private T result;

        public FunctionResult() {
        }

        public FunctionResult(boolean test, T result) {
            this.test = test;
            this.result = result;
        }

        public static <T> FunctionResult testTrue(T result) {
            return new FunctionResult(true, result);
        }

        public static <T> FunctionResult testFalse() {
            return new FunctionResult(false, null);
        }

        public static <T> FunctionResult test(boolean test, T result) {
            return new FunctionResult(test, result);
        }

        public boolean isTest() {
            return test;
        }

        public void setTest(boolean test) {
            this.test = test;
        }

        public T getResult() {
            return result;
        }

        public void setResult(T result) {
            this.result = result;
        }
    }

    public ReverseNode getParent() {
        return parent;
    }

    public void setParent(ReverseNode parent) {
        this.parent = parent;
    }
}
