import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPopupMenu;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.sql.SQLException;


public class myFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsername;
	private JPasswordField pwdPassword;
	protected String username;
	protected String password;
	private PassMan pm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myFrame frame = new myFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public myFrame() {
		setTitle("Login Screen");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		pm = new PassMan();

		JLabel lblTitle = new JLabel("Level Seven");
		lblTitle.setForeground(Color.WHITE);
		lblTitle.setBackground(Color.WHITE);
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);

		txtUsername = new JTextField();
		txtUsername.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				txtUsername.setText("");
			}
		});
		txtUsername.setText("Username");
		txtUsername.setColumns(10);

		pwdPassword = new JPasswordField("Password");
		pwdPassword.setPreferredSize(new Dimension(274,23));
		pwdPassword.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				pwdPassword.setText("");
			}
		});
		pwdPassword.setHorizontalAlignment(SwingConstants.LEFT);
		pwdPassword.setForeground(new Color(0, 0, 0));

		JButton btnLogin = new JButton("Login");
		final JLabel errorMessage = new JLabel("");
		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);


		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username = txtUsername.getText();
				password = String.valueOf(pwdPassword.getPassword()); 
				int pass = pm.authenticateLogin(username, password);
				if(pass == 0){
					dispose();
					new passManagerWindow(username);
				}
				else if(pass == 1){
					errorMessage.setText("Username/Password too long");
				}
				else{
					errorMessage.setText("Incorrect password");
				}
			}
		});
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				username = txtUsername.getText();
				password = String.valueOf(pwdPassword.getPassword());
				int pass = -1;
				try {
					pass = pm.addUser(username,password);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(pass == 0){
					errorMessage.setText("Account has been created");
				}
				else if(pass == 3){
					errorMessage.setText("Username has been taken");
				}
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 405, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(71)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
														.addComponent(errorMessage, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(pwdPassword)
														.addComponent(txtUsername, GroupLayout.DEFAULT_SIZE, 269, Short.MAX_VALUE)
														.addGroup(gl_contentPane.createSequentialGroup()
																.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(ComponentPlacement.UNRELATED)
																.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
																.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				);
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
						.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
						.addGap(46)
						.addComponent(txtUsername, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(18)
						.addComponent(pwdPassword, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(30)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnLogin)
								.addComponent(btnNewButton))
								.addGap(18)
								.addComponent(errorMessage)
								.addContainerGap(18, Short.MAX_VALUE))
				);
		contentPane.setLayout(gl_contentPane);
		contentPane.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{lblTitle, txtUsername, pwdPassword, btnLogin, btnNewButton}));
	}
}
