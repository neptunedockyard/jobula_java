package jobula_search;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sorter implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
//		System.out.println("USING MY SORTER");
		String pattern_hours = "hour|stunden|Stunden|heur|時間|小时|小時|horas";
		String pattern_minutes = "minute";
		Pattern p_h = Pattern.compile(pattern_hours);
		Pattern p_m = Pattern.compile(pattern_minutes);
		String ar0 = (String)arg0;
		String ar1 = (String)arg1;
		Matcher m_h = p_h.matcher(ar0);
		Matcher n_h = p_h.matcher(ar1);
		Matcher m_m = p_m.matcher(ar0);
		Matcher n_m = p_m.matcher(ar1);
		String r = "[^0-9]";
		
		int x = Integer.parseInt(((String)arg0).split("[^0-9]")[0]);
		int y = Integer.parseInt(((String)arg1).split("[^0-9]")[0]);
//		System.out.println("x: "+x+", y: "+y);
		int a = Integer.valueOf(x);
		int b = Integer.valueOf(y);		
		if(m_h.find() && !n_h.find() && !m_m.find() && !n_m.find()) {
			b = b * 100;
		} else if (!m_h.find() && n_h.find() && !m_m.find() && !n_m.find()) {
			a = a * 100;
		} else if (!m_h.find() && !n_h.find() && m_m.find() && !n_m.find()) {
			b = b * 10;
		} else if (!m_h.find() && !n_h.find() && !m_m.find() && n_m.find()) {
			a = a * 10;
		}
//		System.out.println("x: "+x+", y: "+y+" compare: "+ Integer.valueOf(a).compareTo(Integer.valueOf(b)));
//		return Integer.valueOf(x).compareTo(Integer.valueOf(y));
		return Integer.valueOf(a).compareTo(Integer.valueOf(b));
	}

}
