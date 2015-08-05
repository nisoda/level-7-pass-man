import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.RowFilter;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.JTable;
import javax.swing.SpringLayout;

import java.awt.GridBagLayout;

import javax.swing.JButton;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuBar;

import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;

import java.awt.Panel;

public class passManagerWindow extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel defaultModel;
	private JTable table;
	private JPopupMenu popup;
	private Object[][] data;
	private JTextField searchField;
	private JFrame frame;
	private PassMan pm;
	private String user;
	private String password;
	private String url;
	/**
	 * Create the frame.
	 */
	public passManagerWindow(String username) {
		
		setVisible(true);
		setTitle("Password Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[] columns = {"Website", "Username", "Password"};
		//Get data
		final PassMan pm = new PassMan();
		ResultSet rs = PassMan.viewAllStored(username);
		final JLabel message = new JLabel("");
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Not connected to DB, maybe create a window for input?
				message.setText("");
				LoginForm lf = new LoginForm();
				int success = -1;
				user = lf.getUser();
				password = lf.getPass();
				url = lf.getURL();
				
				if(lf.enteredData()){
					try {
						success = pm.addEntry(user, password, url);
					} catch (SQLException e1) {
						message.setText("Error in your entry, please contact your administrator.");
						e1.printStackTrace();
					}
					if(success == 0){
						message.setText("Successfully added!");
						defaultModel.addRow(new Object[]{url,user,password});
					}
					else if(success == 1){
						message.setText("Please follow instructions.");
					}
					else if(success == 2){
						message.setText("An empty field was detected");
					}
				}
			}
		});
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean success = false;
				int rowNum = -1;
				try{
					rowNum = table.getSelectedRow();
					url = (String) table.getValueAt(rowNum, 0);
					user = (String) table.getValueAt(rowNum,1);
					password = (String) table.getValueAt(rowNum,2);
				}catch(IndexOutOfBoundsException ob){
					message.setText("Nothing was selected");
				}
				try {
					success = pm.delEntry(user, password, url);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				if(success && rowNum >= 0){
					message.setText("Deleted row");
					defaultModel.removeRow(rowNum);
				}
			}
		});
		
		searchField = new JTextField();
		searchField.setColumns(10);
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String search = searchField.getText();
				final TableRowSorter<DefaultTableModel> sorter;
				sorter = new TableRowSorter<DefaultTableModel>(defaultModel);
				table.setRowSorter(sorter); 
				sorter.setRowFilter(RowFilter.regexFilter("(?i)" + search));
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		

		message.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(message, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnSearch_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnDelete_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(searchField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(searchField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addComponent(btnSearch_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(message, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 284, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(btnDelete_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(20)))
					.addContainerGap(75, Short.MAX_VALUE))
		);
		
		table = new JTable();
		defaultModel = new DefaultTableModel();
		defaultModel.setColumnIdentifiers(new String[] {"Website", "Username", "Password"});
		try {
			if(rs.first()) {
				do {
					String[] rowData = new String[3]; 
					for(int i = 0 ; i < 3 ; i++) {
						rowData[i] = rs.getString(i + 1);
					}
					defaultModel.addRow(rowData);
				} while (rs.next());				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		table.setAutoCreateRowSorter(true);
		table.setModel(defaultModel);
		table.getColumnModel().getColumn(0).setPreferredWidth(270);
		scrollPane.setViewportView(table);
		
		JPanel panel = new JPanel();
		scrollPane.setRowHeaderView(panel);
		contentPane.setLayout(gl_contentPane);
	}
}
