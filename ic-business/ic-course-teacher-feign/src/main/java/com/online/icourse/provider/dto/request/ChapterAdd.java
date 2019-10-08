package com.online.icourse.provider.dto.request;

import lombok.Data;

/**
 * @ClassName ChapterAdd
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
@Data
public class ChapterAdd {
    String chapterName;
    Long chapterPid;
    Long courseId;
}
