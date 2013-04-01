package helloworld.android.androidannotations.datecalculator;

import helloworld.android.androidannotations.datecalculator.service.DateService;
import helloworld.android.util.ViewEventUtils;
import helloworld.android.util.ViewParseUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.widget.EditText;
import android.widget.TextView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Bean;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EFragment;
import com.googlecode.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_date_and_day)
public class DateAndDaysFragment extends Fragment {
	Operation operation;

	@Bean DateService service;
	
	@ViewById TextView baseDateInput;
	@ViewById TextView resultDateLabel;
	@ViewById EditText daysInput;
	@ViewById TextView operatorLabel;
	
	DateFormat dateFormat;
	
	public DateAndDaysFragment setOperation(Operation operation) {
		this.operation = operation;
		return this;
	}
	
	@AfterViews
	public void init(){
		/* set initial values */
		dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
		Calendar now = Calendar.getInstance();
		String today = dateFormat.format(now.getTime());
		resultDateLabel.setText(today);
		baseDateInput.setText(today);

		/* bind special event listeners */
		Runnable dateSelector = new DateSelector(getActivity(), baseDateInput, dateFormat);
		ViewEventUtils.registerOnFocusListener(baseDateInput, dateSelector);
		ViewEventUtils.registerOnClickListener(baseDateInput, dateSelector);
	}
	
	@Click
	public void sendButton(){
		Calendar eventTime = ViewParseUtils.parseDate(baseDateInput, dateFormat);
		
		Intent intent = new Intent(Intent.ACTION_INSERT);
		intent.setData(CalendarContract.Events.CONTENT_URI);
		intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, eventTime.getTimeInMillis());
		intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
		intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, eventTime.getTimeInMillis()+60*60*1000);
		startActivity(intent);		
	}

	@Click
	public void calcaulateButton(){
		Date baseDate = ViewParseUtils.parseDate(baseDateInput, dateFormat).getTime();
		int days = ViewParseUtils.parseInt(daysInput) * operation.signBase;
		Date resultDate = service.plus(baseDate, days);
		resultDateLabel.setText(dateFormat.format(resultDate));
	}
}

