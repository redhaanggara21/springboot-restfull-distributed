package com.lsio.springboot.entities;

import java.sql.Date;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import static javax.persistence.TemporalType.TIMESTAMP;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

	@CreatedDate
	@Temporal(TIMESTAMP)
	@Column(name = "createdAt", nullable = false, columnDefinition = "TIMESTAMP")
    protected Date createdAt;

	
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	@Column(name = "updatedAt", nullable = false, columnDefinition = "TIMESTAMP")
	protected Date updatedAt;

	@CreatedBy
	protected U createdBy;

	@LastModifiedBy
	protected U modifiedBy;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public U getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(U createdBy) {
		this.createdBy = createdBy;
	}

	public U getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(U modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	

}