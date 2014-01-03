/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.koobe.common.data.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @author lyhcode
 */
@Entity
public class BookChannel {

    @Id
    private Long id;
    private String title;
    private String link;
    private String description;
    private String image;
    private String language;
    private String category;
    private Date lastBuildDate;
    private Date pubDate;
    private Integer ttl;
    private Integer skipDays;
    private Integer skipHours;
    private String webMaster;
    private Integer rating;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * @return the language
     */
    public String getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the lastBuildDate
     */
    public Date getLastBuildDate() {
        return lastBuildDate;
    }

    /**
     * @param lastBuildDate the lastBuildDate to set
     */
    public void setLastBuildDate(Date lastBuildDate) {
        this.lastBuildDate = lastBuildDate;
    }

    /**
     * @return the pubDate
     */
    public Date getPubDate() {
        return pubDate;
    }

    /**
     * @param pubDate the pubDate to set
     */
    public void setPubDate(Date pubDate) {
        this.pubDate = pubDate;
    }

    /**
     * @return the ttl
     */
    public Integer getTtl() {
        return ttl;
    }

    /**
     * @param ttl the ttl to set
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     * @return the skipDays
     */
    public Integer getSkipDays() {
        return skipDays;
    }

    /**
     * @param skipDays the skipDays to set
     */
    public void setSkipDays(Integer skipDays) {
        this.skipDays = skipDays;
    }

    /**
     * @return the skipHours
     */
    public Integer getSkipHours() {
        return skipHours;
    }

    /**
     * @param skipHours the skipHours to set
     */
    public void setSkipHours(Integer skipHours) {
        this.skipHours = skipHours;
    }

    /**
     * @return the webMaster
     */
    public String getWebMaster() {
        return webMaster;
    }

    /**
     * @param webMaster the webMaster to set
     */
    public void setWebMaster(String webMaster) {
        this.webMaster = webMaster;
    }

    /**
     * @return the rating
     */
    public Integer getRating() {
        return rating;
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
