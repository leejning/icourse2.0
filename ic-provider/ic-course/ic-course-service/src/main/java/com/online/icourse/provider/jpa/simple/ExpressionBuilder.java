package com.online.icourse.provider.jpa.simple;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ExpressionBuilder
 * @Description: TODO 分组统计字段构建器
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
public class ExpressionBuilder {
    List<String> expressionList = new ArrayList<>();

    public ExpressionBuilder append(String ...field){
        expressionList.addAll(Arrays.asList(field));
        return this;
    }

    public List<Expression<?>> bulid(CriteriaBuilder builder, Root<?> root){
        List<Expression<?>> expressions = new ArrayList<>();
        for (String item:expressionList) {
            expressions.add(root.get(item));
        }
        return expressions;
    }
}
