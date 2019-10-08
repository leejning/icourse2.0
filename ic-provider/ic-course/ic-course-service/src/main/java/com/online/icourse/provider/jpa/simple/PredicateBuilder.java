package com.online.icourse.provider.jpa.simple;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName PredicateBuilder
 * @Description: TODO  条件过滤构建起
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
public class PredicateBuilder {
    List<String> predicateList = new ArrayList<>();

    public PredicateBuilder append(String ...feild){
        predicateList.addAll(Arrays.asList(feild));
        return this;
    }
    public List<Selection<?>> bulid(CriteriaBuilder builder, Root<?> root){
        List<Selection<?>> selections = new ArrayList<>();
        for (String item:predicateList) {
            Path<Object> field = root.get(item);
            ParameterExpression<? extends Path> parameter = builder.parameter(field.getClass(), item);
            selections.add(parameter);
        }
        return selections;
    }
}
