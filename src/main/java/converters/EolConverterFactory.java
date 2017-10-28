package converters;

import converters.encoding.Encoding;

public class EolConverterFactory {

    public static EolConverter getConverterFor(Encoding encoding) {
        //TODO pick the right converter given the encoding
        return new NullPatternEolConverter();
    }
}
