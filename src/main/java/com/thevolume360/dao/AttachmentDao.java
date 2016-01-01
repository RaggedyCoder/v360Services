package com.thevolume360.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.Attachment;

@Repository
public interface AttachmentDao extends JpaRepository<Attachment, Long> {

}
