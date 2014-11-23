package co.subsnap.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.subsnap.domain.SendEmail;


@Repository
public interface SendEmailRepository extends PagingAndSortingRepository<SendEmail, Long> {

    // additional custom finder methods go here

    List<SendEmail> findBySendIdAndSendEmailAddress(Long sendId, String sendEmailAddress);

  }