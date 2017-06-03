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
import javax.swing.SwingUtilities;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Point;
import java.awt.GridLayout;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Window {

	private JFrame frmJobula;
	private static JTextField text_search;
	private static JTextField text_city;
	private static JTable job_table;
	
	private static JTabbedPane tabbedPane;
	private static JComboBox<?> combo_source;
	private static JComboBox<?> combo_country;
	private static JComboBox<?> combo_type;
	private static JComboBox<?> combo_jobtype;
	private static JSpinner spinner_age;
	private static JSpinner spinner_radius;
	private static JSpinner spinner_limit;
	private static JCheckBox chckbxCheckForEmail;
	private static JCheckBox chckbxCheckForPhone;
	private static JLabel lblResults;
	private static JProgressBar progressBar;
	private static JButton btnSearch;
	
	
	private static Indeed indeed;

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
					SwingUtilities.getRootPane(btnSearch).setDefaultButton(btnSearch);
					indeed = new Indeed(text_search, combo_source, text_city, combo_country,
							combo_type, combo_jobtype, spinner_age, spinner_radius,
							spinner_limit, chckbxCheckForEmail, chckbxCheckForPhone, lblResults,
							progressBar, job_table);
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
		frmJobula.setBounds(100, 100, 850, 422);
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
		
		JMenuItem menuNew = new JMenuItem("New");
		menuNew.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//clear all the options typically entered by users
				text_search.setText("");
				text_city.setText("");
				chckbxCheckForEmail.setSelected(false);
				chckbxCheckForPhone.setSelected(false);
			}
		});
		menuNew.setIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		menuNew.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/File.gif")));
		mnFile.add(menuNew);
		
		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//TODO add file dialog and handler here
			}
		});
		menuOpen.setIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		menuOpen.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/Directory.gif")));
		mnFile.add(menuOpen);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem menuSave = new JMenuItem("Save Search As");
		menuSave.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				String saveFormat = null;
				saveFormat += text_search + "\r\n"
						+ text_city + "\r\n"
						+ combo_source.getSelectedItem() + "\r\n"
						+ combo_country.getSelectedItem() + "\r\n"
						+ combo_type.getSelectedItem() + "\r\n"
						+ combo_jobtype.getSelectedItem() + "\r\n"
						+ spinner_age.getValue().toString() + "\r\n"
						+ spinner_radius.getValue().toString() + "\r\n"
						+ spinner_limit.getValue().toString() + "\r\n"
						+ chckbxCheckForEmail.isSelected() + "\r\n"
						+ chckbxCheckForPhone.isSelected() + "\r\n";
				//TODO get file dialog and save to file in the users AppData folder
			}
		});
		menuSave.setIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		menuSave.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/FloppyDrive.gif")));
		mnFile.add(menuSave);
		
		JMenuItem menuExport = new JMenuItem("Export as CSV");
		menuExport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				//TODO add file dialog and handler here to export either what's been selected in the job table or the entire thing, not sure yet
			}
		});
		menuExport.setIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		menuExport.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/HardDrive.gif")));
		mnFile.add(menuExport);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.exit(0);
			}
		});
		menuExit.setIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/HomeFolder.gif")));
		menuExit.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/Error.gif")));
		mnFile.add(menuExit);
		
		JMenu mnEdit = new JMenu("Edit");
		mnEdit.setBackground(SystemColor.control);
		menuBar.add(mnEdit);
		
		JMenuItem menuCopy = new JMenuItem("Copy");
		menuCopy.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				int[] selectedRows = job_table.getSelectedRows();
				//TODO copy them to system clipboard
			}
		});
		menuCopy.setIcon(new ImageIcon(Window.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		menuCopy.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/javafx/scene/web/skin/Copy_16x16_JFX.png")));
		mnEdit.add(menuCopy);
		
		JMenuItem menuSelect = new JMenuItem("Select All");
		menuSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent arg0) {
				job_table.selectAll();
			}
		});
		menuSelect.setIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
		menuSelect.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
		mnEdit.add(menuSelect);
		
		JMenu mnHelp = new JMenu("Help");
		mnHelp.setBackground(SystemColor.control);
		menuBar.add(mnHelp);
		
		JMenuItem menuHow = new JMenuItem("How to use");
		menuHow.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JOptionPane.showMessageDialog(null, "This tool allows you to easily search popular job portals.\r\n"
						+ "For the best search results, the following can be used:\r\n"
						+ "specific strings: ex. \"civil engineer\"\r\n"
						+ "inclusive strings: ex. +technician\r\n"
						+ "exclusive strings: ex. -manager\r\n\r\n"
						+ "To expand an ad, double click it to open in a browser.", "How to use this tool", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuHow.setIcon(null);
		menuHow.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/Inform.gif")));
		mnHelp.add(menuHow);
		
		JMenuItem menuAbout = new JMenuItem("About...");
		menuAbout.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JOptionPane.showMessageDialog(null, "Jobula Search Tool\r\nVersion 2.6.1\r\nAuthor: neptuneDockyard", "About", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		menuAbout.setIcon(null);
		menuAbout.setSelectedIcon(new ImageIcon(Window.class.getResource("/com/sun/java/swing/plaf/windows/icons/Question.gif")));
		mnHelp.add(menuAbout);
		
		JPanel panel = new JPanel();
		frmJobula.getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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
		
		combo_source = new JComboBox();
		combo_source.setToolTipText("which job search source website?");
		combo_source.setModel(new DefaultComboBoxModel(new String[] {"Indeed"}));
		combo_source.setBounds(140, 36, 120, 20);
		panel_settings.add(combo_source);
		
		text_city = new JTextField();
		text_city.setBounds(270, 36, 120, 20);
		panel_settings.add(text_city);
		text_city.setColumns(10);
		
		combo_country = new JComboBox();
		combo_country.setModel(new DefaultComboBoxModel(new String[] {"Afghanistan", "Albania", "Algeria", "American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica", "Antigua And Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia And Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (keeling) Islands", "Colombia", "Comoros", "Congo", "Congo", "Cook Islands", "Costa Rica", "Cote D'ivoire", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "East Timor", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guinea", "Guinea-bissau", "Guyana", "Haiti", "Heard Island And Mcdonald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong", "Hungary", "Iceland", "India", "Indonesia", "Iran", "Iraq", "Ireland", "Israel", "Italy", "Jamaica", "Japan", "Jordan", "Kazakstan", "Kenya", "Kiribati", "North Korea", "South Korea", "Kosovo", "Kuwait", "Kyrgyzstan", "Lao People's Democratic Republic", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libyan Arab Jamahiriya", "Liechtenstein", "Lithuania", "Luxembourg", "Macau", "Macedonia", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia", "Moldova", "Monaco", "Mongolia", "Montserrat", "Montenegro", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestinian Territory", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Reunion", "Romania", "Russian Federation", "Rwanda", "Saint Helena", "Saint Kitts And Nevis", "Saint Lucia", "Saint Pierre And Miquelon", "Saint Vincent And The Grenadines", "Samoa", "San Marino", "Sao Tome And Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia And The South Sandwich Islands", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard And Jan Mayen", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic", "Taiwan", "Tajikistan", "Tanzania", "Thailand", "Togo", "Tokelau", "Tonga", "Trinidad And Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks And Caicos Islands", "Tuvalu", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States", "United States Minor Outlying Islands", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela", "Viet Nam", "Virgin Islands (British)", "Virgin Islands (US)", "Wallis And Futuna", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"}));
		combo_country.setSelectedIndex(37);
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
		
		combo_type = new JComboBox();
		combo_type.setToolTipText("jobsite for job boards, employer for direct employers");
		combo_type.setModel(new DefaultComboBoxModel(new String[] {"employer", "jobsite"}));
		combo_type.setSelectedIndex(0);
		combo_type.setBounds(10, 92, 120, 20);
		panel_settings.add(combo_type);
		
		combo_jobtype = new JComboBox();
		combo_jobtype.setModel(new DefaultComboBoxModel(new String[] {"fulltime", "parttime", "contract", "internship", "temporary"}));
		combo_jobtype.setSelectedIndex(0);
		combo_jobtype.setBounds(140, 92, 120, 20);
		panel_settings.add(combo_jobtype);
		
		spinner_age = new JSpinner();
		spinner_age.setToolTipText("how long ago was the ad posted?");
		spinner_age.setModel(new SpinnerNumberModel(15, 0, 30, 1));
		spinner_age.setBounds(270, 92, 120, 20);
		panel_settings.add(spinner_age);
		
		spinner_radius = new JSpinner();
		spinner_radius.setToolTipText("distance from your chosen location");
		spinner_radius.setModel(new SpinnerNumberModel(10, 10, 250, 10));
		spinner_radius.setBounds(400, 92, 120, 20);
		panel_settings.add(spinner_radius);
		
		JLabel lblAdLimit = new JLabel("Ad limit");
		lblAdLimit.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAdLimit.setBounds(10, 123, 46, 14);
		panel_settings.add(lblAdLimit);
		
		spinner_limit = new JSpinner();
		spinner_limit.setToolTipText("the number of ads shown");
		spinner_limit.setModel(new SpinnerNumberModel(100, 1, 1000, 10));
		spinner_limit.setBounds(10, 148, 120, 20);
		panel_settings.add(spinner_limit);
		
		chckbxCheckForEmail = new JCheckBox("Check for email");
		chckbxCheckForEmail.setToolTipText("scan postings for email addresses for direct contact");
		chckbxCheckForEmail.setBounds(10, 175, 120, 23);
		panel_settings.add(chckbxCheckForEmail);
		
		chckbxCheckForPhone = new JCheckBox("Check for phone number");
		chckbxCheckForPhone.setToolTipText("scan postings for phone numbers for direct contact, experimental!");
		chckbxCheckForPhone.setBounds(10, 201, 176, 23);
		panel_settings.add(chckbxCheckForPhone);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("test");
				//first have to reset start page and progress since last click
				indeed.start_page = 0;
				progressBar.setValue(0);
				progressBar.repaint();
				lblResults.setText("Results: ");
				tabbedPane.setTitleAt(1, "Job List");
				
				//collect data from all the fields in gui
				int fields = 0;
				try {
					fields = indeed.collect_Data();
				} catch (MalformedURLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
				//TODO add another exception here that catches when the host is unreachable, otherwise it continues to try and parse data
				
				//check if fields were even entered
				if (fields == -1) {
					indeed.messagePopup();
				} else {
				
					//generate the first url, which will return all the needed info for number of pages,
					//total number of jobs, etc
					String first_url = indeed.Generate_Link();
					
					//because of some exceptions, try to obtain the DOM information, total page number, 
					//and parsed data
					//the parsed data is put into an array of 2D objects, which will become the data
					//for the jtable
					try {
						//first round that gets all the information
						Element elm = indeed.get_Post(first_url);
						int pages = indeed.total_pages;
						//check if pages are zero and exit if they are
						if(pages == 0) {
							TableModel empty = new DefaultTableModel(null, indeed.columns);
							job_table.setModel(empty);
							return;
						}
						System.out.println("Total pages: "+pages);
	//					Object[][] all_parsed_data = indeed.parse_Element(elm);		//for single use
						Object[][] parsed = new Object[pages][25];
						parsed[0] = indeed.parse_Element(elm);
						
	//					Object[][] all_parsed_data = new Object[((indeed.resnum/25)+1)*25][0];			//with padding
						Object[][] all_parsed_data = new Object[indeed.resnum][0];						//without padding
						System.arraycopy(parsed[0], 0, all_parsed_data, 0, parsed[0].length);
						
						//increment the progress bar
						progressBar.setValue((int)(100*((double)1/(double)indeed.total_pages)));
						progressBar.repaint();
						
						//decrement total pages left
						pages--;
						
						//subsequent rounds to fill table
						int parse_idx = 1;
						while(pages > 0) {
							System.out.println("total pages left: "+indeed.total_pages+", "+pages);
							parsed[parse_idx] = indeed.parse_Element(indeed.get_Post(indeed.Generate_Link()));
							System.out.println(parse_idx);
							System.out.println(parsed[parse_idx-1].length);
							System.out.println(parsed[parse_idx].length);
							System.out.println(all_parsed_data.length);
							if(parse_idx == 3) {
								System.out.println("wait here");
							}
							System.arraycopy(parsed[parse_idx], 0, all_parsed_data, parse_idx*parsed[parse_idx-1].length, parsed[parse_idx].length);
							parse_idx++;
							pages--;
							progressBar.setValue((int)(100*((double)(indeed.total_pages-pages)/(double)indeed.total_pages)));
							progressBar.repaint();
						}
						
						//update table with collected data
						TableModel model = new DefaultTableModel(all_parsed_data, indeed.columns);
						job_table.setModel(model);
						indeed.reset_table_shape(job_table);
						
						//set tab title to include jobs found
						tabbedPane.setTitleAt(1, "Job List ("+indeed.resnum+")");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ParserConfigurationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SAXException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
			}
		});
		btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSearch.setBounds(10, 269, 89, 23);
		panel_settings.add(btnSearch);
		
		lblResults = new JLabel("Results: ");
		lblResults.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResults.setBounds(109, 273, 125, 14);
		panel_settings.add(lblResults);
		
		progressBar = new JProgressBar();
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
		
		job_table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		job_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				JTable table = (JTable) arg0.getSource();
				Point p = arg0.getPoint();
				int row = table.rowAtPoint(p);
				if(arg0.getClickCount() == 2 && row != -1) {
					String url = table.getModel().getValueAt(row, 5).toString();
					System.out.println(url);
					if(Desktop.isDesktopSupported()) {
						try {
							Desktop.getDesktop().browse(new URI(url));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (URISyntaxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});

		job_table.setFillsViewportHeight(true);
		scrollPane.setViewportView(job_table);
		job_table.setForeground(new Color(0, 0, 0));
		job_table.setBackground(SystemColor.window);
		job_table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		job_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Title", "Company", "City", "Ad age", "Summary", "URL"
			})
		);
		TableModel model = job_table.getModel();
		
		job_table.getColumnModel().getColumn(0).setPreferredWidth(80);
		job_table.getColumnModel().getColumn(0).setMinWidth(80);
		
		job_table.getColumnModel().getColumn(1).setPreferredWidth(80);
		job_table.getColumnModel().getColumn(1).setMinWidth(80);
		
		job_table.getColumnModel().getColumn(2).setPreferredWidth(80);
		job_table.getColumnModel().getColumn(2).setMinWidth(80);
		
		job_table.getColumnModel().getColumn(3).setPreferredWidth(60);
		job_table.getColumnModel().getColumn(3).setMinWidth(40);
		
		job_table.getColumnModel().getColumn(4).setPreferredWidth(80);
		job_table.getColumnModel().getColumn(4).setMinWidth(80);
		
		job_table.getColumnModel().getColumn(5).setPreferredWidth(80);
		job_table.getColumnModel().getColumn(5).setMinWidth(80);

		job_table.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		job_table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		job_table.setAutoCreateRowSorter(true);
		
		job_table.getTableHeader().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JTableHeader tableHeader = (JTableHeader) e.getSource();
				int selectedColumn = tableHeader.columnAtPoint(e.getPoint());
				TableRowSorter<TableModel> sorter = new TableRowSorter<>(job_table.getModel());
				job_table.setRowSorter(sorter);
				sorter.setComparator(3, new Sorter());
				
				//update table with collected data
//				TableModel model = sorter.getModel();
//				job_table.setModel(model);
//				indeed.reset_table_shape(job_table);
				
				job_table.setModel(sorter.getModel());
				job_table.repaint();
			}});
	}
}
