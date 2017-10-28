package converters;

import converters.EolConversion;
import converters.EolConverter;
import converters.Parser;

public class NullPatternEolConverter implements EolConverter {

    public byte[] convert(byte[] aChar, EolConversion eolConversion, Parser parser) {
        return aChar;
    }
}
