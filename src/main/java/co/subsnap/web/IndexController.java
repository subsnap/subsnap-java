package co.subsnap.web;

import co.subsnap.domain.Project;
import co.subsnap.domain.Send;
import co.subsnap.domain.SendEmail;
import co.subsnap.service.ProjectService;
import co.subsnap.service.SendEmailRepository;
import co.subsnap.service.SendRepository;

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
	
	@Autowired
	private SendRepository sendRepo;
	
	@Autowired
	private SendEmailRepository sendEmailRepository;

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
	
	@RequestMapping(value = "sends/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Send getSendsById(@PathVariable(value = "id") String sendId) {

		return this.sendRepo.findOne(Long.parseLong(sendId));
	}
	
	@RequestMapping(value = "sends/project/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Send> getSendsByProjectId(@PathVariable(value = "id") String projId) {

		return this.sendRepo.findByProjectId(Long.parseLong(projId));
	}

	@RequestMapping(value = "sends", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Send> getAllSends() {

		return IteratorUtils.toList(this.sendRepo.findAll().iterator());
	}

	@RequestMapping(value = "sends", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = { "application/json" })
    @ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public Send createSends(@RequestBody Send send) {

		return this.sendRepo.save(send);
	}
	
	@RequestMapping(value = "sendEmails/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public SendEmail getSendEmailsById(@PathVariable(value = "id") String id) {

		return this.sendEmailRepository.findOne(Long.parseLong(id));
	}

	@RequestMapping(value = "sendEmails", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<SendEmail> getAllSendEmails() {

		return IteratorUtils.toList(this.sendEmailRepository.findAll().iterator());
	}

	@RequestMapping(value = "sendEmails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = { "application/json" })
    @ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public SendEmail createSendEmails(@RequestBody SendEmail email) {

		return this.sendEmailRepository.save(email);
	}
	
}
