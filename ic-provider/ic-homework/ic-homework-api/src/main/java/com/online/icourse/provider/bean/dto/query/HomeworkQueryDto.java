package com.online.icourse.provider.bean.dto.query;

import com.mongodb.QueryBuilder;
import com.online.icourse.common.model.dto.QueryDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @ClassName HomeworkQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Data
public class HomeworkQueryDto extends QueryDto {
    private String homeworkName;
    private Long courseId;

    public static Query getWhere(HomeworkQueryDto queryDto){
        //定义条件匹配器
        QueryBuilder queryBuilder = new QueryBuilder();
        //条件值对象
        //设置条件值
        if (queryDto.getCourseId()!=null) {
            queryBuilder.and("courseId").is(queryDto.getCourseId());
        }
        if (StringUtils.isNotEmpty(queryDto.getHomeworkName())) {
            queryBuilder.and("homeworkName").is(queryDto.getHomeworkName());
        }
        return new BasicQuery(queryBuilder.get().toString());
    }
}