package pe.exceltransport.exceltrack.view.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.exceltransport.exceltrack.R;

public class TrackingTimeLineAdapter extends RecyclerView.Adapter<TrackingTimeLineAdapter.TimeLineViewHolder> {


    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_tracking_timeline, null);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position,getItemCount());
    }

    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.time_marker)
        TimelineView tlMarker;

        TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tlMarker.initLine(viewType);
        }
    }
}
