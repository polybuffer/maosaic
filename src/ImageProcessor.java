public interface ImageProcessor {
    String key(); //"compress", "resize", ... other function
    byte[] process(byte[] in);
}