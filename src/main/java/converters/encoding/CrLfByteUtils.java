package converters.encoding;

public class CrLfByteUtils {

    private static final byte LF_BYTE = (byte) 0x0A;
    private static final byte CR_BYTE = (byte) 0x0D;

    public static boolean isLineFeedOrCarriageReturn(byte first) {
        if (isLineFeed(first)) return true;
        if (isCarriageReturn(first)) return true;

        return false;
    }

    private static boolean isLineFeed(byte first) {
        return (first == LF_BYTE);
    }

    private static boolean isCarriageReturn(byte first) {
        return first == CR_BYTE;
    }

    public static boolean isLineFeedOrCarriageReturn(byte first, byte second) {
        if (isLineFeed(first, second)) return true;

        if (isCarriageReturn(first, second)) return true;

        return false;
    }

    private static boolean isLineFeed(byte first, byte second) {
        if (first == (byte) 0x00 && second == LF_BYTE) return true;
        if (first == LF_BYTE && second == (byte) 0x00) return true;
        return false;
    }

    private static boolean isCarriageReturn(byte first, byte second) {
        if (first == (byte) 0x00 && second == CR_BYTE) return true;
        if (first == CR_BYTE && second == (byte) 0x00) return true;
        return false;
    }

    public static boolean isLineFeedOrCarriageReturn(byte first, byte second, byte third, byte fourth) {
        if (isLineFeed(first, second, third, fourth)) return true;

        if (isCarriageReturn(first, second, third, fourth)) return true;

        return false;
    }

    public static boolean isLineFeed(byte first, byte second, byte third, byte fourth) {
        if (first == (byte) 0x00 && second == (byte) 0x00 && third == (byte) 0x00 && fourth == LF_BYTE) return true;
        if (first == LF_BYTE && second == (byte) 0x00 && third == (byte) 0x00 && fourth == (byte) 0x00) return true;
        return false;
    }

    public static boolean isCarriageReturn(byte first, byte second, byte third, byte fourth) {
        if (first == (byte) 0x00 && second == (byte) 0x00 && third == (byte) 0x00 && fourth == CR_BYTE) return true;
        if (first == CR_BYTE && second == (byte) 0x00 && third == (byte) 0x00 && fourth == (byte) 0x00) return true;
        return false;
    }

}
