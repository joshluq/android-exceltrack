package pe.exceltransport.exceltrack.view.util;


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtil {

    private KeyboardUtil() {
        //empty constructor
    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null && imm != null) imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
