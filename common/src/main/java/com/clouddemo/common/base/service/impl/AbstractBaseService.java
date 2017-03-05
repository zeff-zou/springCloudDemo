package com.clouddemo.common.base.service.impl;


import com.clouddemo.common.base.entity.BaseEntity;
import com.clouddemo.common.base.repository.IBaseRepository;
import com.clouddemo.common.base.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.io.Serializable;
import java.util.List;
/**
 * Created by zzf on 2017/2/14.
 */
public abstract class AbstractBaseService<M extends BaseEntity, ID extends Serializable> implements IBaseService<M, ID> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected IBaseRepository<M, ID> baseRepository;

    @Autowired
    public void setBaseRepository(IBaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public M save(M m) {
        return this.baseRepository.save(m);
    }

    public M saveAndFlush(M m) {
        m = save(m);
        this.baseRepository.flush();
        return m;
    }

    public M update(M m) {
        return this.baseRepository.save(m);
    }

    public void delete(ID id) {
        this.baseRepository.delete(id);
    }

    public void delete(M m) {
        this.baseRepository.delete(m);
    }

    public void delete(List<ID> ids) {
        this.baseRepository.delete(ids);
    }

    public M findOne(ID id) {
        return this.baseRepository.findOne(id);
    }

    public boolean exists(ID id) {
        return this.baseRepository.exists(id);
    }

    public long count() {
        return this.baseRepository.count();
    }

    public List<M> findAll() {
        return this.baseRepository.findAll();
    }

    public List<M> findAll(Sort sort) {
        return this.baseRepository.findAll(sort);
    }

    public Page<M> findAll(Pageable pageable) {
        return this.baseRepository.findAll(pageable);
    }

}
