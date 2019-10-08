package com.online.icourse.provider.service;


import com.mongodb.BasicDBObject;
import com.mongodb.QueryBuilder;
import com.online.icourse.provider.bean.Exercise;
import com.online.icourse.provider.dao.ExerciseDao;
import com.online.icourse.provider.support.CustomSimpleMongoRespriatory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ExerciseServiceImplTest {
    @Autowired
    ExerciseDao exerciseDao;
    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    public void query(){
        /**
         * Respiratory自定义方法
         */
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, 2, sort);
        Page<Exercise> data = exerciseDao.findByCourseId(1L, pageable);
        System.out.println("findByCourseId");
        for (Exercise ex:data
        ) {
            System.out.println(ex);
        }
        Page<Exercise> data2 = exerciseDao.findByContentLike("考试", pageable);
        System.out.println("findByContentLike");
        for (Exercise ex:data2
        ) {
            System.out.println(ex);
        }

        /**
         * example使用
         */
        //构建匹配器
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING) //改变默认字符串匹配方式：模糊查询
                .withIgnoreCase(true) //改变默认大小写忽略方式：忽略大小写
                .withMatcher("content", ExampleMatcher.GenericPropertyMatchers.contains()) //采用“包含匹配”的方式查询
                .withIgnorePaths("answer");  //忽略属性，不参与查询
        //匹配对象
        Exercise exercise = new Exercise();
        exercise.setCourseId(1L);
        exercise.setContent("题");
        //创建实例
        Example<Exercise> example = Example.of(exercise, matcher);
        Page<Exercise> data3 = exerciseDao.findAll(example, pageable);
        System.out.println("by example");
        for (Exercise ex:data3) {
            System.out.println(ex);
        }




    }

    @Test
    public void queryExercise() {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, 1, sort);

        //字段过滤
        BasicDBObject fieldsObject = new BasicDBObject();
        fieldsObject.put("id", true);
        fieldsObject.put("courseId", true);
        fieldsObject.put("createTime", true);
        fieldsObject.put("content", true);
        fieldsObject.put("selectItems", true);
//        fieldsObject.put("answer", true);
        //链式写法
        /*fieldsObject.append("id", true)
                .append("courseId", true)
                .append("content", true)
                .append("createTime", true)
                .append("selectItems", true)
                .append("answer", true);*/

        //动态拼接查询条件
        QueryBuilder queryBuilder = new QueryBuilder();
        Pattern pattern = Pattern.compile("^.*" + "题" + ".*$", Pattern.CASE_INSENSITIVE);
        queryBuilder.and("content").regex(pattern);
        queryBuilder.and("courseId").is(1L);
//        queryBuilder.and("createTime").lessThanEquals(studentReqVO.getCreateTime());
        Query query = new BasicQuery(queryBuilder.get().toString(), fieldsObject.toJson());
        List<Exercise> datas = mongoTemplate.find(query.with(pageable), Exercise.class);
        for (Exercise ex: datas) {
            System.out.println(ex);
        }
        //计算总数
        long total = mongoTemplate.count(query, Exercise.class);
        //分页
        Page<Exercise> studentPage = new PageImpl(datas, pageable, total);
        for (Exercise ex: studentPage) {
            System.out.println(ex);
        }

        System.out.println("自定义Respiratory实现类");
        if(exerciseDao instanceof CustomSimpleMongoRespriatory){
            System.out.println("MySimpleMongoRespriatory");
            CustomSimpleMongoRespriatory myExerciseDao = (CustomSimpleMongoRespriatory)exerciseDao;
            System.out.println("强转成功");
        }
//        Page<Exercise> data = exerciseDao.findAll(query, pageable, Exercise.class);
//        for (Exercise ex: data) {
//            System.out.println(ex);
//        }
    }

    @Test
    public void createExercise() {
        Map<String, String> selectItem = new HashMap<String, String>();
        selectItem.put("A", "选项A");
        selectItem.put("B", "选项B");
        selectItem.put("C", "选项C");
        selectItem.put("D", "选项D");

        Map<String, String> answer = new HashMap<String, String>();
        answer.put("correct", "C");
        answer.put("explain", "长路漫漫，唯剑作伴");

        int k = 1;
        for (Long j = 1L; j <= 3; j++) {
            for (int i = 1; i <= 3; i++) {
                Exercise exercise = new Exercise();
                exercise.setAnswer(answer);
                exercise.setCourseId(j);
                exercise.setCreateTime(new Date());
                exercise.setDifficulty(1);
                exercise.setContent("考试试题" + (k++) + "（）");
                exercise.setSelectItems(selectItem);
                exerciseDao.save(exercise);
            }
        }

    }
}
