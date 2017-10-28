package converters.encoding;

import converters.utf.CrLfByteUtils;

public class EncodingGuesser {

    public Encoding guess(byte[] data, int dataLength) {
        Encoding encoding = encodingFromBom(data, dataLength);
        if (encoding != Encoding.UNKNOWN) return encoding;

        if (isUtf32BE(data, dataLength)) {
            return Encoding.UTF32BE;
        }

        if (isUtf32LE(data, dataLength)) {
            return Encoding.UTF32LE;
        }

        if (isUtf16BE(data, dataLength)) {
            return Encoding.UTF16BE;
        }

        if (isUtf16LE(data, dataLength)) {
            return Encoding.UTF16LE;
        }

        if (isUtf8(data, dataLength)) {
            return Encoding.UTF8;
        }

        return Encoding.UNKNOWN;
    }

    private Encoding encodingFromBom(byte[] data, int dataLength) {
        if (isUtf32BEBom(data, dataLength)) return Encoding.UTF32BE;
        if (isUtf32LEBom(data, dataLength)) return Encoding.UTF32LE;

        if (isUtf16BEBom(data, dataLength)) return Encoding.UTF16BE;
        if (isUtf16LEBom(data, dataLength)) return Encoding.UTF16LE;

        if (isUtf8Bom(data, dataLength)) return Encoding.UTF8;

        return Encoding.UNKNOWN;
    }

    private boolean isUtf32BEBom(byte[] data, int dataLength) {
        if (dataLength < 4) return false;

        boolean isUtf32BigEndian = data[0] == (byte) 0x00 && data[1] == (byte) 0x00 && data[2] == (byte) 0xFE && data[3] == (byte) 0xFF;

        return isUtf32BigEndian;
    }

    private boolean isUtf32LEBom(byte[] data, int dataLength) {
        if (dataLength < 4) return false;

        boolean isUtf32LittleEndian = data[0] == (byte) 0xFF && data[1] == (byte) 0xFE && data[2] == (byte) 0x00 && data[3] == (byte) 0x00;

        return isUtf32LittleEndian;
    }

    private boolean isUtf16BEBom(byte[] data, int dataLength) {
        if (dataLength < 2) return false;

        boolean isUtf16BigEndian = data[0] == (byte) 0xFE && data[1] == (byte) 0xFF;

        return isUtf16BigEndian;
    }

    private boolean isUtf16LEBom(byte[] data, int dataLength) {
        if (dataLength < 2) return false;

        boolean isUtf16LittleEndian = data[0] == (byte) 0xFF && data[1] == (byte) 0xFE;

        return isUtf16LittleEndian;
    }

    private boolean isUtf8Bom(byte[] data, int dataLength) {
        if (dataLength < 3) return false;

        return data[0] == (byte) 0xEF && data[1] == (byte) 0xBB && data[2] == (byte) 0xBF;
    }

    private boolean isUtf8(byte[] data, int dataLength) {
        for (int i=0; i<dataLength; i++) {
            if (CrLfByteUtils.isLineFeedOrCarriageReturn(data[i])) return true;
        }
        return false;
    }

    private boolean isUtf16BE(byte[] data, int dataLength) {
        for (int i=0; i<dataLength - 1; i = i+2) {
            if (CrLfByteUtils.isLineFeedOrCarriageReturnBigEndian(data[i], data[i + 1])) return true;
        }
        return false;
    }

    private boolean isUtf16LE(byte[] data, int dataLength) {
        for (int i=0; i<dataLength - 1; i = i+2) {
            if (CrLfByteUtils.isLineFeedOrCarriageReturnLittleEndian(data[i], data[i + 1])) return true;
        }
        return false;
    }

    private boolean isUtf32BE(byte[] data, int dataLength) {
        for (int i=0; i<dataLength - 3; i = i+4) {
            if (CrLfByteUtils.isLineFeedOrCarriageReturnBigEndian(data[i], data[i + 1], data[i + 2], data[i + 3])) return true;
        }
        return false;
    }

    private boolean isUtf32LE(byte[] data, int dataLength) {
        for (int i=0; i<dataLength - 3; i = i+4) {
            if (CrLfByteUtils.isLineFeedOrCarriageReturnLittleEndian(data[i], data[i + 1], data[i + 2], data[i + 3])) return true;
        }
        return false;
    }

}
