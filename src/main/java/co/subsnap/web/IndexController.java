package co.subsnap.web;

import co.subsnap.domain.Project;
import co.subsnap.service.ProjectRepository;
import co.subsnap.service.ProjectService;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

	@Autowired
	ProjectService projectService;

	/*
	 * @RequestMapping public String getIndexPage() { return "index.html"; }
	 */

	@RequestMapping(value = "projects/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Project getProjectById(@PathVariable(value = "id") String projectId) {

		return this.projectService.getProjectRepo().findByProjectId(Long.parseLong(projectId)).get(0);
	}

	@RequestMapping(value = "projects/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Project> getAllProjects() {

		return IteratorUtils.toList(this.projectService.getProjectRepo().findAll().iterator());
	}

	@RequestMapping(value = "projects/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = { "application/json" })
    @ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public Project createProject(@RequestBody Project project) {

		return this.projectService.getProjectRepo().save(project);
	}
	
}
