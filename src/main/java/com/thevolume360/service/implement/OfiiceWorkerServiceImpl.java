package com.thevolume360.service.implement;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.OfficeWorkerDao;
import com.thevolume360.domain.OfficeWorker;
import com.thevolume360.domain.search.OfficeWorkerSearchCmd;
import com.thevolume360.service.OfficeWorkerService;
import com.thevolume360.utils.StringUtils;

@Service
public class OfiiceWorkerServiceImpl implements OfficeWorkerService {

	@Autowired
	private OfficeWorkerDao officeWorkerDao;

	@Override
	public OfficeWorker create(OfficeWorker officeWorker) {
		// TODO Auto-generated method stub
		return officeWorkerDao.save(officeWorker);
	}

	@Override
	public OfficeWorker findOne(Long id) {
		// TODO Auto-generated method stub
		return officeWorkerDao.findOne(id);
	}

	@Override
	public List<OfficeWorker> findAll() {
		// TODO Auto-generated method stub
		return officeWorkerDao.findAll();
	}

	@Override
	public Page<OfficeWorker> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return officeWorkerDao.findAll(pageable);
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return officeWorkerDao.count();
	}

	@Override
	public void update(OfficeWorker officeWorker) {
	}

	@Override
	public Page<OfficeWorker> findOfficeWorkerBySearchCmd(final OfficeWorkerSearchCmd officeWorkerSearchCmd,
			Pageable pageable) {
		try {
			return officeWorkerDao.findAll(new Specification<OfficeWorker>() {

				@Override
				public Predicate toPredicate(Root<OfficeWorker> labourRoot, CriteriaQuery<?> query,
						CriteriaBuilder cb) {
					Predicate predicate = cb.disjunction();
					try {
						if (StringUtils.isNotEmpty(officeWorkerSearchCmd.getFullName().getFirstName())) {
							predicate.getExpressions().add(cb
									.and(cb.like(cb.upper(labourRoot.get("fullName").get("firstName")), getLikePattern(
											officeWorkerSearchCmd.getFullName().getFirstName().trim().toUpperCase()))));
						}
						if (StringUtils.isNotEmpty(officeWorkerSearchCmd.getFullName().getLastName())) {
							predicate.getExpressions().add(
									cb.and(cb.like(cb.upper(labourRoot.get("fullName").get("lastName")), getLikePattern(
											officeWorkerSearchCmd.getFullName().getLastName().trim().toUpperCase()))));
						}
						if (StringUtils.isNotEmpty(officeWorkerSearchCmd.getContactNumber())) {
							predicate.getExpressions().add(cb.and(cb.equal(labourRoot.get("contactNumber"),
									officeWorkerSearchCmd.getContactNumber().trim())));
						}
						if (officeWorkerSearchCmd.getDesignation() != null) {
							predicate.getExpressions().add(cb.and(
									cb.equal(labourRoot.get("designation"), officeWorkerSearchCmd.getDesignation())));

						}
					} catch (Exception e) {
					}

					try {
						return predicate;
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					return cb.disjunction();
				}
			}, pageable);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public String getLikePattern(String string) {
		return "%" + string.toLowerCase() + "%";
	}

}
