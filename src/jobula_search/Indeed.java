/**
 * 
 */
package jobula_search;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
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
	
//	public void get_Post(String link_url) {
//		try {
//			URL url = new URL(link_url);
//			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//			connection.setDoOutput(true);
//			connection.setInstanceFollowRedirects(false);
//			connection.setRequestMethod("POST");
//			connection.setRequestProperty("Content-Type", "application/xml");
//			
//			OutputStream os = (OutputStream) connection.getOutputStream();
//			//write xml to outputstream
//			System.out.print(os.toString());
//			os.flush();
//			connection.getResponseCode();
//			connection.disconnect();
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}
	
	public void get_Post(String link_url) throws TransformerException, IOException, ParserConfigurationException, SAXException {
		URL url = new URL(link_url);
		URLConnection conn = url.openConnection();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder.parse(conn.getInputStream());
		
		TransformerFactory factory2 = TransformerFactory.newInstance();
		Transformer xform = factory2.newTransformer();
		
		xform.transform(new DOMSource(doc), new StreamResult(System.out));
	}
}