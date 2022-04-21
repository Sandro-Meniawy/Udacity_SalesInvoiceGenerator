package IOmodules;
import java.io.*;
import java.nio.file.Paths;

public class FilesController {
    File currentDir = new File(".");
    int fileSize;
    String returnedData;
    byte[] bytes;
    FileInputStream fileInput;
    FileOutputStream fileOutput;

    public String readFileContent(String filePath) throws IOException {
        fileInput = new FileInputStream(filePath);
        fileSize = fileInput.available();
        bytes = new byte[fileSize];
        fileInput.read(bytes);
        returnedData= new String(bytes);
        fileInput.close();
        return returnedData;
    }

    public void writeFileContent(String filePath, String invoicesTableData) throws IOException {
        if(filePath == "NoPath"){
            filePath = Paths.get("").toAbsolutePath().toString()+"\\InvoicesFiles\\InvoiceLine.csv";
        }

        if(!filePath.endsWith(".csv")){
            filePath +=".csv";
        }
        fileOutput = new FileOutputStream(filePath);
        bytes = invoicesTableData.getBytes();
        fileOutput.write(bytes);
        fileOutput.close();
    }

}
