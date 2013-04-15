package helloworld.android.resttemplate.bookmarker.service;

import helloworld.android.resttemplate.bookmarker.service.NaverSearchHttpMessageConverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import android.os.Build;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;
import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Item;

@RunWith(RobolectricTestRunner.class)
public class SearchServiceTest {
	
	@Before
	public void setUp(){
        Robolectric.Reflection.setFinalStaticField(Build.VERSION.class, "SDK_INT", Build.VERSION_CODES.JELLY_BEAN);
	}
	
	@Test
	public void validDate(){

		// given
		RestTemplate apiClient = createRestClient();

		Map<String, String> params = new HashMap<String, String>();
		String url = "http://openapi.naver.com/search?key={key}&target={target}&query={query}&start={start}&display={display}";
		params.put("key", "daefad88cde80a2cba616e4ad23bdd11");
		params.put("target", "news");
		params.put("query", "네이버");
		params.put("start", "1");
		params.put("display", "15");

		// when
		Channel channel = apiClient.getForObject(url, Channel.class, params);

		// then
		@SuppressWarnings("unchecked")
		List<Item> items = channel.getItems();
		for (Item page : items) {
			System.out.printf("제목: %s \n", page.getTitle());
			System.out.printf("주소: %s \n", page.getLink());
			System.out.printf("요약: %s \n", page.getDescription().getValue());
			System.out.println();
		}
		System.out.println(items.size());
	}

	private RestTemplate createRestClient() {
		RestTemplate apiClient = new RestTemplate();
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new NaverSearchHttpMessageConverter());
		apiClient.setMessageConverters(converters);
		return apiClient;
	}

}
