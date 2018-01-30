package pe.exceltransport.exceltrack.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import dagger.android.AndroidInjection;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.view.TripDetailView;

public class TripDetailActivity extends BaseActivity implements TripDetailView {

    private static final String EXTRA_TRIP_ID = "extra_trip_id";

    public static Intent getCallingIntent(BaseActivity activity, Trip trip) {
        Intent intent = new Intent(activity, TripDetailActivity.class);
        intent.putExtra(EXTRA_TRIP_ID, trip);
        return intent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_detail);
        injectView(this);
        initUI();
    }

    @Override
    public void showError(String message) {
        //default implementation
    }

    @Override
    public long getTripId() {
        return Trip.class.cast(getIntent().getSerializableExtra(EXTRA_TRIP_ID)).getId();
    }
}
