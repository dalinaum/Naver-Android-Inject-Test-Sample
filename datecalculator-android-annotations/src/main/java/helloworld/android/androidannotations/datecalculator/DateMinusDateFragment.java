package helloworld.android.androidannotations.datecalculator;

import helloworld.android.androidannotations.datecalculator.service.DateService;
import helloworld.android.util.ViewEventUtils;
import helloworld.android.util.ViewParseUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_date_minus_date)
public class DateMinusDateFragment extends Fragment {

	@Bean DateService dateService;
	
	@ViewById EditText firstDateInput;
	@ViewById EditText secondDateInput;
	@ViewById TextView resultDaysLabel; 
	
	DateFormat dateFormat;
	
	@AfterViews
	public void init(){
		/* set initial values */
		dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
		Calendar now = Calendar.getInstance();
		String today = dateFormat.format(now.getTime());
		firstDateInput.setText(today);
		secondDateInput.setText(today);

		/* bind special event listeners */
		registerInputEventListener(firstDateInput, dateFormat);
		registerInputEventListener(secondDateInput, dateFormat);
	}

	@Click
	public void calcaulateButton(){
		Date firstDate = ViewParseUtils.parseDate(firstDateInput, dateFormat).getTime();
		Date secondDate = ViewParseUtils.parseDate(secondDateInput, dateFormat).getTime();
		int resultDays = dateService.minus(firstDate, secondDate);
		resultDaysLabel.setText(String.valueOf(resultDays));
	}
	
	private void registerInputEventListener(EditText baseDateInput, DateFormat dateFormat) {
		Runnable dateSelector = new DateSelector(getActivity(), baseDateInput, dateFormat);
		ViewEventUtils.registerOnClickListener(baseDateInput, dateSelector);
		ViewEventUtils.registerOnFocusListener(baseDateInput, dateSelector);
	}
}
