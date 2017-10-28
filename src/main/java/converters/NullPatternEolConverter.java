package converters;

import converters.EolConversion;
import converters.EolConverter;

public class NullPatternEolConverter implements EolConverter {

    public byte[] convert(byte[] data, int dataLength, EolConversion eolConversion) {
        byte[] outputData = new byte[dataLength];
        System.arraycopy(data, 0, outputData, 0, dataLength);
        return outputData;
    }
}
