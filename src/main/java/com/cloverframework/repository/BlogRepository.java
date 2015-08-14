package com.cloverframework.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloverframework.entity.BlogEntity;

public interface BlogRepository extends JpaRepository<BlogEntity, Long>{

}
