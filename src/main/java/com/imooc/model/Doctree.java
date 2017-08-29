package com.imooc.model;

/**
 * Created by zxw on 2017/6/21.
 */
public class Doctree {

    private Long mainid;

    private String nodename;

    private Long parentid;

    private Integer nodetype;

    private String ancestor;

    public Doctree() {
    }

    public Doctree(Long mainid, String nodename, Long parentid, Integer nodetype, String ancestor) {
        this.mainid = mainid;
        this.nodename = nodename;
        this.parentid = parentid;
        this.nodetype = nodetype;
        this.ancestor = ancestor;
    }

    public Long getMainid() {
        return mainid;
    }

    public void setMainid(Long mainid) {
        this.mainid = mainid;
    }

    public String getAncestor() {
        return ancestor;
    }

    public void setAncestor(String ancestor) {
        this.ancestor = ancestor;
    }

    public String getNodename() {
        return nodename;
    }

    public void setNodename(String nodename) {
        this.nodename = nodename;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public Integer getNodetype() {
        return nodetype;
    }

    public void setNodetype(Integer nodetype) {
        this.nodetype = nodetype;
    }
}
