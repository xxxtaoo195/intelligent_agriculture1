package com.topwulian.model;

/**
 * 通知实体类
 */
public class Notice extends BaseEntity<Long> {

	private static final long serialVersionUID = -4401913568806243090L;

	private String title; //标题
	private String content; //内容
	private Integer status; //状态

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public interface Status {
		int DRAFT = 0;
		int PUBLISH = 1;
	}

}
