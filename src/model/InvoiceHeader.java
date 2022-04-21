package model;

import BusinessLogic.BusinessLogicController;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class InvoiceHeader {
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

    public String[][]  returnInvoiceHeaderData(String filePath) throws IOException {
        return businessLogicController.loadInvoicesDataFromFile(filePath);
    }
    public void saveInvoiceHeaderChanges(String filePath, DefaultTableModel invoiceHeaderTableData) throws IOException {
        businessLogicController.saveTableDataToFile(filePath, invoiceHeaderTableData);
    }

    public void exportInvoiceHeaderFile(String filePath, DefaultTableModel invoiceHeaderTableData) throws IOException {
        businessLogicController.saveInvoicesToFile(filePath,invoiceHeaderTableData);
    }
}
