package helloworld.android.resttemplate.bookmarker.ui;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.util.RobolectricBackgroundExecutorService;

import android.os.Build;
import android.os.Bundle;

import com.googlecode.androidannotations.api.BackgroundExecutor;

@RunWith(RobolectricTestRunner.class)
public class BookActivityTest<MenuActivity_> {
	
	Bundle state = new Bundle();
	
	@BeforeClass
	public static void initTestEnvirment(){
        Robolectric.Reflection.setFinalStaticField(Build.VERSION.class, "SDK_INT", Build.VERSION_CODES.JELLY_BEAN);
        
        BackgroundExecutor.setExecutor(new RobolectricBackgroundExecutorService());
		
        System.setProperty("robolectric.logging","stdout");
		ShadowLog.stream = System.out;
		Robolectric.bindShadowClass(ShadowLog.class);
	}
	
	@Test
	public void testLoadMessage() throws InterruptedException{
		BookActivity_ activity = new BookActivity_();
		activity.onCreate(state);
		
		activity.queryInput.setText("김주찬");
		activity.searchButton.performClick();
		
		System.out.println("result:" + activity.bookDisplay.getCount());
	}
}
