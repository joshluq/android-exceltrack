package pe.exceltransport.exceltrack.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import pe.exceltransport.domain.Trip;
import pe.exceltransport.exceltrack.R;

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ItemHolder> {

    private List<Trip> list;

    private OnItemClickListener listener;


    @Inject
    public TripAdapter() {
        this.list = new ArrayList<>();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trip_list, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        holder.bind(list.get(holder.getLayoutPosition()));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void bindList(List<Trip> list) {
        if (list != null) {
            this.list = list;
            notifyDataSetChanged();
        }
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_customer_name)
        TextView tvCustomerName;

        @BindView(R.id.tv_start)
        TextView tvStart;

        @BindView(R.id.tv_finish)
        TextView tvFinish;

        ItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Trip trip) {
            tvCustomerName.setText(trip.getCustomer().getCompany().getTradeName());
            tvStart.setText(trip.getStart().getAddress());
            tvFinish.setText(trip.getFinish().getAddress());
        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onItemClick(list.get(getLayoutPosition()));
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Trip trip);
    }
}
