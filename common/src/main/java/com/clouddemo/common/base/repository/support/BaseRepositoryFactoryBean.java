package com.clouddemo.common.base.repository.support;

import com.clouddemo.common.base.repository.IBaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * Created by zzf on 2017/2/14.
 */
public class BaseRepositoryFactoryBean<R extends JpaRepository<M, ID>, M, ID extends Serializable>  extends JpaRepositoryFactoryBean<R, M, ID> {


    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }
}


class BaseRepositoryFactory<M, ID extends Serializable> extends JpaRepositoryFactory {

    private EntityManager entityManager;

    public BaseRepositoryFactory(EntityManager entityManager) {
        super(entityManager);
        this.entityManager = entityManager;
    }

    protected Object getTargetRepository(RepositoryMetadata metadata) {
        Class<?> repositoryInterface = metadata.getRepositoryInterface();

        if (isBaseRepository(repositoryInterface)) {

            JpaEntityInformation<M, ID> entityInformation = getEntityInformation((Class<M>) metadata.getDomainType());
            BaseRepositoryImpl repository = new BaseRepositoryImpl<M, ID>(entityInformation, entityManager);

            return repository;
        }
        return super.getTargetRepository((RepositoryInformation) metadata);
    }

    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
        if (isBaseRepository(metadata.getRepositoryInterface())) {
            return BaseRepositoryImpl.class;
        }
        return super.getRepositoryBaseClass(metadata);
    }

    private boolean isBaseRepository(Class<?> repositoryInterface) {
        return IBaseRepository.class.isAssignableFrom(repositoryInterface);
    }

}