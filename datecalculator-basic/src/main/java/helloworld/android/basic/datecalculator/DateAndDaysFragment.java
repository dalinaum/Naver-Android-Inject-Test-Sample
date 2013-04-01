package helloworld.android.basic.datecalculator;

import helloworld.android.basic.datecalculator.service.DateService;
import helloworld.android.util.ViewEventUtils;
import helloworld.android.util.ViewParseUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DateAndDaysFragment extends Fragment {
	private static final String TAG = "DateAndDaysOperationFragment";
	Operation operation;
	
	EditText baseDateInput;
	TextView operationLabel;
	TextView resultDateLabel;
	EditText daysInput;
	DateFormat dateFormat;
	DateService dateService = new DateService();
	
	public DateAndDaysFragment setOperation(Operation operation) {
		this.operation = operation;
		return this;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		Log.d(TAG,"on create view");

		return inflater.inflate(R.layout.fragment_date_and_day, container, false);
	}
	
    public void onViewCreated(View view, Bundle savedInstanceState) {
    	/* lookup views */
		baseDateInput = (EditText)view.findViewById(R.id.baseDateInput);
		resultDateLabel = (TextView)view.findViewById(R.id.resultDateLabel);
		daysInput = (EditText)view.findViewById(R.id.daysInput);
		Button calcButton = (Button) view.findViewById(R.id.calcaulateButton);
		Button sendButton = (Button) view.findViewById(R.id.sendButton);
		operationLabel = (TextView)view.findViewById(R.id.operatorLabel);

		/* set initial values */
		dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
		Calendar now = Calendar.getInstance();
		String today = dateFormat.format(now.getTime());
		resultDateLabel.setText(today);
		baseDateInput.setText(today);
		operationLabel.setText(operation.display);

		/* bind event listeners */
		Runnable dateSelector = new DateSelector(getActivity(), baseDateInput, dateFormat);
		ViewEventUtils.registerOnFocusListener(baseDateInput, dateSelector);
		ViewEventUtils.registerOnClickListener(baseDateInput, dateSelector);

		calcButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				calculate();
			}
		});
		sendButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				send();
			}
		});
    }
	
	private void calculate() {
		Date baseDate = ViewParseUtils.parseDate(baseDateInput, dateFormat).getTime();
		int days = ViewParseUtils.parseInt(daysInput) * operation.signBase;
		Date resultDate = dateService.plus(baseDate, days);
		resultDateLabel.setText(dateFormat.format(resultDate));
	}
	
	private void send() {
		Calendar eventTime = ViewParseUtils.parseDate(baseDateInput, dateFormat);
		
		Intent intent = new Intent(Intent.ACTION_INSERT);
		intent.setData(CalendarContract.Events.CONTENT_URI);
		intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, eventTime.getTimeInMillis());
		intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
		intent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, eventTime.getTimeInMillis()+60*60*1000);
		startActivity(intent);
	}	
}
