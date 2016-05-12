package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.Client;
import com.thevolume360.domain.search.ClientSearchCmd;

public interface ClientService {

	public Client create(Client client);

	public Client findOne(Long id);

	public List<Client> findAll();

	Page<Client> findAll(Pageable pageable);

	public long count();

	public void update(Client client);

	public Page<Client> findClientBySearchCmd(ClientSearchCmd clientSearchCmd, Pageable pageable);
}
