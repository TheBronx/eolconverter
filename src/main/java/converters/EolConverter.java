package converters;

public interface EolConverter {

    byte[] convert(byte[] aChar, EolConversion eolConversion, Parser parser);

}
