package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.ClientInvestment;

public interface ClientInvestmentService {

	public ClientInvestment create(ClientInvestment clientInvestment);

	public ClientInvestment findOne(Long id);

	public List<ClientInvestment> findAll();

	Page<ClientInvestment> findAll(Pageable pageable);

	public long count();

	public void update(ClientInvestment clientInvestment);
}
