package com.thevolume360.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Attachment extends PersistentObject implements Auditable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1150232290364846797L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	private String fileName;

	@NotNull
	private String mimeType;

	@NotNull
	private String imageCategory;

	@Column(length = 3000)
	private String comment;

	@NotNull
	@Column(length = 300)
	private String path;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn
	private ProjectInfo projectInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getMimeType() {
		return mimeType;
	}

	public void setMimeType(String mimeType) {
		this.mimeType = mimeType;
	}

	public String getImageCategory() {
		return imageCategory;
	}

	public void setImageCategory(String imageCategory) {
		this.imageCategory = imageCategory;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public ProjectInfo getProjectInfo() {
		return projectInfo;
	}

	public void setProjectInfo(ProjectInfo projectInfo) {
		this.projectInfo = projectInfo;
	}

	@Override
	public String toString() {
		return "Attachment [id=" + id + ", fileName=" + fileName + ", mimeType=" + mimeType + ", imageCategory="
				+ imageCategory + ", comment=" + comment + ", path=" + path + "]";
	}

}
