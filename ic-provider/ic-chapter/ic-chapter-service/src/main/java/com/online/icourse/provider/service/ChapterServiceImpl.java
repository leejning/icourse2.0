package com.online.icourse.provider.service;

import com.online.icourse.common.model.response.QueryResult;
import com.online.icourse.provider.api.ChapterService;
import com.online.icourse.provider.bean.Chapter;
import com.online.icourse.provider.bean.dto.ChapterQueryDto;
import com.online.icourse.provider.dao.ChapterDao;
import com.online.icourse.provider.dto.request.ChapterInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * @ClassName ChapterServiceImpl
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
@Service(version = "2.0.0")
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;


    @Override
    public QueryResult<Chapter> findByJpa(ChapterQueryDto chapterQueryDto) {
        List<Chapter> data = chapterDao.findAll(ChapterQueryDto.getWhere(chapterQueryDto));
        return QueryResult.create(data,(long)data.size());
    }

    @Override
    public Chapter deleteChapter(Long chapterId) {
        Optional<Chapter> opt = chapterDao.findById(chapterId);
        if(opt.isPresent()){
            Chapter chapter = opt.get();
            chapterDao.deleteById(chapterId);
            return chapter;
        }
        return null;
    }

    @Override
    public Boolean modifyChapter(ChapterInfo chapterInfo) {
        Optional<Chapter> opt = chapterDao.findById(chapterInfo.getId());
        if(opt.isPresent()){
            Chapter chapter = opt.get();
            BeanUtils.copyProperties(chapterInfo,chapter);
            chapterDao.save(chapter);
            return true;
        }
        return false;
    }

    @Override
    public Long createChapter(Chapter chapter) {
        Chapter save = chapterDao.save(chapter);
        return save.getId();
    }
}
