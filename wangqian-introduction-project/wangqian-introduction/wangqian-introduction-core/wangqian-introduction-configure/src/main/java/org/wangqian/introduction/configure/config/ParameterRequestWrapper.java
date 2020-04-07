package org.wangqian.introduction.configure.config;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.wangqian.introduction.tools.utils.GetEnvironmentUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.*;

/**
 * 参数请求处理
 *
 * @author 王骞
 * @date 2019-11-06
 */
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String[]> params = new HashMap<String, String[]>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ParameterRequestWrapper.class);

    @SuppressWarnings("unchecked")
    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
    }

    /**
     * 重载一个构造方法
     *
     * @param request
     * @param getEnvironmentUtil
     */
    public ParameterRequestWrapper(HttpServletRequest request, GetEnvironmentUtil getEnvironmentUtil) {
        this(request);
    }


    public void modifyParameterValues() {//将parameter的值去除空格后重写回去
        Set<String> set = params.keySet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            String[] values = params.get(key);


            params.put(key, values);
        }

    }

    // 重写几个HttpServletRequestWrapper中的方法

    /**
     * 获取所有参数名
     *
     * @return 返回所有参数名
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<String>(params.keySet());
        return vector.elements();
    }

    /**
     * 获取指定参数名的值，如果有重复的参数名，则返回第一个的值 接收一般变量 ，如text类型
     *
     * @param name 指定参数名
     * @return 指定参数名的值
     */
    @Override
    public String getParameter(String name) {
        String[] results = params.get(name);
        if (results == null || results.length <= 0)
            return null;
        else {
            return results[0];
        }
    }

    /**
     * 获取指定参数名的所有值的数组，如：checkbox的所有数据
     * 接收数组变量 ，如checkobx类型
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] results = params.get(name);
        List<String> filterValue = new ArrayList<>();
        if (results == null || results.length <= 0) {
            return null;
        } else {
            return Convert.toStrArray(filterValue);
        }
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        Map<String, String[]> paramMap = new HashMap<>();
        String[] values = null;
        for (Enumeration<String> en = getParameterNames(); en.hasMoreElements(); ) {
            String paramName = (String) en.nextElement();
            values = getParameterValues(paramName);
            int n = values.length;

            if (values.length > 0) {
                paramMap.put(paramName, values);
                this.getRequest().setAttribute(paramName, values);
            }

        }
        return paramMap;
    }
}
