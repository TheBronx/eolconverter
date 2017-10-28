package converters;

import converters.utils.ByteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class Utf32BigEndianEolConverterTest {

    private EolConverter converter = new Utf32EolConverter();

    @Mock
    private Parser parser;

    /*
        @Test
        public void convertCarriageReturnAndLineFeedToJustLineFeed() {
            byte[] data = ByteUtils.hexToByteArray("0000000D" + "0000000A");
            byte[] converted = converter.convert(data, data.length, EolConversion.LF);

            assertThat(converted, equalTo(ByteUtils.hexToByteArray("0000000A")));
        }

        @Test
        public void convertCarriageReturnAndLineFeedInOddPositionToJustLineFeed() {
            byte[] data = ByteUtils.hexToByteArray("00000020" + "0000000D" + "0000000A");
            byte[] converted = converter.convert(data, data.length, EolConversion.LF);

            assertThat(converted, equalTo(ByteUtils.hexToByteArray("00000020" + "0000000A")));
        }
    */

    @Test
    public void convertOneCarriageReturnToALineFeed() {
        byte[] data = ByteUtils.hexToByteArray("0000000D");
        byte[] converted = converter.convert(data, EolConversion.LF, parser);

        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0000000A")));
    }

    @Test
    public void convertOneLineFeedToACarriageReturn() {
        byte[] data = ByteUtils.hexToByteArray("0000000A");
        byte[] converted = converter.convert(data, EolConversion.CR, parser);

        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0000000D")));
    }

    @Test
    public void convertOneLineFeedToACarriageReturnAndLineFeed() {
        byte[] data = ByteUtils.hexToByteArray("0000000A");
        byte[] converted = converter.convert(data, EolConversion.CRLF, parser);

        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0000000D" + "0000000A")));
    }

    @Test
    public void convertCarriageReturnAndLineFeedToLineFeedAndConsumeExtraChar() {
        byte[] data = ByteUtils.hexToByteArray("0000000D");
        when(parser.next()).thenReturn(ByteUtils.hexToByteArray("0000000A"));

        byte[] converted = converter.convert(data, EolConversion.LF, parser);

        verify(parser, times(1)).consumeNext();
        assertThat(converted, equalTo(ByteUtils.hexToByteArray("0000000A")));
    }
}