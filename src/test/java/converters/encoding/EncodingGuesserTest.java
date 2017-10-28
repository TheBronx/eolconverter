package converters.encoding;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

public class EncodingGuesserTest {

    private EncodingGuesser guesser = new EncodingGuesser();

    @Test
    public void utf8() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-8.txt");
        assertThat(encoding, equalTo(Encoding.UTF8));
    }

    @Test
    public void utf8WithBom() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-8-bom.txt");
        assertThat(encoding, equalTo(Encoding.UTF8));
    }

    @Test
    public void utf16BigEndian() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-16-be.txt");
        assertThat(encoding, equalTo(Encoding.UTF16BE));
    }

    @Test
    public void utf16BigEndianWithBom() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-16-be-bom.txt");
        assertThat(encoding, equalTo(Encoding.UTF16BE));
    }

    @Test
    public void utf16LittleEndian() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-16-le.txt");
        assertThat(encoding, equalTo(Encoding.UTF16LE));
    }

    @Test
    public void utf16LittleEndianWithBom() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-16-le-bom.txt");
        assertThat(encoding, equalTo(Encoding.UTF16LE));
    }

    @Test
    public void utf32BigEndian() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-32-be.txt");
        assertThat(encoding, equalTo(Encoding.UTF32BE));
    }

    @Test
    public void utf32BigEndianWithBom() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-32-be-bom.txt");
        assertThat(encoding, equalTo(Encoding.UTF32BE));
    }

    @Test
    public void utf32LittleEndian() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-32-le.txt");
        assertThat(encoding, equalTo(Encoding.UTF32LE));
    }

    @Test
    public void utf32LittleEndianWithBom() throws Exception {
        Encoding encoding = getFileEncoding("files/sample-utf-32-le-bom.txt");
        assertThat(encoding, equalTo(Encoding.UTF32LE));
    }

    private Encoding getFileEncoding(String filePath) throws IOException {
        InputStream inputstream = new FileInputStream(filePath);
        byte[] readBuffer = new byte[1024];
        int dataLength = inputstream.read(readBuffer, 0, 1024);

        return guesser.guess(readBuffer, dataLength);
    }

}