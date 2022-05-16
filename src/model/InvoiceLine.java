package model;

import BusinessLogic.BusinessLogicController;

import javax.swing.table.DefaultTableModel;
import java.io.IOException;

public class InvoiceLine {
    private String invoiceNumber;
    private String filePath;
    private String totalItemPrice;
    private String itemName;
    private String itemPrice;
    private String itemCount;
    private BusinessLogicController businessLogicController = new BusinessLogicController();

    public InvoiceLine(){}
    public InvoiceLine(String invoiceNumber, String itemName, String itemPrice, String itemCount){
        this.invoiceNumber = invoiceNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.totalItemPrice = calculateTotalItemPrice(itemPrice,itemCount);
    }

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

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getTotalItemPrice() {
        return totalItemPrice;
    }

    public String calculateTotalItemPrice(String rowItemPrice, String rowItemCount){
        totalItemPrice = String.valueOf(Integer.parseInt(rowItemPrice)*Integer.parseInt(rowItemCount));
        return totalItemPrice;
    }
}
