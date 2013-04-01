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
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DateMinusDateFragment extends RoboFragment {
	private static final String TAG = "DateMinusDateFragment";

	@Inject DateService dateService;
	
	@InjectView(R.id.firstDateInput) EditText firstDateInput;
	@InjectView(R.id.secondDateInput) EditText secondDateInput;
	@InjectView(R.id.resultDaysLabel) TextView resultDaysLabel; 
	@InjectView(R.id.calcaulateButton) Button calcaulateButton;
	DateFormat dateFormat;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		View rootView = inflater.inflate(R.layout.fragment_date_minus_date, container, false);
		Log.d(TAG, "onCreateView");
		return rootView;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedState) {
	        super.onViewCreated(view, savedState);
	        /* set initial values */
			dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
			Calendar now = Calendar.getInstance();
			String today = dateFormat.format(now.getTime());
			firstDateInput.setText(today);
			secondDateInput.setText(today);
			
			/* bind event listeners */
			registerInputEventListener(firstDateInput, dateFormat);
			registerInputEventListener(secondDateInput, dateFormat);
			calcaulateButton.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					calculate();
				}
			});

	}

	private void registerInputEventListener(EditText baseDateInput, DateFormat dateFormat) {
		Runnable dateSelector = new DateSelector(getActivity(), baseDateInput, dateFormat);
		ViewEventUtils.registerOnClickListener(baseDateInput, dateSelector);
		ViewEventUtils.registerOnFocusListener(baseDateInput, dateSelector);
	}

	private void calculate() {
		Date firstDate = ViewParseUtils.parseDate(firstDateInput, dateFormat).getTime();
		Date secondDate = ViewParseUtils.parseDate(secondDateInput, dateFormat).getTime();
		int resultDays = dateService.minus(firstDate, secondDate);
		resultDaysLabel.setText(String.valueOf(resultDays));
	}
}
