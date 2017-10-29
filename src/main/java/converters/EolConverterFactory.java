package converters;

import converters.encoding.Encoding;
import converters.utf.*;

import java.util.HashMap;
import java.util.Map;

public class EolConverterFactory {

    private static final NullPatternEolConverter DEFAULT_CONVERTER = new NullPatternEolConverter();

    private static Map<Encoding, EolConverter> CONVERTERS = new HashMap<Encoding, EolConverter>();
    static {
        CONVERTERS.put(Encoding.UTF32BE, new Utf32BigEndianEolConverter());
        CONVERTERS.put(Encoding.UTF32LE, new Utf32LittleEndianEolConverter());
        CONVERTERS.put(Encoding.UTF16BE, new Utf16BigEndianEolConverter());
        CONVERTERS.put(Encoding.UTF16LE, new Utf16LittleEndianEolConverter());
        CONVERTERS.put(Encoding.UTF8, new Utf8EolConverter());
    }

    public static EolConverter getConverterFor(Encoding encoding) {
        EolConverter eolConverter = CONVERTERS.get(encoding);
        if (eolConverter == null) {
            eolConverter = DEFAULT_CONVERTER;
        }
        return eolConverter;
    }
}
