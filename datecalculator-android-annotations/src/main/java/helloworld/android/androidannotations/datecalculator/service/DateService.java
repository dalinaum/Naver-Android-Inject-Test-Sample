package helloworld.android.androidannotations.datecalculator.service;

import java.util.Calendar;
import java.util.Date;

import com.googlecode.androidannotations.annotations.EBean;

@EBean
public class DateService {
	private static final long milliSecondsInDays = 1000*60*60*24;

	public Date plus(Date baseDate, int days)  {
		Calendar cal = Calendar.getInstance();
		cal.setTime(baseDate);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	public Date minus(Date baseDate, int days) {
		return plus(baseDate, days *(-1));
	}

	public int minus(Date firstDate, Date secondDate) {
		return (int)((firstDate.getTime() - secondDate.getTime()) / milliSecondsInDays);
	}
}
