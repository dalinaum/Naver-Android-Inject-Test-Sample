package helloworld.android.resttemplate.bookmarker.service;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import helloworld.android.resttemplate.bookmarker.service.MessageService;

import java.text.ParseException;

import org.junit.Test;


public class MessageServiceTest {
	
	MessageService service = new  MessageService();
	
	@Test
	public void helloWhenNull() throws ParseException {
		String message = service.hello(null);
		assertThat(message,is(nullValue()));
	}
	
	
	@Test
	public void helloWhenEmpty() throws ParseException {
		String message = service.hello("");
		assertThat(message,is("hello "));
	}
	
	@Test
	public void helloWithName() throws ParseException {
		String message = service.hello("cart");
		assertThat(message,is("hello cart"));
	}

}
