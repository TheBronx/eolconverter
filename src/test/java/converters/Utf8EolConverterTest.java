package converters;

import converters.utils.ByteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class Utf8EolConverterTest {

    private EolConverter converter = new Utf8EolConverter();

    @Mock
    private Parser parser;

    @Test
    public void convertOneCarriageReturnToALineFeed() {
        byte[] data = ByteUtils.hexToByteArray("0D");
        byte[] converted = converter.convert(data, EolConversion.LF, parser);

        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0A")));
    }

    @Test
    public void convertOneLineFeedToACarriageReturn() {
        byte[] data = ByteUtils.hexToByteArray("0A");
        byte[] converted = converter.convert(data, EolConversion.CR, parser);

        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0D")));
    }

    @Test
    public void convertOneLineFeedToACarriageReturnAndLineFeed() {
        byte[] data = ByteUtils.hexToByteArray("0A");
        byte[] converted = converter.convert(data, EolConversion.CRLF, parser);

        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0D" + "0A")));
    }

    @Test
    public void convertCarriageReturnAndLineFeedToLineFeedAndConsumeExtraChar() {
        byte[] data = ByteUtils.hexToByteArray("0D");
        when(parser.next()).thenReturn(ByteUtils.hexToByteArray("0A"));

        byte[] converted = converter.convert(data, EolConversion.LF, parser);

        verify(parser, times(1)).consumeNext();
        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0A")));
    }
}