import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JSplitPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class myFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblHelloWorld = new JLabel("Hello World");
		lblHelloWorld.setFont(new Font("Tahoma", Font.PLAIN, 45));
		lblHelloWorld.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblHelloWorld);
		
		JSplitPane splitPane_2 = new JSplitPane();
		contentPane.add(splitPane_2);
		
		JLabel lblUsername = new JLabel("Username");
		splitPane_2.setLeftComponent(lblUsername);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		splitPane_2.setRightComponent(textField);
		textField.setColumns(10);
		
		JSplitPane splitPane_1 = new JSplitPane();
		contentPane.add(splitPane_1);
		
		JLabel lblPassword_1 = new JLabel("Password");
		splitPane_1.setLeftComponent(lblPassword_1);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		splitPane_1.setRightComponent(passwordField);
		
		JSplitPane splitPane = new JSplitPane();
		contentPane.add(splitPane);
		
		JButton btnLogin_1 = new JButton("Reset");
		btnLogin_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		btnLogin_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		splitPane.setLeftComponent(btnLogin_1);
		
		JButton btnLogin_2 = new JButton("Login");
		btnLogin_2.addMouseListener(new MouseAdapter() {
			private String username;
			private String password;

			@Override
			public void mouseClicked(MouseEvent e) {
				this.username = textField.getText();
				this.password = passwordField.getText();
				System.out.println("You logged in!");
			}
		});
		splitPane.setRightComponent(btnLogin_2);
	}

}
