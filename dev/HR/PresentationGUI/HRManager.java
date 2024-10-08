package HR.PresentationGUI;
import HR.Bussiness.ManagerController;
import HR.PresentationGUI.HRManagaerWindows.AddNewSuper;
import HR.PresentationGUI.HRManagaerWindows.DriversSchedule;
import HR.PresentationGUI.HRManagaerWindows.UpdateEmployee;
import HR.PresentationGUI.HRManagaerWindows.WorkOnABranch;
import Shipment.Service.HRService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HRManager extends JFrame implements ActionListener{
    private JPanel HRManagerWin;
    private JComboBox<String> comboBox1;
    private JButton startButton;
    private JButton exitButton;
    //take instance for manager controller
    private ManagerController managerController;
    private HRService hrService;

    public HRManager() {
        this.setContentPane(HRManagerWin);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(300,200));
        this.pack();
        this.setVisible(true);
        this.setTitle("HR Manager");
        startButton.addActionListener(this);
        exitButton.addActionListener(this);
        comboBox1.addActionListener(this);
        this.managerController = ManagerController.getInstance();
        this.hrService = HRService.getInstance();
        //set default color of messabe boxes
        UIManager UI = new UIManager();
        UI.put("OptionPane.background", Color.BLACK);
        UI.put("Panel.background", Color.BLACK);
        UI.put("OptionPane.messageForeground", Color.WHITE);
        UI.put("Button.foreground", Color.BLACK);
        UI.put("Label.foreground", Color.WHITE);
        UI.put("Label.foreground", Color.WHITE);

    }
    private void createUIComponents() {
        comboBox1 = new JComboBox<String>();
        comboBox1.addItem("work on a branch (snif)");
        comboBox1.addItem("add new branch");
        comboBox1.addItem("send weekly shifts to history for all branches");
        comboBox1.addItem("update employee");
        comboBox1.addItem("Show Drivers Schedule");
        comboBox1.addItem("Show all shipments");
        comboBox1.addItem("pay salaries");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton)
        {
            if(comboBox1.getSelectedItem().equals("work on a branch (snif)"))
            {
                String[] allBranches = managerController.getAllSuperNames();
                JList<String> list = new JList<>(allBranches);


                int result = JOptionPane.showConfirmDialog(null, new JScrollPane(list), "Select a branch to work on:", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // save the selected option
                    String name = list.getSelectedValue();
                    new WorkOnABranch(this,name);
                    this.setVisible(false);
                }
                //JTextField textField = new JTextField();
                //int result = JOptionPane.showConfirmDialog(null, textField, "Enter branch name:", JOptionPane.OK_CANCEL_OPTION);
//                if (result == JOptionPane.OK_OPTION) {
//                    String name = textField.getText();
//                    boolean check =managerController.CheckBranchExist(name);
//                    //if the branch exists
//                    if(check)
//                    {
//                        new WorkOnABranch(this,name);
//                        this.setVisible(false);
//                    }
//                    else {
//                        JOptionPane.showMessageDialog(null,"no such branch");
//                    }
//                }
            }
            else if(comboBox1.getSelectedItem().equals("add new branch"))
            {
                new AddNewSuper(this);
                this.setVisible(false);
            }
            else if(comboBox1.getSelectedItem().equals("send weekly shifts to history for all branches"))
            {
                //check if he created the weekly for everyone
                if(!managerController.CheckAllHaveWeekly()){
                    JOptionPane.showMessageDialog(null, "not all the branches has a weekly plan create them first and then you can send", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to send weekly shifts to history for all branches?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // user clicked "Yes"
                    //send all to history
                    managerController.SendConstraintsToHistory();
                    JOptionPane.showMessageDialog(null, "Sending Done!", "Send To History", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    // user clicked "No" or closed the dialog
                    JOptionPane.showMessageDialog(null, "Sending Canceled!", "Send To History", JOptionPane.INFORMATION_MESSAGE);
                }

            }
            else if(comboBox1.getSelectedItem().equals("update employee"))
            {
                new UpdateEmployee(this);
                this.setVisible(false);
            }
            else if(comboBox1.getSelectedItem().equals("Show Drivers Schedule"))
            {
                new DriversSchedule(this);
                this.setVisible(false);
            }
            else if(comboBox1.getSelectedItem().equals("Show all shipments"))
            {
                //todo: add the shipments print aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                this.hrService.openMainShipmentInfo();
                
            }
            else if(comboBox1.getSelectedItem().equals("pay salaries"))
            {
                int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to pay salaries?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    // user clicked "Yes"
                    managerController.loadAllWorkersFrom();
                    managerController.Payment();
                    JOptionPane.showMessageDialog(null, "Payment Done!", "Payment", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    // user clicked "No" or closed the dialog
                    JOptionPane.showMessageDialog(null, "Payment Canceled!", "Payment", JOptionPane.INFORMATION_MESSAGE);
                }
            }

        }
        else if(e.getSource()==exitButton)
        {
            //do what we need in the database when closed
            managerController.closeDB();
            System.exit(0);
        }
    }
}
