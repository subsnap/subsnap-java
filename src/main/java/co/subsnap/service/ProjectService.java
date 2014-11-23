package co.subsnap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectService {

	private ProjectRepository projectRepo;
	
	@Autowired
	public void ProjectService(ProjectRepository repo) {
		this.projectRepo = repo;
	}
	
	public ProjectRepository getProjectRepo() {
		return this.projectRepo;
	}
}
