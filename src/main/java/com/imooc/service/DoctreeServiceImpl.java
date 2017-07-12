package com.imooc.service;

import com.imooc.model.Doctree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zxw on 2017/6/21.
 */
@Service
public class DoctreeServiceImpl implements DoctreeService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<Doctree> findBySql(String sql, Object[] params) {

        return jdbcTemplate.query(sql, params, new DoctreeRowMapper());
    }
}

class DoctreeRowMapper implements RowMapper<Doctree> {
    @Override
    public Doctree mapRow(ResultSet rs, int i) throws SQLException {
        Doctree doctree = new Doctree();
        doctree.setMainid(rs.getLong("mainid"));
        doctree.setNodename(rs.getString("nodename"));
        doctree.setNodetype(rs.getInt("nodetype"));
        doctree.setParentid(rs.getLong("parentid"));

        return doctree;
    }
}
