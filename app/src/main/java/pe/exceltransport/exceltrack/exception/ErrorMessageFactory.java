
package pe.exceltransport.exceltrack.exception;

import android.content.Context;

import pe.exceltransport.data.exception.DefaultException;
import pe.exceltransport.exceltrack.R;

public class ErrorMessageFactory {

  public static String create(Context context, DefaultException exception) {
    if (exception.getCode() == DefaultException.Codes.NO_INTERNET.getCode()) {
      return context.getString(R.string.text_no_internet_message);
    } else if (exception.getCode() == DefaultException.Codes.DEFAULT_ERROR.getCode()) {
      return context.getString(R.string.text_default_exception_message);
    }else if(exception.getCode() == DefaultException.Codes.INVALID_CREDENTIALS.getCode()){
      return exception.getMessage();
    }
    return context.getString(R.string.text_unknown_message);
  }
}
