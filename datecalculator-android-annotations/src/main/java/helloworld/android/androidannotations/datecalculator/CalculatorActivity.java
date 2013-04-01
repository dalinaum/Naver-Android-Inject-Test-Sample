package helloworld.android.androidannotations.datecalculator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ListView;

import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_operation_menu)
public class CalculatorActivity extends FragmentActivity implements OperationListFragment.ItemEventListener {
	private static final String TAG = "CalculatorActivity";
	private Fragment[] operFrags;
	private int currentPosition = ListView.INVALID_POSITION;
	
	@AfterViews
	protected void init() {
		operFrags = new Fragment[]{ new DateAndDaysFragment_().setOperation(Operation.PLUS), 
									new DateAndDaysFragment_().setOperation(Operation.MINUS), 
									new DateMinusDateFragment_()};
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
