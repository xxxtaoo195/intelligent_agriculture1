package com.topwulian.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件信息
 */
@Data
public class FileInfo implements Serializable {

	private static final long serialVersionUID = -1438078028040922174L;

	private String id;
	/** 原始文件名 */
	private String name;
	private Boolean isImg;
	private String contentType;
	private long size;
	private String path;
	private String url;
	/**
	 * 文件来源
	 * 
	 * @see FileSource
	 */
	private String source;
	private Date createTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getImg() {
		return isImg;
	}

	public void setImg(Boolean img) {
		isImg = img;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
