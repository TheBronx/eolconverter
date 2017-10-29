package converters.data;

public class DataBytesBuilder {

    private byte[] data;

    private DataBytesBuilder() {
        data = new byte[0];
    }

    public static DataBytesBuilder dataBytes() {
        return new DataBytesBuilder();
    }

    public DataBytesBuilder append(byte[] bytes) {
        byte[] result = new byte[data.length + bytes.length];
        for (int i=0; i<result.length; i++) {
            if (i < data.length) {
                result[i] = data[i];
            } else {
                result[i] = bytes[i - data.length];
            }
        }
        data = result;
        return this;
    }

    public DataBytes build() {
        return new DataBytes(data, data.length);
    }
}
