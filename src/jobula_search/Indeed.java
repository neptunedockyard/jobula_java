/**
 * 
 */
package jobula_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.omg.CORBA_2_3.portable.OutputStream;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

/**
 * @author atreyu-win10
 *
 */
public class Indeed {

	/**
	 * 
	 */
	
	public static String publisher = "444457643170882";
	public static int version = 2;
	public static String format = "xml";
	public static String query = "";
	public static String location = "";
	public static String sort = "relevance";
	public static int radius = 25;
	public static String sitetype = "jobsite";
	public static String jobtype = "fulltime";
//	public static int start = 0;
	public static int limit = 100;
	public static int fromage = 15;
	public static int highlight = 0;
	public static int filter = 1;
	public static int latlong = 0;
	public static String country = "Canada";
	public static String channel = "neptuneSearch_Java";
	public static String userip = "";
	public static String userAgent = "Mozilla/5.0";
	public static String html = "http://api.indeed.com/ads/apisearch?publisher=";
	public static String[][] countries = new String[][] {
		{ "aq", "Antarctica" },
		{ "ar", "Argentina" },
		{ "au", "Australia" },
		{ "at", "Austria" },
		{ "bh", "Bahrain" },
		{ "be", "Belgium" },
		{ "br", "Brazil" },
		{ "ca", "Canada" },
		{ "cl", "Chile" },
		{ "cn", "China" },
		{ "co", "Colombia" },
		{ "cr", "Costa Rica" },
		{ "cz", "Czech Republic" },
		{ "dk", "Denmark" },
		{ "ec", "Ecuador" },
		{ "eg", "Egypt" },
		{ "fi", "Finland" },
		{ "fr", "France" },
		{ "de", "Germany" },
		{ "gr", "Greece" },
		{ "hk", "Hong Kong" },
		{ "hu", "Hungary" },
		{ "in", "India" },
		{ "id", "Indonesia" },
		{ "ie", "Ireland" },
		{ "il", "Israel" },
		{ "it", "Italy" },
		{ "jp", "Japan" },
		{ "kw", "Kuwait" },
		{ "lu", "Luxembourg" },
		{ "my", "Malaysia" },
		{ "mx", "Mexico" },
		{ "ma", "Morocco" },
		{ "nl", "Netherlands" },
		{ "nz", "New Zealand" },
		{ "ng", "Nigeria" },
		{ "no", "Norway" },
		{ "om", "Oman" },
		{ "pk", "Pakistan" },
		{ "pa", "Panama" },
		{ "pe", "Peru" },
		{ "ph", "Philippines" },
		{ "pl", "Poland" },
		{ "pt", "Portugal" },
		{ "qa", "Qatar" },
		{ "ro", "Romania" },
		{ "ru", "Russia" },
		{ "sa", "Saudi Arabia" },
		{ "sg", "Singapore" },
		{ "za", "South Africa" },
		{ "kr", "South Korea" },
		{ "es", "Spain" },
		{ "se", "Sweden" },
		{ "ch", "Switzerland" },
		{ "tw", "Taiwan" },
		{ "th", "Thailand" },
		{ "tr", "Turkey" },
		{ "ua", "Ukraine" },
		{ "ae", "United Arab Emirates" },
		{ "gb", "United Kingdom" },
		{ "us", "United States" },
		{ "uy", "Uruguay" },
		{ "ve", "Venezuela" },
		{ "vn", "Vietnam" }
	};
	
	public int start_page;
	private int end_page;
	public int total_pages;
	public Object[] columns = {"Title", "Company", "City", "Ad age", "Summary", "URL"};
	public int resnum;
	
	private JTextField text_search;
	private Object source_s;
	private JTextField city_s;
	private JComboBox<?> country_s;
	private JComboBox<?> emptype_s;
	private JComboBox<?> jobtype_s;
	private JSpinner age_s;
	private JSpinner radius_s;
	private JSpinner limit_s;
	private JCheckBox email_s;
	private JCheckBox phone_s;
	private JLabel resultnumber_s;
	private JProgressBar progress_s;
	private JTable output_table_s;
	
	public String ip_url = "https://icanhazip.com";
	
	public Indeed(JTextField text_search, JComboBox<?> combo_source, JTextField text_city, JComboBox<?> combo_country, JComboBox<?> combo_type, JComboBox<?> combo_jobtype,
			JSpinner spinner_age, JSpinner spinner_radius, JSpinner spinner_limit, JCheckBox chckbxCheckForEmail, JCheckBox chckbxCheckForPhone,
			JLabel lblResults, JProgressBar progressBar, JTable job_table) {
		// TODO Auto-generated constructor stub
		this.text_search = text_search;
		this.source_s = combo_source;
		this.city_s = text_city;
		this.country_s = combo_country;
		this.emptype_s = combo_type;
		this.jobtype_s = combo_jobtype;
		this.age_s = spinner_age;
		this.radius_s = spinner_radius;
		this.limit_s = spinner_limit;
		this.email_s = chckbxCheckForEmail;
		this.phone_s = chckbxCheckForPhone;
		this.resultnumber_s = lblResults;
		this.progress_s = progressBar;
		this.output_table_s = job_table;
	}
	
	public int collect_Data() throws MalformedURLException {
//		query = !text_search.getText().isEmpty() ? text_search.getText() : "";
//		location = !city_s.getText().isEmpty() ? city_s.getText() : "";
		
		query = text_search.getText().trim().replaceAll("\\s+", "%20");
		location = city_s.getText().trim().replaceAll("\\s+", "%20");
		
		radius = (int) radius_s.getValue();
		sitetype = emptype_s.getSelectedItem().toString().trim().replaceAll("\\s+", "%20");
		jobtype = jobtype_s.getSelectedItem().toString().trim().replaceAll("\\s+", "%20");
		fromage = (int) age_s.getValue();
//		country = country_s.getSelectedItem().toString().trim().replaceAll("\\s+", "%20");
		country = countries[country_s.getSelectedIndex()][0].toString().trim();
//		userip = get_IP();
		
		System.out.println("q: "+query+" l: "+location);
		if(query == "ALL") {
			query = "";
			userip = get_IP();
			return 0;
		}
		if(query.trim().isEmpty() || location.trim().isEmpty() ) {
			return -1;
		} else {
			userip = get_IP();
			return 0;
		}
	}
	
	public void messagePopup() {
		JOptionPane.showMessageDialog(null, "Please enter all fields", "Search Criteria", JOptionPane.WARNING_MESSAGE);
	}
	
	public String Generate_Link() {
		//reset the start page to zero
//		this.start_page = 0;
		String link = html+
				publisher+
				"&q="+query+
				"&l="+location+
				"&sort="+sort+
				"&radius="+radius+
				"&st="+sitetype+
				"&jt="+jobtype+
				"&start="+this.start_page+
				"&limit="+limit+
				"&fromage="+fromage+
				"&latlong="+latlong+
				"&co="+country+
				"&chnl="+channel+
				"&userip="+userip+
				"&useragent="+userAgent+
				"&v="+version;
		System.out.println(link);
		return link;
	}
	
	public Element get_Post(String link) throws IOException, ParserConfigurationException, SAXException {
		URL url = new URL(link);
		URLConnection conn = url.openConnection();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());
		
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);

			System.out.println(node.getNodeName());
			if (node.getNodeName() == "totalresults") {
				System.out.println(node.getFirstChild().getNodeValue());
				//the 3 lines here grab the total results from the search, but thru experimentation, looks like 1000 is max
				resnum = Integer.parseInt(node.getFirstChild().getNodeValue());
				if (resnum >= (int) limit_s.getValue()) {
					resnum = (int) limit_s.getValue();
				}
				resultnumber_s.setText("Results: "+node.getFirstChild().getNodeValue());
				if(resnum%25 != 0) {
					this.total_pages = 1+resnum/25;
				} else {
					this.total_pages = resnum/25;
				}
			} else if (node.getNodeName() == "start") {
				System.out.println("start page: "+node.getFirstChild().getNodeValue());
//				this.start_page = Integer.parseInt(node.getFirstChild().getNodeValue());
			} else if (node.getNodeName() == "end") {
				System.out.println("end page: "+node.getFirstChild().getNodeValue());
//				this.end_page = Integer.parseInt(node.getFirstChild().getNodeValue());
				//define new start page to be used for the next round
				this.start_page = Integer.parseInt(node.getFirstChild().getNodeValue());
			}
		}
		return root;
	}
	
	@SuppressWarnings("null")
	public Object[][] parse_Element(Element element) {
		NodeList nodeList = element.getElementsByTagName("results");
		NodeList subNodeList = nodeList.item(0).getChildNodes();
		int length = subNodeList.getLength();
//		Object[][] parsed_data = new Object[Integer.parseInt(this.resultnumber_s.getText())][6];
		Object[][] parsed_data = new Object[length][6];
		
		System.out.println("length: " + length);
		if(subNodeList != null) {
			for (int index = 0; index < length; index++) {
				if(subNodeList.item(index).getNodeType() == Node.ELEMENT_NODE) {
					Element eln = (Element) subNodeList.item(index);
					if(eln.getNodeName().contains("result")) {
						parsed_data[index][0] = eln.getElementsByTagName("jobtitle").item(0).getTextContent();
						parsed_data[index][1] = eln.getElementsByTagName("company").item(0).getTextContent();
						parsed_data[index][2] = eln.getElementsByTagName("formattedLocation").item(0).getTextContent();
						parsed_data[index][3] = eln.getElementsByTagName("formattedRelativeTime").item(0).getTextContent();
						parsed_data[index][4] = eln.getElementsByTagName("snippet").item(0).getTextContent();
						parsed_data[index][5] = eln.getElementsByTagName("url").item(0).getTextContent();
					}
				}
			}
		}
		return parsed_data;
	}
	
	public Object[][] append(Object[] parsed, Object[] parsed2)
	{
	    Object[][] result = new Object[parsed.length + parsed2.length][];
	    System.arraycopy(parsed, 0, result, 0, parsed.length);
	    System.arraycopy(parsed2, 0, result, parsed.length, parsed2.length);
	    return result;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T[] clean(T[] a) {
	    List<T> list = new ArrayList<T>(Arrays.asList(a));
	    list.removeAll(Collections.singleton(null));
	    return list.toArray((T[]) Array.newInstance(a.getClass().getComponentType(), list.size()));
	}
	
	public String get_IP() throws MalformedURLException {
		URL myIP = new URL(ip_url);
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(myIP.openStream()));
			String ip = in.readLine();
			return ip;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Could not connect to the Internet", "Connection Error", JOptionPane.WARNING_MESSAGE);
		}
		return null;
	}
	
//	public void table_Sorter(JTable table) {
//		//TODO this is the sorter for the ad age column, needs some work here to develop into a smart sorter
//		TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(table.getModel());
//		table.setRowSorter(sorter);
//		List<RowSorter.SortKey> sortKeys = new ArrayList<>();
//		
//		int columnIndexToSort = 3;
//		sortKeys.add(new RowSorter.SortKey(columnIndexToSort, SortOrder.ASCENDING));
//		System.out.println("sorting");
//		
//		sorter.setSortKeys(sortKeys);
//		sorter.sort();
//	}
	
	public void reset_table_shape(JTable table) {
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(0).setMinWidth(80);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(80);
		
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(3).setMinWidth(40);
		
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		table.getColumnModel().getColumn(4).setMinWidth(80);
		
		table.getColumnModel().getColumn(5).setPreferredWidth(80);
		table.getColumnModel().getColumn(5).setMinWidth(80);
	}
}