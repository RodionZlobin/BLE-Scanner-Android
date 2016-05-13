package se.rodion.scanble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DeviceDetailsActivity extends AppCompatActivity {

    private TextView textViewAddress;
    private TextView textViewName;
    private TextView textViewRSSI;
    private TextView textViewUuids;
    private TextView textViewSerialNumber;
    private String address;
    private String name;
    private String rssi;
    private String uuids;
    private String serialNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_details);


        textViewAddress = (TextView) findViewById(R.id.device_address_details);
        textViewName = (TextView) findViewById(R.id.device_name_details);
        textViewRSSI = (TextView) findViewById(R.id.device_rssi_details);
        textViewUuids = (TextView) findViewById(R.id.device_uuids_details);
        textViewSerialNumber = (TextView) findViewById(R.id.device_serialnumber_details);

        Bundle bundle = getIntent().getExtras();
        address = bundle.getString("address");
        name = bundle.getString("name");
        rssi = bundle.getString("rssi");
        uuids = bundle.getString("uuids");
        serialNumber = bundle.getString("serialNumber");

        textViewAddress.setText(address);
        textViewName.setText(name);
        textViewRSSI.setText(rssi);
        textViewUuids.setText(uuids);
        textViewSerialNumber.setText(serialNumber);
    }
}
