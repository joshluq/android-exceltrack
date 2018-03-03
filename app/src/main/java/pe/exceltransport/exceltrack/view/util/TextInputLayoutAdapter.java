package pe.exceltransport.exceltrack.view.util;

import android.support.design.widget.TextInputLayout;

import com.mobsandgeeks.saripaar.adapter.ViewDataAdapter;
import com.mobsandgeeks.saripaar.exception.ConversionException;

public class TextInputLayoutAdapter implements ViewDataAdapter<TextInputLayout, String> {

    @Override
    public String getData(TextInputLayout view) throws ConversionException {
        return getText(view);
    }

    private String getText(TextInputLayout view) {
        return view.getEditText() == null ? "" : view.getEditText().getText().toString();
    }
}
