package helloworld.android.roboguice.datecalculator;

import roboguice.activity.RoboFragmentActivity;
import roboguice.inject.ContentView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ListView;

@ContentView(R.layout.activity_operation_menu)
public class CalculatorActivity extends RoboFragmentActivity implements OperationListFragment.ItemEventListener {
	private static final String TAG = "CalculatorActivity";
	private Fragment[] operFrags;
	private int currentPosition = ListView.INVALID_POSITION;
	
	@Override
	protected void onCreate(Bundle savedState) {
		super.onCreate(savedState);
		operFrags = new Fragment[]{ new DateAndDaysFragment().setOperation(Operation.PLUS), 
									new DateAndDaysFragment().setOperation(Operation.MINUS), 
									new DateMinusDateFragment()};
	}

	@Override
	public void onItemSelected(int position) {
			if (this.currentPosition == position) {
				return;
			}
		
			this.currentPosition = position;
			Log.d(TAG, "fragment selected : " + position);
			
			FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
			tx.replace(R.id.operation_detail_container, operFrags[position]);
			tx.commit();
	}
}
