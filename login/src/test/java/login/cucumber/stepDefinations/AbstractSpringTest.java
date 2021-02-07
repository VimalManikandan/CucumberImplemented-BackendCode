package login.cucumber.stepDefinations;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.buf.UEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.spring.CucumberContextConfiguration;
import login.login.LoginApplication;
import login.login.model.GenericResponce;
import login.login.model.User;

@SpringBootTest(classes=LoginApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class AbstractSpringTest {
	
	public static final String URL="http://localhost:8765/login-server";
	protected ObjectMapper mapper=new ObjectMapper();
	
	String url="http://localhost:8765/login-server/loginApi";
	
	@LocalServerPort
	protected String port;
	
	@Autowired
	protected TestRestTemplate restTemplate;
	
	
	public TestRestTemplate getRestTemplate(){
		return restTemplate != null ?restTemplate :new TestRestTemplate();
	}

	public void setRestTemplate(TestRestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public ResponseEntity<User> loginUserRestCall(String url,HttpEntity<User> request,Class<User> responseType){
		
		ResponseEntity<User> reponse= this.restTemplate.postForEntity(url, request, responseType);
		return reponse;		
	}
	
	public ResponseEntity<User> logoutUserRestCall(String url,HttpEntity<User> request,Class<User> responseType){
		System.out.println("Url:"+url);
		ResponseEntity<User> reponse= this.restTemplate.postForEntity(url, request, responseType);
		return reponse;		
	}
	
	public ResponseEntity<User> addUserRestCall(String url,HttpEntity<User> request,Class<User> responseType){
		System.out.println("Url:"+url);
		ResponseEntity<User> reponse= this.restTemplate.postForEntity(url, request, responseType);
		return reponse;		
	}
	public ResponseEntity<User> updateUserRestCall(String url,HttpEntity<User> request,Class<User> responseType){
		System.out.println("Url:"+url);
		ResponseEntity<User> reponse=this.restTemplate.exchange(url, HttpMethod.PUT, request, responseType);
		return reponse;
	}
	
	public ResponseEntity<GenericResponce> deleteUserRestCall(String url,HttpMethod method, HttpEntity<?> request,Class<GenericResponce> responseType){
		System.out.println("Url:"+url);
		ResponseEntity<GenericResponce> reponse= this.restTemplate.exchange(url, method,request, responseType);
		return reponse;		
	}
	
	public ResponseEntity<User> getUserRestCall(String url,HttpMethod method, HttpEntity<?> request,Class<User> responseType){
		System.out.println("Url:"+url);
		ResponseEntity<User> reponse= this.restTemplate.exchange(url, method,request, responseType);
		return reponse;		
	}
	

}
