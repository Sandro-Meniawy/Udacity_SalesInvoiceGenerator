package model;

import BusinessLogic.BusinessLogicController;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class InvoiceLine {
    private String invoiceNumber;
    private String filePath;
    private BusinessLogicController businessLogicController = new BusinessLogicController();

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String[][]  returnInvoiceLineData(String filePath) throws IOException {
        return businessLogicController.loadInvoicesDataFromFile(filePath);
    }

    public void saveInvoiceLineChanges(String filePath,DefaultTableModel invoiceLineTableData) throws IOException {
        businessLogicController.saveTableDataToFile(filePath, invoiceLineTableData);
    }

    public void exportInvoiceLineFile(String filePath, DefaultTableModel invoiceLineTableData) throws IOException {
        businessLogicController.saveInvoicesToFile(filePath,invoiceLineTableData);
    }
}
