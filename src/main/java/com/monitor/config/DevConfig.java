package com.monitor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

@Slf4j
@Configuration
@Profile("dev")
public class DevConfig {
    @Bean
    public String jsonURL() {
        log.info("Using dev URL");
        return "https://mk-codes.co.uk/json";
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
    }
}
