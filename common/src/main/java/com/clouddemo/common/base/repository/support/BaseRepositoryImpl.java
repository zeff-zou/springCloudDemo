package com.clouddemo.common.base.repository.support;

import com.clouddemo.common.base.repository.IBaseRepository;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
/**
 * Created by zzf on 2017/2/14.
 */
@Transactional(readOnly = true)
public class  BaseRepositoryImpl<M, ID extends Serializable> extends SimpleJpaRepository<M,ID> implements IBaseRepository<M,ID> {

    private EntityManager entityManager;
    private JpaEntityInformation entityInformation;

    public BaseRepositoryImpl(JpaEntityInformation<M, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
        this.entityInformation = entityInformation;
    }

    public BaseRepositoryImpl(Class<M> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    @Transactional
    public void delete(List<ID> ids) {
        //@TODO使用批量删除方式
        Assert.notNull(ids, "The given Iterable of entities not be null!");
        if(ids.iterator().hasNext()) {
            int count = applyAndBind(QueryUtils.getQueryString("delete from %s x", this.entityInformation.getEntityName()), ids, this.entityManager).executeUpdate();
            if(count < 0){
                throw new RuntimeException("删除失败");
            }
        }
    }

    //灵感从QueryUtils.applyAndBind(...)而来，仅有一行代码不同 builder.append(String.format(" %s.id = ?%d", var10002));
    private Query applyAndBind(String queryString, Iterable entities, EntityManager entityManager) {
        Assert.notNull(queryString);
        Assert.notNull(entities);
        Assert.notNull(entityManager);
        Iterator iterator = entities.iterator();
        if(!iterator.hasNext()) {
            return entityManager.createQuery(queryString);
        } else {
            String alias = QueryUtils.detectAlias(queryString);
            StringBuilder builder = new StringBuilder(queryString);
            builder.append(" where");
            int i = 0;

            while(iterator.hasNext()) {
                iterator.next();
                Object[] var10002 = new Object[]{alias, null};
                ++i;
                var10002[1] = Integer.valueOf(i);
                builder.append(String.format(" %s.id = ?%d", var10002));
                if(iterator.hasNext()) {
                    builder.append(" or");
                }
            }

            Query query = entityManager.createQuery(builder.toString());
            iterator = entities.iterator();
            i = 0;

            while(iterator.hasNext()) {
                ++i;
                query.setParameter(i, iterator.next());
            }

            return query;
        }
    }


}
