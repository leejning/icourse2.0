package com.online.icourse.provider.bean.dto;

import com.online.icourse.common.model.dto.QueryDto;
import com.online.icourse.provider.bean.Course;
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
 * @ClassName CourseQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class CourseQueryDto extends QueryDto {
    private String CourseName;
    private String courseType;

    public static Specification<Course> getWhere(CourseQueryDto queryDTO) {
        return new Specification<Course>() {
            @Override
            public Predicate toPredicate(Root<Course> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<Predicate>();

                if (!StringUtils.isEmpty(queryDTO.getCourseName())) {
                    Predicate predicate = criteriaBuilder.like(root.get("courseName").as(String.class)
                            , "%" + queryDTO.getCourseName() + "%");
                    predicates.add(predicate);
                }
                if (queryDTO.getCourseType() != null) {
                    Predicate predicate = criteriaBuilder.equal(root.get("courseType").as(String.class), queryDTO.getCourseType());
                    predicates.add(predicate);
                }


                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }
}
