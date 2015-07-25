package org.eclipse.wb.swing;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class passManagerWindow extends JFrame {

	private JPanel contentPane;
	private DefaultTableModel defaultModel;
	private JTable table;
	private JPopupMenu popup;
	private Object[][] data;
	private JTextField textField;
	
	/**
	 * Create the frame.
	 */
	public passManagerWindow() {
		setVisible(true);
		setTitle("Password Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String[] columns = {"URL", "Username", "Password"};
		data = new Object[][]{
				{"http://www.donothingfor2minutes.com","Heach1963","aifiRa0ed0a"},
				{"http://weavesilk.com","Redet1983","Giechaiph3ee"}
		};
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				defaultModel.addRow(new Object[]{"","",""});
			}
		});
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNum = table.getSelectedRow();
				defaultModel.removeRow(rowNum);
			}
		});
		
		textField = new JTextField();
		textField.setColumns(10);
		
		final JLabel errorMessage = new JLabel("");
		
		JButton btnSearch_1 = new JButton("Search");
		btnSearch_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(defaultModel);
				RowFilter<DefaultTableModel, Object> firstFilter = null;
				RowFilter<DefaultTableModel, Object> secondFilter = null;
				RowFilter<DefaultTableModel, Object> thirdFilter = null;
				List<RowFilter<DefaultTableModel,Object>> filters = new ArrayList<RowFilter<DefaultTableModel,Object>>();
				RowFilter<DefaultTableModel, Object> compoundRowFilter = null;
				
				sorter.setRowFilter(compoundRowFilter);
				table.setRowSorter(sorter);
				String searches = textField.getText();
				
				try {
				    firstFilter = RowFilter.regexFilter(searches, 0);
				    secondFilter = RowFilter.regexFilter(searches, 1);
				    thirdFilter = RowFilter.regexFilter(searches, 2);
				    filters.add(firstFilter);
				    filters.add(secondFilter);
				    filters.add(thirdFilter);
				    compoundRowFilter = RowFilter.andFilter(filters); // you may also choose the OR filter
				} catch (java.util.regex.PatternSyntaxException pse) {
					errorMessage.setText(String.format("Unable to find %s", searches));
				}
				sorter.setRowFilter(compoundRowFilter);
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		

		errorMessage.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(errorMessage, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnSearch_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(btnDelete_1, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
						.addComponent(textField, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
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
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(14)
							.addComponent(btnSearch_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(26)
							.addComponent(errorMessage, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 265, Short.MAX_VALUE)
							.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(15)
							.addComponent(btnDelete_1, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
							.addGap(20)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		
		table = new JTable();
		defaultModel = new DefaultTableModel((
				new Object[][] {
						{"http://www.donothingfor2minutes.com", "Heach1963", "aifiRa0ed0a"},
						{"http://weavesilk.com", "Redet1983", "Giechaiph3ee"},
						{"http://thequietplaceproject.com/thethoughtsroom/", "Appose", "OhmyGOD"},
					}),
					new String[] {
						"URL", "Username", "Password"
					});
		table.setAutoCreateRowSorter(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"http://www.donothingfor2minutes.com", "Heach1963", "aifiRa0ed0a"},
				{"http://weavesilk.com", "Redet1983", "Giechaiph3ee"},
				{"http://thequietplaceproject.com/thethoughtsroom/", "Appose", "OhmyGOD"},
			},
			new String[] {
				"URL", "Username", "Password"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(270);
		scrollPane.setViewportView(table);
		contentPane.setLayout(gl_contentPane);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
