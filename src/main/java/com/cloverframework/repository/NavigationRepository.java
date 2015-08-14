package com.cloverframework.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloverframework.entity.NavigationEntity;

public interface NavigationRepository extends JpaRepository<NavigationEntity, Long>{
	
	/**
	 * 根据type查询菜单
	 * @param type
	 * @return
	 */
	List<NavigationEntity> findByTypeAndStatusIs(int type, int status);
}
