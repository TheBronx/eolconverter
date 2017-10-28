package converters.encoding;

public class EncodingGuesser {

    private static final byte LF_BYTE = (byte) 0x0A;
    private static final byte CR_BYTE = (byte) 0x0D;

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
            if (isLineFeedOrCarriageReturn(data[i])) return true;
        }
        return false;
    }

    private boolean isUtf16(byte[] data, int dataLength) {
        for (int i=0; i<dataLength - 1; i++) {
            if (isLineFeedOrCarriageReturn(data[i], data[i + 1])) return true;
        }
        return false;
    }

    private boolean isUtf32(byte[] data, int dataLength) {
        for (int i=0; i<dataLength - 3; i++) {
            if (isLineFeedOrCarriageReturn(data[i], data[i + 1], data[i + 2], data[i + 3])) return true;
        }
        return false;
    }

    private boolean isLineFeedOrCarriageReturn(byte first) {
        if (first == LF_BYTE) return true;
        if (first == CR_BYTE) return true;

        return false;
    }

    private boolean isLineFeedOrCarriageReturn(byte first, byte second) {
        if (first == (byte) 0x00 && second == LF_BYTE) return true;
        if (first == LF_BYTE && second == (byte) 0x00) return true;

        if (first == (byte) 0x00 && second == CR_BYTE) return true;
        if (first == CR_BYTE && second == (byte) 0x00) return true;

        return false;
    }

    private boolean isLineFeedOrCarriageReturn(byte first, byte second, byte third, byte fourth) {
        if (first == (byte) 0x00 && second == (byte) 0x00 && third == (byte) 0x00 && fourth == LF_BYTE) return true;
        if (first == LF_BYTE && second == (byte) 0x00 && third == (byte) 0x00 && fourth == (byte) 0x00) return true;

        if (first == (byte) 0x00 && second == (byte) 0x00 && third == (byte) 0x00 && fourth == CR_BYTE) return true;
        if (first == CR_BYTE && second == (byte) 0x00 && third == (byte) 0x00 && fourth == (byte) 0x00) return true;

        return false;
    }
}
