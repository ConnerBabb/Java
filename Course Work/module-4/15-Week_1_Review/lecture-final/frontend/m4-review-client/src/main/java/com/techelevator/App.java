package com.techelevator;

import java.util.Scanner;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

/**
 * Hello world!
 *
 */
public class App 
{
	
	private static final RestTemplate restTemplate = new RestTemplate();
	
    public static void main( String[] args )
    {
    	Scanner input = new Scanner(System.in);
    	System.out.print("User id >>>");
    	int userId = input.nextInt();
    	input.nextLine();
    	
       String url = "http://localhost:8080/users/" + userId +"/balance";
       
       //HttpHeaders headers = new HttpHeaders();
       //HttpEntity entity = new HttpEntity<>(headers);
       //Balance balance = restTemplate.exchange(url, HttpMethod.GET, entity, Balance.class).getBody();
       
       Balance balance = restTemplate.getForObject(url, Balance.class);
       
       System.out.println("Your balance is $" + balance.getAmount());
       
    }
    
    
   
}
