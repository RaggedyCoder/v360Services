package com.thevolume360.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.AuditLog;

@Repository
@Transactional
public interface AuditLogDao extends JpaRepository<AuditLog, Long> {
}
