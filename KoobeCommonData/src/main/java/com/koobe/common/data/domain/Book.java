/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.koobe.common.data.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author lyhcode
 */
@Entity
public class Book {

    @Id
    private String guid;
    private String name;
    private String bucket;
    private String folder;
    private Integer zipped;
    private String coverId;
    private String coverHref;
    private Integer coverWidth;
    private Integer coverHeight;
    private String koobeLayout;
    private String flipOrder;
    private String kt;
    private Boolean disabled;
    private Date modifyDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the guid
     */
    public String getGuid() {
        return guid;
    }

    /**
     * @param guid the guid to set
     */
    public void setGuid(String guid) {
        this.guid = guid;
    }

    /**
     * @return the bucket
     */
    public String getBucket() {
        return bucket;
    }

    /**
     * @param bucket the bucket to set
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * @return the folder
     */
    public String getFolder() {
        return folder;
    }

    /**
     * @param folder the folder to set
     */
    public void setFolder(String folder) {
        this.folder = folder;
    }

    /**
     * @return the zipped
     */
    public Integer getZipped() {
        return zipped;
    }

    /**
     * @param zipped the zipped to set
     */
    public void setZipped(Integer zipped) {
        this.zipped = zipped;
    }

    /**
     * @return the coverId
     */
    public String getCoverId() {
        return coverId;
    }

    /**
     * @param coverId the coverId to set
     */
    public void setCoverId(String coverId) {
        this.coverId = coverId;
    }

    /**
     * @return the coverHref
     */
    public String getCoverHref() {
        return coverHref;
    }

    /**
     * @param coverHref the coverHref to set
     */
    public void setCoverHref(String coverHref) {
        this.coverHref = coverHref;
    }

    /**
     * @return the coverWidth
     */
    public Integer getCoverWidth() {
        return coverWidth;
    }

    /**
     * @param coverWidth the coverWidth to set
     */
    public void setCoverWidth(Integer coverWidth) {
        this.coverWidth = coverWidth;
    }

    /**
     * @return the coverHeight
     */
    public Integer getCoverHeight() {
        return coverHeight;
    }

    /**
     * @param coverHeight the coverHeight to set
     */
    public void setCoverHeight(Integer coverHeight) {
        this.coverHeight = coverHeight;
    }

    /**
     * @return the koobeLayout
     */
    public String getKoobeLayout() {
        return koobeLayout;
    }

    /**
     * @param koobeLayout the koobeLayout to set
     */
    public void setKoobeLayout(String koobeLayout) {
        this.koobeLayout = koobeLayout;
    }

    /**
     * @return the flipOrder
     */
    public String getFlipOrder() {
        return flipOrder;
    }

    /**
     * @param flipOrder the flipOrder to set
     */
    public void setFlipOrder(String flipOrder) {
        this.flipOrder = flipOrder;
    }

    /**
     * @return the kt
     */
    public String getKt() {
        return kt;
    }

    /**
     * @param kt the kt to set
     */
    public void setKt(String kt) {
        this.kt = kt;
    }

    /**
     * @return the disabled
     */
    public Boolean getDisabled() {
        return disabled;
    }

    /**
     * @param disabled the disabled to set
     */
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * @return the modifyDate
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * @param modifyDate the modifyDate to set
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }
}
