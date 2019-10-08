package com.online.icourse.provider.bean.dto;

import com.online.icourse.common.model.dto.QueryDto;
import com.online.icourse.provider.bean.Student;
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
 * @ClassName StudentQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/9/30 0030
 * @Version V1.0
 **/
@Data
public class StudentQueryDto extends QueryDto {
    String className;
    Integer grade;

    public static Specification<Student> getWhere(StudentQueryDto queryDTO) {
        return new Specification<Student>() {
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                /**
                 * 班级
                 */
                if (!StringUtils.isEmpty(queryDTO.getClassName())) {
                    Predicate predicate = cb.equal(root.get("className").as(String.class), queryDTO.getClassName());
                    predicates.add(predicate);
                }
                if (queryDTO.getGrade()!=null) {
                    Predicate predicate = cb.equal(root.get("grade").as(Integer.class), queryDTO.getGrade());
                    predicates.add(predicate);
                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }

}
