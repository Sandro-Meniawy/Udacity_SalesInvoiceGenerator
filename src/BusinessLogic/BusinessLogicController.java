package BusinessLogic;

import IOModules.FilesController;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class BusinessLogicController {
    FilesController filesController = new FilesController();
    private String[] rows;
    private String[][] tableData;
    public String[][] loadInvoicesFromFile(String filePath) throws IOException {
        rows = filesController.readFileContent(filePath).split("\r\n");
        tableData = new String[rows.length][rows[0].split(",").length];
        for (int rowsCounter = 0;rowsCounter<rows.length;rowsCounter++) {
            tableData[rowsCounter] = rows[rowsCounter].split(",");
        }
        return tableData;
    }

    public void saveInvoicesToFile(String filePath, DefaultTableModel invoicesTableData) throws IOException {
        String tableData ="";
        for (int rowsCounter = 0; rowsCounter < invoicesTableData.getRowCount(); rowsCounter++) {
            for (int colsCounter = 0; colsCounter < invoicesTableData.getColumnCount(); colsCounter++) {
                if(invoicesTableData.getValueAt(rowsCounter, colsCounter)==null){
                    invoicesTableData.setValueAt("",rowsCounter, colsCounter);
                }
                tableData = tableData+invoicesTableData.getValueAt(rowsCounter, colsCounter).toString() + ",";
            }
            tableData = tableData.substring(0, tableData.length() - 1)+"\r\n";
        }
        filesController.writeFileContent(filePath,tableData);
    }

    public void saveTableDataToFile(DefaultTableModel inputTableData) throws IOException {
        String tableData ="";
        for (int rowsCounter = 0; rowsCounter < inputTableData.getRowCount(); rowsCounter++) {
            for (int colsCounter = 0; colsCounter < inputTableData.getColumnCount(); colsCounter++) {
                if(inputTableData.getValueAt(rowsCounter, colsCounter)==null){
                    inputTableData.setValueAt("",rowsCounter, colsCounter);
                }
                tableData = tableData+inputTableData.getValueAt(rowsCounter, colsCounter).toString() + ",";
            }
            tableData = tableData.substring(0, tableData.length() - 1)+"\r\n";
        }
        filesController.writeFileContent("NoPath",tableData);
    }

    public String[][] initiallyLoadTableDataFromFile(String tableFlag) throws IOException {

        rows = filesController.readInitialFileContent(tableFlag).split("\r\n");
        tableData = new String[rows.length][rows[0].split(",").length];
        for (int rowsCounter = 0;rowsCounter<rows.length;rowsCounter++) {
            tableData[rowsCounter] = rows[rowsCounter].split(",");
        }
        return tableData;
    }
}
