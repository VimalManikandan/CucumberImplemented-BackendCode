package login.cucumber.stepDefinations;


import static org.junit.Assert.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import login.login.model.GenericResponce;
import login.login.model.User;
import login.login.service.UserAuthServiceImpl;

public class UserAuthStepDefinition extends AbstractSpringTest {
	
	public static Logger logger = LoggerFactory.getLogger(UserAuthStepDefinition.class);
	
	public String url=this.URL;
	HttpHeaders headers= new HttpHeaders();
	HttpEntity<?> request =null;
	ResponseEntity<?> response =null;
	HttpEntity<String> entity ;
	
	UriComponentsBuilder requestUrl=null;

	@Before
	public void setup(){
		url=url+"/loginApi";
		headers.add("Accept", "application/json");
	}
	
	@When("user is logging in")
	public void user_is_logging_in(){				
		User u1=new User(1,"User2","123Password","USER","ABCD");
		HttpEntity<User> request = new HttpEntity<>(u1, headers);
		response=this.loginUserRestCall(this.URL+"/loginApi/login", request,User.class);
		logger.info("user is logging in: "+response);
	}
	@Then("Should get a http status code of {int}")
	public void user_is_logging_out(int statusCode){		
		assertEquals(statusCode,response.getStatusCodeValue());
	}

	@When("user is loggged out")
	public void user_is_loggged_out(){				
		User u1=new User(1,"User2","123Password","USER","ABCD");
		HttpEntity<User> request = new HttpEntity<>(u1, headers);
		response=this.logoutUserRestCall(this.URL+"/loginApi/logout", request,User.class);
		logger.info("user is loggged out: "+response);
	}
		
	@When("New User is added")
	public void new_User_is_added(){				
		User u1=new User();
		u1.setUsername("Userrrr");
		u1.setUserpwd("123Password");
		u1.setUsertype("USER");
		HttpEntity<User> request = new HttpEntity<>(u1, headers);
		response=this.addUserRestCall(this.URL+"/userApi/create", request,User.class);
		logger.info("New User is added"+response);
	}
	
	@Then("Should return not null user entity")
	public void should_return_not_null_user_entity(){
		assertNotNull(response.getBody());
	}
	
	@Then("User is fetched")
	public void user_is_fetched(){
		HttpEntity<User> request = new HttpEntity<>(headers);
		response= this.getUserRestCall(this.URL+"/userApi/get/18", HttpMethod.GET,request,User.class);
		logger.info("User is fetched: "+response);
		
	}	
	@When("New User is updated")
	public void new_User_is_update(){
		User u1=new User(18, "UserChanaged", "pwdchanges", "ADMIN", "XYZ");
		HttpEntity<User> request = new HttpEntity<>(u1, headers);
		response=this.updateUserRestCall(this.URL+"/userApi/update", request,User.class);
		logger.info("New User is update :"+response);
	}
	
	@When("User is deleted")
	public void user_is_deleted(){
		HttpEntity<User> request = new HttpEntity<>(headers);
		response= this.deleteUserRestCall(this.URL+"/userApi/delete/18", HttpMethod.DELETE,request,GenericResponce.class);
		logger.info("User is deleted :"+response);
	}
	
	@Then("Should return Generic Response with success")
	public void  should_return_generic_response_with_success(){
		assertEquals(200,((GenericResponce)response.getBody()).getStatus());
	}
	
}
