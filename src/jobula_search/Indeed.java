/**
 * 
 */
package jobula_search;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
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
	
	public int start_page;
	private int end_page;
	public int total_pages;
	public Object[] columns = {"Jobtitle", "Company", "Location", "Ad age", "Snippet", "URL"};
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
//		limit = (int) limit_s.getValue();					//this limit is actually how many ads per page, leave set to 100
		fromage = (int) age_s.getValue();
		country = country_s.getSelectedItem().toString();
	}
	
	public String Generate_Link() {
		//reset the start page to zero
//		this.start_page = 0;
		String link = html+publisher+
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
	
	public Element get_Post(String link_url) throws IOException, ParserConfigurationException, SAXException {
		URL url = new URL(link_url);
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
				resnum = Integer.parseInt(node.getFirstChild().getNodeValue());
				resultnumber_s.setText(node.getFirstChild().getNodeValue());
				this.total_pages = 1+resnum/25;
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
//		TableModel model = new DefaultTableModel(parsed_data, columns);
//		this.output_table_s.setModel(model);
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
}