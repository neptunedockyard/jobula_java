package jobula_search;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sorter implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
//		System.out.println("USING MY SORTER");
		String pattern = "minute|hour|stunden|Stunden|heur|時間|小时|小時|horas";
		Pattern p = Pattern.compile(pattern);
		String ar0 = (String)arg0;
		String ar1 = (String)arg1;
		Matcher m = p.matcher(ar0);
		Matcher n = p.matcher(ar1);
		String r = "[^0-9]";
		
		int x = Integer.parseInt(((String)arg0).split("[^0-9]")[0]);
		int y = Integer.parseInt(((String)arg1).split("[^0-9]")[0]);
//		System.out.println("x: "+x+", y: "+y);
		int a = Integer.valueOf(x);
		int b = Integer.valueOf(y);		
		if(m.find() && !n.find()) {
			y = y * 100;
			b = b * 100;
		} else if (!m.find() && n.find()) {
			x = x * 100;
			a = a * 100;
		}
//		System.out.println("x: "+x+", y: "+y+" compare: "+ Integer.valueOf(a).compareTo(Integer.valueOf(b)));
//		return Integer.valueOf(x).compareTo(Integer.valueOf(y));
		return Integer.valueOf(a).compareTo(Integer.valueOf(b));
	}

}
