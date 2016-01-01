package com.thevolume360.service.implement;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.thevolume360.dao.ClientDao;
import com.thevolume360.domain.Client;
import com.thevolume360.domain.search.ClientSearchCmd;
import com.thevolume360.service.ClientService;
import com.thevolume360.utils.StringUtils;

@Service
public class ClientServiceImpl implements ClientService {

	static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

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
		Client updateClient = findOne(client.getId());
		updateClient.setName(client.getName());
		updateClient.setClientType(client.getClientType());
		clientDao.save(updateClient);
	}

	@Override
	public Page<Client> findClientBySearchCmd(ClientSearchCmd clientSearchCmd, Pageable pageable) {
		LOGGER.debug("finding patient information with info : {}", clientSearchCmd);
		try {
			return clientDao.findAll(new Specification<Client>() {
				@Override
				public Predicate toPredicate(Root<Client> clientRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
					Predicate predicate = cb.disjunction();
					try {
						if (StringUtils.isNotEmpty(clientSearchCmd.getName())) {
							predicate.getExpressions().add(cb.and(cb.like(cb.upper(clientRoot.get("name")),
									getLikePattern(clientSearchCmd.getName().trim().toUpperCase()))));
						}
						if (clientSearchCmd.getClientType() != null) {
							predicate.getExpressions().add(
									cb.and(cb.equal(clientRoot.get("clientType"), clientSearchCmd.getClientType())));

						}
					} catch (Exception e) {

					}
					return predicate;
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
