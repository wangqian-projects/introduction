package com.wangqian.code.utils.tools;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 扫描工具类
 */
public class ScanTools {

    private String basePath;

    private List<Class<?>> classList = new ArrayList<Class<?>>();

    public ScanTools() {
        URL resource = Thread.currentThread().getContextClassLoader().getResource("");
        if (resource != null) {
            basePath = resource.getPath();
        }
    }

    /**
     * 根据包名加载class文件
     *
     * @param basePackage 包名
     */
    public void loadClassFile(String basePackage) throws ClassNotFoundException {
        File[] listFiles = new File(basePath + basePackage.replaceAll("\\.", "/")).listFiles();
        if (listFiles == null || listFiles.length == 0) {
            return;
        }
        String fileName;
        for (File item : listFiles) {
            fileName = item.getName();
            //如果是class文件
            if (item.isFile() && fileName.contains(".class")) {
                classList.add(Class.forName(basePackage + "." + fileName.replace(".class", "")));
                continue;
            }
            //如果是文件夹继续向下扫描
            if (item.isDirectory()) {
                loadClassFile(basePackage + "." + fileName);
            }

        }

    }

    public List<Class<?>> getClassList() {
        return classList;
    }

}
