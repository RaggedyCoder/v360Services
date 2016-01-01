package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.Attachment;

public interface AttachmentService {

	public Attachment create(Attachment attachment);

	public Attachment findOne(Long id);

	public List<Attachment> findAll();

	Page<Attachment> findAll(Pageable pageable);

	public long count();

	public void update(Attachment attachment);

}
