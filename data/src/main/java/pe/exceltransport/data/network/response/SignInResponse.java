package pe.exceltransport.data.network.response;

import com.google.gson.annotations.SerializedName;

import pe.exceltransport.data.entity.SessionEntity;
import pe.exceltransport.data.entity.UserEntity;

public class SignInResponse {

    @SerializedName("session")
    private SessionEntity sessionEntity;

    public SessionEntity getSessionEntity() {
        return sessionEntity;
    }
}
