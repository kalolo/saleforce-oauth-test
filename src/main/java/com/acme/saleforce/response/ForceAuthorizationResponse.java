package com.acme.saleforce.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ForceAuthorizationResponse {

    private String id;
    private String issued_at;
    private String scope;
    private String instance_url;
    private String token_type;
    private String refresh_token;
    private String id_token;
    private String signature;
    private String access_token;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIssued_at() {
        return issued_at;
    }

    public void setIssued_at(String issued_at) {
        this.issued_at = issued_at;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getInstance_url() {
        return instance_url;
    }

    public void setInstance_url(String instance_url) {
        this.instance_url = instance_url;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getId_token() {
        return id_token;
    }

    public void setId_token(String id_token) {
        this.id_token = id_token;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String toString() {
        return "\n{\n id: " + this.id + "\n issued_at: " + this.issued_at + "\n scope: " + this.scope + "\n instance_url: "
                + this.instance_url + "\n token_type: " + this.token_type + "\n refresh_token: " + this.refresh_token
                + "\n id_token: " + this.id_token + "\n signature: " + this.signature + "\n access_token: " + this.access_token + "\n}\n";

    }
}
