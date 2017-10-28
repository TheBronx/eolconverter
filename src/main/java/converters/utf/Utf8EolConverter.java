package converters.utf;

import converters.EolConversion;
import converters.EolConverter;
import converters.Parser;
import converters.utils.ByteUtils;

import java.util.HashMap;
import java.util.Map;

public class Utf8EolConverter implements EolConverter {

    private static Map<EolConversion, byte[]> CONVERSION = new HashMap<EolConversion, byte[]>();
    static {
        CONVERSION.put(EolConversion.LF, ByteUtils.hexToByteArray("0A"));
        CONVERSION.put(EolConversion.CR, ByteUtils.hexToByteArray("0D"));
        CONVERSION.put(EolConversion.CRLF, ByteUtils.hexToByteArray("0D0A"));
    }

    public byte[] convert(byte[] aChar, EolConversion eolConversion, Parser parser) {
        if (isCR(aChar)) {
            byte[] nextChar = parser.next();
            if (isLF(nextChar)) {       //look ahead to see if it is CRLF or just CR
                parser.consumeNext();   //it is CRLF, consume next char too so we dont replace this entity twice
            }

            return CONVERSION.get(eolConversion);
        } else if (isLF(aChar)) {
            return CONVERSION.get(eolConversion);
        }

        return aChar;
    }

    private boolean isCR(byte[] bytes) {
        if (bytes == null || bytes.length<1) return false;

        return CrLfByteUtils.isCarriageReturn(bytes[0]);
    }

    private boolean isLF(byte[] bytes) {
        if (bytes == null || bytes.length<1) return false;

        return CrLfByteUtils.isLineFeed(bytes[0]);
    }

}
