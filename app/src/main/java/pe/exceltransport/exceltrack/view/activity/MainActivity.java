package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pe.exceltransport.exceltrack.R;

public class MainActivity extends AppCompatActivity {



    public static Intent getCallingIntent(BaseActivity activity) {
        return new Intent(activity, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
