package com.online.icourse.business.dto.request;

import lombok.Data;

/**
 * @ClassName ChapterInfo
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/1 0001
 * @Version V1.0
 **/
@Data
public class ChapterInfo {
    Long id;
    String chapterName;
    Long chapterPid;
}
