package com.thevolume360.web.formatter;

import static com.thevolume360.web.ApplicationContextProvider.getObjectMapper;
import static com.thevolume360.web.ApplicationContextProvider.getObjectWriter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thevolume360.domain.Labour;

public class LabourFormatter implements Formatter<Labour> {

	public LabourFormatter() {
	}

	@Override
	public String print(Labour labour, Locale locale) {	
		String json = null;
		try {
			json = getObjectWriter().writeValueAsString(labour);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = json.replaceAll(",", "`");
		return json;
	}

	@Override
	public Labour parse(String json, Locale locale) throws ParseException {
		Labour labour = null;
		json = json.replaceAll("`", ",");
		try {
			labour = getObjectMapper().readValue(json, Labour.class);
			System.err.println(labour);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
		}
		return labour;
	}
}
