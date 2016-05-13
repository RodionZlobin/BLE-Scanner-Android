package se.rodion.scanble;

import java.util.Locale;

public final class Beacon {
    private String name;
    private String address;
    private String rssi;
    private String uuids;
    private String serialNumber;

    public Beacon(String name, String address, String rssi, String uuids)
    {
        this.name = name;
        this.address = address;
        this.rssi = rssi;
        this.uuids = uuids;
        this.serialNumber = createSerialNumber(address);
    }

    public String getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public String getRssi() {
        return rssi;
    }

    public String getUuids() {
        return uuids;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    private String createSerialNumber(String macAddress)
    {
        String[] macAddressArray = macAddress.split(":");
        byte[] macAddressAsByte = new byte[6];
        int[] serialNumberAsInt = new int[6];
        StringBuilder createdSerialNumber = new StringBuilder();
        for (int i = 0; i<macAddressArray.length; i++)
        {
            macAddressAsByte[i] = Integer.decode("0x" + macAddressArray[i]).byteValue();
            serialNumberAsInt[i] = macAddressAsByte[i] & 0xff;
            String stringToAppend = String.format(Locale.getDefault(),"%03d", serialNumberAsInt[i]);

            createdSerialNumber.append(stringToAppend);
            if(i<macAddressArray.length-1)
            {
                createdSerialNumber.append("-");
            }
        }

        return createdSerialNumber.toString();
    }
}
