package co.subsnap.service;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import co.subsnap.domain.Project;

@Repository
public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

    // additional custom finder methods go here

    List<Project> findByProjectId(long projectId);


  }