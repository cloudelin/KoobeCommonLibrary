package com.koobe.common.data.domain;

import java.io.Serializable;

import javax.persistence.*;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the Draft database table.
 * 
 */
@Entity
@NamedQuery(name="Draft.findAll", query="SELECT d FROM Draft d")
public class Draft implements Serializable, com.google.gwt.user.client.rpc.IsSerializable {
	private static final long serialVersionUID = 1L;
	
	public enum DraftStatus {
        CONVERTING, READY, PUBLISHED;
    }

    public enum DraftPrivacy {
        PUBLIC, RELATIONS, GROUP, PRIVATE;
    }

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCreated;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdated;

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
	
	//bi-directional many-to-many association to User
	@ManyToMany
	@JoinTable(
		name="UserDraft"
		, joinColumns={
			@JoinColumn(name="draftId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="userId")
			}
		)
	private List<User> users;

	//bi-directional many-to-one association to DraftDetail
	@OneToMany(mappedBy="draft")
	private List<DraftDetail> draftDetails;
	
	@PrePersist
    protected void onCreate() {
        setDateCreated(new Date());
        setLastUpdated(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        setLastUpdated(new Date());
    }

	public Draft() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<DraftDetail> getDraftDetails() {
		return this.draftDetails;
	}

	public void setDraftDetails(List<DraftDetail> draftDetails) {
		this.draftDetails = draftDetails;
	}

	public DraftDetail addDraftDetail(DraftDetail draftDetail) {
		getDraftDetails().add(draftDetail);
		draftDetail.setDraft(this);

		return draftDetail;
	}

	public DraftDetail removeDraftDetail(DraftDetail draftDetail) {
		getDraftDetails().remove(draftDetail);
		draftDetail.setDraft(null);

		return draftDetail;
	}

}