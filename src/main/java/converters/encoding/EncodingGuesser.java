package converters.encoding;

public class EncodingGuesser {

    private static final byte UTF8_LF = (byte) 0x0A;
    private static final byte UTF8_CR = (byte) 0x0D;

    public Encoding guess(byte[] data, int dataLength) {
        Encoding encoding = encodingFromBom(data, dataLength);
        if (encoding != Encoding.UNKNOWN) return encoding;

        if (isUtf32(data, dataLength)) {
            return Encoding.UTF32;
        }

        if (isUtf16(data, dataLength)) {
            return Encoding.UTF16;
        }

        if (isUtf8(data, dataLength)) {
            return Encoding.UTF8;
        }

        return Encoding.UNKNOWN;
    }

    private Encoding encodingFromBom(byte[] data, int dataLength) {
        if (isUtf32Bom(data, dataLength)) return Encoding.UTF32;

        if (isUtf16Bom(data, dataLength)) return Encoding.UTF16;

        if (isUtf8Bom(data, dataLength)) return Encoding.UTF8;

        return Encoding.UNKNOWN;
    }

    private boolean isUtf32Bom(byte[] data, int dataLength) {
        if (dataLength < 4) return false;

        boolean isUtf32BigEndian = data[0] == (byte) 0x00 && data[1] == (byte) 0x00 && data[2] == (byte) 0xFE && data[3] == (byte) 0xFF;
        boolean isUtf32LittleEndian = data[0] == (byte) 0xFF && data[1] == (byte) 0xFE && data[2] == (byte) 0x00 && data[3] == (byte) 0x00;

        return isUtf32BigEndian || isUtf32LittleEndian;
    }

    private boolean isUtf16Bom(byte[] data, int dataLength) {
        if (dataLength < 2) return false;

        boolean isUtf16BigEndian = data[0] == (byte) 0xFE && data[1] == (byte) 0xFF;
        boolean isUtf16LittleEndian = data[0] == (byte) 0xFF && data[1] == (byte) 0xFE;

        return isUtf16BigEndian || isUtf16LittleEndian;
    }

    private boolean isUtf8Bom(byte[] data, int dataLength) {
        if (dataLength < 3) return false;

        return data[0] == (byte) 0xEF && data[1] == (byte) 0xBB && data[2] == (byte) 0xBF;
    }

    private boolean isUtf8(byte[] data, int dataLength) {
        for (int i=0; i<dataLength; i++) {
            if (data[i] == UTF8_CR || data[i] == UTF8_LF) return true;
        }
        return false;
    }

    private boolean isUtf16(byte[] data, int dataLength) {
        return false;
    }

    private boolean isUtf32(byte[] data, int dataLength) {
        return false;
    }
}
