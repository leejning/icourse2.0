package com.online.icourse.provider.bean.dto;

import com.online.icourse.common.model.dto.QueryDto;
import com.online.icourse.provider.bean.ClazzBase;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ClazzBaseQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class ClazzBaseQueryDto extends QueryDto {
    String clazzbaseName;
    Long courseId;
    Long teacherId;
    public static Specification<ClazzBase> getWhere(ClazzBaseQueryDto queryDTO) {
        return new Specification<ClazzBase>() {
            @Override
            public Predicate toPredicate(Root<ClazzBase> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (!StringUtils.isEmpty(queryDTO.getClazzbaseName())) {
                    Predicate predicate = cb.like(root.get("clazzbaseName").as(String.class)
                            , "%" + queryDTO.getClazzbaseName() + "%");
                    predicates.add(predicate);
                }
                if (queryDTO.getCourseId() != null) {
                    Predicate predicate = cb.equal(root.get("courseId").as(Integer.class), queryDTO.getCourseId());
                    predicates.add(predicate);
                }
                if (queryDTO.getTeacherId() != null) {
                    Predicate predicate = cb.equal(root.get("teacherId").as(Integer.class), queryDTO.getTeacherId());
                    predicates.add(predicate);
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }
}
