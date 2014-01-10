package com.koobe.common.data.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * Draft Book Master Entity
 */
@Entity
public class Draft {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Date dateCreated;

    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        setDateCreated(new Date());
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
}
