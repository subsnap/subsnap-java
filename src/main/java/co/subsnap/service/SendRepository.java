package co.subsnap.service;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.subsnap.domain.Send;


@Repository
public interface SendRepository extends PagingAndSortingRepository<Send, Long> {

    // additional custom finder methods go here

  }