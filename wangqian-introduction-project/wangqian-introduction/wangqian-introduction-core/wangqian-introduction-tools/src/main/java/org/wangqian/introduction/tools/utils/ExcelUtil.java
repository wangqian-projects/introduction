package org.wangqian.introduction.tools.utils;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;

/**
 * Excel文件导出工具
 *
 * @author PC20190724
 */
public class ExcelUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 实例化一个值操作器
     *
     * @param title EXCEL标题
     * @return ValueOperator
     */
    public static ValueOperator instanceValueOperator(String title) {
        return new ValueOperator(title);
    }

    /**
     * 获取操作器Map
     *
     * @return
     */
    static LinkedHashMap<String, ValueOperator> getValueOperatorMap() {
        return new LinkedHashMap<>();
    }

    /**
     * 导出EXCEL数据
     *
     * @param response  HttpServletResponse
     * @param list      数据
     * @param titlesStr ","隔开的标题
     * @param sheetName 导出文件名
     */
    public static void exportFile(HttpServletResponse response, List<Map<String, Object>> list, String titlesStr, String sheetName) {
        List<List<String>> data = getData(list, titlesStr);
        doWriteExport(response, data, sheetName);
    }

    public static void exportFile2(HttpServletResponse response,
                                   List<Map<String, Object>> list1, List<Map<String, Object>> list2,
                                   String titlesStr1, String titlesStr2,
                                   String fileName, String sheetName1, String sheetName2) {

        List<List<String>> data1 = getData(list1, titlesStr1);
        List<List<String>> data2 = getData(list2, titlesStr2);
        doWriteExport2(response, data1, data2, fileName, sheetName1, sheetName2);
    }

    /**
     * 导出EXCEL数据
     *
     * @param response    HttpServletResponse
     * @param dataMapList 数据
     * @param titleMap    标题Map(K是原始数据的key, V是标题值)
     * @param fileName    导出文件名
     */
    public static void exportFile(HttpServletResponse response, List<Map<String, Object>> dataMapList, Map<String, String> titleMap, String fileName) {
        doWriteExport(response, getData(dataMapList, titleMap), fileName);
    }

    /**
     * 导出EXCEL数据并对值处理
     * <p>
     * valueOperatorMap参数示例<br>
     * <p>使用 LinkedHashMap 保证put的顺序是EXCEL列的顺序<br>
     * <code>LinkedHashMap<String, WriterFileUtil.ValueOperator> valueOperatorMap = new LinkedHashMap<>();</code>
     *
     * <p>不适用任何指令或函数, 直接将结果转化String输出<br>
     * <code>valueOperatorMap.put("accountRealName", WriterFileUtil.instanceValueOperator("发起人"));</code>
     *
     * <p>使用指令DIVIDE, amount对应的值作为被除数, 除100精度为2, 计算后输出<br>
     * <code>valueOperatorMap.put("amount", WriterFileUtil.instanceValueOperator("转入金额")
     * .setCommand(WriterFileUtil.CommandEnum.DIVIDE)
     * .setValue("100").setScale(2));</code>
     *
     * <p>使用指令REPLACE, status对应的值不能直接输出, 替换后输出对应的字符, 使用于一些type字段<br>
     * <code>valueOperatorMap.put("status", WriterFileUtil.instanceValueOperator("转入状态")
     * .setCommand(WriterFileUtil.CommandEnum.REPLACE)
     * .putReplaceMap("pendingAudit", "转入中")
     * .putReplaceMap("pass", "转入成功")
     * .putReplaceMap("reject", "转入失败"));</code>
     * <p>使用函数式编程, 可获取行数据(rowMap)自定义函数处理, 操作后输出当前KEY对应的值<br>
     * 这里的示例是status为pass时, 才显示成功的时间<br>
     * <code>valueOperatorMap.put("audit_date_time", WriterFileUtil.instanceValueOperator("转入成功时间")
     * .setFunction(rowMap->{
     * Object status = rowMap.get("status");
     * if (status != null && "pass".equals(status)) {
     * return StringUtils.objToStr(rowMap.get("audit_date_time")) ;
     * }
     * return "";
     * }));</code>
     *
     * @param response         HttpServletResponse
     * @param dataMapList      数据
     * @param valueOperatorMap LinkedHashMap
     * @param fileName         文件名
     */
    public static void exportFileOperateValue(HttpServletResponse response, List<Map<String, Object>> dataMapList,
                                              LinkedHashMap<String, ValueOperator> valueOperatorMap, String fileName) {
        doWriteExport(response, processValueOperateDataMapList(dataMapList, valueOperatorMap), fileName);
    }

    /**
     * 数据生成写入response
     *
     * @param response HttpServletResponse
     * @param data     EXCEL数据
     * @param fileName 文件名
     */
    private static void doWriteExport(HttpServletResponse response, List<List<String>> data, String fileName) {
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getBigWriter();
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(data);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        ServletOutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xlsx");
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.flush(out);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 数据生成写入response
     *
     * @param response HttpServletResponse
     * @param data     EXCEL数据
     * @param fileName 文件名
     */
    private static void doWriteExport2(HttpServletResponse response, List<List<String>> data1,
                                       List<List<String>> data2, String fileName, String sheetName1, String sheetName2) {
        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getBigWriter();
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.renameSheet(0, sheetName1);
        writer.write(data1);
        writer.setSheet(sheetName2);
        writer.write(data2);
        //out为OutputStream，需要写出到的目标流
        //response为HttpServletResponse对象
        ServletOutputStream out = null;
        try {
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO8859-1") + ".xlsx");
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.flush(out);
        // 关闭writer，释放内存
        writer.close();
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
    }

    /**
     * 处理操作组织导出数据
     *
     * @param dataMapList      数据
     * @param valueOperatorMap 值操作器集合 K:对应dataMapList的KEY, V: 操作器
     * @return
     */
    private static List<List<String>> processValueOperateDataMapList(List<Map<String, Object>> dataMapList,
                                                                     Map<String, ValueOperator> valueOperatorMap) {
        List<List<String>> result = new LinkedList<>();
        List<String> titleList = new LinkedList<>();

        Set<Map.Entry<String, ValueOperator>> valueOperatorMapEntrySet = valueOperatorMap.entrySet();
        // 遍历值操作器获取所有标题
        valueOperatorMapEntrySet.forEach(entry -> titleList.add(entry.getValue().getTitle()));
        result.add(titleList);

        // 遍历数据集组织处理值操作后的数据
        dataMapList.forEach(rowDataMap -> {
            List<String> dataList = new LinkedList<>();
            valueOperatorMapEntrySet.forEach(indexValueOperatorEntry ->
                    dataList.add(doValueOperate(indexValueOperatorEntry, rowDataMap))
            );
            result.add(dataList);
        });
        return result;
    }

    /**
     * 组织要生成EXCEL的数据(title + data)
     *
     * @param dataMapList 原始数据
     * @param titleMap    标题Map(K是原始数据的key, V是标题值)
     * @return List<List < String>>
     */
    private static List<List<String>> getData(List<Map<String, Object>> dataMapList, Map<String, String> titleMap) {
        List<List<String>> result = new ArrayList<>();
        List<String> titleList = new ArrayList<>();
        Set<String> keySet = titleMap.keySet();
        // 组织标题
        for (String key : keySet) {
            titleList.add(titleMap.get(key));
        }
        result.add(titleList);
        // 组织数据
        dataMapList.forEach(map -> {
            List<String> dataList = new LinkedList<>();
            for (String key2 : keySet) {
                dataList.add(StringUtils.objToStr(map.get(key2)));
            }
            result.add(dataList);
        });

        return result;
    }

    /**
     * 组织要生成EXCEL的数据
     *
     * @param list      原始数据
     * @param titlesStr ","隔开的标题
     * @return List<List < String>>
     */
    private static List<List<String>> getData(List<Map<String, Object>> list, String titlesStr) {
        List<List<String>> result = new ArrayList<>();
        List<String> titles = Arrays.asList(titlesStr.split(","));
        result.add(titles);
        for (Map<String, Object> stringObjectMap : list) {
            List<String> hangList = new ArrayList<>();
            for (String key : stringObjectMap.keySet()) {
                String value = "";
                Object o = stringObjectMap.get(key);
                if (null != o) {
                    value = stringObjectMap.get(key).toString();
                }
                hangList.add(value);
            }
            result.add(hangList);
        }
        return result;
    }

    /**
     * 执行操作值 (行级别)
     * 当前指令可操作一行的数据
     *
     * @param indexEntry Map.Entry<String, ValueOperator> 当前操作指令
     * @param rowDataMap 行数据
     * @return 处理后的值
     */
    private static String doValueOperate(Map.Entry<String, ValueOperator> indexEntry, Map<String, Object> rowDataMap) {
        ValueOperator valueOperator = indexEntry.getValue();
        Object obj = rowDataMap.get(indexEntry.getKey());
        if (valueOperator == null) {
            return obj == null ? "" : obj.toString();
        }
        return valueOperator.getCommand().getCommand().apply(obj, valueOperator, rowDataMap);
    }


    /**
     * 定义指令函数接口
     *
     * @param <A> 参数 A
     * @param <B> 参数 B
     * @param <C> 参数 C
     * @param <R> 返回值 R
     */
    @FunctionalInterface
    public interface CommandFunction<A, B, C, R> {

        /**
         * 将此函数应用于给定的参数.
         *
         * @param a 此函数参数 a
         * @param b 此函数参数 b
         * @param c 此函数参数 c
         * @return 此函数参数结果
         */
        R apply(A a, B b, C c);
    }


    /**
     * 指令枚举
     * 配合值操作器处理指令, 可直接拓展指令维护, 自定义函数操作<br>
     * {@link com.lsj.common.utils.ExcelUtil.ValueOperator}
     */
    public enum CommandEnum {

        /**
         * 不执行任何指令直接返回数据值
         */
        NONE((obj, valueOperator, rowDataMap) ->
                obj == null ? "" : obj.toString()
        ),

        /**
         * 使用自定义函数式执行指令
         */
        FUNCTION((obj, valueOperator, rowDataMap) -> {
            Function<Map<String, Object>, String> defineFunction = valueOperator.getFunction();
            Objects.requireNonNull(defineFunction);
            return defineFunction.apply(rowDataMap);
        }),

        /**
         * 常量
         */
        CONSTANT((obj, valueOperator, rowDataMap) -> valueOperator.getValue()),

        /**
         * 转化值 将数据结果直接替换replaceMap中对应的值
         */
        REPLACE((obj, valueOperator, rowDataMap) -> {
            Map<String, String> replaceMap = valueOperator.getReplaceMap();
            return replaceMap.get(obj);
        }),

        /**
         * 格式化日期
         */
        DATE_FORMAT((obj, valueOperator, rowDataMap) -> {
            if (obj == null) {
                return "";
            }
            String value = valueOperator.getValue();
            if (StringUtils.isEmpty(value)) {
                value = DatePattern.NORM_DATETIME_PATTERN;
            }
            if (obj instanceof Date) {
                return DateUtil.format((Date) obj, value);
            }
            return "";
        }),

        /**
         * 除法计算, 作为被除数
         */
        DIVIDE((obj, valueOperator, rowDataMap) -> {
            if (obj == null) {
                return "";
            }
            BigDecimal bigDecimal = new BigDecimal(obj.toString());
            String value = valueOperator.getValue();
            if (StringUtils.isEmpty(value)) {
                throw new RuntimeException("除法指令除数值不能为空");
            }
            BigDecimal divide = bigDecimal.divide(new BigDecimal(value), valueOperator.getScale(), BigDecimal.ROUND_HALF_UP);
            return divide.toString();
        });

        /**
         * 指令函数
         */
        private CommandFunction<Object, ValueOperator, Map<String, Object>, String> command;

        /**
         * 构造器
         *
         * @param command 指令函数
         */
        CommandEnum(CommandFunction<Object, ValueOperator, Map<String, Object>, String> command) {
            this.command = command;
        }

        /**
         * 获取指令
         *
         * @return 指令函数
         */
        public CommandFunction<Object, ValueOperator, Map<String, Object>, String> getCommand() {
            return command;
        }
    }

    /**
     * 内部类: 值操作器
     */
    public static class ValueOperator {

        /**
         * EXCEL标题
         */
        private String title;

        /**
         * 操作指令
         */
        private CommandEnum command = CommandEnum.NONE;

        /**
         * 操作函数
         */
        private Function<Map<String, Object>, String> function;

        /**
         * 操作值
         */
        private String value;

        /**
         * 计算精度
         */
        private int scale;

        /**
         * command是REPLACE时
         * K: 待替换的值
         * V: 替换后的值
         */
        private Map<String, String> replaceMap;

        public ValueOperator(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public ValueOperator setTitle(String title) {
            this.title = title;
            return this;
        }

        public CommandEnum getCommand() {
            return command;
        }

        public ValueOperator setCommand(CommandEnum command) {
            this.command = command;
            return this;
        }

        public String getValue() {
            return value;
        }

        public ValueOperator setValue(String value) {
            this.value = value;
            return this;
        }

        public int getScale() {
            return scale;
        }

        public ValueOperator setScale(int scale) {
            this.scale = scale;
            return this;
        }

        public Map<String, String> getReplaceMap() {
            return replaceMap;
        }

        public ValueOperator setReplaceMap(Map<String, String> replaceMap) {
            this.replaceMap = replaceMap;
            return this;
        }

        public ValueOperator putReplaceMap(String key, String value) {
            if (this.replaceMap == null) {
                replaceMap = new HashMap<>(25);
            }
            this.replaceMap.put(key, value);
            this.command = CommandEnum.REPLACE;
            return this;
        }

        public Function<Map<String, Object>, String> getFunction() {
            return function;
        }

        public ValueOperator setFunction(Function<Map<String, Object>, String> function) {
            this.function = function;
            this.command = CommandEnum.FUNCTION;
            return this;
        }
    }

}
