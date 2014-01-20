package com.koobe.common.data.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Draft Book Master Entity
 */
@Entity
public class Draft {

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public enum DraftStatus {
        CONVERTING, READY, PUBLISHED;
    }

    public enum DraftPrivacy {
        PUBLIC, RELATIONS, GROUP, PRIVATE;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /**
     * 書名:可輸入文字訊息
     */
    private String name;

    /**
     * 作者：可輸入文字訊息
     */
    private String author;

    /**
     * 簡介：建議輸入200字左右簡介
     */
    private String description;

    /**
     * 處理狀態
     */
    private DraftStatus status;

    /**
     * 處理進度
     */
    private Integer progress;

    @ManyToMany(mappedBy="drafts")
    private List<User> users;

    private Date dateCreated;

    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        setDateCreated(new Date());
        setLastUpdated(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        setLastUpdated(new Date());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public DraftStatus getStatus() {
        return status;
    }

    public void setStatus(DraftStatus status) {
        this.status = status;
    }

    public Integer getProgress() {
        return progress;
    }

    public void setProgress(Integer progress) {
        this.progress = progress;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}