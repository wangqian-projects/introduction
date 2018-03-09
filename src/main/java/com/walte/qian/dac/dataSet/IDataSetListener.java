package com.walte.qian.dac.dataSet;

import com.walte.qian.dac.util.vo.Results;

/**
 * <p>ClassName: IDataSetListener</p>
 * <p>Description: 数据监听器</p>
 *
 * @author wangqian
 * @date 2018-03-09 15:54
 */
public interface IDataSetListener {

    /**
     * 监听操作类型
     */
    public enum ListenerOption {
        Check,
        Init,
        Before,
        After
    }

    /**
     * 删除事件
     *
     * @param set            IRowSet
     * @param rowValues      IRowValues
     * @param listenerOption ListenerOption
     * @return Results<Boolean, Object>
     */
    public Results<Boolean, Object> delete(IRowSet set, IRowValues rowValues, ListenerOption listenerOption);


    /**
     * 字段变更事件
     *
     * @param set            IRowSet
     * @param rowValues      IRowValues
     * @param listenerOption ListenerOption
     * @param column         字段
     * @param newValue       新值
     * @param oldValue       旧值
     * @return Results<Boolean,  Object>
     */
    public Results<Boolean, Object> fieldChanged(IRowSet set, IRowValues rowValues, ListenerOption listenerOption, String column, Object newValue, Object oldValue);


    /**
     * 新增时的事件
     *
     * @param set            IRowSet
     * @param rowValues      IRowValues
     * @param listenerOption ListenerOption
     * @return Results<Boolean, Object>
     */
    public Results<Boolean, Object> insert(IRowSet set, IRowValues rowValues, ListenerOption listenerOption);


}
