package com.online.icourse.provider.bean.dto;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.online.icourse.common.model.dto.QueryDto;
import com.online.icourse.provider.bean.Exercise;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

import java.util.regex.Pattern;

/**
 * @ClassName ExerciseQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/3 0003
 * @Version V1.0
 **/
@Data
public class ExerciseQueryDto extends QueryDto {
    private Long courseId;
    private Long chapterId;
    private Integer difficulty;
    private String content;


    public static Query getWhere(ExerciseQueryDto queryDto){
        //定义条件匹配器
        QueryBuilder queryBuilder = new QueryBuilder();
        //条件值对象
        Exercise exercise = new Exercise();
        //设置条件值
        if (queryDto.getCourseId()!=null) {
            queryBuilder.and("courseId").is(queryDto.getCourseId());
        }
        if (queryDto.getChapterId()!=null) {
            queryBuilder.and("chapterId").is(queryDto.getChapterId());
        }
        if (queryDto.getDifficulty()!=null) {
            queryBuilder.and("difficulty").is(queryDto.getDifficulty());
        }
        if (StringUtils.isNotEmpty(queryDto.getContent())) {
            Pattern pattern = Pattern.compile("^.*" + queryDto.getContent() + ".*$", Pattern.CASE_INSENSITIVE);
            queryBuilder.and("content").regex(pattern);
        }
        return new BasicQuery(queryBuilder.get().toString());
    }
    public static Query getWhere(ExerciseQueryDto queryDto, BasicDBObject fields){
        //定义条件匹配器
        QueryBuilder queryBuilder = new QueryBuilder();
        //条件值对象
        Exercise exercise = new Exercise();
        //设置条件值
        if (queryDto.getCourseId()!=null) {
            queryBuilder.and("courseId").is(queryDto.getCourseId());
        }
        if (queryDto.getDifficulty()!=null) {
            queryBuilder.and("difficulty").is(queryDto.getDifficulty());
        }
        if (StringUtils.isNotEmpty(queryDto.getContent())) {
            Pattern pattern = Pattern.compile("^.*" + queryDto.getContent() + ".*$", Pattern.CASE_INSENSITIVE);
            queryBuilder.and("content").regex(pattern);
        }
        return new BasicQuery(queryBuilder.get().toString(),fields.toJson());
    }

    public static BasicDBObject filterFieldNoAnswer(){
        BasicDBObject fields = new BasicDBObject();
        fields.append("id", true)
                .append("courseId", true)
                .append("content", true)
                .append("selectItems", true);
        return fields;
    }

}
