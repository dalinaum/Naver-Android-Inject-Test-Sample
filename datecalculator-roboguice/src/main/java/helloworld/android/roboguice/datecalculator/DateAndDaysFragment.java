package helloworld.android.roboguice.datecalculator;

import helloworld.android.roboguice.datecalculator.service.DateService;
import helloworld.android.util.ViewEventUtils;
import helloworld.android.util.ViewParseUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.inject.Inject;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DateAndDaysFragment extends RoboFragment {
	private static final String TAG = "DateAndDaysOperationFragment";
	private Operation operation;
	@Inject DateService dateService;

	@InjectView(R.id.baseDateInput) EditText baseDateInput;
	@InjectView(R.id.resultDateLabel) TextView resultDateLabel;
	@InjectView(R.id.daysInput) EditText daysInput;
	@InjectView(R.id.calcaulateButton) Button calcaulateButton;
	@InjectView(R.id.sendButton) Button sendButton;
	@InjectView(R.id.operatorLabel) TextView operationLabel;
	DateFormat dateFormat;
	
	public DateAndDaysFragment setOperation(Operation operation) {
		this.operation = operation;
		return this;
	}

   @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

		/* set initial values */
		operationLabel.setText(operation.display);
		
		dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
		Calendar now = Calendar.getInstance();
		String today = dateFormat.format(now.getTime());
		resultDateLabel.setText(today);
		baseDateInput.setText(today);

		/* bind event listeners */
		Runnable dateSelector = new DateSelector(getActivity(), baseDateInput, dateFormat);
		ViewEventUtils.registerOnFocusListener(baseDateInput, dateSelector);
		ViewEventUtils.registerOnClickListener(baseDateInput, dateSelector);
		
		calcaulateButton.setOnClickListener(new OnClickListener() {
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
	   
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		Log.d(TAG,"on create view");
		super.onCreateView(inflater, container, savedState);
		return inflater.inflate(R.layout.fragment_date_and_day, container, false);
		
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

