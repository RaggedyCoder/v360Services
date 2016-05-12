package com.thevolume360.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.BankWithdrawDao;
import com.thevolume360.domain.BankWithdraw;
import com.thevolume360.service.BankWithdrawService;

@Service
public class BankWithdrawServiceImpl implements BankWithdrawService {

	@Autowired
	private BankWithdrawDao bankWithdrawDao;

	@Override
	public BankWithdraw create(BankWithdraw bankWithdraw) {
		// TODO Auto-generated method stub
		return bankWithdrawDao.save(bankWithdraw);
	}

	@Override
	public BankWithdraw findOne(Long id) {
		// TODO Auto-generated method stub
		return bankWithdrawDao.findOne(id);
	}

	@Override
	public List<BankWithdraw> findAll() {
		// TODO Auto-generated method stub
		return bankWithdrawDao.findAll();
	}

	@Override
	public Page<BankWithdraw> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return bankWithdrawDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return bankWithdrawDao.count();
	}

	@Override
	public void update(BankWithdraw bankWithdraw) {
		// TODO Auto-generated method stub

	}

}
