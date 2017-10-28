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
     * There is no such thing as C# "out" in Java so instead I am returning outputData and length in an Object (DataBytes)
     */
    public DataBytes convert(byte[] data, int dataLength) {
        Encoding encoding = encodingGuesser.guess(data, dataLength);

        byte[] convertedData = convertData(data, dataLength, encoding);

        return new DataBytes(convertedData, convertedData.length);
    }

    private byte[] convertData(byte[] data, int dataLength, Encoding encoding) {
        EolConverter converter = EolConverterFactory.getConverterFor(encoding);
        Parser parser = new Parser(data, dataLength, encoding);

        byte[] output = new byte[dataLength * 2];
        int outputLength = 0;

        byte[] charToConvert = parser.consumeNext();
        while(charToConvert != null) {
            byte[] convertedChar = converter.convert(charToConvert, eolConversion, parser);
            copyToOutput(output, outputLength, convertedChar);
            outputLength += convertedChar.length;

            charToConvert = parser.consumeNext();
        }

        return getDataBytes(output, outputLength);
    }

    private void copyToOutput(byte[] output, int outputLength, byte[] convertedChar) {
        System.arraycopy(convertedChar, 0, output, outputLength, convertedChar.length);
    }

    private byte[] getDataBytes(byte[] output, int outputLength) {
        byte[] convertedData = new byte[outputLength];
        System.arraycopy(output, 0, convertedData, 0, outputLength);
        return convertedData;
    }
}