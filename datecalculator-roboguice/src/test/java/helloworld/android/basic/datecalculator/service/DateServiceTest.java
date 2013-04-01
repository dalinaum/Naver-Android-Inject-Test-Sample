package helloworld.android.basic.datecalculator.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import helloworld.android.roboguice.datecalculator.service.DateService;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.junit.Test;


public class DateServiceTest {
	
	DateService service = new  DateService();
	 
	@Test
	public void plusDaysCase1() throws ParseException {
		Date baseDate = date("2012-03-31");
		Date addedDate = service.plus(baseDate, 3);
		assertThat(addedDate, is(date("2012-04-03")));
	}
	
	@Test
	public void plusDaysCase2() throws ParseException {
		Date baseDate = date("2013-02-27");
		Date addedDate = service.plus(baseDate, 2);
		assertThat(addedDate, is(date("2013-03-01")));
	}

	
	@Test
	public void minusDaysCase1() throws ParseException {
		Date baseDate = date("2012-03-01");
		Date addedDate = service.minus(baseDate, 1);
		assertThat(addedDate, is(date("2012-02-29")));
	}
	
	@Test
	public void minusDateCase1() throws ParseException {
		Date firstDate = date("2012-03-02");
		Date secondDate = date("2012-03-1");
		int diffDays = service.minus(firstDate, secondDate);
		assertThat(diffDays, is(1));
	}
	
	@Test
	public void minusDateCase2() throws ParseException {
		Date firstDate = date("2012-03-01");
		Date secondDate = date("2012-02-21");
		int diffDays = service.minus(firstDate, secondDate);
		assertThat(diffDays, is(9));
	}
	
	private Date date(String base) throws ParseException {
		return DateUtils.parseDate(base, new String[]{"yyyy-MM-dd"});
	}
}
