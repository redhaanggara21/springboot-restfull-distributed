package com.lsio.springboot.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import static javax.persistence.TemporalType.TIMESTAMP;


@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class Auditable <T> {

	@Column(name = "created_date", updatable = false)
	@Temporal(TIMESTAMP)
	@CreatedDate
    protected Date creationDate;


	@Column(name = "lastMod_date")
	@LastModifiedDate
	@Temporal(TIMESTAMP)
	protected Date lastModifiedDate;

	@CreatedBy
	@Column(name="created_by")
	protected T createdBy;

	@LastModifiedBy
	@Column(name="modified_by")
	protected T modifiedBy;

}