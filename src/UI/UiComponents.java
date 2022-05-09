package UI;

import BusinessLogic.BusinessLogicController;
import com.sun.jdi.event.StepEvent;
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Vector;

public class UiComponents extends JFrame implements ActionListener {
    private JPanel leftSidePanel;
    private JPanel rightSidePanel;
    private JPanel leftSideBottomPanel;
    private JPanel rightSideBottomPanel;
    private JPanel rightSideTopPanel;
    private JPanel invoiceNumPanel;
    private JPanel invoiceDatePanel;
    private JPanel customerNamePanel;
    private JPanel invoiceTotalPanel;
    private JPanel invoiceItemsTableLabelPanel;
    private JPanel invoicesTableLabelPanel;
    private JTable invoicesTable;
    private JTable invoiceItemsTable;
    private JLabel invoicesTableLabel;
    private JLabel invoiceItemsLabel;
    private JLabel invoiceNumLabel;
    private JLabel invoiceNumValue;
    private JLabel invoiceDateLabel;
    private JTextField invoiceDateInput;
    private JLabel customerNameLabel;
    private JTextField customerNameInput;
    private JLabel invoiceTotalLabel;
    private JLabel invoiceTotalValue;
    private DefaultTableModel invoicesTableModel;
    private DefaultTableModel invoiceItemsTableModel;
    private JButton createNewInvoiceBtn;
    private JButton deleteInvoiceBtn;
    private JButton saveChangesBtn;
    private JButton cancelChangesBtn;
    private JMenu fileHandlingMenu;
    private JMenuBar fileHandlingMenuBar;
    private JMenuItem saveFileMenuItem;
    private JMenuItem loadFileMenuItem;
    private JMenuItem loadInvoiceHeaderMenuItem;
    private JMenuItem loadInvoiceLineMenuItem;
    private JMenuItem saveInvoiceHeaderMenuItem;
    private JMenuItem saveInvoiceLineMenuItem;
    private JOptionPane errorMsgs;
    private String[] invoicesTableColumns = {"No.","Date","Customer","Total"};
    private String[] invoiceItemsTableColumns = {"No.","Item Name","Item Price","Count","Item Total"};
    private String filePath;
    private BusinessLogicController businessLogicController = new BusinessLogicController();
    private String[][] invoicesData;
    private InvoiceItemsNumDialog invoiceItemsNumDialog = new InvoiceItemsNumDialog();
    private InvoiceHeader invoiceHeader = new InvoiceHeader();
    private InvoiceLine invoiceLine = new InvoiceLine();

    public UiComponents(){
        super("Sales Invoice Generator App");
        setSize(1000,600);
        setLocation(130,40);
        setLayout(new GridLayout(0,2));
        leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel,BoxLayout.Y_AXIS));
        rightSidePanel = new JPanel();
        rightSidePanel.setLayout(new BoxLayout(rightSidePanel,BoxLayout.Y_AXIS));
        leftSideBottomPanel = new JPanel();
        rightSideBottomPanel = new JPanel();
        rightSideTopPanel = new JPanel();
        rightSideTopPanel.setLayout(new BoxLayout(rightSideTopPanel,BoxLayout.Y_AXIS));
        invoiceNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        customerNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceTotalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoicesTableLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceItemsTableLabelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoicesTableLabel = new JLabel("Invoices Table");
        invoiceItemsLabel = new JLabel("Invoice Items");
        invoiceNumLabel = new JLabel("Invoice Number");
        invoiceNumValue = new JLabel("0");
        invoiceDateLabel = new JLabel("Invoice Date");
        invoiceDateInput = new JTextField(30);
        customerNameLabel = new JLabel("Customer Name");
        customerNameInput = new JTextField(30);
        invoiceTotalLabel = new JLabel("Invoice Total");
        invoiceTotalValue = new JLabel("0.00");
        invoiceDateInput.setSize(20,7);
        fileHandlingMenuBar = new JMenuBar();
        loadFileMenuItem = new JMenu("Load File");
        loadInvoiceHeaderMenuItem = new JMenuItem("Load Invoice Header");
        loadInvoiceLineMenuItem = new JMenuItem("Load Invoice Line");
        saveFileMenuItem = new JMenu("Save File");
        saveInvoiceHeaderMenuItem = new JMenuItem("Save Invoice Header");
        saveInvoiceLineMenuItem = new JMenuItem("Save Invoice Line");
        fileHandlingMenu = new JMenu("File");
        loadFileMenuItem.add(loadInvoiceHeaderMenuItem);
        loadFileMenuItem.add(loadInvoiceLineMenuItem);
        saveFileMenuItem.add(saveInvoiceHeaderMenuItem);
        saveFileMenuItem.add(saveInvoiceLineMenuItem);
        fileHandlingMenu.add(loadFileMenuItem);
        fileHandlingMenu.add(saveFileMenuItem);
        fileHandlingMenuBar.add(fileHandlingMenu);
        setJMenuBar(fileHandlingMenuBar);

        saveChangesBtn = new JButton("Save");
        cancelChangesBtn = new JButton("Cancel");
        createNewInvoiceBtn = new JButton("Create New Invoice");
        deleteInvoiceBtn = new JButton("Delete Invoice");
        invoicesTableModel = new DefaultTableModel();
        invoicesTable = new JTable(invoicesTableModel);
        invoicesTableModel.addColumn(invoicesTableColumns[0]);
        invoicesTableModel.addColumn(invoicesTableColumns[1]);
        invoicesTableModel.addColumn(invoicesTableColumns[2]);
        invoicesTableModel.addColumn(invoicesTableColumns[3]);
        invoiceItemsTableModel = new DefaultTableModel();
        invoiceItemsTable = new JTable(invoiceItemsTableModel);
        invoiceItemsTableModel.addColumn(invoiceItemsTableColumns[0]);
        invoiceItemsTableModel.addColumn(invoiceItemsTableColumns[1]);
        invoiceItemsTableModel.addColumn(invoiceItemsTableColumns[2]);
        invoiceItemsTableModel.addColumn(invoiceItemsTableColumns[3]);
        invoiceItemsTableModel.addColumn(invoiceItemsTableColumns[4]);
        errorMsgs = new JOptionPane();

        loadInvoiceHeaderMenuItem.setActionCommand("LIH");
        loadInvoiceLineMenuItem.setActionCommand("LIL");
        saveInvoiceHeaderMenuItem.setActionCommand("SIH");
        saveInvoiceLineMenuItem.setActionCommand("SIL");
        saveChangesBtn.setActionCommand("SC");
        cancelChangesBtn.setActionCommand("CC");
        createNewInvoiceBtn.setActionCommand("CNI");
        deleteInvoiceBtn.setActionCommand("DI");
        invoiceItemsNumDialog.okBtn.setActionCommand("Ok");
        invoiceItemsNumDialog.exitBtn.setActionCommand("Exit");


        loadInvoiceHeaderMenuItem.addActionListener(this);
        loadInvoiceLineMenuItem.addActionListener(this);
        saveInvoiceHeaderMenuItem.addActionListener(this);
        saveInvoiceLineMenuItem.addActionListener(this);
        saveChangesBtn.addActionListener(this);
        cancelChangesBtn.addActionListener(this);
        createNewInvoiceBtn.addActionListener(this);
        deleteInvoiceBtn.addActionListener(this);
        invoiceItemsNumDialog.okBtn.addActionListener(this);
        invoiceItemsNumDialog.exitBtn.addActionListener(this);
        invoicesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showInvoicesTableRowDetails();
            }
        });

        invoicesTable.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                showInvoicesTableRowDetails();
            }
        });




        add(leftSidePanel);
        add(rightSidePanel);
        invoicesTableLabelPanel.add(invoicesTableLabel);
        leftSidePanel.add(invoicesTableLabelPanel);
        leftSidePanel.add(new JScrollPane(invoicesTable));
        leftSideBottomPanel.add(createNewInvoiceBtn);
        leftSideBottomPanel.add(deleteInvoiceBtn);
        leftSidePanel.add(leftSideBottomPanel);
        invoiceNumPanel.add(invoiceNumLabel);
        invoiceNumPanel.add(invoiceNumValue);
        invoiceDatePanel.add(invoiceDateLabel);
        invoiceDatePanel.add(invoiceDateInput);
        customerNamePanel.add(customerNameLabel);
        customerNamePanel.add(customerNameInput);
        invoiceTotalPanel.add(invoiceTotalLabel);
        invoiceTotalPanel.add(invoiceTotalValue);
        rightSideTopPanel.add(invoiceNumPanel);
        rightSideTopPanel.add(invoiceDatePanel);
        rightSideTopPanel.add(customerNamePanel);
        rightSideTopPanel.add(invoiceTotalPanel);
        invoiceItemsTableLabelPanel.add(invoiceItemsLabel);
        rightSideTopPanel.add(invoiceItemsTableLabelPanel);
        rightSidePanel.add(rightSideTopPanel);
        rightSidePanel.add(new JScrollPane(invoiceItemsTable));
        rightSideBottomPanel.add(saveChangesBtn);
        rightSideBottomPanel.add(cancelChangesBtn);
        rightSidePanel.add(rightSideBottomPanel);

        initiallyLoadInvoicesData();
        initiallyLoadInvoiceItemsData();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }


    public void showInvoicesTableRowDetails(){
if(invoicesTable.getSelectedRow() != -1) {
    invoiceNumValue.setText("");
    invoiceDateInput.setText("");
    customerNameInput.setText("");
    invoiceTotalValue.setText("");
    Vector invoiceData = invoicesTableModel.getDataVector().get(invoicesTable.getSelectedRow());
    if (invoiceData.get(0) != null) {
        invoiceNumValue.setText(invoiceData.get(0).toString());
    }
    if (invoiceData.get(1) != null) {
        invoiceDateInput.setText(invoiceData.get(1).toString());
    }

    if (invoiceData.get(2) != null) {
        customerNameInput.setText(invoiceData.get(2).toString());
    }

    if (invoiceData.get(3) != null) {
        invoiceTotalValue.setText(invoiceData.get(3).toString());
    }
}
    }

    public void initiallyLoadInvoicesData() {
        try {
            invoiceHeader.setFilePath(Paths.get("").toAbsolutePath().toString()+"\\InvoicesFiles\\InvoiceHeader.csv");
            invoicesData = invoiceHeader.returnInvoiceHeaderData(invoiceHeader.getFilePath());
            invoicesTableModel.getDataVector().removeAllElements();
            for (   String[] row : invoicesData) {
                invoicesTableModel.addRow(row);
            }
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void initiallyLoadInvoiceItemsData() {
        try {
            invoiceLine.setFilePath(Paths.get("").toAbsolutePath().toString()+"\\InvoicesFiles\\InvoiceLine.csv");
            invoicesData = invoiceLine.returnInvoiceLineData(invoiceLine.getFilePath());
            invoiceItemsTableModel.getDataVector().removeAllElements();
            for (String[] row : invoicesData) {
                invoiceItemsTableModel.addRow(row);
            }
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void saveInvoiceChanges() {
        try {
            invoiceHeader.saveInvoiceHeaderChanges(invoiceHeader.getFilePath(),invoicesTableModel);
            invoiceLine.saveInvoiceLineChanges(invoiceLine.getFilePath(),invoiceItemsTableModel);
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void fireInvoiceItemsNumDialog(){
        invoiceItemsNumDialog.setInvoiceItemsNumber(0);
        invoiceItemsNumDialog.clearInvoiceItemsField();
        invoiceItemsNumDialog.setVisible(true);

    }

    public void createNewInvoice(boolean clickedBtnFlag){
        if(clickedBtnFlag){
            invoiceItemsNumDialog.performOkBtnActions();
            invoicesTableModel.addRow(new String[]{"", "", "", ""});
            for(int rowCount = 0 ;rowCount < invoiceItemsNumDialog.getInvoiceItemsNumber();rowCount++){
                invoiceItemsTableModel.addRow(new String[]{"","","","",""});
            }
        }else{
            invoiceItemsNumDialog.performExitBtnActions();
        }
    }

    public void deleteInvoiceData(){
        if(invoicesTable.getSelectedRow()!=-1) {
            invoiceHeader.setInvoiceNumber(invoicesTable.getValueAt(invoicesTable.getSelectedRow(),0).toString());
            invoicesTableModel.removeRow(invoicesTable.getSelectedRow());
            invoiceLine.setInvoiceNumber(invoiceHeader.getInvoiceNumber());
            for(int invoiceItemsTableRowIndex=invoiceItemsTable.getRowCount()-1;invoiceItemsTableRowIndex >= 0;invoiceItemsTableRowIndex--){
                if(invoiceLine.getInvoiceNumber().equals(invoiceItemsTable.getValueAt(invoiceItemsTableRowIndex,0).toString())){
                    invoiceItemsTableModel.removeRow(invoiceItemsTableRowIndex);
                }
            }
        }
    }

    public void loadFileFromMachine(String fileCode){
        JFileChooser fileSelector = new JFileChooser();
        if(fileSelector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePath=fileSelector.getSelectedFile().getPath();
            try {
            if(fileCode == "IH"){
                invoiceHeader.setFilePath(filePath);
                invoicesData = invoiceHeader.returnInvoiceHeaderData(invoiceHeader.getFilePath());
                invoicesTableModel.getDataVector().removeAllElements();
                for(String[] row : invoicesData) {
                    invoicesTableModel.addRow(row);
                }
            }else if(fileCode == "IL"){
                    invoiceLine.setFilePath(filePath);
                    invoicesData = invoiceLine.returnInvoiceLineData(invoiceLine.getFilePath());
                    invoiceItemsTableModel.getDataVector().removeAllElements();
                    for(String[] row : invoicesData) {
                        invoiceItemsTableModel.addRow(row);
                    }
            }

            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public void cancelInvoiceChanges(){
        try{
            invoicesData = invoiceHeader.returnInvoiceHeaderData(invoiceHeader.getFilePath());
            invoicesTableModel.getDataVector().removeAllElements();
            for (   String[] row : invoicesData) {
                invoicesTableModel.addRow(row);
            }

            invoicesData = invoiceLine.returnInvoiceLineData(invoiceLine.getFilePath());
            invoiceItemsTableModel.getDataVector().removeAllElements();
            for (String[] row : invoicesData) {
                invoiceItemsTableModel.addRow(row);
            }
    }catch (IOException ex) {
        JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
    }
    }

    public void saveFileToMachine(String fileCode){
        JFileChooser fileSelector = new JFileChooser();
        if(fileSelector.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            filePath=fileSelector.getSelectedFile().getPath();
            String fileName = filePath.substring(filePath.lastIndexOf("\\")+1);
            String invoiceHeaderPath = filePath.replace(fileName,"InvoiceHeader_"+fileName);
            String invoiceLinePath = filePath.replace(fileName,"\\InvoiceLine_"+fileName);

            try {
                if(fileCode == "IH"){
                    invoiceHeader.exportInvoiceHeaderFile(invoiceHeaderPath,invoicesTableModel);
                }else if(fileCode == "IL"){
                    invoiceLine.exportInvoiceLineFile(invoiceLinePath,invoiceItemsTableModel);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "LIH":
                loadFileFromMachine("IH");
                break;

            case "LIL":
                loadFileFromMachine("IL");
                break;

            case "SIH":
                saveFileToMachine("IH");
                break;

            case "SIL":
                saveFileToMachine("IL");
                break;

            case "SC":
                saveInvoiceChanges();
                break;

            case "CC":
                cancelInvoiceChanges();
                break;

            case "CNI":
                fireInvoiceItemsNumDialog();
                break;

            case "DI":
                deleteInvoiceData();
                break;

            case "Ok":
                createNewInvoice(true);
                break;

            case "Exit":
                createNewInvoice(false);
                break;

            default:

                break;
        }
    }
}
