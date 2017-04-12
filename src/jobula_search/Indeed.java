/**
 * 
 */
package jobula_search;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
	public static int start = 0;
	public static int limit = 10;
	public static int fromage = 15;
	public static int highlight = 0;
	public static int filter = 1;
	public static int latlong = 0;
	public static String country = "Canada";
	public static String channel = "neptuneSearch_Java";
	public static String userip = "";
	public static String userAgent = "Mozilla/5.0";
	public static String html = "http://api.indeed.com/ads/apisearch?publisher=";
	
	private int start_page;
	private int end_page;
	
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
	
	public void collect_Data() {
		query = text_search.getText();
		location = city_s.getText();
		radius = (int) radius_s.getValue();
		sitetype = emptype_s.getSelectedItem().toString();
		jobtype = jobtype_s.getSelectedItem().toString();
		limit = (int) limit_s.getValue();
		fromage = (int) age_s.getValue();
		country = country_s.getSelectedItem().toString();
	}
	
	public String Generate_Link() {
		String link = html+publisher+
				"&q="+query+
				"&l="+location+
				"&sort="+sort+
				"&radius="+radius+
				"&st="+sitetype+
				"&jt="+jobtype+
				"&start="+start+
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
	
	public Element get_Post(String link_url) throws SAXException, IOException, ParserConfigurationException {
		URL url = new URL(link_url);
		URLConnection conn = url.openConnection();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());
		
		Element root = doc.getDocumentElement();
		NodeList nodes = root.getChildNodes();
		NodeList resultsNodes;
		ArrayList<String> nodeArray = new ArrayList<String>();
		
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);

			System.out.println(node.getNodeName());
			if (node.getNodeName() == "totalresults") {
				System.out.println(node.getFirstChild().getNodeValue());
				resultnumber_s.setText(node.getFirstChild().getNodeValue());
			} else if (node.getNodeName() == "start") {
				System.out.println("start page: "+node.getFirstChild().getNodeValue());
				this.start_page = Integer.parseInt(node.getFirstChild().getNodeValue());
			} else if (node.getNodeName() == "end") {
				System.out.println("end page: "+node.getFirstChild().getNodeValue());
				this.end_page = Integer.parseInt(node.getFirstChild().getNodeValue());
			}
		}
		return root;
	}
	
/*	public ArrayList<String> parse_Element(Element element) {
		NodeList nodeList = element.getElementsByTagName("result");
		ArrayList<String> list = new ArrayList<String>();
		Object[][] parsed_data;
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			list.add(getTextValue(nodeList.item(i)));
		}
		System.out.println("parsed");
		System.out.print(list.toString());
		return list;
	}

	public String getTextValue(Node node) {
		// TODO Auto-generated method stub
		StringBuffer buff = new StringBuffer();
		int length = node.getChildNodes().getLength();
		for (int i = 0; i < length; i++) {
			Node c = node.getChildNodes().item(i);
			if (c.getNodeType() == Node.TEXT_NODE) {
				buff.append(c.getNodeValue());
			}
		}
		return buff.toString().trim();
	}*/
	
	@SuppressWarnings("null")
	public Object[][] parse_Element(Element element) {
		NodeList nodeList = element.getElementsByTagName("results");
		NodeList subNodeList = nodeList.item(0).getChildNodes();
		int length = subNodeList.getLength();
		Object[][] parsed_data = new Object[Integer.parseInt(this.resultnumber_s.getText())][6];
		
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

	/*private String getXMLValue(Node node, String header_string) {
		// TODO Auto-generated method stub
		int length = node.getChildNodes().getLength();
		for (int idx = 0; idx < length; idx++) {
			Node c = node.getChildNodes().item(idx);
			String temp = c.getFirstChild().getNodeName();
			String value = c.getNodeValue();
			if (c.getNodeName() == header_string) {
				return c.getNodeValue();
			} else {
				return "fail";
			}
		}
		return null;
	}*/
}