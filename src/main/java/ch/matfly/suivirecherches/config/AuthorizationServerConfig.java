package ch.matfly.suivirecherches.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Arrays;

@Configuration
//@EnableAuthorizationServer: Enables an authorization server
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //Client id: defines the id of the client application that is authorized to authenticate,
    // the client application provides this in order to be allowed to send a request to the server.
    @Value("${security.jwt.client-id}")
    private String clientId;

    //Client secret: is the client application’s password.
    // In a non-trivial implementation client ids and passwords will be securely stored
    // in a database and retrievable through a separate API that clients applications
    // access during deployment. These pieces of information can also be shared and stored
    // in environment variables although that would not be my preferred option.
    @Value("${security.jwt.client-secret}")
    private String clientSecret;

    //Grant type: we define the grant type password here because it’s not enabled by default
    @Value("${security.jwt.grant-type}")
    private String grantType;

    //The scope: read/write defines the level of access we are allowing to resources
    @Value("${security.jwt.scope-read}")
    private String scopeRead;

    @Value("${security.jwt.scope-write}")
    private String scopeWrite = "write";

    //Resource Id: The resource id specified here must be specified on the resource server as well
    @Value("${security.jwt.resource-ids}")
    private String resourceIds;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter accessTokenConverter;

    //AuthenticationManager: Spring’s authentication manager takes care of checking user credential validity
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer
                .inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .authorizedGrantTypes(grantType)
                .scopes(scopeRead, scopeWrite)
                .resourceIds(resourceIds);
    }

    //AuthorizationServerConfig which is our authorization server configuration class extends AuthorizationServerConfigurerAdapter
    // which in turn is an implementation of AuthorizationServerConfigurer.
    // The presence of a bean of type AuthorizationServerConfigurer simply tells Spring Boot
    // to switch off auto-configuration and use the custom configuration.
    // Also, the AuthorizationServerConfig, like any other configuration class,
    // has its definition automatically scanned, wired, and applied by Spring Boot
    // because of the @Configuration annotation.

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        TokenEnhancerChain enhancerChain = new TokenEnhancerChain();
        enhancerChain.setTokenEnhancers(Arrays.asList(accessTokenConverter));
        endpoints.tokenStore(tokenStore)
                .accessTokenConverter(accessTokenConverter)
                //TokenEnhancerChain: We define a token enhancer
                // that enables chaining multiple types of claims containing different information
                .tokenEnhancer(enhancerChain)
                .authenticationManager(authenticationManager);
    }

}
