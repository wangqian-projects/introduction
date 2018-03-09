package com.walte.qian.dac.dataSet;

import com.walte.qian.dac.enums.DataState;

import java.util.Map;

/**
 * <p>ClassName: IRowValues</p>
 * <p>Description: 行数据接口</p>
 *
 * @author wangqian
 * @date 2018-03-09 11:26
 */
public interface IRowValues {

    /**
     * 添加行属性
     *
     * @param attrName 属性名称
     * @param obj      Object
     */
    public void addRowValueAttr(String attrName, Object obj);


    /**
     * 变更数据集
     *
     * @param target 目标数据集
     */
    public void changeSet(IRowSet target);


    /**
     * 变更数据集
     *
     * @param target 目标数据集
     * @param append 是否插入最前
     */
    public void changeSet(IRowSet target, boolean append);


    /**
     * 获取该行的数据ID
     *
     * @param <K> K
     * @return K
     */
    public <K> K getDataID();


    /**
     * 获取该行的实体类
     *
     * @param <K>
     * @return K
     */
    public <K> K getEntity();


    /**
     * 获取该行所在的数据集
     *
     * @return IRowSet
     */
    public IRowSet getRowSet();


    /**
     * 获取行属性
     *
     * @param attrName 属性名称
     * @param <T>      泛型
     * @return T
     */
    public <T> T getRowValueAttr(String attrName);


    /**
     * 以字符串式获取行数据属性
     *
     * @param attrName 属性名称
     * @return String
     */
    public String getRowValueAttrStr(String attrName);


    /**
     * 获取行数据状态
     *
     * @return DataState
     */
    public DataState getState();


    /**
     * 以泛型的形式获取列值
     *
     * @param columnName 列名
     * @param <T>        泛型
     * @return T
     */
    public <T> T getValue(String columnName);


    /**
     * 以泛型形式获取列值
     *
     * @param bizIdent 业务标识
     * @param <T>      泛型
     * @return T
     */
    public <T> T getValueByBizIdent(String bizIdent);


    /**
     * 以字符串方式获得列值
     *
     * @param columnName 列名
     * @return String
     */
    public String getValueStr(String columnName);


    /**
     * 是否存在某个业务标识字段
     *
     * @param bizIdent 业务标识
     * @return boolean
     */
    public boolean isExistBizIdent(String bizIdent);


    /**
     * }
     * 是否存在某个字段
     *
     * @param columnName 列名
     * @return boolean
     */
    public boolean isExisColumn(String columnName);

    /**
     * 是否锁定
     *
     * @return boolean
     */
    public boolean isLockData();

    /**
     * ID是否相同
     *
     * @param id ID
     * @return boolean
     */
    public boolean isSameId(Object id);

    /**
     * 数据锁定, 锁定的数据将无法修改, 内存中
     */
    public void lockData();

    /**
     * 数据解锁
     */
    public void unLockDate();

    /**
     * 为共享数据在当前前程 赋值
     *
     * @param columnName 列名
     * @param value      值
     */
    public void setLocaleValue(String columnName, Object value);


    /**
     * 赋值
     *
     * @param columnName 列名
     * @param value      值
     */
    public void setValue(String columnName, Object value);

    /**
     * 赋值
     *
     * @param bizIdent 业务标识
     * @param value    值
     */
    public void setValueByBizIdent(String bizIdent, Object value);


    /**
     * 赋值
     *
     * @param columnName 列名
     * @param value      值
     */
    public void setValueStr(String columnName, String value);


    /**
     * 行数据转化为Map
     *
     * @return Map
     */
    public Map<String, Object> toMap();


    /**
     * 行数据转化为Map
     *
     * @param call ToMapCall
     * @return Map
     */
    public Map<String, Object> toMap(ToMapCall call);

    /**
     * 行数据转化为Map
     *
     * @return
     */
    public Map<String, String> toMapString();


}
