package com.online.icourse.provider.bean.dto.query;

import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.online.icourse.common.model.dto.QueryDto;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @ClassName AnswerRecordQueryDto
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/5 0005
 * @Version V1.0
 **/
@Data
public class AnswerRecordQueryDto extends QueryDto {
    private Long clazzId;
    private Long studentId;
    private String studentName;
    private Boolean finish;
    private String id;
    private String hpId;

    public static Query getWhere(AnswerRecordQueryDto queryDto) {
        //定义条件匹配器
        QueryBuilder queryBuilder = new QueryBuilder();
        //设置条件值
        filter(queryDto, queryBuilder);
        return new BasicQuery(queryBuilder.get().toString());
    }

    public static Query getWhere(AnswerRecordQueryDto queryDto, BasicDBObject fields) {
        //定义条件匹配器
        QueryBuilder queryBuilder = new QueryBuilder();
        //设置条件值
        filter(queryDto, queryBuilder);
        return new BasicQuery(queryBuilder.get().toString(), fields.toJson());
    }

    public static QueryBuilder filter(AnswerRecordQueryDto queryDto, QueryBuilder queryBuilder) {
        if (queryDto.getClazzId() != null) {
            queryBuilder.and("clazzId").is(queryDto.getClazzId());
        }
        if (queryDto.getStudentId() != null) {
            queryBuilder.and("studentId").is(queryDto.getStudentId());
        }
        if (StringUtils.isNotEmpty(queryDto.getHpId())) {
            queryBuilder.and("homeworkPublishId").is(queryDto.getHpId());
        }
        return queryBuilder;
    }

    public static BasicDBObject viewList() {
        /**
         * private String id;
         *     private Long studentId;
         *     private String studentName;
         *     private Long clazzId;
         *     private Data finishTime;
         */
        BasicDBObject feild = new BasicDBObject();
        feild.append("studentId", true)
                .append("studentName", true)
                .append("clazzId", true)
                .append("finishTime", true);
        return feild;
    }
}
