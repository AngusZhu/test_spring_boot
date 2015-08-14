package com.cloverframework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloverframework.entity.BaseEntity;
import com.cloverframework.entity.NavigationEntity;
import com.cloverframework.repository.NavigationRepository;

@Service
public class NavigationServiceImpl implements NavigationService {
	@Autowired
	private NavigationRepository navigationRepository;
	
	/**
	 * 根据type查询菜单
	 * @param type
	 * @return
	 */
	public List<NavigationEntity> findByType(int type) {
		return navigationRepository.findByTypeAndStatusIs(type, BaseEntity.STATUS_VALID);
	}
}
