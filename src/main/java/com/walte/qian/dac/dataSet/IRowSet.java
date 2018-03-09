package com.walte.qian.dac.dataSet;

import com.walte.qian.dac.enums.DataState;
import com.walte.qian.dac.util.vo.KeyValue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * <p>ClassName: IRowSet</p>
 * <p>Description: 数据库查询的行数据集接口</p>
 *
 * @author wangqian
 * @date 2018-03-09 11:30
 */
public interface IRowSet {

    /**
     * 添加数据集的属性
     *
     * @param attr  属性
     * @param value 值
     */
    public void addSetAttr(String attr, Object value);

    /**
     * 给数据添加监听
     *
     * @param listenter IDataSetListener
     */
    public void addSetListener(IDataSetListener listenter);

    /**
     * 给数据集添加一组监听
     *
     * @param listeners List<IDataSetListener>
     */
    public void addSetListener(List<IDataSetListener> listeners);


    /**
     * 改变行数据的状态
     *
     * @param row   行数据, 可以是IRowValue , 也可以是Entity
     * @param state 改变的状态
     */
    public void changeDataState(Object row, DataState state);


    /**
     * 数据集中有效数据对象数量, 不包括删除的
     *
     * @return
     */
    public int count();

    /**
     * 删除所有
     */
    public void deleteAll();

    /**
     * 获取数据集元数据模型
     */
    //TODO 元数据模型定义
//    public DoModel getDoModel();


    /**
     * 获取所有数据集属性的名称
     *
     * @param row 行
     * @return DataState
     */
    public DataState getRowState(Object row);


    /**
     * 获取所有数据集属性的名称
     *
     * @return Set<String>
     */
    public Set<String> getSetAllAttrNames();


    /**
     * 以泛型的形式获取数据集的属性
     *
     * @param attr 属性
     * @param <T>  泛型
     * @return T
     */
    public <T> T getSetAttr(String attr);


    /**
     * 以字符串的形式获取数据集的属性
     *
     * @param attr 属性
     * @return String
     */
    public String getSetAttrStr(String attr);


    /**
     * 获取所有数据集的监听器
     *
     * @return
     */
    public Set<IDataSetListener> getSetListeners();


    /**
     * 获取表的UUID
     *
     * @return UUID
     */
    public UUID getTableUUID();


    /**
     * 以泛型的形式获取行数据列值
     *
     * @param row        行
     * @param columnName 列名
     * @param <T>        泛型
     * @return T
     */
    public <T> T getValue(Object row, String columnName);


    /**
     * 以字符串的形式获取行数据列值
     *
     * @param row        行
     * @param columnName 列名
     * @return String
     */
    public String getValueStr(Object row, String columnName);


    /**
     * 判断数据集是否为空
     *
     * @return boolean
     */
    public boolean isEmpty();


    /**
     * 数据集移除一个监听
     *
     * @param listener IDataSetListener
     */
    public void removeSetListener(IDataSetListener listener);


    /**
     * 行数据赋值
     *
     * @param row        行
     * @param columnName 列
     * @param value      值
     */
    public void setValue(Object row, String columnName, Object value);


    /**
     * 行数据赋值
     *
     * @param row      行
     * @param bizIdent 业务标识
     * @param value    列值
     */
    public void setValueByBizIdent(Object row, String bizIdent, Object value);


    /**
     * 数据集转化成Map
     *
     * @param call ToMapCall
     * @return List<Map   <   String   ,       Object>>
     */
    public List<Map<String, Object>> toMapList(ToMapCall call);


    /**
     * 增加并返回数据行对象
     *
     * @return IRowValues
     */
    public IRowValues addRow(IRowValues rowValues);


    /**
     * 清空数据集
     */
    public void clearAll();


    /**
     * 删除指定行数据
     *
     * @param rowValue IRowValues
     */
    public void deleteRow(IRowValues rowValue);


    /**
     * 根据属性查找行数据
     *
     * @param attrName  属性名
     * @param attrValue 实现值
     * @return IRowValues
     */
    public IRowValues findRowByAttr(String attrName, Object attrValue);


    /**
     * 根据列名找行数据
     *
     * @param kvs 列名与值的keyValue对象组
     * @return IRowValues
     */
    public IRowValues findRowByColumn(KeyValue... kvs);


    /**
     * 根据列名找行数据
     *
     * @param columnName 列名
     * @param value      列值
     * @return IRowValues
     */
    public IRowValues findRowByColumn(String columnName, Object value);


    /**
     * 找行数据
     *
     * @param id 数据ID
     * @return IRowValues
     */
    public IRowValues findRowById(Object id);


    /**
     * 获取所有数据包括删除的数据
     *
     * @return List<IRowValues>
     */
    public List<IRowValues> getAllRows();


    /**
     * 获取删除的数据
     *
     * @return List<IRowValues>
     */
    public List<IRowValues> getDelRows();


    /**
     * 获取未删除的所有数据
     *
     * @return List<IRowValues>
     */
    public List<IRowValues> getRows();


    /**
     * 根据指导行数据移除数据, 不做删除标记
     *
     * @param rowValue
     */
    public void removeRow(IRowValues rowValue);


}
