package com.thevolume360.web.formatter;

import static com.thevolume360.web.ApplicationContextProvider.getObjectMapper;
import static com.thevolume360.web.ApplicationContextProvider.getObjectWriter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thevolume360.domain.WageType;

public class WageTypeFormatter implements Formatter<WageType> {

	@Override
	public String print(WageType wageType, Locale locale) {
		String json = null;
		try {
			json = getObjectWriter().writeValueAsString(wageType);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = json.replaceAll(",", "`");
		return json;

	}

	@Override
	public WageType parse(String json, Locale locale) throws ParseException {
		WageType wageType = null;
		json = json.replaceAll("`", ",");
		try {
			wageType = getObjectMapper().readValue(json, WageType.class);
			System.err.println(wageType);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
		}
		return wageType;
	}

}
