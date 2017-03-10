package com.clouddemo.demoservice.sysuser.repostory.impl;

import com.clouddemo.common.sysuser.entity.SysUser;
import com.clouddemo.demoservice.sysuser.repostory.SysUserRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.util.StringUtils;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zzf on 2017/2/14.
 */
public class SysUserRepositoryImpl implements SysUserRepositoryCustom {

    @Resource
    EntityManager entityManager;
    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page<SysUser> query(Pageable pageable, String searchUser) {
        StringBuffer sql = new StringBuffer();
        sql.append(" FROM SYS_USER a WHERE 1=1");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(searchUser)){
            sql.append(" and ( USER_NAME LIKE :userName");
            paramMap.put(SysUser.USER_NAME, "%" + searchUser + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Long total = new NamedParameterJdbcTemplate(this.jdbcTemplate).queryForObject("select count(*)"+ sql, paramMap, Long.class);
        List<SysUser> sysAppList = new NamedParameterJdbcTemplate(this.jdbcTemplate)
                .query("select a.* " + QueryUtils.applySorting(sql.toString(),pageable.getSort(),"a") + " limit " + firstIdx + "," + pageSize, paramMap, new BeanPropertyRowMapper(SysUser.class));
        return new PageImpl<SysUser>(sysAppList, pageable, total);
    }
}
