package converters;

import data.DataBytes;
import converters.encoding.Encoding;
import converters.encoding.EncodingGuesser;

public class EolDataConverter {

    private final EolConversion eolConversion;

    private EncodingGuesser encodingGuesser = new EncodingGuesser();

    public EolDataConverter(EolConversion eolConversion) {
        this.eolConversion = eolConversion;
    }

    /**
     * There is no such thing as C# "out" in Java so instead I am returning outputData and length in an Object
     */
    public DataBytes convert(byte[] data, int dataLength) {
        Encoding encoding = encodingGuesser.guess(data, dataLength);
        EolConverter converter = EolConverterFactory.getConverterFor(encoding);
        byte[] convertedData = converter.convert(data, dataLength, eolConversion);

        return new DataBytes(convertedData, convertedData.length);
    }
}