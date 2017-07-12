package com.imooc.service;

import com.imooc.model.Doctree;

import java.util.List;

/**
 * Created by zxw on 2017/6/21.
 */
public interface DoctreeService {


    List<Doctree> findBySql(String sql, Object[] params);

}
