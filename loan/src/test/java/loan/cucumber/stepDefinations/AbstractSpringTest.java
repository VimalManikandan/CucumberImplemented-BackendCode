package loan.cucumber.stepDefinations;

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
import loan.loan.LoanAppApplication;
import loan.loan.datamodel.GenericResponce;
import loan.loan.model.Loan;


@SpringBootTest(classes=LoanAppApplication.class,webEnvironment=WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class AbstractSpringTest {
	
	public static final String URL="http://localhost:8765/loan-server/loanApi"; 
	protected ObjectMapper mapper=new ObjectMapper();
	
	
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
	
	public ResponseEntity<Loan> addLoanRestCall(String url,HttpEntity<Loan> request,Class<Loan> responseType){
		ResponseEntity<Loan> reponse= this.restTemplate.postForEntity(url, request, responseType);
		return reponse;		
	}
	public ResponseEntity<Loan> updateLoanRestCall(String url,HttpEntity<Loan> request,Class<Loan> responseType){
		ResponseEntity<Loan> reponse=this.restTemplate.exchange(url, HttpMethod.PUT, request, responseType);
		return reponse;
	}
	
	public ResponseEntity<GenericResponce> deleteLoanRestCall(String url,HttpMethod method, HttpEntity<?> request,Class<GenericResponce> responseType){
		ResponseEntity<GenericResponce> reponse= this.restTemplate.exchange(url, method,request, responseType);
		return reponse;		
	}
	
	public ResponseEntity<Loan> getLoanRestCall(String url,HttpMethod method, HttpEntity<?> request,Class<Loan> responseType){
		ResponseEntity<Loan> reponse= this.restTemplate.exchange(url, method,request, responseType);
		return reponse;		
	}
	

}
