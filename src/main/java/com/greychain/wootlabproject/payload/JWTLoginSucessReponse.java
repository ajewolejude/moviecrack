package com.greychain.wootlabproject.payload;

public class JWTLoginSucessReponse {
    private Long id;
    private boolean success;
    private String token;

    public JWTLoginSucessReponse(boolean success, String token, Long id) {
        this.success = success;
        this.token = token;
        this.id = id;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JWTLoginSucessReponse{" +
                "id=" + id +
                ", success=" + success +
                ", token='" + token + '\'' +
                '}';
    }


}
