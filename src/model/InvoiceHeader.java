package model;

import BusinessLogic.BusinessLogicController;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class InvoiceHeader {
    private String invoiceNumber;
    private String filePath;
    private InvoiceLine[] invoiceItems;
    private int invoiceItemsCounter;

    private int totalInvoicePrice = 0;
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

    public void calculateTotalInvoicePrice(String invoiceItemPrice){
        totalInvoicePrice = totalInvoicePrice + Integer.parseInt(invoiceItemPrice);
    }

    public String[] reformatInvoicesRows(String[] invoiceRow){
             String[] reformatedInvoiceRow = {};
        if(invoiceRow.length == 3){
            reformatedInvoiceRow = new String[invoiceRow.length+1];
            for(int rowItemCounter=0;rowItemCounter<invoiceRow.length;rowItemCounter++){
                reformatedInvoiceRow[rowItemCounter] = invoiceRow[rowItemCounter];
            }
            reformatedInvoiceRow[3] = String.valueOf(totalInvoicePrice);
        }else if(invoiceRow.length == 4){
            reformatedInvoiceRow = invoiceRow;
            reformatedInvoiceRow[3] = String.valueOf(totalInvoicePrice);
        }
        return reformatedInvoiceRow;
    }
    public int getTotalInvoicePrice() {
        return totalInvoicePrice;
    }

    public void setTotalInvoicePrice(int totalInvoicePrice) {
        this.totalInvoicePrice = totalInvoicePrice;
    }

    public InvoiceLine[] getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(String[][] invoiceItemsRows) {
        invoiceItems = new InvoiceLine[invoiceItemsRows.length];
        invoiceItemsCounter = 0;
        for (String[] row : invoiceItemsRows) {
            invoiceItems[invoiceItemsCounter] = new InvoiceLine(row[0],row[1],row[2],row[3]);
            invoiceItemsCounter++;
        }
    }
}
