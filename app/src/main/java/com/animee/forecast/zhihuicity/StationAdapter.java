package com.animee.forecast.zhihuicity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.animee.forecast.R;

import java.util.List;

public class StationAdapter extends RecyclerView.Adapter<StationAdapter.StationViewHolder> {

    private List<Station> stations;

    public StationAdapter(List<Station> stations) {
        this.stations = stations;
    }

    @NonNull
    @Override
    public StationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_station, parent, false);
        return new StationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StationViewHolder holder, int position) {
        Station station = stations.get(position);
        holder.tvName.setText(station.getName());
        holder.tvDistance.setText(station.getDistance());
        holder.tvTime.setText(station.getTime());

        // 第一个站点高亮显示
        if (position == 0) {
            holder.tvName.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_blue_dark));
            holder.tvDistance.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_blue_dark));
            holder.tvTime.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_blue_dark));
        } else {
            holder.tvName.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.black));
            holder.tvDistance.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
            holder.tvTime.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.darker_gray));
        }
    }

    @Override
    public int getItemCount() {
        return stations.size();
    }

    public List<Station> getStations() {
        return stations;
    }

    static class StationViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvDistance, tvTime;

        public StationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvStationName);
            tvDistance = itemView.findViewById(R.id.tvStationDistance);
            tvTime = itemView.findViewById(R.id.tvStationTime);
        }
    }
}
