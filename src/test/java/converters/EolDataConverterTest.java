package converters;

import data.DataBytes;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class EolDataConverterTest {

    private static final int BLOCK_SIZE = 1024;

    @Test
    public void convertCrToLfInUtf8() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.LF);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf8-cr.txt");

        assertResultEqualsFile(result, "files/bigfile-utf8-lf.txt");
    }

    @Test
    public void convertCrLfToLfInUtf8() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.LF);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf8-crlf.txt");

        assertResultEqualsFile(result, "files/bigfile-utf8-lf.txt");
    }

    @Test
    public void convertLfToCrLfInUtf8() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.CRLF);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf8-lf.txt");

        assertResultEqualsFile(result, "files/bigfile-utf8-crlf.txt");
    }

    @Test
    public void convertLfToCrInUtf8() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.CR);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf8-lf.txt");

        assertResultEqualsFile(result, "files/bigfile-utf8-cr.txt");
    }

    @Test
    public void convertMixedToLfInUtf8() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.LF);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf8-mixed.txt");

        assertResultEqualsFile(result, "files/bigfile-utf8-lf.txt");
    }

    @Test
    public void convertLfToLfInUtf8() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.LF);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf8-lf.txt");

        assertResultEqualsFile(result, "files/bigfile-utf8-lf.txt");
    }

    @Test
    public void convertCrLfToLfInUtf16() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.LF);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf16-crlf.txt");

        assertResultEqualsFile(result, "files/bigfile-utf16-lf.txt");
    }

    @Test
    public void convertLfToCrLfInUtf16() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.CRLF);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf16-lf.txt");

        assertResultEqualsFile(result, "files/bigfile-utf16-crlf.txt");
    }

    @Test
    public void convertLfToCrInUtf16() throws Exception {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.CR);

        byte[] result = convertFileContents(eolConverter, "files/bigfile-utf16-lf.txt");

        assertResultEqualsFile(result, "files/bigfile-utf16-cr.txt");
    }


    private void assertResultEqualsFile(byte[] result, String file) throws IOException {
        InputStream inputstream = new FileInputStream(file);
        byte[] expected = IOUtils.toByteArray(inputstream);

        for (int i=0; i<result.length; i++) {
            assertThat(result[i], equalTo(expected[i]));
        }
    }

    private byte[] convertFileContents(EolDataConverter eolConverter, String file) throws IOException {
        InputStream inputstream = new FileInputStream(file);
        ByteArrayOutputStream result = new ByteArrayOutputStream();

        byte[] readBuffer = new byte[BLOCK_SIZE];

        int dataLength = inputstream.read(readBuffer, 0, BLOCK_SIZE);
        while(dataLength != -1) {
            DataBytes converted = eolConverter.convert(readBuffer, dataLength);
            result.write(converted.getOutputData(), 0, converted.getOutputLength());

            dataLength = inputstream.read(readBuffer, 0, BLOCK_SIZE);
        }
        inputstream.close();
        return result.toByteArray();
    }

}