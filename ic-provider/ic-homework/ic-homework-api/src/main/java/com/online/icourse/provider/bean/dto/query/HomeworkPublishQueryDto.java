package com.online.icourse.provider.bean.dto.query;

import com.mongodb.QueryBuilder;
import com.online.icourse.common.model.dto.QueryDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @ClassName HomeworkPublishQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
@Data
public class HomeworkPublishQueryDto extends QueryDto {
    private String homeworkId;
    private Long courseId;

    public static Query getWhere(HomeworkPublishQueryDto queryDto){
        //定义条件匹配器
        QueryBuilder queryBuilder = new QueryBuilder();
        //条件值对象
        //设置条件值
        if (queryDto.getCourseId()!=null) {
            queryBuilder.and("courseId").is(queryDto.getCourseId());
        }
        if (StringUtils.isNotEmpty(queryDto.getHomeworkId())) {
            queryBuilder.and("homeworkId").is(queryDto.getHomeworkId());
        }
        return new BasicQuery(queryBuilder.get().toString());
    }
}
