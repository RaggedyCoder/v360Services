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
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		System.err.println(projectLabour);
		try {
			String json = gson.toJson(projectLabour);
			System.out.println(json);
			json = json.replaceAll(",", "`");
			return json;
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
			return Long.toString(projectLabour.getId());
		}
	}

	@Override
	public ProjectLabour parse(String json, Locale locale) throws ParseException {
		ProjectLabour projectLabour = new ProjectLabour();
		json = json.replaceAll("`", ",");
		if (json.isEmpty()) {
			return projectLabour;
		}
		try {
			Gson gson = new GsonBuilder().create();
			projectLabour = gson.fromJson(json, ProjectLabour.class);
			System.err.println(projectLabour);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
			projectLabour = new ProjectLabour();
			projectLabour.setId(Long.parseLong(json));
		}
		return projectLabour;
	}

}
