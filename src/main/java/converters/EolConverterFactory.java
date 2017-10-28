package converters;

import converters.encoding.Encoding;

import java.util.HashMap;
import java.util.Map;

public class EolConverterFactory {

    private static Map<Encoding, EolConverter> CONVERTERS = new HashMap<Encoding, EolConverter>();
    static {
        CONVERTERS.put(Encoding.UTF32, new Utf32EolConverter());
        CONVERTERS.put(Encoding.UTF16, new Utf16EolConverter());
        CONVERTERS.put(Encoding.UTF8, new Utf8EolConverter());
    }

    public static EolConverter getConverterFor(Encoding encoding) {
        EolConverter eolConverter = CONVERTERS.get(encoding);
        if (eolConverter == null) {
            eolConverter = new NullPatternEolConverter();
        }
        return eolConverter;
    }
}
