package converters;

import converters.encoding.Encoding;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class EolConverterFactoryTest {

    @Test
    public void utf32Converter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UTF32);

        assertThat(converter, is(instanceOf(Utf32EolConverter.class)));
    }

    @Test
    public void utf16Converter() {
        EolConverter converter = EolConverterFactory.getConverterFor(Encoding.UTF16);

        assertThat(converter, is(instanceOf(Utf16EolConverter.class)));
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