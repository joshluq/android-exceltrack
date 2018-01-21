package pe.exceltransport.exceltrack.view;


public interface SignInView extends LoadDataView {

    String getEmail();

    String getPassword();

    boolean isCheckedRemember();

    void setEmail(String email);

    void goToMainActivity();

}
