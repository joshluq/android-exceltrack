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
import pe.exceltransport.domain.Event;
import pe.exceltransport.exceltrack.R;
import pe.exceltransport.exceltrack.view.util.DateUtil;

public class EventTimeLineAdapter extends RecyclerView.Adapter<EventTimeLineAdapter.TimeLineViewHolder> {

    private Context context;

    private List<Event> list;

    private Event.Type type;

    @Inject
    public EventTimeLineAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
        this.type = Event.Type.TRACKING;
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

    public void bindList(List<Event> list, Event.Type type) {
        if (list != null) {
            this.list = filterByType(list, type);
            this.type = type;
            notifyDataSetChanged();
        }
    }

    private List<Event> filterByType(List<Event> list, Event.Type type) {
        List<Event> filteredList = new ArrayList<>();
        for (Event event : list) {
            if (event.getType().equals(type)) {
                filteredList.add(event);
            }
        }
        return filteredList;
    }


    public class TimeLineViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.time_marker)
        TimelineView tlMarker;

        @BindView(R.id.tv_date)
        TextView tvDate;

        @BindView(R.id.tv_detail)
        TextView tvDetail;

        TimeLineViewHolder(View itemView, int viewType) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            tlMarker.initLine(viewType);
        }

        private void bind(Event event) {
            if (type.equals(Event.Type.INCIDENCE)) {
                tlMarker.setMarker(ContextCompat.getDrawable(context, R.drawable.ic_timeline_marker_inactive));
            } else {
                tlMarker.setMarker(getLayoutPosition() == getItemCount() - 1 ? ContextCompat.getDrawable(context, R.drawable.ic_timeline_marker_disable) : ContextCompat.getDrawable(context, R.drawable.ic_timeline_marker_active));
            }

            tvDate.setText(DateUtil.milliSecondsToDateFormatted(event.getCreationDate(), DateUtil.DEFAULT_FORMAT));
            tvDetail.setText(event.getDetail());
        }
    }
}
