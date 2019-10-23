// 	A Barnard 28430093
// 	Practical Assignment 2
// 	ITRW 222

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class UserInterface extends JFrame
{
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	GridLayout gLayout = new GridLayout(10,2);
	JLabel emptyLabel = new JLabel("");
	JLabel lblClientName = new JLabel("Name: ");
	JTextField txtName = new JTextField();
	JLabel lblAL1 = new JLabel("Address Line 1:");
	JTextField txtAL1 = new JTextField();
	JLabel lblAL2 = new JLabel("Address Line 2:");
	JTextField txtAL2 = new JTextField();
	JLabel lblPostalCode = new JLabel("Postal Code:");
	JTextField txtPostalCode = new JTextField();
	JLabel lblServiceProvider = new JLabel("Service Provider: ");
	JTextField txtServiceProvider = new JTextField();
	JLabel lblContactNo = new JLabel("Contact Number: ");
	JTextField txtCellNo = new JTextField();
	JButton btnSave = new JButton("Save");
	
	ListenForButton lForButton = new ListenForButton();
	btnSave.addActionListener(lForButton);
	
	//Contract users
	JPanel panelContract = new JPanel();
	JLabel lblStartDate = new JLabel("Contract Start Date:");
	JTextField txtStartDate = new JTextField();
	JLabel lblContractDesc = new JLabel("Contract Description: ");
	JTextField txtContractDesc = new JTextField();
	JLabel lblPhoneDesc = new JLabel("Phone Description: ");
	JTextField txtPhoneDesc = new JTextField();
	JLabel lblFreeMins = new JLabel("Free minutes:");
	JTextField txtFreeMins = new JTextField();
	JLabel lblFreeSMS = new JLabel("Free SMS: ");
	JTextField txtFreeSMS = new JTextField();
	GridLayout contractLayout = new GridLayout(6,2);
	
	//Prepaid users
	JPanel panelPrepaid = new JPanel();
	JLabel lblDataBal = new JLabel("Data Balance: ");
	JTextField txtDataBal = new JTextField();
	JLabel lblMinBal = new JLabel("Minutes Balance: ");
	JTextField txtMinBal = new JTextField();
	JLabel lblAmountBal = new JLabel("Amount Balance: ");
	JTextField txtAmountBal = new JTextField();
	GridLayout prepaidLayout = new GridLayout(4,2);
	
	public UserInterface()
	{
		frame = this;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Customer Form");
		
		panel.setLayout(gLayout);
		panel.add(lblClientName);
		panel.add(txtName);
		panel.add(lblAL1);
		panel.add(txtAL1);
		panel.add(lblAL2);
		panel.add(txtAL2);
		panel.add(lblPostalCode);
		panel.add(txtPostalCode);
		panel.add(lblServiceProvider);
		panel.add(txtServiceProvider);
		panel.add(lblContactNo);
		panel.add(txtCellNo);
		panel.add(emptyLabel);
		panel.add(btnSave);
		frame.setSize(600,900);
		frame.add(panel);
		frame.setVisible(true);
		
		//Contract users' panel
		panelContract.setLayout(contractLayout); 
		panelContract.add(lblStartDate);
		panelContract.add(txtStartDate);
		panelContract.add(lblContractDesc);
		panelContract.add(txtContractDesc);
		panelContract.add(lblPhoneDesc);
		panelContract.add(txtPhoneDesc);
		panelContract.add(lblFreeMins);
		panelContract.add(txtFreeMins);
		panelContract.add(lblFreeSMS);
		panelContract.add(txtFreeSMS);
		
		//Prepaid users' panel
		panelPrepaid.setLayout(prepaidLayout);
		panelPrepaid.add(lblDataBal);
		panelPrepaid.add(txtDataBal);
		panelPrepaid.add(lblMinBal);
		panelPrepaid.add(txtMinBal);
		panelPrepaid.add(lblAmountBal);
		panelPrepaid.add(txtAmountBal);
		
		
		
	}
	
	public static void main(String [] args)
	{
		new UserInterface();	
	}
	
	public class ListenForButton implements ActionListener()
	{
		public void actionPerformed(ActionEvent ev)
		{
			if (ev.getSource() == btnSave)
			{
				String name = txtName.getText();
				String AL1 = txtAL1.getText();
				String AL2 = txtAL2.getText();
				String PC = txtPostalCode.getText();
				String CN = txtCellNo.getText();
				String SP = txtServiceProvider.getText();
				JOptionPane.showMessageDialog("Saved");
			}
		}
	}

}
















