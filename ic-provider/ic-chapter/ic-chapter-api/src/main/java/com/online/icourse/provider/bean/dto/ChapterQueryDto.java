package com.online.icourse.provider.bean.dto;

import com.online.icourse.provider.bean.Chapter;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChapterQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/2 0002
 * @Version V1.0
 **/
@Data
public class ChapterQueryDto {
    Long chapterPid;
    Long courseId;
    public static Specification<Chapter> getWhere(ChapterQueryDto queryDTO) {
        return new Specification<Chapter>() {
            @Override
            public Predicate toPredicate(Root<Chapter> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<Predicate>();

                if (queryDTO.getChapterPid() != null) {
                    Predicate predicate = cb.equal(root.get("chapterPid").as(Long.class), queryDTO.getChapterPid());
                    predicates.add(predicate);
                }
                if (queryDTO.getCourseId() != null) {
                    Predicate predicate = cb.equal(root.get("courseId").as(Long.class), queryDTO.getCourseId());
                    predicates.add(predicate);
                }
                return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

    }

}
