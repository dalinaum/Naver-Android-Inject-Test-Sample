package helloworld.android.transfuse.datecalculator;

import javax.inject.Inject;

import org.androidtransfuse.annotations.Activity;
import org.androidtransfuse.annotations.Intent;
import org.androidtransfuse.annotations.IntentFilter;
import org.androidtransfuse.annotations.IntentType;
import org.androidtransfuse.annotations.Layout;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.ListView;

@Activity(label = "@string/app_name")
@Layout(R.layout.activity_operation_menu)
@IntentFilter({
    @Intent(type = IntentType.ACTION, name = android.content.Intent.ACTION_MAIN),
    @Intent(type = IntentType.CATEGORY, name = android.content.Intent.CATEGORY_LAUNCHER)
})
public class CalculatorActivity implements OperationListFragment.ItemEventListener {
	private static final String TAG = "CalculatorActivity";
	private Fragment[] operFrags;
	private int currentPosition = ListView.INVALID_POSITION;
	@Inject
	FragmentManager fragManager;

	
	protected void onCreate(Bundle savedState) {
		
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
			
			FragmentTransaction tx = fragManager.beginTransaction();
			tx.replace(R.id.operation_detail_container, operFrags[position]);
			tx.commit();
	}
}
