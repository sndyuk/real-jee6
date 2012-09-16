package com.sndyuk.jee6.persistence.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.sndyuk.jee6.domain.core.Job;
import com.sndyuk.jee6.persistence.Dao;

@Entity
@Table(name = Dao.SCHEME_SECURE_DOMAIN_PREFIX + "M_TASK")
@SuppressWarnings("serial")
public class SimpleTaskEntity extends Job implements Serializable {

	private final AtomicReference<String> statusMessage = new AtomicReference<>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name", nullable = false, length = 100)
	private String name;

	@Column(name = "message", nullable = true, length = 1024)
	private String message;

	@Column(name = "closed", nullable = false)
	private boolean closed;

	@Column(name = "create_datetime", nullable = false)
	private Date createDatetime;

	public void setStatusMessage(String statusMessage) {
		this.statusMessage.set(statusMessage);
		afterChangeState();
	}

	@Override
	public String getStatusMessage() {
		return statusMessage.get();
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
		afterChangeState();
	}

	@Override
	public boolean isClosed() {
		return closed;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Date createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
	}
}
