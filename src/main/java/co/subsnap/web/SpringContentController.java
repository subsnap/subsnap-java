package co.subsnap.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import co.subsnap.domain.UserDetails;

@Controller
public class SpringContentController {
	@Autowired UserDetails userDetails;
	@RequestMapping(value="/springcontent",
			method=RequestMethod.GET,produces={"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
	public @ResponseBody
	UserDetails getUser() {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("Praveen");
		userDetails.setEmailId("praveen@gmail.com");
		return userDetails;
	}
}