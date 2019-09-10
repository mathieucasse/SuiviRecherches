package ch.matfly.suivirecherches.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MatFormat {
	
	private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private final static String standard = "2000-01-01";

	public static String format(Long l) {
		if(null == l) return standard;
		Timestamp timestamp = new Timestamp(l);
		Date date = new Date(timestamp.getTime());
		return sdf.format(date);
	}
	
	public static String format(Timestamp t) {
		if(null == t) return standard;
		return sdf.format(new Date(t.getTime()));
	}
	
	public static String format(Date d) {
		if(null == d) return standard;
		return sdf.format(d);
	}
	
	public static String format(String s) {
		if(null == s) return standard;
		log.debug("======= Date d = " + s );
		return sdf.format(s.substring(0, 11));
	}
}
