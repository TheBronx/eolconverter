package converters;

import converters.encoding.Encoding;

import java.util.HashMap;
import java.util.Map;

public class Parser {

    private static Map<Encoding, Integer> CHAR_SIZE = new HashMap<Encoding, Integer>();
    static {
        CHAR_SIZE.put(Encoding.UNKNOWN, 1);
        CHAR_SIZE.put(Encoding.UTF8, 1);
        CHAR_SIZE.put(Encoding.UTF16BE, 2);
        CHAR_SIZE.put(Encoding.UTF16LE, 2);
        CHAR_SIZE.put(Encoding.UTF32BE, 4);
        CHAR_SIZE.put(Encoding.UTF32LE, 4);
    }

    private final byte[] data;
    private final int dataLength;
    private final Encoding encoding;

    private int position;

    public Parser(byte[] data, int dataLength, Encoding encoding) {
        this.data = data;
        this.dataLength = dataLength;
        this.encoding = encoding;
        this.position = 0;
    }

    public byte[] consumeNext() {
        Integer size = CHAR_SIZE.get(encoding);

        byte[] next = next();
        position += size;
        return next;
    }

    public byte[] next() {
        if (position >= dataLength) return null;

        Integer size = CHAR_SIZE.get(encoding);
        byte[] next = new byte[size];
        System.arraycopy(data, position, next, 0, size);
        return next;
    }

}
