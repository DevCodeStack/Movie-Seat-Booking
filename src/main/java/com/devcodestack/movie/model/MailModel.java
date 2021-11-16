package com.devcodestack.movie.model;

import java.io.Serializable;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "mail")
public class MailModel implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private boolean enabled;
	private String from;
	private String[] to;
	private String[] cc;
	private String[] bcc;

}
