package com.online.icourse.business.controller;

import com.online.icourse.business.api.ChapterControllerApi;
import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.api.ChapterService;
import com.online.icourse.provider.bean.Chapter;
import com.online.icourse.provider.bean.dto.ChapterQueryDto;
import com.online.icourse.business.dto.request.ChapterAdd;
import com.online.icourse.business.dto.request.ChapterInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ChapterControllrt
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
@RestController
@RequestMapping("chapter")
public class ChapterControllrt implements ChapterControllerApi {
    @Reference(version = "2.0.0")
    private ChapterService chapterService;

    @PostMapping
    @Override
    public ResponseResult<Chapter> createChapter(@RequestBody ChapterAdd chapterAdd) {
        Chapter chapter = new Chapter();
        BeanUtils.copyProperties(chapterAdd,chapter);
        Long id = chapterService.createChapter(chapter);
        if (id == -1) {
            ResponseResult.FAIL(ChapterCode.ADD_CHAPTER_FAIL);
        }
        chapter.setId(id);
        return ResponseResult.SUCCESS(chapter);
    }

    @PutMapping("{chapterId}")
    @Override
    public ResponseResult<Chapter> modifyChapter(@PathVariable Long chapterId,@RequestBody ChapterInfo chapterInfo) {
        chapterInfo.setId(chapterId);
        Boolean res = chapterService.modifyChapter(chapterInfo);
        if(res){
            ResponseResult.FAIL(ChapterCode.MODIFY_CHAPTER_INFO_FAIL);
        }
        return ResponseResult.SUCCESS("修改章节信息成功");
    }

    @DeleteMapping("{chapterId}")
    @Override
    public ResponseResult<Chapter> deleteChapter(Long chapterId) {
        Chapter chapter = chapterService.deleteChapter(chapterId);
        /*if(!res){
            ResponseResult.FAIL(ChapterCode.DELETE_CHAPTER_INFO_FAIL);
        }*/
        return ResponseResult.SUCCESS(chapter);
    }

    @GetMapping("{chapterPId}")
    @Override
    public QueryResponseResult<Chapter> getClazzChapter(ChapterQueryDto chapterQueryDto) {
        QueryResult<Chapter> queryResult = chapterService.findByJpa(chapterQueryDto);
        return QueryResponseResult.SUCCESS(queryResult);
    }
}
