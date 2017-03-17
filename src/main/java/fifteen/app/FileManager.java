package fifteen.app;

import java.io.FileNotFoundException;
import java.io.IOException;


public interface FileManager<T> {
    T ReadFromFile(String wrongFileName) throws IOException;
}
