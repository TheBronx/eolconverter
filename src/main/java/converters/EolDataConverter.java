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
        byte[] convertedData = convertData(converter, data, dataLength);

        return new DataBytes(convertedData, convertedData.length);
    }

    public byte[] convertData(EolConverter converter, byte[] data, int dataLength) {
        byte[] output = new byte[dataLength * 2];
        int outputLength = 0;

        Parser parser = new Parser(data, dataLength, Encoding.UTF32);
        byte[] nextChar = parser.consumeNext();
        while(nextChar != null) {
            byte[] convertedChar = converter.convert(nextChar, eolConversion, parser);
            System.arraycopy(convertedChar, 0, output, outputLength, convertedChar.length);
            outputLength += convertedChar.length;

            nextChar = parser.consumeNext();
        }

        return getDataBytes(output, outputLength);
    }

    private byte[] getDataBytes(byte[] output, int outputLength) {
        byte[] convertedData = new byte[outputLength];
        System.arraycopy(output, 0, convertedData, 0, outputLength);
        return convertedData;
    }
}