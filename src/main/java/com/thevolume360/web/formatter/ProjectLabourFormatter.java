package com.thevolume360.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thevolume360.domain.ProjectLabour;

public class ProjectLabourFormatter implements Formatter<ProjectLabour> {

	@Override
	public String print(ProjectLabour projectLabour, Locale locale) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		System.err.println(projectLabour);
		try {
			String json = gson.toJson(projectLabour);
			System.out.println(json);
			json = json.replaceAll(",", "`");
			return json;
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " "
					+ e.getMessage());
			return Long.toString(projectLabour.getId());
		}
	}

	@Override
	public ProjectLabour parse(String id, Locale locale) throws ParseException {
		ProjectLabour projectLabour;
		System.err.println("json before- " + id);
		id = id.replaceAll("`", ",");
		System.err.println("json after- " + id);
		try {
			Gson gson = new GsonBuilder().create();
			projectLabour = gson.fromJson(id, ProjectLabour.class);
			System.err.println(projectLabour);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " "
					+ e.getMessage());
			projectLabour = new ProjectLabour();
			projectLabour.setId(Long.parseLong(id));
		}
		return projectLabour;
	}

}
