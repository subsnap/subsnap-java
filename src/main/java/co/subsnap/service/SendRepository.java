package co.subsnap.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.subsnap.domain.Send;
import co.subsnap.domain.SendEmail;


@Repository
public interface SendRepository extends PagingAndSortingRepository<Send, Long> {

    // additional custom finder methods go here
    List<Send> findByProjectId(Long projectId);

	
  }