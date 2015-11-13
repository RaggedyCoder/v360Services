package com.thevolume360.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.thevolume360.domain.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client>  {

}
