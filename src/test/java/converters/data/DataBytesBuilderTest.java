package converters.data;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class DataBytesBuilderTest {

    @Test
    public void singleAppend() {
        DataBytes data = DataBytesBuilder.dataBytes()
                .append(new byte[]{1})
                .build();

        assertThat(data.getOutputData(), equalTo(new byte[]{1}));
    }

    @Test
    public void multipleAppends() {
        DataBytes data = DataBytesBuilder.dataBytes()
                .append(new byte[]{1})
                .append(new byte[]{2})
                .append(new byte[]{3})
                .build();

        assertThat(data.getOutputData(), equalTo(new byte[]{1, 2, 3}));
    }

}