package com.wangqian.utils.tools;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * 扫描工具类单元测试
 */
public class ToolsTest {

    @Test
    public void ScanToolsTest() throws ClassNotFoundException {
        ScanTools st = new ScanTools();
        st.loadClassFile("com");
        List<Class<?>> classList = st.getClassList();
        for (Class<?> clas : classList) {
            System.out.println("****************" + clas.getName());
        }
    }

    @Test
    public void StringUtilTest(){
        Assert.assertEquals(StringUtil.isStrEmpty(""),false);
    }

}
