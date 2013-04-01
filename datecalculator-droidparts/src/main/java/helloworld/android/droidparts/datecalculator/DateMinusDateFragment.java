package helloworld.android.droidparts.datecalculator;

import helloworld.android.droidparts.datecalculator.service.DateService;
import helloworld.android.util.ViewEventUtils;
import helloworld.android.util.ViewParseUtils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import org.droidparts.annotation.inject.InjectView;
import org.droidparts.fragment.support.Fragment;

import android.os.Bundle;
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

	@InjectView(id=R.id.firstDateInput) EditText firstDateInput;
	@InjectView(id=R.id.secondDateInput) EditText secondDateInput;
	@InjectView(id=R.id.resultDaysLabel) TextView resultDaysLabel; 
	@InjectView(id=R.id.calcaulateButton) Button calcaulateButton;
	DateFormat dateFormat;

	@Override
	public View onCreateView(Bundle savedState, LayoutInflater inflater, ViewGroup container) {
		Log.d(TAG, "onCreateView");
		return inflater.inflate(R.layout.fragment_date_minus_date, container, false);
	}
	
    public void onViewCreated(View view, Bundle savedInstanceState) {

		/* set initial values */
    	dateFormat = android.text.format.DateFormat.getDateFormat(getActivity());
		Calendar now = Calendar.getInstance();
		String today = dateFormat.format(now.getTime());
		firstDateInput.setText(today);
		secondDateInput.setText(today);

		/* bind event listener */
		registerInputEventListener(firstDateInput, dateFormat);
		registerInputEventListener(secondDateInput, dateFormat);
		
		calcaulateButton.setOnClickListener(new OnClickListener() {
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
