package helloworld.android.util;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;

public class ViewEventUtils {
	
	public static void registerOnClickListener(View viewToRegister, final Runnable function) {
		viewToRegister.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				function.run();
			}
		});
	}

	public static void registerOnFocusListener(View viewToRegister, final Runnable function) {
		viewToRegister.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View view, boolean hasFocus) {
				if(!hasFocus) {
					return;
				}
				function.run();
			}
		});
	}

}
