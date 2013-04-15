package helloworld.android.resttemplate.bookmarker.service;

import org.springframework.http.MediaType;
import org.springframework.http.converter.feed.AbstractWireFeedHttpMessageConverter;

import com.google.code.rome.android.repackaged.com.sun.syndication.feed.rss.Channel;

public class NaverSearchHttpMessageConverter extends AbstractWireFeedHttpMessageConverter<Channel> {

	public NaverSearchHttpMessageConverter() {
		super(MediaType.TEXT_XML);
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return Channel.class.isAssignableFrom(clazz);
	}
}