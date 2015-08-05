import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import net.miginfocom.swing.MigLayout;

import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

import java.awt.Color;


public class LoginForm extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfURL;
	private JTextField tfUsername;
	private JPasswordField tfPassword;
	private String username;
	private String password;
	private String url;
	private boolean enteredData;

	/**
	 * Create the dialog.
	 */
	public LoginForm() {
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(30, 144, 255));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		setModal(true);
		contentPanel.setLayout(new MigLayout("", "[][][][][grow]", "[][][][][][][][]"));
		{
			JLabel lblSite = new JLabel("Site");
			lblSite.setFont(new Font("Tahoma", Font.BOLD, 25));
			contentPanel.add(lblSite, "cell 0 0 5 1,alignx center,aligny center");
		}
		{
			JLabel lblNewLabel = new JLabel("Limit your url to 240 characters and username/password to 40");
			contentPanel.add(lblNewLabel, "cell 1 1 4 1");
		}
		{
			JLabel lbURL = new JLabel("URL");
			lbURL.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lbURL, "cell 1 3");
		}
		{
			tfURL = new JTextField();
			contentPanel.add(tfURL, "cell 4 3,growx");
			tfURL.setColumns(10);
		}
		{
			JLabel lbUsername = new JLabel("Username");
			lbUsername.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lbUsername, "cell 1 5");
		}
		{
			tfUsername = new JTextField();
			contentPanel.add(tfUsername, "cell 4 5,growx");
			tfUsername.setColumns(10);
		}
		{
			JLabel lbPassword = new JLabel("Password");
			lbPassword.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lbPassword, "cell 1 7");
		}
		{
			tfPassword = new JPasswordField();
			contentPanel.add(tfPassword, "cell 4 7,growx,aligny baseline");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						url = tfURL.getText();
						username = tfUsername.getText();
						password = String.valueOf(tfPassword.getPassword()); 
						enteredData = true;
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						enteredData = false;
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setVisible(true);
	}
	
	public String getUser(){
		return username;
	}
	public String getPass(){
		return password;
	}
	public String getURL(){
		return url;
	}
	public boolean enteredData(){
		return enteredData;
	}

}
