package com.monitor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.monitor.domain.JobStatus;
import com.monitor.service.JobStatusComparitor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class MonitorApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate, Gson gson, String jsonURL) throws Exception {
		return args -> {
			String jobStatusFile = restTemplate.getForObject(jsonURL, String.class);
			System.out.println(jobStatusFile);
//			List<JobStatus> jobStatus = gson.fromJson(jobStatusFile, new TypeToken<List<JobStatus>>() {}.getType());

//			System.out.println(jobStatus);
		};
	}
}
