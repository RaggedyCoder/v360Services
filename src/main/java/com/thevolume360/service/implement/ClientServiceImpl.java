package com.thevolume360.service.implement;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.ClientDao;
import com.thevolume360.domain.Client;
import com.thevolume360.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {

	static final Logger LOGGER = LoggerFactory
			.getLogger(ClientServiceImpl.class);

	@Autowired
	private ClientDao clientDao;

	@Override
	public Client create(Client client) {
		// TODO Auto-generated method stub
		return clientDao.save(client);
	}

	@Override
	public Client findOne(Long id) {
		// TODO Auto-generated method stub
		return clientDao.findOne(id);
	}

	@Override
	public List<Client> findAll() {
		return clientDao.findAll();
	}

	@Override
	public Page<Client> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return clientDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return clientDao.count();
	}

	@Override
	public void update(Client client) {

	}

}
