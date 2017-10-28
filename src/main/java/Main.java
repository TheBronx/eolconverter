import converters.EolConversion;
import converters.EolDataConverter;
import data.DataBytes;

import java.io.*;

public class Main {

    private static final int BLOCK_SIZE = 1024;

    public static void main(String[] args) {
        EolDataConverter eolConverter = new EolDataConverter(EolConversion.CRLF);

        try {
            InputStream inputstream = new FileInputStream("source.txt");
            OutputStream outputStream = new FileOutputStream("destination.txt");

            byte[] readBuffer = new byte[BLOCK_SIZE];

            int dataLength = inputstream.read(readBuffer, 0, BLOCK_SIZE);
            while(dataLength != -1) {
                DataBytes converted = eolConverter.convert(readBuffer, dataLength);
                outputStream.write(converted.getOutputData(), 0, converted.getOutputLength());

                dataLength = inputstream.read(readBuffer, 0, BLOCK_SIZE);
            }
            inputstream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
