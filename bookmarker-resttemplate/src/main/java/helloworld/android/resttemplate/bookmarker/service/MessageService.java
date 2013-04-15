package helloworld.android.resttemplate.bookmarker.service;

import android.util.Log;


public class MessageService {
	private static final String TAG = "MesssageService";
	public String hello(String name) {
		if (name == null) {
			return null;
		}
		String message = "hello " + name;
		Log.i(TAG, message);
		return message;
	}
}
