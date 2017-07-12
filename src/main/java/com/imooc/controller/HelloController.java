package com.imooc.controller;

import com.imooc.model.DocDrawing;
import com.imooc.model.Doctree;
import com.imooc.service.DocDrawingService;
import com.imooc.service.DoctreeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import net.devin.util.http.DownloadFile;
import net.devin.util.http.HttpDowloadFile;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Spring Boot HelloWorld案例
 *
 * Created by zxw on 2017/2/22.
 */
@RestController
public class HelloController {

    @Autowired
    DoctreeService doctreeService;
    @Autowired
    DocDrawingService docDrawingService;

    @Value("${application.hello}")
    private String hello;
    @Value("{application.node_sjwjjl}")
    private String node_sjwjjl;
    @Value("{application.node_tz}")
    private String node_tz;

    @ApiOperation(value="和屠美人打招呼", notes="")
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String say(@RequestParam String projectNumber) {
        ///return "Hello 屠美人！";

        //List<Doctree> list = doctreeService.findByProjectNumber("select * from doctree where ancestor like ? and nodetype = ?",
        //        new Object[] {"%12854743%", 17});

        List<Doctree> list = doctreeService.findBySql("select * from doctree where nodename like ? and nodetype = ?",
                new Object[] {"%" + projectNumber + "%", 7});



        return list.get(0).getNodename();
    }

    @RequestMapping(value = "/findProjectInfo")
    public List<Doctree> findProjectInfo() {
        // 2017年
        List<Doctree> list = doctreeService.findBySql("select * from doctree where nodename = ?", new Object[] {"2017年"});
        if (list != null && list.size() > 0) {
            // 拿到年节点的mainid
            long mid = list.get(0).getMainid();
            List<Doctree> projectList = doctreeService.findBySql("select * from doctree where parentid = ? order by nodename", new Object[] {mid});

            return projectList;
        }

        return null;
    }

    public String beginDownload(@RequestParam long pid) {
        // 方案设计、初步设计、施工图设计，值放于springboot配置文件中
        String[] phaseArray = {};

        for (int i = 0; i < phaseArray.length; i ++) {
            // 阶段列表
            List<Doctree> phaseList = doctreeService.findBySql("select * from doctree where parentid = ? and nodename = ?",
                    new Object[] {pid, phaseArray[i]});
            // 存在
            if (phaseList != null && phaseList.size() > 0) {
                // 子项列表
                List<Doctree> itemList = doctreeService.findBySql("select * from doctree where parentid = ? and nodetype = 3",
                        new Object[] {phaseList.get(0).getMainid()});
                // 遍历每个子项
                for (int j = 0; j < itemList.size(); j ++) {
                    // 设计文件记录节点
                    List<Doctree> sjwjList = doctreeService.findBySql("select * from doctree where parentid = ? and nodename = ?",
                            new Object[] {itemList.get(j).getMainid(), node_sjwjjl});
                    if (sjwjList != null && sjwjList.size() > 0) {
                        // 图纸节点
                        List<Doctree> tzList = doctreeService.findBySql("select * from doctree where parentid = ? and nodename = ?",
                                new Object[] {sjwjList.get(0).getMainid(), node_tz});

                        if (tzList != null && sjwjList.size() > 0) {
                            // 专业节点
                            List<Doctree> majorList = doctreeService.findBySql("select * from doctree where parentid = ? and nodetype = 9",
                                    new Object[] {tzList.get(0).getMainid()});
                            if (majorList != null && majorList.size() > 0) {
                                // 遍历每个专业
                            }
                        }

                    }
                }
            }
        }


        return "";

    }


    // 下载图纸文件及信息
    @RequestMapping(value = "/dealDrawingInfo", method = RequestMethod.GET)
    public String dealDrawingInfo() throws HttpException, IOException, HttpDowloadFile {

        // 输入：图纸节点common_id，从doc_drawing中获取信息
        List<DocDrawing> drawingList = docDrawingService.findBySql("select * from doc_drawing where common_id = ?",
                new Object[] {12701069});

        if (drawingList != null && drawingList.size() > 0) {

            // 下载对应文件，如归档文件、底图归档文件、plt文件等
            File file = DownloadFile.downloadFile(Integer.parseInt(drawingList.get(0).getReservenumber().replace("$", "")),
                    new File("H:\\zxwtjq\\"));

            return drawingList.get(0).toString();
        } else {
            return "sorry, nothing find.";
        }


    }



    @ApiOperation(value="创建用户", notes="根据用户名创建用户")
    @ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String")
    @RequestMapping(value="/info", method=RequestMethod.GET)
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @ApiOperation(value="获取用户列表", notes="自定义用户列表")
    @RequestMapping(value="/list", method=RequestMethod.GET)
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 1; i <= 5; i++) {
            map = new HashMap<>();
            map.put("name", "Shanhy-" + i);
            list.add(map);
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("nihao");
    }
}
