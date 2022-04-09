package UI;

import BusinessLogic.BusinessLogicController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Vector;

public class UiComponents extends JFrame implements ActionListener {
    private JPanel leftSidePanel;
    private JPanel rightSidePanel;
    private JPanel leftSideTopPanel;
    private JPanel loadSaveFileContainerPanel;
    private JPanel leftSideBottomPanel;
    private JPanel rightSideBottomPanel;
    private JPanel rightSideTopPanel;
    private JPanel invoiceNumPanel;
    private JPanel invoiceDatePanel;
    private JPanel customerNamePanel;
    private JPanel invoiceTotalPanel;
    private JPanel invoiceItemsTableLabelPanel;
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
    private JButton saveFileBtn;
    private JButton loadFileBtn;
    private JButton createNewInvoiceBtn;
    private JButton deleteInvoiceBtn;
    private JButton saveChangesBtn;
    private JButton cancelChangesBtn;
    private JOptionPane errorMsgs;
    private String[] invoicesTableColumns = {"No.","Date","Customer","Total"};
    private String[] invoiceItemsTableColumns = {"No.","Item Name","Item Price","Count","Item Total"};
    private String[][] temp1 = {{"1","2","3","4"},{"5","6","7","8"}};
    private String[][] temp2 = {{"1","2","3","4","9"},{"5","6","7","8","10"}};
    private String filePath;
    private BusinessLogicController businessLogicController = new BusinessLogicController();
    private String[][] invoicesData;
    public UiComponents(){
        super("Sales Invoice Generator App");
        setSize(1000,600);
        setLocation(130,40);
        setLayout(new GridLayout(0,2));
        leftSidePanel = new JPanel();
        leftSidePanel.setLayout(new BoxLayout(leftSidePanel,BoxLayout.Y_AXIS));
        rightSidePanel = new JPanel();
        rightSidePanel.setLayout(new BoxLayout(rightSidePanel,BoxLayout.Y_AXIS));
        rightSidePanel.setBackground(Color.BLUE);
        leftSideTopPanel = new JPanel(new GridLayout(1,2));
        loadSaveFileContainerPanel = new JPanel();
        loadSaveFileContainerPanel.setLayout(new BoxLayout(loadSaveFileContainerPanel,BoxLayout.Y_AXIS));
        leftSideBottomPanel = new JPanel();
        rightSideBottomPanel = new JPanel();
        rightSideTopPanel = new JPanel();
        rightSideTopPanel.setLayout(new BoxLayout(rightSideTopPanel,BoxLayout.Y_AXIS));
        invoiceNumPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        customerNamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        invoiceTotalPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
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
        loadFileBtn = new JButton("Load File");
        saveFileBtn = new JButton("Save File");
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

        loadFileBtn.setActionCommand("LF");
        saveFileBtn.setActionCommand("SF");
        saveChangesBtn.setActionCommand("SC");
        cancelChangesBtn.setActionCommand("CC");
        createNewInvoiceBtn.setActionCommand("CNI");
        deleteInvoiceBtn.setActionCommand("DI");


        loadFileBtn.addActionListener(this);
        saveFileBtn.addActionListener(this);
        saveChangesBtn.addActionListener(this);
        cancelChangesBtn.addActionListener(this);
        createNewInvoiceBtn.addActionListener(this);
        deleteInvoiceBtn.addActionListener(this);

        invoicesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showInvoicesTableRowDetails();
            }
        });



        add(leftSidePanel);
        add(rightSidePanel);
        leftSidePanel.add(leftSideTopPanel);
        leftSideTopPanel.add(loadSaveFileContainerPanel);
        loadSaveFileContainerPanel.add(loadFileBtn);
        loadSaveFileContainerPanel.add(saveFileBtn);
        loadSaveFileContainerPanel.add(invoicesTableLabel);
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
        Vector invoiceData = invoicesTableModel.getDataVector().get(invoicesTable.getSelectedRow());
       invoiceNumValue.setText(invoiceData.get(0).toString());
       invoiceDateInput.setText(invoiceData.get(1).toString());
       customerNameInput.setText(invoiceData.get(2).toString());
       invoiceTotalValue.setText(invoiceData.get(3).toString());
    }

    public void initiallyLoadInvoicesData() {
        try {
            invoicesData = businessLogicController.initiallyLoadTableDataFromFile("InvoicesTable");
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
            invoicesData = businessLogicController.initiallyLoadTableDataFromFile("InvoiceItemsTable");
            invoiceItemsTableModel.getDataVector().removeAllElements();
            for (String[] row : invoicesData) {
                invoiceItemsTableModel.addRow(row);
            }
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void saveInvoiceItemsChanges() {
        try {
            businessLogicController.saveTableDataToFile(invoiceItemsTableModel);
        }catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileSelector = new JFileChooser();
        switch(e.getActionCommand()){
            case "LF":
                if(fileSelector.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                    filePath=fileSelector.getSelectedFile().getPath();
                    try {
                        invoicesData = businessLogicController.loadInvoicesFromFile(filePath);
                        invoicesTableModel.getDataVector().removeAllElements();
                        for(String[] row : invoicesData) {
                            invoicesTableModel.addRow(row);
                        }
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
                break;

            case "SF":
                if(fileSelector.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                    filePath=fileSelector.getSelectedFile().getPath();
                    try {
                        businessLogicController.saveInvoicesToFile(filePath,invoicesTableModel);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage(),"Error",JOptionPane.PLAIN_MESSAGE);
                    }
                }
                break;

            case "SC":
                saveInvoiceItemsChanges();
                break;

            case "CC":
                initiallyLoadInvoiceItemsData();
                break;

            case "CNI":
                invoicesTableModel.addRow(new String[]{"", "", "", ""});
                break;

            case "DI":
                if(invoicesTable.getSelectedRow()!=-1) {
                    invoicesTableModel.removeRow(invoicesTable.getSelectedRow());
                }
                break;

            default:

                break;
        }
    }
}
