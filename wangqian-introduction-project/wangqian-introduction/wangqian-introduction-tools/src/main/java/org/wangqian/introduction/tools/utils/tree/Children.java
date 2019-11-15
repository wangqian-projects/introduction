package org.wangqian.introduction.tools.utils.tree;

import java.util.*;


public class Children {

    private List<Node> list = new ArrayList<>();

    public int getSize(){
        return list.size();
    }

    public void addChild(Node node){
        list.add(node);
    }

    //拼接孩子节点的json字符串
    @Override
    public String toString() {
        String result = "[";
        for (Iterator it = list.iterator(); it.hasNext();) {
            result += ((Node) it.next()).toString();
            result += ",";
        }
        result = result.substring(0, result.length() - 1);
        result += "]";
        return result;
    }

    // 孩子节点排序
    public void sortChildren() {
        // 对本层节点进行排序
        // 可根据不同的排序属性，传入不同的比较器，这里传入ID比较器
        Collections.sort(list, new NodeIDComparator());
        // 对每个节点的下一层节点进行排序
        for (Iterator it = list.iterator(); it.hasNext();) {
            ((Node) it.next()).sortChildren();
        }
    }

    public List<Node> getList() {
        return list;
    }

    public void setList(List<Node> list) {
        this.list = list;
    }
}

/**
 * 节点比较器
 */
class NodeIDComparator implements Comparator {
    // 按照节点编号比较
    @Override
    public int compare(Object o1, Object o2) {
        int j1 = Integer.parseInt(((Node)o1).id);
        int j2 = Integer.parseInt(((Node)o2).id);
        return (j1 < j2 ? -1 : (j1 == j2 ? 0 : 1));
    }


}
