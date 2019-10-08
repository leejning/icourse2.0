package com.online.icourse.business.api;


import com.online.icourse.common.model.response.QueryResponseResult;
import com.online.icourse.common.model.response.ResponseResult;
import com.online.icourse.provider.bean.Chapter;
import com.online.icourse.provider.bean.dto.ChapterQueryDto;
import com.online.icourse.provider.dto.request.ChapterAdd;
import com.online.icourse.provider.dto.request.ChapterInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @ClassName ChapterControllerApi
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
@Api(value = "课堂学习章节信息管理",description = "课堂学习章节信息管理接口")
public interface ChapterControllerApi {

    @ApiOperation("")
    public ResponseResult<Chapter> createChapter(ChapterAdd chapterAdd);

    @ApiOperation("")
    public ResponseResult<Chapter> modifyChapter(Long chapterId, ChapterInfo chapterInfo);

    @ApiOperation("")
    public ResponseResult<Chapter> deleteChapter(Long chapterId);

    @ApiOperation("")
    public QueryResponseResult<Chapter> getClazzChapter(ChapterQueryDto chapterQueryDto);
}
