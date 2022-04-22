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
    public JButton okBtn;
    public JButton exitBtn;
    private int invoiceItemsNumber;

    public InvoiceItemsNumDialog(){
        setSize(500,100);
        setLocation(130,40);
        setLayout(new FlowLayout());
        numberOfInvoiceItemsLabel = new JLabel("Number Of Invoice Items:");
        okBtn = new JButton("Ok");
        exitBtn = new JButton("Exit");
        NumberFormatter numberOfItemsFormatter = new NumberFormatter(NumberFormat.getIntegerInstance());
        numberOfItemsFormatter.setMinimum(1);
        numberOfInvoiceItemsInput = new JFormattedTextField(numberOfItemsFormatter);
        numberOfInvoiceItemsInput.setColumns(10);

        add(numberOfInvoiceItemsLabel);
        add(numberOfInvoiceItemsInput);
        add(okBtn);
        add(exitBtn);
        setVisible(false);
    }

    public void performOkBtnActions()
    {
        invoiceItemsNumber = Integer.parseInt(numberOfInvoiceItemsInput.getText());
        setVisible(false);
    }

    public void performExitBtnActions() {
         setVisible(false);
    }

    public void setInvoiceItemsNumber(int invoiceItemsNumber){
         this.invoiceItemsNumber = invoiceItemsNumber;
    }

    public void clearInvoiceItemsField(){
        numberOfInvoiceItemsInput.setText("");
    }

    public int getInvoiceItemsNumber(){
       return invoiceItemsNumber;
    }



}
