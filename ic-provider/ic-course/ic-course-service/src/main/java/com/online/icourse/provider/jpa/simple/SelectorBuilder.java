package com.online.icourse.provider.jpa.simple;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName SelectorBuilder
 * @Description: TODO   选择过滤器
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
public class SelectorBuilder {
    List<String> selectorList = new ArrayList<>();
    public SelectorBuilder append(String ...field){
        selectorList.addAll(Arrays.asList(field));
        return this;
    }

    public List<Selection<?>> bulid(CriteriaBuilder builder, Root<?> root){
        List<Selection<?>> selections = new ArrayList<>();
        for (String item:selectorList) {
            Path<Object> field = root.get(item);
//            ParameterExpression<? extends Path> parameter = builder.parameter(field.getClass(), item);
            selections.add(field);
        }
        return selections;
    }
}
