package com.online.icourse.provider.jpa;

import com.online.icourse.provider.jpa.simple.ExpressionBuilder;
import com.online.icourse.provider.jpa.simple.PredicateBuilder;
import com.online.icourse.provider.jpa.simple.SelectorBuilder;
import lombok.Setter;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NbQueryBuilder
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Setter
public class NbQueryBuilder<T> implements CriteriaQueryBuilder<T> {
    private ExpressionBuilder expressionBuilder;
    private PredicateBuilder predicateBuilder;
    private SelectorBuilder selectorBuilder;

    @Override
    public List<Selection<?>> buildSelections(CriteriaBuilder builder, Root<T> root) {
        return null;
    }

    @Override
    public List<Expression<?>> buildGroupBy(Root<T> root) {
        return null;
    }

    @Override
    public List<Tuple> findResult(EntityManager entityManager, Class<T> t) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(t);
        CriteriaQuery<Tuple> criteriaQuery = criteriaBuilder.createTupleQuery();
        Root<T> root = criteriaQuery.from(t);

        if (selectorBuilder!=null) {
            criteriaQuery.multiselect(selectorBuilder.bulid(criteriaBuilder,root));
            query.select(root);
        }
        if (expressionBuilder!=null) {
            criteriaQuery.groupBy(buildGroupBy(root));
        }
        criteriaQuery.where(toPredicate(root, criteriaQuery, criteriaBuilder));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<Predicate>();
        Predicate predicate = criteriaBuilder.like(root.get("courseName").as(String.class)
                , "%" + "java" + "%");
        predicates.add(predicate);
        return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
    }
}
