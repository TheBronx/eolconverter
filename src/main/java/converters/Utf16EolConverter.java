package converters;

public class Utf16EolConverter implements EolConverter {
    public byte[] convert(byte[] data, int dataLength, EolConversion eolConversion) {
        return new byte[0];
    }
}
