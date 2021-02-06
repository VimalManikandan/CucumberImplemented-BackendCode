package loan.cucumber.stepDefinations;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

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
import loan.loan.datamodel.GenericResponce;
import loan.loan.model.Loan;
import loan.loan.model.User;


public class LoanControllerStepDefinition extends AbstractSpringTest {
	
	public static Logger logger = LoggerFactory.getLogger(LoanControllerStepDefinition.class);
	
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
		
	@When("New Loan is added")
	public void new_Loan_is_added(){	
		User u1=new User(4, "Userrr", "123password", "ADMIN", "ABCD");
		Loan loan =new Loan();
		loan.setFname("Vimal");
		loan.setLname("V");
		loan.setPaddress("KERALA");
		loan.setLoanAmount(320000);
		loan.setLoantype("Personal");
		loan.setLoanterm(60);
		loan.setUser(u1);
		HttpEntity<Loan> request = new HttpEntity<>(loan, headers);
		response=this.addLoanRestCall(this.URL+"/create", request,Loan.class);
		logger.info("New Loan is added"+response);
	}
	
	@Then("Should return not null loan entity")
	public void should_return_not_null_user_entity(){
		assertNotNull(response.getBody());
	}
	
	@Then("Loan is fetched")
	public void loan_is_fetched(){
		HttpEntity<Loan> request = new HttpEntity<>(headers);
		
		UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(this.URL+"/getLoan/28")
				.queryParam("userId",4);		
		response= this.getLoanRestCall(builder.toUriString(), HttpMethod.GET,request,Loan.class);
		logger.info("Loan is fetched: "+response);
		
	}	
	@When("New Loan is updated")
	public void new_loan_is_updated(){
		User user=new User(4, "Userrr", "123password", "ADMIN", "ABCD");		
		Loan l1=new Loan(28, "FanemChange", "LnameChqange", "Bannglore", 210000, "ADMIN", 72);
		l1.setUser(user);
		HttpEntity<Loan> request = new HttpEntity<>(l1, headers);
		response=this.updateLoanRestCall(this.URL+"/updateLoan", request,Loan.class);
		logger.info("New Loan is update :"+response);
	}
	
	@When("Loan is deleted")
	public void loan_is_deleted(){
		HttpEntity<Loan> request = new HttpEntity<>(headers);
		UriComponentsBuilder builder=UriComponentsBuilder.fromHttpUrl(this.URL+"/deleteLoan/38")
				.queryParam("userId",4);
		
		response= this.deleteLoanRestCall(builder.toUriString(), HttpMethod.DELETE,request,GenericResponce.class);
		logger.info("Loan is deleted :"+response);
	}
	
	@Then("Should return Generic Response with success")
	public void  should_return_generic_response_with_success(){
		assertEquals(200,((GenericResponce)response.getBody()).getStatus());
	}
	
}
