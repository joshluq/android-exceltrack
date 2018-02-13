package pe.exceltransport.exceltrack.view.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import dagger.android.support.AndroidSupportInjection;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.presenter.TripListPresenter;
import pe.exceltransport.exceltrack.view.TripListView;
import pe.exceltransport.exceltrack.view.activity.MainActivity;
import pe.exceltransport.exceltrack.view.adapter.TripAdapter;

public class TripListFragment extends BaseFragment implements TripListView, TripAdapter.OnItemClickListener {

    @BindView(R.id.rv_trips)
    ShimmerRecyclerView rvTrips;

    @Inject
    TripListPresenter presenter;

    @Inject
    TripAdapter adapter;

    private MainActivity activity;

    public static TripListFragment newInstance() {
        return new TripListFragment();
    }

    public TripListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_trip_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectView(this, view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) getActivity();
        presenter.setView(this);
        initUI();
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    protected void initUI() {
        setupRecyclerView();
        presenter.getPendingTrips();
    }

    @Override
    public void goToSignIn() {
        activity.getNavigator().navigateToSignInActivity();
    }

    @Override
    public void renderTrips(List<Trip> trips) {
        adapter.bindList(trips);
    }

    @Override
    public void showLoading() {
        rvTrips.showShimmerAdapter();
    }

    @Override
    public void hideLoading() {
        rvTrips.hideShimmerAdapter();
    }

    @Override
    public void showError(String message) {
        activity.getNavigator().showAlertDialog(getString(R.string.text_error),message,getString(R.string.text_got_it));
    }

    private void setupRecyclerView() {
        rvTrips.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTrips.setHasFixedSize(true);
        rvTrips.setDemoChildCount(3);
        rvTrips.setDemoLayoutReference(R.layout.item_trip_list_demo);
        adapter.setListener(this);
        rvTrips.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Trip trip) {
        activity.getNavigator().navigateToTripDetailActivity(trip);
    }
}
