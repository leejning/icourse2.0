package com.online.icourse.provider.api;


import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.bean.Chapter;
import com.online.icourse.provider.bean.dto.ChapterQueryDto;
import com.online.icourse.business.dto.request.ChapterInfo;

/**
 * @ClassName ChapterService
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
public interface ChapterService {
    QueryResult<Chapter> findByJpa(ChapterQueryDto chapterQueryDto);

    Chapter deleteChapter(Long chapterId);

    Boolean modifyChapter(ChapterInfo chapterInfo);

    Long createChapter(Chapter chapter);
}
