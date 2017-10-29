package converters;

public class NullPatternEolConverter implements EolConverter {

    public byte[] convert(byte[] aChar, EolConversion eolConversion, Parser parser) {
        return aChar;
    }
}
