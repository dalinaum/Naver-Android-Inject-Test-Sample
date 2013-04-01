package helloworld.android.droidparts.datecalculator;

import java.util.Arrays;

import org.droidparts.fragment.support.ListFragment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class OperationListFragment extends ListFragment {
	private static final String TAG = "OperationListFragment";
	private static final String SAVED_POSITION_KEY = "position";
	private ItemEventListener itemListener = dummyListener;
	private int activatedPosition = ListView.INVALID_POSITION;

	public interface ItemEventListener {
		public void onItemSelected(int position);
	}

	private static ItemEventListener dummyListener = new ItemEventListener() {
		@Override
		public void onItemSelected(int position) {
		}
	};

	public OperationListFragment() {
	}

	@Override
	public void onCreate(Bundle savedState) {
		super.onCreate(savedState);

		String[] menuNames = getResources().getStringArray(R.array.operationMenus);
		setListAdapter(new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_activated_1,
				android.R.id.text1, Arrays.asList(menuNames)));
	}

	@Override
	public void onViewCreated(View view, Bundle savedState) {
		super.onViewCreated(view, savedState);
		Log.d(TAG,"onViewCreated");		

		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);		
		
		if (savedState != null && savedState.containsKey(SAVED_POSITION_KEY)) {
			setActivatedPosition(savedState.getInt(SAVED_POSITION_KEY));
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		Log.d(TAG,"onAttach");		
		
		if (!(activity instanceof ItemEventListener)) {
			throw new IllegalStateException(
					"Activity must implement OperationListFragment.ItemEventListener");
		}
		this.itemListener = (ItemEventListener) activity;
	}

	@Override
	public void onDetach() {
		super.onDetach();
		Log.d(TAG,"onDetach");		
		this.itemListener = dummyListener;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id) {
		this.itemListener.onItemSelected(position);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(TAG,"onSaveInstanceState");		

		if (this.activatedPosition != ListView.INVALID_POSITION) {
			outState.putInt(SAVED_POSITION_KEY, this.activatedPosition);
		}
	}

	private void setActivatedPosition(int position) {
		if (position == ListView.INVALID_POSITION) {
			getListView().setItemChecked(this.activatedPosition, false);
		} else {
			getListView().setItemChecked(position, true);
		}
		this.activatedPosition = position;
	}
}
