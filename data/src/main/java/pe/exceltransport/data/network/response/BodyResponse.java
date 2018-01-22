package pe.exceltransport.data.network.response;

import com.google.gson.annotations.SerializedName;

public class BodyResponse<T> {

    @SerializedName("body")
    private T body;

    @SerializedName("error")
    private ErrorResponse error;

    public T getBody() {
        return body;
    }

    public ErrorResponse getError() {
        return error;
    }
}
