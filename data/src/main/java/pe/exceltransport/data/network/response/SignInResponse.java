package pe.exceltransport.data.network.response;

import com.google.gson.annotations.SerializedName;

import pe.exceltransport.data.entity.UserEntity;

public class SignInResponse {

    @SerializedName("user")
    private UserEntity userEntity;

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
