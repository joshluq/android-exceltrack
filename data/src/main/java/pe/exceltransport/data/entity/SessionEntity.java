package pe.exceltransport.data.entity;

import com.google.gson.annotations.SerializedName;

public class SessionEntity {

    @SerializedName("token")
    private String token;

    @SerializedName("user")
    private UserEntity userEntity;

    public String getToken() {
        return token;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
}
