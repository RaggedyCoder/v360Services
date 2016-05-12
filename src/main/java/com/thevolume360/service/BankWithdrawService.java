package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.BankWithdraw;

public interface BankWithdrawService {

	public BankWithdraw create(BankWithdraw bankWithdraw);

	public BankWithdraw findOne(Long id);

	public List<BankWithdraw> findAll();

	Page<BankWithdraw> findAll(Pageable pageable);

	public long count();

	public void update(BankWithdraw bankWithdraw);

}
