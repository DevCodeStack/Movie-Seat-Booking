package com.devcodestack.movie;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class MovieSeatBookingApplication {
	
	@Value("${trust-store}")
	private Resource trustStore;
	
	@Value("${trust-store-password}")
	private String trustStorePass;

	public static void main(String[] args) {
		SpringApplication.run(MovieSeatBookingApplication.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() throws Exception {
		SSLContext sslContext = new SSLContextBuilder()
			      .loadTrustMaterial(trustStore.getURL(), trustStorePass.toCharArray())
			      .build();
	    SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(sslContext);
	    HttpClient httpClient = HttpClients.custom()
	      .setSSLSocketFactory(socketFactory)
	      .build();
	    HttpComponentsClientHttpRequestFactory factory = 
	      new HttpComponentsClientHttpRequestFactory(httpClient);
	    return new RestTemplate(factory);
//		return new RestTemplate();
	}
}
