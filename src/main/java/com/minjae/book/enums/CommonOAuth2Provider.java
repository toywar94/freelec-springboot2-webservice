package com.minjae.book.enums;


import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.ClientAuthenticationMethod;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import sun.security.provider.certpath.Builder;

public enum CommonOAuth2Provider {

    GOOGLE{
        @Override
        public Builder getBuilder(String registrationId){
            ClientAuthentication.Builder builder = getBuilder(registrationId,
                    ClientAuthenticationMethod.CLIENT_SECRET_BASIC, DEFAULT_LOGIN_REDIRECT_URL);

            return builder;
        }
    };


    private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/oauth2/callback/{registrationId}";

    protected final Builder getBuilder(String registrationId,
                                       ClientAuthenticationMethod method, String redirectUri) {
        ClientRegistration.Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(redirectUri);
        return builder;
    }

    public abstract Builder getBuilder(String registrationId);

}
