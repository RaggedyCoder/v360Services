package com.thevolume360.domain.enums;

public enum FileType {
	JPG("jpg", "image/jpeg"), JPEG("jpeg", "image/jpeg"), PDF("pdf", "application/pdf"), DOC("doc",
			"application/msword"), DOCX("docx", "application/msword");

	private String extention;
	private String contentType;

	private FileType(String extention, String contentType) {
		this.extention = extention;
		this.contentType = contentType;
	}

	public String getExtention() {
		return extention;
	}

	public void setExtention(String extention) {
		this.extention = extention;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
