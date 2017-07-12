package com.imooc.model;

/**
 * Created by zxw on 2017/6/21.
 */
public class DocDrawing {

    private Long common_id;

    private Long parentid;

    private String picnumber;

    private String picname;

    private String version;

    private String reservenumber;

    private String annex;

    private String editor;

    private String collator;

    private String auditor;

    private String reader;

    private String itemperson;

    private String specialperson;

    private String projectnumber;

    private String projectname;

    private String itemname;

    private String phdate;

    private String creator;

    private String guidangdate;

    private Long pltid;

    private Long dituzbr;

    private String designmater;

    private String specname;

    private String phrasename;

    private String projectnumber1;

    private String barcode;

    private String convertpapertype;

    public DocDrawing() {
    }

    public DocDrawing(Long common_id, Long parentid, String picnumber, String picname, String version, String reservenumber, String annex, String editor, String collator, String auditor, String reader, String itemperson, String specialperson, String projectnumber, String projectname, String itemname, String phdate, String creator, String guidangdate, Long pltid, Long dituzbr, String designmater, String specname, String phrasename, String projectnumber1, String barcode, String convertpapertype) {
        this.common_id = common_id;
        this.parentid = parentid;
        this.picnumber = picnumber;
        this.picname = picname;
        this.version = version;
        this.reservenumber = reservenumber;
        this.annex = annex;
        this.editor = editor;
        this.collator = collator;
        this.auditor = auditor;
        this.reader = reader;
        this.itemperson = itemperson;
        this.specialperson = specialperson;
        this.projectnumber = projectnumber;
        this.projectname = projectname;
        this.itemname = itemname;
        this.phdate = phdate;
        this.creator = creator;
        this.guidangdate = guidangdate;
        this.pltid = pltid;
        this.dituzbr = dituzbr;
        this.designmater = designmater;
        this.specname = specname;
        this.phrasename = phrasename;
        this.projectnumber1 = projectnumber1;
        this.barcode = barcode;
        this.convertpapertype = convertpapertype;
    }



    @Override
    public String toString() {
        return "DocDrawing{" +
                "common_id=" + common_id +
                ", parentid=" + parentid +
                ", picnumber='" + picnumber + '\'' +
                ", picname='" + picname + '\'' +
                ", version='" + version + '\'' +
                ", reservenumber='" + reservenumber + '\'' +
                ", annex='" + annex + '\'' +
                ", editor='" + editor + '\'' +
                ", collator='" + collator + '\'' +
                ", auditor='" + auditor + '\'' +
                ", reader='" + reader + '\'' +
                ", itemperson='" + itemperson + '\'' +
                ", specialperson='" + specialperson + '\'' +
                ", projectnumber='" + projectnumber + '\'' +
                ", projectname='" + projectname + '\'' +
                ", itemname='" + itemname + '\'' +
                ", phdate='" + phdate + '\'' +
                ", creator='" + creator + '\'' +
                ", guidangdate='" + guidangdate + '\'' +
                ", pltid=" + pltid +
                ", dituzbr=" + dituzbr +
                ", designmater='" + designmater + '\'' +
                ", specname='" + specname + '\'' +
                ", phrasename='" + phrasename + '\'' +
                ", projectnumber1='" + projectnumber1 + '\'' +
                ", barcode='" + barcode + '\'' +
                ", convertpapertype='" + convertpapertype + '\'' +
                '}';
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getConvertpapertype() {
        return convertpapertype;
    }

    public void setConvertpapertype(String convertpapertype) {
        this.convertpapertype = convertpapertype;
    }

    public String getProjectnumber1() {
        return projectnumber1;
    }

    public void setProjectnumber1(String projectnumber1) {
        this.projectnumber1 = projectnumber1;
    }

    public Long getCommon_id() {
        return common_id;
    }

    public void setCommon_id(Long common_id) {
        this.common_id = common_id;
    }

    public Long getParentid() {
        return parentid;
    }

    public void setParentid(Long parentid) {
        this.parentid = parentid;
    }

    public String getPicnumber() {
        return picnumber;
    }

    public void setPicnumber(String picnumber) {
        this.picnumber = picnumber;
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReservenumber() {
        return reservenumber;
    }

    public void setReservenumber(String reservenumber) {
        this.reservenumber = reservenumber;
    }

    public String getAnnex() {
        return annex;
    }

    public void setAnnex(String annex) {
        this.annex = annex;
    }

    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public String getCollator() {
        return collator;
    }

    public void setCollator(String collator) {
        this.collator = collator;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getReader() {
        return reader;
    }

    public void setReader(String reader) {
        this.reader = reader;
    }

    public String getItemperson() {
        return itemperson;
    }

    public void setItemperson(String itemperson) {
        this.itemperson = itemperson;
    }

    public String getSpecialperson() {
        return specialperson;
    }

    public void setSpecialperson(String specialperson) {
        this.specialperson = specialperson;
    }

    public String getProjectnumber() {
        return projectnumber;
    }

    public void setProjectnumber(String projectnumber) {
        this.projectnumber = projectnumber;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getPhdate() {
        return phdate;
    }

    public void setPhdate(String phdate) {
        this.phdate = phdate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getGuidangdate() {
        return guidangdate;
    }

    public void setGuidangdate(String guidangdate) {
        this.guidangdate = guidangdate;
    }

    public Long getPltid() {
        return pltid;
    }

    public void setPltid(Long pltid) {
        this.pltid = pltid;
    }

    public Long getDituzbr() {
        return dituzbr;
    }

    public void setDituzbr(Long dituzbr) {
        this.dituzbr = dituzbr;
    }

    public String getDesignmater() {
        return designmater;
    }

    public void setDesignmater(String designmater) {
        this.designmater = designmater;
    }

    public String getSpecname() {
        return specname;
    }

    public void setSpecname(String specname) {
        this.specname = specname;
    }

    public String getPhrasename() {
        return phrasename;
    }

    public void setPhrasename(String phrasename) {
        this.phrasename = phrasename;
    }
}
