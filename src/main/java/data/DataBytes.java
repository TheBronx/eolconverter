package data;

public class DataBytes {

    private byte[] outputData;
    private int outputLength;

    public DataBytes(byte[] outputData, int outputLength) {
        this.outputData = outputData;
        this.outputLength = outputLength;
    }

    public byte[] getOutputData() {
        return outputData;
    }

    public int getOutputLength() {
        return outputLength;
    }
}
