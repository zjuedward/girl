package com.imooc.service;

import com.imooc.model.DocDrawing;

import java.util.List;

/**
 * Created by zxw on 2017/6/26.
 */
public interface DocDrawingService {
    List<DocDrawing> findBySql(String sql, Object[] params);
}
