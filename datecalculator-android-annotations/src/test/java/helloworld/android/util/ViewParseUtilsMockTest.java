package helloworld.android.util;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import android.widget.TextView;

/**
 * Robolectric와의 비교를 위해 만든 테스트
 * @author sanghyuk.jung
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ViewParseUtilsMockTest {
	@Mock TextView input;
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREAN);
	
	@Test
	public void validDate(){
		given(input.getText()).willReturn("2013-03-14");
		Calendar parsed = ViewParseUtils.parseDate(input, format);
		
		assertDate(parsed, 2013, 3, 14);
	}

	@Test
	public void strangeButValidDate(){
		given(input.getText()).willReturn("2013-13-03");
		Calendar parsed = ViewParseUtils.parseDate(input, format);
		assertDate(parsed, 2014, 1, 3);
	}
	
	@Test
	@Ignore // AndroidLog 코드에 걸려서 test가 fail한다.
	public void wrongDateFormat(){
		given(input.getText()).willReturn("2013/5/3");
		Calendar parsed = ViewParseUtils.parseDate(input, format);
		assertToday(parsed);
	}
	
	@Test
	public void validInteger(){
		given(input.getText()).willReturn("314");
		int parsed = ViewParseUtils.parseInt(input);
		assertThat(parsed,is(314));
	}
	
	@Test
	@Ignore // AndroidLog 코드에 걸려서 test가 fail한다.
	public void wrongInteger(){
		given(input.getText()).willReturn("314a");
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
