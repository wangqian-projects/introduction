package org.wangqian.introduction.tools.utils.tree;

import com.alibaba.fastjson.JSONObject;
import org.wangqian.introduction.tools.utils.StringUtils;

import java.util.*;


public class MultipleTree {

    //获取树形json
    public static String getTreeJsonString(List dataList) {
        dataList.add(getInitMap());
        // 节点列表（散列表，用于临时存储节点对象）
        HashMap nodeList = new HashMap();
        // 根节点
        Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (Iterator it = dataList.iterator(); it.hasNext(); ) {
            Map dataRecord = (Map) it.next();
            Node node = new Node();
            node.id = (String) dataRecord.get("id");
            node.parentId = (String) dataRecord.get("parentId");
            node.index = (String) dataRecord.get("index");
            node.title = (String) dataRecord.get("title");
            node.remark = (String) dataRecord.get("remark");
            nodeList.put(node.id, node);
        }

        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            //树根
            if (node.parentId == null || node.parentId.equals("") || node.parentId.equals("-1")) {
                root = node;
            } else {
                ((Node) nodeList.get(node.parentId)).addChild(node);
            }
        }
        // 对多叉树进行横向排序
        root.sortChildren();
        // 输出有序的树形菜单的JSON字符串
        return root.toString();
    }

    /**
     * 获取树形json
     *
     * @param dataList
     * @return
     */
    public static JSONObject getTree(List dataList) {
        dataList.add(getInitMap());
        // 节点列表（散列表，用于临时存储节点对象）
        HashMap nodeList = new HashMap((int) (dataList.size() / 0.75 + 1));
        // 根节点
        Node root = null;
        // 根据结果集构造节点列表（存入散列表）
        for (Iterator it = dataList.iterator(); it.hasNext(); ) {
            Map dataRecord = (Map) it.next();
            Node node = new Node();
            node.id = (String) dataRecord.get("id");
            node.parentId = (String) dataRecord.get("parentId");
            node.index = StringUtils.objToStr(dataRecord.get("index"));
            node.title = StringUtils.objToStr(dataRecord.get("title"));
            node.remark = StringUtils.objToStr(dataRecord.get("remark"));
            nodeList.put(node.id, node);
        }

        // 构造无序的多叉树
        Set entrySet = nodeList.entrySet();
        for (Iterator it = entrySet.iterator(); it.hasNext(); ) {
            Node node = (Node) ((Map.Entry) it.next()).getValue();
            //树根
            if (node.parentId == null || node.parentId.equals("") || node.parentId.equals("-1")) {
                root = node;
            } else {
                if (!nodeList.containsKey(node.parentId)) {
                    throw new RuntimeException("当前id=" + node.id + "---------node节点不存在父节点" + node.parentId);
                }
                ((Node) nodeList.get(node.parentId)).addChild(node);
            }
        }
        // 对多叉树进行横向排序
        root.sortChildren();
        // 输出有序的树形菜单的JSON字符串
        String toString = root.toString();
        //转化为json对象
        return JSONObject.parseObject(toString);
    }

    /**
     * 构造一个父级菜单
     *
     * @return
     */
    private static Map<String, Object> getInitMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "0");
        map.put("parentId", "");
        map.put("index", "/");
        map.put("title", "根节点");
        map.put("remark", "");
        return map;

    }
}
