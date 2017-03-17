package fifteen.app;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class PuzzleFileManagerTest {

    private FileManager fileManager;
    private final static String WRONG_FILE_NAME="wrongName";
    private final static String MATRIX_2X2_FILE ="Matrix2x2.txt";

    @Before
    public void setUp()
    {
        fileManager = new PuzzleFileManager();
    }
    @Test(expected = FileNotFoundException.class)
    public void LoadFileIntoArrayList() throws IOException {
        fileManager.ReadFromFile(WRONG_FILE_NAME);
    }

    @Test
    public void readFileIntoAnArray() throws IOException {

        createFileContainingMatrix2x2(MATRIX_2X2_FILE);

        ArrayList<Byte> someRandomBytes  = (ArrayList<Byte>) fileManager.ReadFromFile(MATRIX_2X2_FILE);

        assertThat(someRandomBytes)
                .isNotNull()
                .hasSize(6)
                .containsSequence((byte)2,(byte)2,(byte)1,(byte)2,(byte)3,(byte)0);
    }
    private void createFileContainingMatrix2x2(String fileName) throws IOException {
        List<String> lines = Arrays.asList("2 2","1 2", "3 0");
        Path file = Paths.get(fileName);
        Files.write(file, lines, Charset.forName("UTF-8"));
    }
}
