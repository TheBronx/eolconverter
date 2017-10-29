package converters;

import converters.data.DataBytes;
import converters.data.DataBytesBuilder;
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

        return convertData(data, dataLength, encoding);
    }

    private DataBytes convertData(byte[] data, int dataLength, Encoding encoding) {
        EolConverter converter = EolConverterFactory.getConverterFor(encoding);
        Parser parser = new Parser(data, dataLength, encoding);

        return convertData(converter, parser);
    }

    private DataBytes convertData(EolConverter converter, Parser parser) {
        DataBytesBuilder builder = DataBytesBuilder.dataBytes();

        byte[] charToConvert = parser.consumeNext();
        while(charToConvert != null) {
            byte[] convertedChar = converter.convert(charToConvert, eolConversion, parser);
            builder.append(convertedChar);

            charToConvert = parser.consumeNext();
        }

        return builder.build();
    }

}