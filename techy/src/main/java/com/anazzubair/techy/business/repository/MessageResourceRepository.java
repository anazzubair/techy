package com.anazzubair.techy.business.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anazzubair.techy.business.model.MessageResource;

public interface MessageResourceRepository extends JpaRepository<MessageResource, Long> {

}
