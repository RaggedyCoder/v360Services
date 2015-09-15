package com.thevolume360.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.thevolume360.domain.Material;

public interface MaterialService {
	
	public Material create(Material material);

	public Material findOne(Long id);

	public List<Material> findAll();

	Page<Material> findAll(Pageable pageable);

	public long count();

	public void update(Material material);

}
