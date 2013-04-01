package helloworld.android.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;

import android.util.Log;
import android.widget.TextView;

public class ViewParseUtils {
	private static final String TAG = "ViewParseUtils";
	
	public static Calendar parseDate(TextView view, DateFormat dateFormat) {
		String dateString = view.getText().toString();

		Calendar inputDate = Calendar.getInstance();
		try {
			inputDate.setTime(dateFormat.parse(dateString));
		} catch (ParseException e) {
			Log.i(TAG,"fail to parse : " + dateString);
			inputDate.setTimeInMillis(System.currentTimeMillis());
			// set today
		}
		return inputDate;
	}
	
	public static int parseInt(TextView input) {
		String intString = input.getText().toString();
		try {
			return Integer.parseInt(intString);
		} catch (Exception e) {
			Log.i(TAG,"fail to parse : " + intString);
			
			return 0;
		}
	}
}
