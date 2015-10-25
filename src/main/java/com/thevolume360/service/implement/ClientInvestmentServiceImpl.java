package com.thevolume360.service.implement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.ClientInvestmentDao;
import com.thevolume360.domain.ClientInvestment;
import com.thevolume360.service.ClientInvestmentService;

@Service
public class ClientInvestmentServiceImpl implements ClientInvestmentService {

	static final Logger LOGGER = LoggerFactory.getLogger(ClientInvestmentServiceImpl.class);

	@Autowired
	private ClientInvestmentDao clientInvestmentDao;

	@Override
	public ClientInvestment create(ClientInvestment clientService) {
		// TODO Auto-generated method stub
		return clientInvestmentDao.save(clientService);
	}

	@Override
	public ClientInvestment findOne(Long id) {
		// TODO Auto-generated method stub
		return clientInvestmentDao.findOne(id);
	}

	@Override
	public List<ClientInvestment> findAll() {
		return clientInvestmentDao.findAll();
	}

	@Override
	public Page<ClientInvestment> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientInvestmentDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return clientInvestmentDao.count();
	}

	@Override
	public void update(ClientInvestment clientService) {

	}

}
