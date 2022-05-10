package UI;

import javax.swing.*;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class InvoiceItemsNumDialog extends JFrame {

    private JLabel numberOfInvoiceItemsLabel;
    private JFormattedTextField numberOfInvoiceItemsInput;
    private JLabel invoiceNumberLabel;
    private JTextField invoiceNumberInput;
    public JButton okBtn;
    public JButton exitBtn;
    private int invoiceItemsNumber;
    private String invoiceNumber;
    private JPanel outerContainer;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPanel bottomPanel;

    public InvoiceItemsNumDialog(){
        setSize(500,150);
        setLocation(130,40);
        outerContainer = new JPanel();
        outerContainer.setLayout(new BoxLayout(outerContainer,BoxLayout.Y_AXIS));
        topPanel = new JPanel(new FlowLayout());
        centerPanel = new JPanel(new FlowLayout());
        bottomPanel = new JPanel(new FlowLayout());
        numberOfInvoiceItemsLabel = new JLabel("Number Of Invoice Items:");
        invoiceNumberLabel  = new JLabel("Invoice Number:");
        invoiceNumberInput = new JTextField();
        invoiceNumberInput.setColumns(10);

        okBtn = new JButton("Ok");
        exitBtn = new JButton("Exit");
        NumberFormatter numberOfItemsFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
        numberOfItemsFormatter.setMinimum(1);
        numberOfInvoiceItemsInput = new JFormattedTextField(numberOfItemsFormatter);
        numberOfInvoiceItemsInput.setColumns(10);

        topPanel.add(invoiceNumberLabel);
        topPanel.add(invoiceNumberInput);
        centerPanel.add(numberOfInvoiceItemsLabel);
        centerPanel.add(numberOfInvoiceItemsInput);
        bottomPanel.add(okBtn);
        bottomPanel.add(exitBtn);

        outerContainer.add(topPanel);
        outerContainer.add(centerPanel);
        outerContainer.add(bottomPanel);

        add(outerContainer);

        setVisible(false);
    }

    public void performOkBtnActions()
    {
        invoiceItemsNumber = Integer.parseInt(numberOfInvoiceItemsInput.getText());
        invoiceNumber = invoiceNumberInput.getText();
        setVisible(false);
    }

    public void performExitBtnActions() {
         setVisible(false);
    }

    public void setInvoiceItemsNumber(int invoiceItemsNumber){
         this.invoiceItemsNumber = invoiceItemsNumber;
    }

    public void clearInvoiceItemsFields(){
        numberOfInvoiceItemsInput.setText("");
        invoiceNumberInput.setText("");
    }

    public int getInvoiceItemsNumber(){
       return invoiceItemsNumber;
    }

    public void setInvoiceNumber(String invoiceNumber){
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceNumber(){
        return invoiceNumber;
    }

}
