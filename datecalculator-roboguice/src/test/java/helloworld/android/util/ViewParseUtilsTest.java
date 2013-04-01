package helloworld.android.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import helloworld.android.roboguice.datecalculator.CalculatorActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
public class ViewParseUtilsTest {
	TextView input;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
	
	@Before
	public void prepare(){
		CalculatorActivity activity = new CalculatorActivity();
		input = new TextView(activity);
		System.setProperty("robolectric.logging","stdout");

	}
	
	@Test
	public void validDate(){
		input.setText("2013-03-12");
		Calendar parsed = ViewParseUtils.parseDate(input, format);
		
		assertDate(parsed, 2013, 3, 12);
	}

	@Test
	public void strangeButValidDate(){
		input.setText("2013-13-03");
		Calendar parsed = ViewParseUtils.parseDate(input, format);
		
		assertDate(parsed, 2014, 1, 3);
	}
	
	@Test
	public void wrongDateFormat(){
		input.setText("2013/5/3");
		Calendar parsed = ViewParseUtils.parseDate(input, format);
		
		assertToday(parsed);
	}
	
	@Test
	public void validInteger(){
		input.setText("314");
		int parsed = ViewParseUtils.parseInt(input);
		assertThat(parsed,is(314));
	}
	
	@Test
	public void wrongInteger(){
		input.setText("314a");
		int parsed = ViewParseUtils.parseInt(input);
		assertThat(parsed,is(0));
	}

	private void assertDate(Calendar date, int year, int month, int day) {
		assertThat("year", date.get(Calendar.YEAR), is(year));
		assertThat("month", date.get(Calendar.MONTH), is(month-1)); // Calendar객체에서는 0을 1월달로 취급
		assertThat("day", date.get(Calendar.DAY_OF_MONTH), is(day));
	}
	
	private void assertToday(Calendar date) {
		Calendar today = Calendar.getInstance();
		today.setTimeInMillis(System.currentTimeMillis());
		
		assertThat(date.get(Calendar.YEAR), is(today.get(Calendar.YEAR)));
		assertThat(date.get(Calendar.MONTH), is(today.get(Calendar.MONTH))); 
		assertThat(date.get(Calendar.DAY_OF_MONTH), is(today.get(Calendar.DAY_OF_MONTH)));
	}
}
