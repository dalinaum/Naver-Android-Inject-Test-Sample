package helloworld.android.androidannotations.datecalculator;

import helloworld.android.androidannotations.datecalculator.CalculatorActivity;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.os.Bundle;

@RunWith(RobolectricTestRunner.class)
public class CalculatorActivityTest {
	Bundle savedStated = new Bundle();
	
	CalculatorActivity activity = new CalculatorActivity();
	
	@Test
	public void printMenu(){
		//activity.onCreate(savedStated);
		// 아직 fragment에 대한 test가 잘 지원되지 않는다.
		// 2.0-alpha-3q버전에서 지원된다고 함.
		// https://groups.google.com/forum/#!topic/robolectric/A1oJ_jtIRSI 참조
		String[] menus = activity.getResources().getStringArray(R.array.operationMenus);
		System.out.println(Arrays.asList(menus));
	}
}
