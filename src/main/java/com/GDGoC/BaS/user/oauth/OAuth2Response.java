package com.GDGoC.BaS.user.oauth;

public interface OAuth2Response {
    String getProvider();

    String getProviderId();

    String getName();

    String getEmail();
}
