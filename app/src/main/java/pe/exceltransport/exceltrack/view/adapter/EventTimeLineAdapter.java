package pe.exceltransport.exceltrack.view.adapter;


import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.vipulasri.timelineview.TimelineView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pe.exceltransport.domain.Event;
import pe.exceltransport.domain.Location;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.view.util.DateUtil;

public class EventTimeLineAdapter extends RecyclerView.Adapter<EventTimeLineAdapter.TimeLineViewHolder> {

    private Context context;
    private List<Event> list;
    private OnItemClickListener listener;
    private int lastTrackingPosition;

    @Inject
    public EventTimeLineAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.lastTrackingPosition = -1;
    }

    @Override
    public TimeLineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.item_event_timeline, null);
        return new TimeLineViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(TimeLineViewHolder holder, int position) {
        holder.bind(list.get(holder.getLayoutPosition()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return TimelineView.getTimeLineViewType(position, getItemCount());
    }

    public void bindList(List<Event> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
            getLastTrackingPosition(list);
        }
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private void getLastTrackingPosition(List<Event> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getType().equals(Event.Type.TRACKING)) {
                lastTrackingPosition = i;
            }
        }
    }

    class TimeLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.time_marker)
        TimelineView tlMarker;

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.tv_detail)
        TextView tvDetail;

        private View rootView;

        TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tlMarker.initLine(viewType);
            rootView = itemView;
        }

        private void bind(Event event) {
            if (event.getType().equals(Event.Type.INCIDENCE)) {
                rootView.setPadding(30, 0, 0, 0);
                tlMarker.setMarker(ContextCompat.getDrawable(context, R.drawable.ic_timeline_marker_inactive));
            } else {
                rootView.setPadding(0, 0, 0, 0);
                tlMarker.setMarker(getLayoutPosition() == lastTrackingPosition ? ContextCompat.getDrawable(context, R.drawable.ic_timeline_marker_disable) : ContextCompat.getDrawable(context, R.drawable.ic_timeline_marker_active));
            }
            tvDate.setText(DateUtil.milliSecondsToDateFormatted(event.getCreationDate(), DateUtil.DEFAULT_FORMAT));
            tvDetail.setText(event.getDetail());

            rootView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onMarkerClick(event.getLocation());
                }
            });
        }

    }

    public interface OnItemClickListener {

        void onMarkerClick(Location location);
    }
}