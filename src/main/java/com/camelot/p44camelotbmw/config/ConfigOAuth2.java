package com.camelot.p44camelotbmw.config;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class ConfigOAuth2 extends AuthorizationServerConfigurerAdapter {
    
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Bean
    public JwtAccessTokenConverter tokenEnhancer() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        /* Project44.camelot-itlab.com */
        String privateKey = "-----BEGIN RSA PRIVATE KEY-----\n" + "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\n" + "-----END RSA PRIVATE KEY-----";
        converter.setSigningKey(privateKey);
        /* Project44.camelot-itlab.com */
        String publicKey = "-----BEGIN PUBLIC KEY-----\n" + "MIICIjANBgkqhkiG9w0BAQEFAAOCAg8AMIICCgKCAgEA7OH+IluWH2yW0siYnfZB\n" + "VeO4VecgARXnNnnMgGYNs4lbQOUzzXizU+A9K/yhVIBKCU1FGlshxsNH/270TrGD\n" + "7/rXg3CMRW5zVlz1Zt9gLmdof4w/u78yhkAZ9Ym6L9np50ZcFMpBg1oL5E9AjHU5\n" + "XFDimOsrWcnS1F+jmVDtPz/L180lnSaoa1awY4YdC9goifGehlcKeUW+/RppFJYl\n" + "lROkl1j1jHJeoGlMQQOhtrABwwPg0u+pVMQwEYYYOJ79iODAju0gbYQrZNiBbUgd\n" + "zqLDFMtb1/yvP+DxSiJyx5Ohx1/O//ThDVWvSQGTFACaCQgECfwX81E60+Ur8khI\n" + "zILpQiZGo4QedqW0z4EuZigfc2h6jYROj3wGn49wcWE9pyLZT7ofPeE8XH9SOpgQ\n" + "2HwFpaqZN3kYqI3W1TlX5D5C8NDA8a0nDts+A/i+l9BeguOyHkMOkgrdS+tJ3J0E\n" + "cU/IREkzF3rrueym1nsooIOnlSj+DlTSPG2KZglQph2koNUn1aSJjpA4vYu974wg\n" + "k96SX6YPnJgoD1ikdx8Gd420uMBPaYcu5oBT4t++He4w3LrJ6AjlXUi3SSkkdiOk\n" + "uheQH/VsWkYXjo3lVJmA7g/vuHWS08cS3wJrmcHnGvRUHjrSM2DtIJUqnQbI10o2\n" + "xHUpSUHcmzbgPGc8HZA+mHkCAwEAAQ==\n" + "-----END PUBLIC KEY-----";
        converter.setVerifierKey(publicKey);
        return converter;
    }
    
    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(tokenEnhancer());
    }
    
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.pathMapping("/oauth/token", "/v1/auth/token").authenticationManager(authenticationManager).tokenStore(tokenStore()).accessTokenConverter(tokenEnhancer());
    }
    
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //clients.inMemory().withClient("bmwTest").secret(passwordEncoder.encode("$2a$12$pqzt83QlYx2eCIbGjyK9f")).scopes("read").authorizedGrantTypes("client_credentials").accessTokenValiditySeconds(2000).refreshTokenValiditySeconds(20000);
        clients.inMemory()
                .withClient("p44Prod").secret(passwordEncoder.encode("$2a$12$w42qnoiKWqs6PA3urxohX"))
                .scopes("read").authorizedGrantTypes("client_credentials")
                .and()
                .withClient("bmwTest").secret(passwordEncoder.encode("$2a$12$pqzt83QlYx2eCIbGjyK9f"))
                .scopes("read").authorizedGrantTypes("client_credentials")
//                .and()
//                .withClient("bmwProd").secret(passwordEncoder.encode("$2a$12$TkvSnNaV4unYMCYJrwXbT"))
//                .scopes("read").authorizedGrantTypes("client_credentials")
                .accessTokenValiditySeconds(43199).refreshTokenValiditySeconds(43199);
    }
    
    
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) throws Exception {
        char[] password = "praveen".toCharArray();
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource("classpath:bmw-int.jks");
        SSLContext sslContext = SSLContextBuilder.create().loadKeyMaterial(keyStore(resource, password), password).loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
        HttpClient client = HttpClients.custom().setSSLContext(sslContext).build();
        return builder.requestFactory(() -> new HttpComponentsClientHttpRequestFactory(client)).build();
    }
    
    
    private KeyStore keyStore(Resource resource, char[] password) throws Exception {
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        try (InputStream in = resource.getInputStream()) {
            keyStore.load(in, password);
        }
        return keyStore;
    }
}