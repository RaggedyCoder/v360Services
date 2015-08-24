package com.thevolume360.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.thevolume360.domain.enums.AuditAction;

@Entity
public class AuditLog extends PersistentObject{
    /**
	 * 
	 */
	private static final long serialVersionUID = -1119293594458535045L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long auditLogId;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private AuditAction action;

    @Column(columnDefinition = "TEXT")
    private String detail;
    private Long entityId;
    private String entityName;

    public Long getAuditLogId() {
        return auditLogId;
    }

    public void setAuditLogId(Long auditLogId) {
        this.auditLogId = auditLogId;
    }

    public AuditAction getAction() {
        return action;
    }

    public void setAction(AuditAction action) {
        this.action = action;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public long getEntityId() {
        return entityId;
    }

    public void setEntityId(long entityId) {
        this.entityId = entityId;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
}
