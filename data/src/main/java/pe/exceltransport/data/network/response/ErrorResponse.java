package pe.exceltransport.data.network.response;

import com.google.gson.annotations.SerializedName;

public class ErrorResponse {

    @SerializedName("code")
    private int code;

    @SerializedName("message")
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}