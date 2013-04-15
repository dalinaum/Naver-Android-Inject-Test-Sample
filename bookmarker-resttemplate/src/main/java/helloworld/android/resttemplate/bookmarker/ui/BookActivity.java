package helloworld.android.resttemplate.bookmarker.ui;

import helloworld.android.resttemplate.bookmarker.R;
import helloworld.android.resttemplate.bookmarker.service.NaverSearchService;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Item;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.InstanceState;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.res.StringRes;
import com.googlecode.androidannotations.annotations.rest.RestService;

@EActivity(R.layout.activity_book)
public class BookActivity extends Activity {
	private static final String TARGET = "book"; 
	
	@ViewById EditText queryInput;
	@ViewById ImageButton searchButton;
	@ViewById TextView messageLabel;
	@ViewById ListView bookDisplay;

	@RestService NaverSearchService searchService;
	@StringRes String apiKey;
	
	@InstanceState ArrayList<String> bookTitles; 

	@AfterViews 
	void restoreSearchResult(){
		if (bookTitles != null) {
			updateSearched();
		}
	}
			
	@Click
	void searchButton(){
		String query = queryInput.getText().toString();
		loadSearchResult(query);
	}

	@Background
	void loadSearchResult(String query) {
		Channel channel = searchService.search(apiKey, TARGET, query, 1, 10);
		@SuppressWarnings("unchecked")
		List<Item> searchedItems = channel.getItems();
		
		ArrayList<String> titles = new ArrayList<String>(searchedItems.size());
		
		for(Item item : searchedItems) {
			titles.add(item.getTitle());
		}
		this.bookTitles = titles; 
		updateSearched();
	}
	
	@UiThread
	void updateSearched() {
		ArrayAdapter<String> adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, bookTitles);
		bookDisplay.setAdapter(adaptor);
	}
}
