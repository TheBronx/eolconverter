package converters;

public interface EolConverter {
    byte[] convert(byte[] data, int dataLength, EolConversion eolConversion);
}
