package converters;

import converters.encoding.Encoding;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EolConverterFactoryTest {

    @Test
    public void utf32BEConverter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UTF32BE);

        assertThat(converter, is(instanceOf(Utf32BigEndianEolConverter.class)));
    }

    @Test
    public void utf32LEConverter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UTF32LE);

        assertThat(converter, is(instanceOf(Utf32LittleEndianEolConverter.class)));
    }

    @Test
    public void utf16BEConverter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UTF16BE);

        assertThat(converter, is(instanceOf(Utf16BigEndianEolConverter.class)));
    }

    @Test
    public void utf16LEConverter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UTF16LE);

        assertThat(converter, is(instanceOf(Utf16LittleEndianEolConverter.class)));
    }

    @Test
    public void utf8Converter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UTF8);

        assertThat(converter, is(instanceOf(Utf8EolConverter.class)));
    }

    @Test
    public void nullPatternConverter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UNKNOWN);

        assertThat(converter, is(instanceOf(NullPatternEolConverter.class)));
    }

}