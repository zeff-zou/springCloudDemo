package com.clouddemo.common.base.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zzf on 2017/2/14.
 */
@NoRepositoryBean
public interface IBaseRepository<M, ID extends Serializable> extends JpaRepository<M, ID> {
    void delete(List<ID> ids);
}
