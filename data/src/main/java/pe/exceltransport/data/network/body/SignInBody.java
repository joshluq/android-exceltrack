package pe.exceltransport.data.network.body;

public class SignInBody {

    private String email;
    private String password;
    private String firebaseToken;

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }
}
