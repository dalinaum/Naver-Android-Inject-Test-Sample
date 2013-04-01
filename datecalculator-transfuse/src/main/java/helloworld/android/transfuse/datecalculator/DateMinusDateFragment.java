package helloworld.android.transfuse.datecalculator;

import helloworld.android.transfuse.datecalculator.service.DateService;
import helloworld.android.util.ViewEventUtils;
import helloworld.android.util.ViewParseUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DateMinusDateFragment extends Fragment {
	private static final String TAG = "DateMinusDateFragment";
	DateService service = new DateService();

	EditText firstDateInput;
	EditText secondDateInput; 
	DateFormat dateFormat;
	TextView resultDaysLabel;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedState) {
		Log.d(TAG, "onCreateView");
		return inflater.inflate(R.layout.fragment_date_minus_date, container, false);
	}
	
    public void onViewCreated(View view, Bundle savedInstanceState) {
		/* lookup views */
		firstDateInput = (EditText)view.findViewById(R.id.firstDateInput);
		secondDateInput = (EditText)view.findViewById(R.id.secondDateInput);
		resultDaysLabel = (TextView)view.findViewById(R.id.resultDaysLabel);
		Button calcButton = (Button)view.findViewById(R.id.calcaulateButton);

		/* set initial values */
		Calendar now = Calendar.getInstance();
		String today = dateFormat.format(now.getTime());
		firstDateInput.setText(today);
		secondDateInput.setText(today);
		dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());

		/* bind event listener */
		registerInputEventListener(firstDateInput, dateFormat);
		registerInputEventListener(secondDateInput, dateFormat);
		
		calcButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				calculate();
			}
		});
    }

	private void calculate() {
		Date firstDate = ViewParseUtils.parseDate(firstDateInput, dateFormat).getTime();
		Date secondDate = ViewParseUtils.parseDate(secondDateInput, dateFormat).getTime();
		int resultDays = service.minus(firstDate, secondDate);
		resultDaysLabel.setText(String.valueOf(resultDays));
	}

	private void registerInputEventListener(EditText baseDateInput, DateFormat dateFormat) {
		Runnable dateSelector = new DateSelector(getActivity(), baseDateInput, dateFormat);
		ViewEventUtils.registerOnClickListener(baseDateInput, dateSelector);
		ViewEventUtils.registerOnFocusListener(baseDateInput, dateSelector);
	}
}
