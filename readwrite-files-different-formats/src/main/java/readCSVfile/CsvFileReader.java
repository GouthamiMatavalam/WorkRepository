package readCSVfile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import com.opencsv.CSVReader;

public class CsvFileReader {

	public static void main(String[] args) throws IOException
    {
        //Get scanner instance
      //  Scanner scanner = new Scanner(new File("ExcelFileLocation/Worker_Document_Download.csv"));
         
        String csvFile = "FileLocation/Worker_Document_Download.csv";


        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(csvFile));
            String[] line;
            while ((line = reader.readNext()) != null) {
                System.out.println("Country [id= " + line[0] + ", code= " + line[1] + "]");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }
	
	
}