package helloworld.android.resttemplate.bookmarker.service;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;
import com.googlecode.androidannotations.annotations.rest.Get;
import com.googlecode.androidannotations.annotations.rest.Rest;

@Rest(rootUrl ="http://openapi.naver.com", converters= NaverSearchHttpMessageConverter.class)
public interface NaverSearchService {
	@Get("/search?key={key}&target={target}&query={query}&start={start}&display={display}")
	Channel search(String key, String target, String query, int start, int display);
//	void setRestTemplate(RestTemplate restTemplate);
}
