package com.monitor.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
@PropertySource("classpath:dev.properties")
public class DevConfig {

    @Autowired
    private Environment environment;

    @Bean
    public String getJsonURL(){
        return environment.getProperty("jsonurl");
    }

    @Bean
    public String getJsonURLFilter(){
        return environment.getProperty("jsonurlfilter");
    }

    @Bean
    public String getProxy() {
        return environment.getProperty("proxy");
    }

    @Bean
    public String getRefreshPage() {
        return environment.getProperty("refreshto");
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder, String getProxy) throws NoSuchAlgorithmException, KeyManagementException {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(getProxy, 80));
        requestFactory.setProxy(proxy);

        SSLContext ctx = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
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
