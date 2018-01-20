/**
 * Copyright (C) 2014 android10.org. All rights reserved.
 * @author Fernando Cejas (the android10 coder)
 */
package pe.exceltransport.exceltrack.view;

public interface LoadDataView {

  void showLoading();

  void hideLoading();

  void showNetworkError(String message);

}
