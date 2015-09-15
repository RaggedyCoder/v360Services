package com.thevolume360.service.implement;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.LabourDao;
import com.thevolume360.domain.Labour;
import com.thevolume360.domain.ProjectInfo;
import com.thevolume360.domain.search.LabourSearchCmd;
import com.thevolume360.service.LabourService;
import com.thevolume360.utils.StringUtils;

@Service
@Transactional
public class LabourServiceImpl implements LabourService {

	private static final Logger LOGGER = LoggerFactory.getLogger(LabourServiceImpl.class);

	@Autowired
	private LabourDao labourDao;

	@Override
	public Labour create(Labour labour) {
		LOGGER.debug("creating new patient with info : {}", labour);
		return labourDao.save(labour);
	}

	@Override
	public Labour findOne(Long id) {
		return labourDao.findOne(id);
	}

	@Override
	public List<Labour> findAll() {
		return labourDao.findAll();
	}

	@Override
	public Page<Labour> findAll(Pageable pageable) {
		return labourDao.findAll(pageable);
	}

	@Override
	public long count() {
		return labourDao.count();
	}

	@Override
	public void update(Labour labour) {
		LOGGER.debug("Updating labour with info : {}", labour);
	}

	@Override
	public List<Labour> getLaboursNotInThisProject(ProjectInfo projectInfo) {
		// TODO Auto-generated method stub
		return labourDao.findLabourNotInProjectByProjectInfoId(projectInfo.getId());
	}

	@Override
	public Page<Labour> findLabourBySearchCmd(final LabourSearchCmd labourSearchCmd, Pageable pageable) {
		LOGGER.debug("finding patient information with info : {}", labourSearchCmd);
		try {
			return labourDao.findAll(new Specification<Labour>() {

				@Override
				public Predicate toPredicate(Root<Labour> labourRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicate = cb.disjunction();
					try {
						if (StringUtils.isNotEmpty(labourSearchCmd.getFullName().getFirstName())) {
							predicate.getExpressions().add(cb
									.and(cb.like(cb.upper(labourRoot.get("fullName").get("firstName")), getLikePattern(
											labourSearchCmd.getFullName().getFirstName().trim().toUpperCase()))));
						}
						if (StringUtils.isNotEmpty(labourSearchCmd.getFullName().getLastName())) {
							predicate.getExpressions()
									.add(cb.and(cb.like(cb.upper(
											labourRoot.get("fullName").get("lastName")),
									getLikePattern(labourSearchCmd.getFullName().getLastName().trim().toUpperCase()))));
						}
						if (StringUtils.isNotEmpty(labourSearchCmd.getContactNumber())) {
							System.out.println("Contact Number- " + labourSearchCmd.getContactNumber());
							predicate.getExpressions().add(cb.and(cb.equal(labourRoot.get("contactNumber"),
									labourSearchCmd.getContactNumber().trim())));
						}
						if (labourSearchCmd != null) {
							System.out.println("Gender- " + labourSearchCmd.getGender());
							predicate.getExpressions()
									.add(cb.and(cb.equal(labourRoot.get("gender"), labourSearchCmd.getGender())));

						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
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
