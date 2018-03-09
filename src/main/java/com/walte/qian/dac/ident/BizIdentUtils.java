package com.walte.qian.dac.ident;

import com.walte.qian.dac.config.DacConfig;
import com.walte.qian.dac.enums.ColumnDataEnum;
import com.walte.qian.dac.util.CollectionsUtil;
import com.walte.qian.dac.util.EmptyUtil;
import com.walte.qian.dac.util.RegularUtil;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>ClassName: BizIdentUtils</p>
 * <p>Description: 业务标识抽象类</p>
 *
 * @author wangqian
 * @date 2018-03-09 14:32
 */
public abstract class BizIdentUtils {

    public static String baseName = "i18n.enums.BizIdent";

    public static Map<String, BizIdentVo> bizIdentMap = CollectionsUtil.newHashMap();

    /**
     * 查找业务标识
     *
     * @param columnDataEnum 数据库字段类型
     * @return List<BizIdentVo>
     */
    public static List<BizIdentVo> findBizIdent(ColumnDataEnum columnDataEnum) {
        List<BizIdentVo> list = CollectionsUtil.newArrayList();
        for (BizIdentVo bizIdentVo : bizIdentMap.values()) {
            if (bizIdentVo.isSupport(columnDataEnum)) {
                list.add(bizIdentVo);
            }
        }
        Collections.sort(list);
        return list;
    }

    /**
     * 查找业务标识
     *
     * @return List<BizIdentVo>
     */
    public static List<BizIdentVo> findDynamicBizIdent() {
        List<BizIdentVo> list = CollectionsUtil.newArrayList();
        for (BizIdentVo vo : bizIdentMap.values()) {
            if (vo.isDynamicFK()) {
                list.add(vo);
            }
        }
        Collections.sort(list);
        return list;
    }

    /**
     * 获取业务标识的title
     *
     * @param ident 业务标识
     * @return String
     */
    public static String getIdentTitle(String ident) {
        if (EmptyUtil.isEmpty(ident) || RegularUtil.IsNum.matcher(ident).matches()) {
            return null;
        }
        return DacConfig.t(baseName, ident);
    }

    /**
     * 初始化业务标识集合
     *
     * @param cls Class<?>
     */
    public static void initBizIdent(Class<?> cls) {
        if (null == cls) {
            return;
        }

        Field[] declaredFields = cls.getDeclaredFields();

        for (Field field : declaredFields) {
            Ident annotation = field.getAnnotation(Ident.class);
            if (null == annotation) {
                continue;
            }
            String name = null;
            try {
                field.setAccessible(true);
                name = field.get(cls).toString();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            if (EmptyUtil.isEmpty(name)) {
                throw new RuntimeException(String.format("业务标识为初始化值，%s:%s", cls.getSimpleName(), field.getName()));
            }

            BizIdentVo bizIdentVo = new BizIdentVo(name, annotation.order(), annotation.dynamicFK(), annotation.columnDatas());
            if (bizIdentMap.get(name) != null) {
                throw new RuntimeException(String.format("存在重复的业务标识：%s", name));
            }

            bizIdentMap.put(name, bizIdentVo);


        }


    }


}
