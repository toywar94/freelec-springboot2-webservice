package com.minjae.book.enums;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistration.Builder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;

@Slf4j
@RequiredArgsConstructor
public enum CommonOAuth2Provider {

    GOOGLE {

        @Override
        public Builder getBuilder(String registrationId) {
            Builder builder = getBuilder(registrationId,
                    ClientAuthenticationMethod.BASIC, DEFAULT_LOGIN_REDIRECT_URL);
            builder.scope("profile", "email");
            builder.authorizationUri("https://accounts.google.com/o/oauth2/v2/auth");
            builder.tokenUri("https://www.googleapis.com/oauth2/v4/token");
            builder.jwkSetUri("https://www.googleapis.com/oauth2/v3/certs");
            builder.userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo");
            builder.userNameAttributeName(IdTokenClaimNames.SUB);
            builder.clientName("Google");
            return builder;
        }
    },

    KAKAO {

        @Override
        public Builder getBuilder(String registrationId) {
            Builder builder = getBuilder(registrationId,
                    ClientAuthenticationMethod.POST,DEFAULT_LOGIN_REDIRECT_URL);
            builder.scope("account_email", "profile");// 요청할 권한
            builder.authorizationUri("https://kauth.kakao.com/oauth/authorize"); // authorization code 얻는 API
            builder.tokenUri("https://kauth.kakao.com/oauth/token"); // access Token 얻는 API
            builder.userInfoUri("https://kapi.kakao.com/v2/user/me"); // 유저 정보 조회 API
            builder.userNameAttributeName("id"); // userInfo API Response에서 얻어올 ID 프로퍼티
            builder.clientName("Kakao"); // spring 내에서 인식할 OAuth2 Provider Name
            log.info(builder.toString());
            return builder;
        }
    },


    NAVER {
        @Override
        public Builder getBuilder(String registrationId) {
            return getBuilder(registrationId,
                    ClientAuthenticationMethod.POST,DEFAULT_LOGIN_REDIRECT_URL)
                    .scope("profile")
                    .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
                    .tokenUri("https://nid.naver.com/oauth2.0/token")
                    .userInfoUri("https://openapi.naver.com/v1/nid/me")
                    .userNameAttributeName("response")
                    .clientName("Naver");
        }
    };

    private static final String DEFAULT_LOGIN_REDIRECT_URL = "{baseUrl}/oauth2/callback/{registrationId}";
    //{baseUrl}/{action}/oauth2/code/{registrationId}
    protected final Builder getBuilder(String registrationId,
                                       ClientAuthenticationMethod method, String redirectUri) {
        Builder builder = ClientRegistration.withRegistrationId(registrationId);
        builder.clientAuthenticationMethod(method);
        builder.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE);
        builder.redirectUriTemplate(redirectUri);
        return builder;
    }

    public abstract Builder getBuilder(String registrationId);

}
