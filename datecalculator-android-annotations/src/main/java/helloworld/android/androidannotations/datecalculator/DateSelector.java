package helloworld.android.androidannotations.datecalculator;

import helloworld.android.util.ViewParseUtils;

import java.text.DateFormat;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

public class DateSelector implements Runnable {
	private static final String TAG = "DateSelector";
	private Context context;
	private TextView dateView;
	private DateFormat dateFormat;
	private DatePickerDialog.OnDateSetListener dateListener;
	
	public DateSelector(Context context, final TextView dateView,final DateFormat dateFormat) {
		this.context = context;
		this.dateView = dateView;
		this.dateFormat = dateFormat;
		this.dateListener = new DatePickerDialog.OnDateSetListener(){
			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
				Calendar calendar = Calendar.getInstance();
			   	calendar.set(year, monthOfYear, dayOfMonth);
			   	dateView.setText(dateFormat.format(calendar.getTime()));				
			}
		};
		Log.d(TAG, "date format : "  + dateFormat);
	}
	
	@Override
	public void run() {
		Calendar previousInput = ViewParseUtils.parseDate(dateView, dateFormat);
		DatePickerDialog dialog = new DatePickerDialog(context,  dateListener,
											previousInput.get(Calendar.YEAR),
											previousInput.get(Calendar.MONTH), 
											previousInput.get(Calendar.DAY_OF_MONTH));
		dialog.show();				
	}
}


