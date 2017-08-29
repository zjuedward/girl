package com.imooc.controller;

import com.imooc.model.DocDrawing;
import com.imooc.model.Doctree;
import com.imooc.service.DocDrawingService;
import com.imooc.service.DoctreeService;
import com.imooc.util.ExcelUtil;
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
    @Value("${application.node_sjwjjl}")
    private String node_sjwjjl;
    @Value("${application.node_tz}")
    private String node_tz;
    @Value("${application.download_path}")
    private String download_path;
    @Value("${application.is_write_excel}")
    private String is_write_excel;

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
        List<Doctree> list = doctreeService.findBySql("select * from doctree where nodename like '%2017%' and nodetype = 10", null);
        if (list != null && list.size() > 0) {
            // 拿到年节点的mainid
            long mid = list.get(0).getMainid();
            List<Doctree> projectList = doctreeService.findBySql("select * from doctree where parentid = ? order by nodename", new Object[] {mid});

            return projectList;
        }

        return null;
    }



    @RequestMapping(value = "q555")
    public String q555(@RequestParam long pid, @RequestParam String pname) {

        //List<Doctree> itemList = doctreeService.findBySql("select * from doctree where ancestor like ?",
        //        new Object[]{"%" + pid + "%"});

        int count = 0;

        String yearroot = "";

        // 2017年
        List<Doctree> list = doctreeService.findBySql("select * from doctree where nodename like '%2017%' and nodetype = 10", null);
        if (list != null && list.size() > 0) {
            yearroot = list.get(0).getNodename();
        }

        List<Doctree> rootpathList = doctreeService.findBySql("select * from doctree where mainid = ?",
                new Object[]{pid});

        String root = "";

        if (rootpathList.size() != 0) {
            root = download_path + "\\" + yearroot + "\\" + rootpathList.get(0).getNodename();
        }

        // 图纸节点
        List<Doctree> drawingList = doctreeService.findBySql("select * from doctree where ancestor like ? and nodetype = ?",
                new Object[]{"%" + pid + "%", 5});

        List<DocDrawing> allFileInfoList = new ArrayList<>();

        for (int t = 0; t < drawingList.size(); t++) {

            List<DocDrawing> fileInfoList = docDrawingService.findBySql("select * from doc_drawing where versioncommonid = ?",
                    new Object[]{drawingList.get(t).getMainid()});



            StringBuilder folderpath = new StringBuilder();
            folderpath.append(download_path);
            String[] parts = drawingList.get(t).getAncestor().split("\\$");
            for (int qq = 2; qq < parts.length; qq++) {
                List<Doctree> pathlist = doctreeService.findBySql("select * from doctree where mainid = ?",
                        new Object[]{Long.parseLong(parts[qq])});
                if (pathlist.size() != 0) {
                    folderpath.append(pathlist.get(0).getNodename());
                    folderpath.append("\\");
                }
            }

            for (int kk = 0; kk < fileInfoList.size(); kk ++) {
                fileInfoList.get(kk).setPath(folderpath.toString());
            }

            allFileInfoList.addAll(fileInfoList);

            // 建立文件夹
            File pfile = new File(folderpath.toString());
            //System.out.println("fuck: " + folderpath.toString());
            if (!pfile.exists()) {

                if (!pfile.mkdirs()) {
                    System.out.println("创建目标文件所在目录失败！");
                    //return false;
                }
            }

            for (int p = 0; p < fileInfoList.size(); p++) {
                // 下载对应文件，如归档文件、底图归档文件、plt文件等
                try {
                    // 下载文件

                    File file = DownloadFile.downloadFile(Integer.parseInt(fileInfoList.get(p).getReservenumber().replace("$", "")),
                            new File(folderpath.toString()));
                    count++;
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (HttpDowloadFile httpDowloadFile) {
                    httpDowloadFile.printStackTrace();
                }
            }

        }

        if (is_write_excel.equals("yes")) {
            ExcelUtil.createExcelFile(root + ".xlsx");
            // 写图纸信息
            ExcelUtil.writeExcel(allFileInfoList, 21, root + ".xlsx");
        }



        return "all files:" + count;

    }


    @RequestMapping(value = "/beginDownload")
    public String beginDownload(@RequestParam long pid, @RequestParam String pname) {
        // 方案设计、初步设计、施工图设计，值放于springboot配置文件中
        //String[] phaseArray = {"方案设计", "初步设计", "施工图设计"};

        int count = 0;

        StringBuilder sb_path = new StringBuilder();
        StringBuilder sb_item = new StringBuilder();
        StringBuilder sb_major = new StringBuilder();

        //for (int i = 0; i < phaseArray.length; i ++) {
            // 路径
            sb_path = new StringBuilder();
            sb_path.append(download_path);
            sb_path.append(pname);
            sb_path.append("\\");
            // 阶段列表
            //List<Doctree> phaseList = doctreeService.findBySql("select * from doctree where parentid = ? and nodename = ?",
            //        new Object[] {pid, phaseArray[i]});
            // 存在
            //if (phaseList != null && phaseList.size() > 0) {
                //sb_path.append(phaseList.get(0).getNodename());
                sb_path.append("\\");
                // 子项列表
                List<Doctree> itemList = doctreeService.findBySql("select * from doctree where parentid = ?",
                        new Object[] {pid});
                // 遍历每个子项
                for (int j = 0; j < itemList.size(); j ++) {
                    sb_item = new StringBuilder();
                    sb_item.append(sb_path.toString());
                    sb_item.append(itemList.get(j).getNodename());
                    sb_item.append("\\");
                    // 设计文件记录节点
                    List<Doctree> sjwjList = doctreeService.findBySql("select * from doctree where parentid = ? and nodename = ?",
                            new Object[] {itemList.get(j).getMainid(), node_sjwjjl});
                    if (sjwjList != null && sjwjList.size() > 0) {
                        sb_item.append(sjwjList.get(0).getNodename());
                        sb_item.append("\\");
                        // 图纸节点
                        List<Doctree> tzList = doctreeService.findBySql("select * from doctree where parentid = ? and nodename = ?",
                                new Object[] {sjwjList.get(0).getMainid(), node_tz});

                        if (tzList != null && sjwjList.size() > 0) {
                            sb_item.append(tzList.get(0).getNodename());
                            sb_item.append("\\");
                            // 专业节点
                            List<Doctree> majorList = doctreeService.findBySql("select * from doctree where parentid = ? and nodetype = 9",
                                    new Object[] {tzList.get(0).getMainid()});
                            if (majorList != null && majorList.size() > 0) {
                                // 遍历每个专业
                                for (int k = 0; k < majorList.size(); k ++) {
                                    sb_major = new StringBuilder();
                                    sb_major.append(sb_item.toString());
                                    sb_major.append(majorList.get(k).getNodename());
                                    sb_major.append("\\");
                                    // 获取每个专业节点下的图纸节点
                                    List<Doctree> drawingList = doctreeService.findBySql("select * from doctree where parentid = ?",
                                            new Object[] {majorList.get(k).getMainid()});

                                    // 建立文件夹
                                    File pfile = new File(sb_major.toString() );
                                    if(!pfile.exists()) {

                                        if(!pfile.mkdirs()) {
                                            System.out.println("创建目标文件所在目录失败！");
                                            //return false;
                                        }
                                    }

                                    List<DocDrawing> allFileInfoList = new ArrayList<DocDrawing>();

                                    for (int t = 0; t < drawingList.size(); t ++) {
                                        List<DocDrawing> fileInfoList = docDrawingService.findBySql("select * from doc_drawing where versioncommonid = ?",
                                                new Object[] {drawingList.get(t).getMainid()});

                                        allFileInfoList.addAll(fileInfoList);
                                        // 下载前先删除
                                        /*File tempdir = new File(sb_major.toString());
                                        if (tempdir.exists()) {
                                            File[] files = tempdir.listFiles();
                                            for (int q = 0; q < files.length; q ++) {
                                                files[q].delete();
                                            }
                                        }*/

                                        //File infofile = new File(sb_major.toString() + "info.xls");


                                        for (int p = 0; p < fileInfoList.size(); p ++) {
                                            // 下载对应文件，如归档文件、底图归档文件、plt文件等
                                            try {
                                                // 下载文件

                                                File file = DownloadFile.downloadFile(Integer.parseInt(fileInfoList.get(p).getReservenumber().replace("$", "")),
                                                        new File(sb_major.toString()));
                                                count ++;
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            } catch (HttpDowloadFile httpDowloadFile) {
                                                httpDowloadFile.printStackTrace();
                                            }
                                        }

                                    }

                                    ExcelUtil.createExcelFile(sb_major.toString() + "info.xlsx");
                                    // 写图纸信息
                                    ExcelUtil.writeExcel(allFileInfoList, 20, sb_major.toString() + "info.xlsx");

                                }
                            }
                        }

                    }
                }
            //}
        //}


        return "all files number: " + count;

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
                    new File(download_path));

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
