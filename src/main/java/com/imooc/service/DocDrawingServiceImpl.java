package com.imooc.service;

import com.imooc.model.DocDrawing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by zxw on 2017/6/26.
 */
@Service
public class DocDrawingServiceImpl implements DocDrawingService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<DocDrawing> findBySql(String sql, Object[] params) {
        return jdbcTemplate.query(sql, params, new DocDrawingRowMapper());
    }
}

class DocDrawingRowMapper implements RowMapper<DocDrawing> {
    @Override
    public DocDrawing mapRow(ResultSet rs, int i) throws SQLException {
        DocDrawing docDrawing = new DocDrawing();

        docDrawing.setCommon_id(rs.getLong("common_id"));
        docDrawing.setParentid(rs.getLong("parentid"));
        docDrawing.setPicnumber(rs.getString("picnumber"));
        docDrawing.setPicname(rs.getString("picname"));
        docDrawing.setVersion(rs.getString("version"));
        docDrawing.setReservenumber(rs.getString("reservenumber"));
        docDrawing.setAnnex(rs.getString("annex"));
        docDrawing.setEditor(rs.getString("editor"));
        docDrawing.setCollator(rs.getString("collator"));
        docDrawing.setReader(rs.getString("reader"));
        docDrawing.setItemperson(rs.getString("itemperson"));
        docDrawing.setSpecialperson(rs.getString("specialperson"));
        docDrawing.setProjectnumber(rs.getString("projectnumber"));
        docDrawing.setProjectname(rs.getString("projectname"));
        docDrawing.setItemname(rs.getString("itemname"));
        docDrawing.setPhdate(rs.getString("phdate"));
        docDrawing.setCreator(rs.getString("creator"));
        docDrawing.setGuidangdate(rs.getString("guidangdate"));
        docDrawing.setPltid(rs.getLong("pltid"));
        docDrawing.setDituzbr(rs.getLong("dituzbr"));
        docDrawing.setDesignmater(rs.getString("designmater"));
        docDrawing.setSpecname(rs.getString("specname"));
        docDrawing.setPhrasename(rs.getString("phrasename"));
        docDrawing.setProjectnumber1(rs.getString("projectnumber1"));
        docDrawing.setAuditor(rs.getString("auditor"));
        docDrawing.setBarcode(rs.getString("barcode"));
        docDrawing.setConvertpapertype(rs.getString("convertpapertype"));

        return docDrawing;
    }
}