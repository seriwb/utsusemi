package box.white.utsusemi.component;

import java.io.FileWriter;
import java.io.IOException;

import box.white.utsusemi.UseInstance;

/**
 * ファイル書き込み用クラス
 */
public class FileWriterComponent {

    private final FileWriter writer;
    
    private FileWriterComponent(final String fileName) throws IOException {
        writer = new FileWriter(fileName);
    }
    
    private void close() throws IOException {
        writer.close();
    }
    
    public void write(final String message) throws IOException {
        writer.write(message);
    }
    
    /**
     * ファイル書き込み処理を行う
     * 
     * @param fileName 書き出すファイル名
     * @param block 書き込み処理
     * @throws IOException 書き込み例外時に発生
     */
    public static void use(
            final String fileName,
            final UseInstance<FileWriterComponent, IOException> block)
    throws IOException {
        
        final FileWriterComponent instance = new FileWriterComponent(fileName);
        try {
            block.accept(instance);
        } finally {
            instance.close();
        }
    }
}
