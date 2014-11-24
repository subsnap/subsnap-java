package co.subsnap.web;

import co.subsnap.domain.Project;
import co.subsnap.domain.Send;
import co.subsnap.domain.SendEmail;
import co.subsnap.service.ProjectService;
import co.subsnap.service.SendEmailRepository;
import co.subsnap.service.SendRepository;

import org.apache.commons.collections.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.sendgrid.SendGrid;
import com.sendgrid.SendGridException;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	ProjectService projectService;
	
	@Autowired
	private SendRepository sendRepo;
	
	@Autowired
	private SendEmailRepository sendEmailRepository;
	
	@Autowired
	private JavaMailSenderImpl mailSender;

	@RequestMapping(value = "projects/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public Project getProjectById(@PathVariable(value = "id") String projectId) {

		return this.projectService.getProjectRepo().findByProjectId(Long.parseLong(projectId)).get(0);
	}

	@RequestMapping(value = "projects", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
	@ResponseBody
	public List<Project> getAllProjects() {

		return IteratorUtils.toList(this.projectService.getProjectRepo().findAll().iterator());
	}

	@RequestMapping(value = "projects", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = { "application/json" })
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
	
	@RequestMapping(value = "sendEmails/{projectId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = { "application/json" })
    @ResponseStatus(value = HttpStatus.CREATED)
	@ResponseBody
	public SendEmail createSendAndSendEmails(@PathVariable(value = "projectId") String id, @RequestBody SendEmail email) throws SendGridException {
		Send toCreateSend = new Send();
		toCreateSend.setProjectId(Long.parseLong(id));
		logger.info("TO BE CREATED SEND:" + toCreateSend.toString());
		Send send = this.createSends(toCreateSend);
		logger.info("CREATE SEND:" + send.toString());

		//SendEmail email = new SendEmail();
		String sendId = send.getSendId().toString();
		email.setSendId(Long.parseLong(sendId));
		logger.info("TO BE CREATED EMAIL:" + email.toString());		
		SendEmail result = this.createSendEmails(email);
		logger.info("CREATED EMAIL:" + result.toString());
/*		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(result.getSendEmailAddress());
		message.setSubject(email.getSendEmailTitle());
		message.setText(email.getSendEmailBody());
		//message.setFrom("info@subsnap.com");
		mailSender.send(message);;*/
		SendGrid sendgrid = new SendGrid("subsnap", "5195Wint!");
	    SendGrid.Email message = new SendGrid.Email();
	    message.addTo(result.getSendEmailAddress());
		message.setSubject(email.getSendEmailTitle());
		message.setText(email.getSendEmailBody());
		message.setFrom("info@subsnap.com");
		
		SendGrid.Response response = sendgrid.send(message);
	    logger.info(response.getMessage());		
		
		return result;
	}
	
}
