package converters.utf;

public class CrLfByteUtils {

    private static final byte LF_BYTE = (byte) 0x0A;
    private static final byte CR_BYTE = (byte) 0x0D;

    public static boolean isLineFeedOrCarriageReturn(byte first) {
        if (isLineFeed(first)) return true;
        if (isCarriageReturn(first)) return true;

        return false;
    }

    public static boolean isLineFeed(byte first) {
        return (first == LF_BYTE);
    }

    public static boolean isCarriageReturn(byte first) {
        return first == CR_BYTE;
    }

    public static boolean isLineFeedOrCarriageReturnBigEndian(byte first, byte second) {
        if (isLineFeedBigEndian(first, second)) return true;

        if (isCarriageReturnBigEndian(first, second)) return true;

        return false;
    }

    public static boolean isLineFeedOrCarriageReturnLittleEndian(byte first, byte second) {
        if (isLineFeedLittleEndian(first, second)) return true;

        if (isCarriageReturnLittleEndian(first, second)) return true;

        return false;
    }

    public static boolean isLineFeed(byte first, byte second) {
        if (isLineFeedBigEndian(first, second)) return true;
        if (isLineFeedLittleEndian(first, second)) return true;
        return false;
    }

    private static boolean isLineFeedLittleEndian(byte first, byte second) {
        return first == LF_BYTE && second == (byte) 0x00;
    }

    private static boolean isLineFeedBigEndian(byte first, byte second) {
        return first == (byte) 0x00 && second == LF_BYTE;
    }

    public static boolean isCarriageReturn(byte first, byte second) {
        if (isCarriageReturnBigEndian(first, second)) return true;
        if (isCarriageReturnLittleEndian(first, second)) return true;
        return false;
    }

    private static boolean isCarriageReturnLittleEndian(byte first, byte second) {
        return first == CR_BYTE && second == (byte) 0x00;
    }

    private static boolean isCarriageReturnBigEndian(byte first, byte second) {
        return first == (byte) 0x00 && second == CR_BYTE;
    }

    public static boolean isLineFeedOrCarriageReturnBigEndian(byte first, byte second, byte third, byte fourth) {
        if (isLineFeedBigEndian(first, second, third, fourth)) return true;

        if (isCarriageReturnBigEndian(first, second, third, fourth)) return true;

        return false;
    }

    public static boolean isLineFeedOrCarriageReturnLittleEndian(byte first, byte second, byte third, byte fourth) {
        if (isLineFeedLittleEndian(first, second, third, fourth)) return true;

        if (isCarriageReturnLittleEndian(first, second, third, fourth)) return true;

        return false;
    }

    public static boolean isLineFeed(byte first, byte second, byte third, byte fourth) {
        if (isLineFeedBigEndian(first, second, third, fourth)) return true;
        if (isLineFeedLittleEndian(first, second, third, fourth)) return true;
        return false;
    }

    private static boolean isLineFeedLittleEndian(byte first, byte second, byte third, byte fourth) {
        return first == LF_BYTE && second == (byte) 0x00 && third == (byte) 0x00 && fourth == (byte) 0x00;
    }

    private static boolean isLineFeedBigEndian(byte first, byte second, byte third, byte fourth) {
        return first == (byte) 0x00 && second == (byte) 0x00 && third == (byte) 0x00 && fourth == LF_BYTE;
    }

    public static boolean isCarriageReturn(byte first, byte second, byte third, byte fourth) {
        if (isCarriageReturnBigEndian(first, second, third, fourth)) return true;
        if (isCarriageReturnLittleEndian(first, second, third, fourth)) return true;
        return false;
    }

    private static boolean isCarriageReturnLittleEndian(byte first, byte second, byte third, byte fourth) {
        return first == CR_BYTE && second == (byte) 0x00 && third == (byte) 0x00 && fourth == (byte) 0x00;
    }

    private static boolean isCarriageReturnBigEndian(byte first, byte second, byte third, byte fourth) {
        return first == (byte) 0x00 && second == (byte) 0x00 && third == (byte) 0x00 && fourth == CR_BYTE;
    }

}
