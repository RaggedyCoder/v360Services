package com.thevolume360.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thevolume360.domain.Labour;

public class LabourFormatter implements Formatter<Labour> {

	@Override
	public String print(Labour labour, Locale locale) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		System.err.println(labour);
		// labour.setCreatedDate(null);
		// labour.setLastModifiedDate(null);
		// labour.setCreatedBy(null);
		// labour.setLastModifiedBy(null);
		try {
			String json = gson.toJson(labour);
			System.out.println(json);
			json = json.replaceAll(",", "`");
			return json;
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " "
					+ e.getMessage());
			return Long.toString(labour.getId());
		}

	}

	@Override
	public Labour parse(String id, Locale locale) throws ParseException {
		Labour labour;
		System.err.println("json before- " + id);
		id = id.replaceAll("`", ",");
		System.err.println("json after- " + id);
		try {
			Gson gson = new GsonBuilder().create();
			labour = gson.fromJson(id, Labour.class);
			System.err.println(labour);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " "
					+ e.getMessage());
			labour = new Labour();
			labour.setId(Long.parseLong(id));
		}
		return labour;
	}
}
