package com.koobe.common.data.domain;

import javax.persistence.*;
import java.util.Date;

/**
 * User and Draft Relationship Entity
 */
@Entity
public class UserDraft {

    public enum UserDraftRelationType {
        OWNER;
    }

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private User user;

    private Draft draft;

    private UserDraftRelationType type;

    private Date dateCreated;

    private Date lastUpdated;

    public UserDraft() {

    }

    public UserDraft(User user, Draft draft) {
        this.user = user;
        this.draft = draft;
        this.type = UserDraftRelationType.OWNER;
    }

    @PrePersist
    protected void onCreate() {
        setDateCreated(new Date());
        setLastUpdated(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        setLastUpdated(new Date());
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Draft getDraft() {
        return draft;
    }

    public void setDraft(Draft draft) {
        this.draft = draft;
    }

    public UserDraftRelationType getType() {
        return type;
    }

    public void setType(UserDraftRelationType type) {
        this.type = type;
    }
}
