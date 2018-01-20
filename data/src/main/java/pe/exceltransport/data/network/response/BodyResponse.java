package pe.exceltransport.data.network.response;

public class BodyResponse<T> {

    private T body;
    private ErrorResponse error;

    public T getBody() {
        return body;
    }

    public ErrorResponse getError() {
        return error;
    }
}
