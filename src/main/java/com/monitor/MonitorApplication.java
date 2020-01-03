package com.monitor;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
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
import java.util.List;
import java.util.Properties;

@SpringBootApplication
public class MonitorApplication {
	public static void main(String[] args) {
		SpringApplication.run(MonitorApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws NoSuchAlgorithmException, KeyManagementException {
		SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.tcsdbs.com", 80));
		requestFactory.setProxy(proxy);

		SSLContext ctx = SSLContext.getInstance("TLS");
		X509TrustManager tm = new X509TrustManager() {

			@Override
			public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				// TODO Auto-generated method stub

			}

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
		};
		ctx.init(null, new TrustManager[] {tm}, null);
		SSLContext.setDefault(ctx);
		RestTemplate restTemp = builder.build();
		restTemp.setRequestFactory(requestFactory);
		return restTemp;
		//return builder.build();
	}

	@Bean
	public Gson gson() {
		return new Gson();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate, Gson gson) throws Exception {
		return args -> {
			String jobStatusFile = restTemplate.getForObject(
					"https://mk-codes.co.uk/json", String.class);
			System.out.println(jobStatusFile);
			/*List<JobStatus> jobStatus = gson.fromJson(jobStatusFile, new TypeToken<List<JobStatus>>() {}.getType());
			System.out.println(jobStatus);*/
		};
	}
}
