package helloworld.android.droidparts.datecalculator;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import helloworld.android.droidparts.datecalculator.CalculatorActivity;
import helloworld.android.droidparts.datecalculator.R;
import java.util.Arrays;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

@RunWith(RobolectricTestRunner.class)
public class CalculatorActivityTest {
	Bundle savedStated = new Bundle();
	CalculatorActivity activity = new CalculatorActivity();
	int menuSize = 3;
	
	@Test
	public void countFragMents(){
		activity.onCreate(savedStated);
		assertThat(activity.operFrags.length, is(menuSize));
	}
	@Test
	public void checkMenuName(){
		String[] menuNames = activity.getResources().getStringArray(R.array.operationMenus);
		System.out.println(Arrays.asList(menuNames));
		assertThat(menuNames.length, is(menuSize));
	}
	
	@Test
	@Ignore
	public void selectItem(){
		activity.onItemSelected(1);
		View ooperationDetail = activity.findViewById(R.id.operation_detail_container);
		TextView operationLabel = (TextView)ooperationDetail.findViewById(R.id.operatorLabel);
		assertThat(operationLabel.toString(), is("+"));
	}
}
