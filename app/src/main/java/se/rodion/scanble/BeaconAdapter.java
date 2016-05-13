package se.rodion.scanble;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class BeaconAdapter extends RecyclerView.Adapter<BeaconAdapter.DeviceViewHolder> {
    private Context context;
    private List<Beacon> beacons;

    public BeaconAdapter(Context context, List<Beacon> beacons) {
        this.context = context;
        this.beacons = beacons;
    }

    @Override
    public DeviceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_devices, parent, false);
        return new DeviceViewHolder(view, context, beacons);
    }

    @Override
    public void onBindViewHolder(DeviceViewHolder holder, int position) {

        holder.nameView.setText(beacons.get(position).getName());
        holder.serialNumberView.setText(beacons.get(position).getSerialNumber());
        holder.rssiView.setText(beacons.get(position).getRssi());

        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLineLight));
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorLineMedium));
        }
    }

    @Override
    public int getItemCount() {
        return beacons.size();
    }

    public static final class DeviceViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public final TextView nameView;
        public final TextView serialNumberView;
        public final TextView rssiView;
        List<Beacon> beacons;
        Context context;

        public DeviceViewHolder(View view, Context context, List<Beacon> beacons) {
            super(view);
            this.beacons = beacons;
            this.context = context;

            view.setOnClickListener(this);
            this.nameView = (TextView) view.findViewById(R.id.device_name);
            this.serialNumberView = (TextView) view.findViewById(R.id.device_serialnumber);
            this.rssiView = (TextView) view.findViewById(R.id.device_rssi);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Beacon beacon = this.beacons.get(position);
            Intent intent = new Intent(this.context, DeviceDetailsActivity.class);
            intent.putExtra("name", beacon.getName());
            intent.putExtra("address", beacon.getAddress());
            intent.putExtra("rssi", beacon.getRssi());
            intent.putExtra("uuids", beacon.getUuids());
            intent.putExtra("serialNumber", beacon.getSerialNumber());
            this.context.startActivity(intent);

        }
    }
}
