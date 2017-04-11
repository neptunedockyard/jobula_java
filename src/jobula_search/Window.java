package jobula_search;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JCheckBox;
import javax.swing.SpinnerNumberModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.GridLayout;

public class Window {

	private JFrame frmJobula;
	private JTextField text_search;
	private JTextField text_city;
	private JTable job_table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frmJobula.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmJobula = new JFrame();
		frmJobula.setBackground(SystemColor.control);
		frmJobula.getContentPane().setBackground(SystemColor.control);
		frmJobula.setTitle("Jobula - Job search tool v2.6.1");
		frmJobula.setBounds(100, 100, 850, 400);
		frmJobula.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmJobula.setMinimumSize(new Dimension(850, 400));
		frmJobula.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JToolBar toolBar = new JToolBar();
		toolBar.setBackground(SystemColor.control);
		toolBar.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		toolBar.setFloatable(false);
		frmJobula.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(SystemColor.control);
		menuBar.setBorderPainted(false);
		toolBar.add(menuBar);
		
		JMenu mnFile = new JMenu("File");
		mnFile.setBackground(SystemColor.control);
		menuBar.add(mnFile);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setBackground(SystemColor.control);
		menuBar.add(mnEdit);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setBackground(SystemColor.control);
		menuBar.add(mnHelp);
		
		JPanel panel = new JPanel();
		frmJobula.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel.add(tabbedPane);
		tabbedPane.setBackground(SystemColor.control);
		tabbedPane.setBorder(new MatteBorder(1, 10, 10, 10, (Color) new Color(240, 240, 240)));
		
		JPanel panel_settings = new JPanel();
		panel_settings.setBackground(SystemColor.window);
		tabbedPane.addTab("Search Settings", null, panel_settings, null);
		tabbedPane.setEnabledAt(0, true);
		panel_settings.setLayout(null);
		
		JLabel lblSearch = new JLabel("Search");
		lblSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSearch.setBounds(10, 11, 46, 14);
		panel_settings.add(lblSearch);
		
		JLabel lblJobSite = new JLabel("Source");
		lblJobSite.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblJobSite.setBounds(140, 12, 46, 14);
		panel_settings.add(lblJobSite);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCity.setBounds(270, 12, 46, 14);
		panel_settings.add(lblCity);
		
		text_search = new JTextField();
		text_search.setBounds(10, 36, 120, 20);
		panel_settings.add(text_search);
		text_search.setColumns(10);
		
		JComboBox combo_source = new JComboBox();
		combo_source.setToolTipText("which job search source website?");
		combo_source.setModel(new DefaultComboBoxModel(new String[] {"Indeed"}));
		combo_source.setBounds(140, 36, 120, 20);
		panel_settings.add(combo_source);
		
		text_city = new JTextField();
		text_city.setBounds(270, 36, 120, 20);
		panel_settings.add(text_city);
		text_city.setColumns(10);
		
		JComboBox combo_country = new JComboBox();
		combo_country.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan", "Albania", "Algeria", "Andorra", "Angola", "Antigua & Deps", "Argentina", "Armenia", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bhutan", "Bolivia", "Bosnia Herzegovina", "Botswana", "Brazil", "Brunei", "Bulgaria", "Burkina", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Central African Rep", "Chad", "Chile", "China", "Colombia", "Comoros", "Congo", "Congo {Democratic Rep}", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Fiji", "Finland", "France", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Greece", "Grenada", "Guatemala", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Honduras", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland {Republic}", "Israel", "Italy", "Ivory Coast", "Jamaica", "Japan", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea North", "Korea South", "Kosovo", "Kuwait", "Kyrgyzstan", "Laos", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Mauritania", "Mauritius", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montenegro", "Morocco", "Mozambique", "Myanmar, {Burma}", "Namibia", "Nauru", "Nepal", "Netherlands", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Norway", "Oman", "Pakistan", "Palau", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Poland", "Portugal", "Qatar", "Romania", "Russian Federation", "Rwanda", "St Kitts & Nevis", "St Lucia", "Saint Vincent & the Grenadines", "Samoa", "San Marino", "Sao Tome & Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Swaziland", "Sweden", "Switzerland", "Syria", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tonga", "Trinidad & Tobago", "Tunisia", "Turkey", "Turkmenistan", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "Uruguay", "Uzbekistan", "Vanuatu", "Vatican City", "Venezuela", "Vietnam", "Yemen", "Zambia", "Zimbabwe"}));
		combo_country.setSelectedIndex(30);
		combo_country.setBounds(400, 36, 120, 20);
		panel_settings.add(combo_country);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblCountry.setBounds(400, 12, 46, 14);
		panel_settings.add(lblCountry);
		
		JLabel lblSearchBy = new JLabel("Search by");
		lblSearchBy.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSearchBy.setBounds(10, 67, 65, 14);
		panel_settings.add(lblSearchBy);
		
		JLabel lblJobType = new JLabel("Job type");
		lblJobType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblJobType.setBounds(140, 67, 65, 14);
		panel_settings.add(lblJobType);
		
		JLabel lblAdAgedays = new JLabel("Ad age (days)");
		lblAdAgedays.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdAgedays.setBounds(270, 67, 82, 14);
		panel_settings.add(lblAdAgedays);
		
		JLabel lblRadiuskm = new JLabel("Radius (km)");
		lblRadiuskm.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRadiuskm.setBounds(400, 67, 65, 14);
		panel_settings.add(lblRadiuskm);
		
		JComboBox combo_type = new JComboBox();
		combo_type.setToolTipText("jobsite for job boards, employer for direct employers");
		combo_type.setModel(new DefaultComboBoxModel(new String[] {"employer", "jobsite"}));
		combo_type.setSelectedIndex(0);
		combo_type.setBounds(10, 92, 120, 20);
		panel_settings.add(combo_type);
		
		JComboBox combo_jobtype = new JComboBox();
		combo_jobtype.setModel(new DefaultComboBoxModel(new String[] {"fulltime", "parttime", "contract", "internship", "temporary"}));
		combo_jobtype.setSelectedIndex(0);
		combo_jobtype.setBounds(140, 92, 120, 20);
		panel_settings.add(combo_jobtype);
		
		JSpinner spinner_age = new JSpinner();
		spinner_age.setToolTipText("how long ago was the ad posted?");
		spinner_age.setModel(new SpinnerNumberModel(15, 0, 30, 1));
		spinner_age.setBounds(270, 92, 120, 20);
		panel_settings.add(spinner_age);
		
		JSpinner spinner_radius = new JSpinner();
		spinner_radius.setToolTipText("distance from your chosen location");
		spinner_radius.setModel(new SpinnerNumberModel(10, 10, 250, 10));
		spinner_radius.setBounds(400, 92, 120, 20);
		panel_settings.add(spinner_radius);
		
		JLabel lblAdLimit = new JLabel("Ad limit");
		lblAdLimit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdLimit.setBounds(10, 123, 46, 14);
		panel_settings.add(lblAdLimit);
		
		JSpinner spinner_limit = new JSpinner();
		spinner_limit.setToolTipText("the number of ads shown");
		spinner_limit.setModel(new SpinnerNumberModel(100, 1, 500, 1));
		spinner_limit.setBounds(10, 148, 120, 20);
		panel_settings.add(spinner_limit);
		
		JCheckBox chckbxCheckForEmail = new JCheckBox("Check for email");
		chckbxCheckForEmail.setToolTipText("scan postings for email addresses for direct contact");
		chckbxCheckForEmail.setBounds(10, 175, 120, 23);
		panel_settings.add(chckbxCheckForEmail);
		
		JCheckBox chckbxCheckForPhone = new JCheckBox("Check for phone number");
		chckbxCheckForPhone.setToolTipText("scan postings for phone numbers for direct contact, experimental!");
		chckbxCheckForPhone.setBounds(10, 201, 176, 23);
		panel_settings.add(chckbxCheckForPhone);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.out.println("test");
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(10, 269, 89, 23);
		panel_settings.add(btnSearch);
		
		JLabel lblResults = new JLabel("Results: ");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResults.setBounds(109, 273, 46, 14);
		panel_settings.add(lblResults);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBounds(244, 271, 276, 23);
		panel_settings.add(progressBar);
		panel_settings.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{text_search, combo_source, text_city, combo_country, combo_type, combo_jobtype, spinner_age, spinner_radius, spinner_limit, chckbxCheckForEmail, chckbxCheckForPhone, btnSearch}));
		
		JPanel panel_search = new JPanel();
		panel_search.setBorder(null);
		tabbedPane.addTab("Job List", null, panel_search, null);
		panel_search.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		panel_search.add(scrollPane);
		
		job_table = new JTable();
		job_table.setFillsViewportHeight(true);
		scrollPane.setViewportView(job_table);
		job_table.setForeground(new Color(0, 0, 0));
		job_table.setCellSelectionEnabled(true);
		job_table.setBackground(SystemColor.window);
		job_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		job_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Title", "Company", "City", "Ad age", "Summary"
			}
		));
		job_table.getColumnModel().getColumn(3).setPreferredWidth(80);
		job_table.getColumnModel().getColumn(3).setMaxWidth(80);
		job_table.getColumnModel().getColumn(4).setPreferredWidth(150);
		job_table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		job_table.setFont(new Font("Tahoma", Font.PLAIN, 12));
	}
}
