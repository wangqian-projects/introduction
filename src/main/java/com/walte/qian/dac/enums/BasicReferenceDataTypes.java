package com.walte.qian.dac.enums;

/**
 * <p>ClassName: BasicReferenceDataTypes</p>
 * <p>Description: </p>
 *
 * @author wangqian
 * @date 2018-01-22 17:51
 */
public enum BasicReferenceDataTypes {
    BYTE    (0, byte.class,    Byte.class),
    SHORT   (1, short.class,   Short.class),
    INT     (2, int.class,     Integer.class),
    LONG    (3, long.class,    Long.class),
    FLOAT   (4, float.class,   Float.class),
    DOUBLE  (5, double.class,  Double.class),
    BOOLEAN (6, boolean.class, Boolean.class),
    CHAR    (7, char.class,    Character.class),
    OUTSIDER(8, null,          null);


    private int id;
    private Class<?> basicDateType;
    private Class<?> referenceDataType;

    BasicReferenceDataTypes(int id, Class<?> basicDateType, Class<?> referenceDataType) {
        this.id = id;
        this.basicDateType = basicDateType;
        this.referenceDataType = referenceDataType;

    }

    public static Class<?> getReferenceByPrimitiveClass(Class<?> basicDateType) {
        for (BasicReferenceDataTypes types: BasicReferenceDataTypes.values()) {
            if (types.basicDateType == basicDateType) {
                return types.referenceDataType;
            }
        }
        return OUTSIDER.referenceDataType;
    }
}
