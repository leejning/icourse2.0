package com.online.icourse.provider.dao;

import com.online.icourse.provider.bean.Chapter;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ChapterDao extends CrudRepository<Chapter,Long>, JpaSpecificationExecutor<Chapter> {
}
