package com.thevolume360.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.AttachmentDao;
import com.thevolume360.domain.Attachment;
import com.thevolume360.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentDao attachmentDao;

	@Override
	public Attachment create(Attachment attachment) {
		// TODO Auto-generated method stub
		return attachmentDao.save(attachment);
	}

	@Override
	public Attachment findOne(Long id) {
		// TODO Auto-generated method stub
		return attachmentDao.findOne(id);
	}

	@Override
	public List<Attachment> findAll() {
		// TODO Auto-generated method stub
		return attachmentDao.findAll();
	}

	@Override
	public Page<Attachment> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return attachmentDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return attachmentDao.count();
	}

	@Override
	public void update(Attachment attachment) {
		// TODO Auto-generated method stub

	}

}
