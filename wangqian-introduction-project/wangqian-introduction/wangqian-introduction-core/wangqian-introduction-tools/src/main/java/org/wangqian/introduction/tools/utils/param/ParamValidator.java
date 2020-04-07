package org.wangqian.introduction.tools.utils.param;

import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import org.wangqian.introduction.tools.utils.JsonResult;
import org.wangqian.introduction.tools.utils.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.function.Predicate;

/**
 * 前端传参校验工具
 * 使用此工具可简化Controller中繁琐的if else判断, 传入校验的参数即可得到验证的结果
 *
 * @param <K> Map key type
 * @param <V> Map value type
 * @author 卢兴旺
 * @date 2019-08-22
 */
public class ParamValidator<K, V> extends JsonResult {

    /**
     * 是否正常
     * 默认正常, 校验不通过为异常 false
     */
    @JSONField(serialize = false)
    private boolean succeed = true;

    /**
     * 待校验的参数
     */
    @JSONField(serialize = false)
    private Map<K, V> paramMap;

    /**
     * 构造器
     *
     * @param paramMap 前端传的参数
     */
    public ParamValidator(Map<K, V> paramMap) {
        this.paramMap = paramMap;
    }

    /**
     * 获取校验器对象
     *
     * @param paraMap 前端传的参数
     * @return ParamValidator
     */
    public static <K, V> ParamValidator<K, V> build(Map<K, V> paraMap) {
        return new ParamValidator<>(paraMap);
    }

    /**
     * 校验参数非空
     * 默认返回错误code为201, 推荐链式调用
     *
     * @param key              需要验证的参数
     * @param errorMsgTemplate 返给前端的错误信息
     * @return ParamValidator
     */
    public ParamValidator<K, V> validateBlank(K key, String errorMsgTemplate) {
        return validateBlank(key, 201, errorMsgTemplate);
    }

    /**
     * 校验参数非空
     * 推荐链式调用, 发现校验不通过时错误提示信息, 返回第一次错误的
     *
     * @param key              需要验证的参数
     * @param errorCode        返回的错误码
     * @param errorMsgTemplate 返给前端的错误信息
     * @return ParamValidator
     */
    public ParamValidator<K, V> validateBlank(K key, int errorCode, String errorMsgTemplate) {
        if (!succeed) {
            return this;
        }
        Map<K, V> paraMap = this.getParamMap();
        if (MapUtil.isEmpty(paraMap) || StringUtils.isBlank(paraMap.get(key))) {
            this.setCode(errorCode);
            this.setMessage(errorMsgTemplate);
            this.setSucceed(false);
            return this;
        }
        return this;
    }

    /**
     * 使用Predicate自定义处理验证逻辑
     * errorCode默认使用201
     *
     * @param key              paramMap key
     * @param predicate        Predicate<V> 验证逻辑
     * @param errorMsgTemplate 返回的错误信息
     * @return ParamValidator
     */
    public ParamValidator<K, V> validatePredicate(K key, Predicate<V> predicate, String errorMsgTemplate) {
        return validatePredicate(key, predicate, 201, errorMsgTemplate);
    }

    /**
     * 使用Predicate自定义处理验证逻辑
     *
     * @param key              paramMap key
     * @param predicate        Predicate<V> 验证逻辑
     * @param errorCode        返回的错误码
     * @param errorMsgTemplate 返回的错误信息
     * @return ParamValidator
     */
    public ParamValidator<K, V> validatePredicate(K key, Predicate<V> predicate, int errorCode, String errorMsgTemplate) {
        if (!succeed) {
            return this;
        }
        Objects.requireNonNull(predicate);
        boolean test = predicate.test(this.paramMap.get(key));
        if (!test) {
            this.setCode(errorCode);
            this.setMessage(errorMsgTemplate);
            this.setSucceed(false);
        }
        return this;
    }

    /**
     * 如果与key关联的值被函数执行的结果为true,
     * 则用指定的值替换原来的值
     *
     * @param key       要与指定值关联的Key
     * @param value     值与指定的键关联
     * @param predicate
     * @return
     */
    public ParamValidator<K, V> putIf(K key, V value, Predicate<V> predicate) {
        if (!succeed) {
            return this;
        }
        Objects.requireNonNull(predicate);
        if (predicate.test(this.paramMap.get(key))) {
            this.paramMap.put(key, value);
        }
        return this;
    }

    public ParamValidator<K, V> putIfPageAbsent(V page, V limit) {
        Map<K, V> paraMap = this.getParamMap();
        K pageKey = (K) "page";
        K limitKey = (K) "limit";
        paraMap.putIfAbsent(pageKey, page);
        paraMap.putIfAbsent(limitKey, limit);
        paraMap.put((K) "pageNum", paraMap.get(pageKey));
        paraMap.put((K) "pageSize", paraMap.get(limitKey));
        return this;
    }

    /**
     * 获取参数
     *
     * @param key
     * @return
     */
    public V get(K key) {
        return paramMap.get(key);
    }

    /**
     * 使用示例
     *
     * @param args
     */
    public static void main(String[] args) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put("id", "23423423");
        paraMap.put("code", "098080");

        ParamValidator<String, Object> validate = ParamValidator.build(paraMap)
                .validateBlank("id", "ID不能为空")
                .validatePredicate("id", value -> value.toString().equals("123456"), "id不为空时, 值必须为33")
                .validateBlank("code", "CODE不能为空");

        if (!validate.isSucceed()) {
            System.out.println(validate.getCode() + " ---- " + validate.getMessage());
        }
        System.out.println(JSON.toJSONString(validate));
    }


    public Map<K, V> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<K, V> paramMap) {
        this.paramMap = paramMap;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public boolean isError() {
        return !isSucceed();
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ParamValidator.class.getSimpleName() + "[", "]")
                .add("succeed=" + succeed)
                .add("paraMap=" + paramMap)
                .toString();
    }

}
