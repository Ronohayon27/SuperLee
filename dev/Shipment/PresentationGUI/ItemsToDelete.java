package Shipment.PresentationGUI;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import Shipment.Bussiness.shipmentManagement;
import Shipment.Service.GUIService;

public class ItemsToDelete extends JFrame implements ActionListener {
    private JPanel panel;
    private JComboBox<String> comboBox;
    private JLabel label, fieldLabel;
    private JTextField field;
    private JButton doButton;
    private shipmentManagement sManagement;
    private ExecuteShipment save;
    private GUIService guiService;
    private String siteName;
    private List<String> itemNames;
    private List<Integer> itemQuantities;


    public ItemsToDelete(ExecuteShipment executeShipment, String siteName){
        this.siteName = siteName;
        save = executeShipment;
        createUIComponents();
        setContentPane(panel);
        pack();
        setVisible(true);

        // finishing stuff
        comboBox.addActionListener(this);
        doButton.addActionListener(this);
        field.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {checkBox();}

            @Override
            public void removeUpdate(DocumentEvent e) {checkBox();}

            @Override
            public void changedUpdate(DocumentEvent e) {checkBox();}
        });
    }
    private void checkBox(){
        setEnabled(!field.getText().isEmpty());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == doButton){
            int index = comboBox.getSelectedIndex();
            if (field.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "the Quantity field is empty", "Invalid input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (sManagement.deleteItemFromShipment(itemNames.get(index),Integer.parseInt(field.getText()),siteName)){
                if (itemQuantities.get(index) > Integer.parseInt(field.getText())){
                    String temp = String.valueOf(itemQuantities.get(index) - Integer.parseInt(field.getText()));
                    String msg = "The item Quantity was reduced to " + temp ;
                    JOptionPane.showMessageDialog(null, msg, "Remove", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null, "The item was deleted", "Remove", JOptionPane.INFORMATION_MESSAGE);
                }
                dispose();
                save.setVisible(true);
            };
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.setTitle("ItemsToDelete");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 250));

        // setting the main panel.
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(Color.BLACK);

        sManagement = shipmentManagement.getInstance();
        guiService = GUIService.getInstance();
        itemNames = new ArrayList<>();
        itemQuantities = new ArrayList<>();


        // setting the combo box
        JPanel comboPanel = new JPanel();
        comboPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        comboPanel.setBackground(Color.BLACK);
        comboPanel.setPreferredSize(new Dimension(270,25));
        splittingList();
        String[] options = itemNames.toArray(new String[itemNames.size()]);
        comboBox = new JComboBox<>(options);
        comboPanel.add(comboBox);
        panel.add(comboPanel);
        comboBox.setPreferredSize(new Dimension(270, 25)); // Set a narrower width for the combo box
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("this item quantity is " + itemQuantities.get(comboBox.getSelectedIndex()));
                label.setVisible(true);
                fieldLabel.setVisible(true);
                field.setVisible(true);
            }
        });



        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        labelPanel.setBackground(Color.BLACK);
        labelPanel.setPreferredSize(new Dimension(300, 20));
        label = new JLabel();
        label.setForeground(Color.white);
        label.setPreferredSize(new Dimension(300, 20));
        label.setVisible(false);
        labelPanel.add(label);
        panel.add(labelPanel);

        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        fieldPanel.setBackground(Color.BLACK);
        fieldLabel = new JLabel("Quantity:");
        field = new JTextField();
        fieldLabel.setVisible(false);
        fieldLabel.setForeground(Color.white);
        fieldLabel.setInputVerifier(new digitVerifier(fieldLabel));
        field.setVisible(false);
        field.setPreferredSize(new Dimension(280, field.getPreferredSize().height));
        fieldPanel.add(fieldLabel);
        fieldPanel.add(field);
        panel.add(fieldPanel);


        // setting the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1,1));
        buttonPanel.setBackground(Color.BLACK);
        doButton = new JButton("Delete");
        buttonPanel.add(doButton);
        panel.add(buttonPanel);
    }

    private void splittingList(){
        List<String> temp = guiService.getItemsFromDoc(siteName);
        for(int i=0; i< temp.size(); i++){
            if (i % 2 == 0){
                itemNames.add(temp.get(i));
            }
            else{
                itemQuantities.add(Integer.parseInt(temp.get(i)));
            }
        }
    }

    class digitVerifier extends InputVerifier {
        private JLabel fieldLabel;

        public digitVerifier(JLabel fieldLabel) {
            this.fieldLabel = fieldLabel;
        }

        @Override
        public boolean verify(JComponent input) {
            String w = ((JTextField) input).getText();

            if (!w.matches("\\d+")) {
                // Change the color to red
                fieldLabel.setForeground(Color.RED);
                return false;
            }

            // Change the color to white
            fieldLabel.setForeground(Color.WHITE);
            return true;
        }
    }
}
