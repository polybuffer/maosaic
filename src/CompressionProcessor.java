import java.util.zip.Deflater;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class CompressionProcessor implements ImageProcessor {
    @Override
    public String key() {
        return "compress";
    }

    @Override
    public byte[] process(byte[] data) {
        Deflater deflater = new Deflater(Deflater.BEST_COMPRESSION);
        deflater.setInput(data); //feed the input
        deflater.finish();       //tell the deflater no more inout is coming

        // compressed result into memory
        try (ByteArrayOutputStream out = new ByteArrayOutputStream(data.length)) {
            byte[] buffer = new byte[4096];//a 4 KB working chunk for each loop, not limit size for total data
            while(!deflater.finished()){
                out.write(buffer, 0, deflater.deflate(buffer)); //append each chunk to the output stream
            }
                return out.toByteArray();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}