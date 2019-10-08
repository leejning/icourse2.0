package com.online.icourse.provider.jpa;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * @ClassName MySimpleJpaRepository
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/10/4 0004
 * @Version V1.0
 **/
//@Repository
//@Transactional(readOnly = true)
public class MySimpleJpaRepository<T,ID> extends SimpleJpaRepository<T,ID> {
    public MySimpleJpaRepository(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }
}
