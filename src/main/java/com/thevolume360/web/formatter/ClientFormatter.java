package com.thevolume360.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thevolume360.domain.Client;

public class ClientFormatter implements Formatter<Client> {

	@Override
	public String print(Client client, Locale locale) {
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
		System.err.println(client);
		// client.setCreatedDate(null);
		// client.setLastModifiedDate(null);
		// client.setCreatedBy(null);
		// client.setLastModifiedBy(null);
		try {
			String json = gson.toJson(client);
			System.out.println(json);
			json = json.replaceAll(",", "`");
			return json;
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " "
					+ e.getMessage());
			return Long.toString(client.getId());
		}

	}

	@Override
	public Client parse(String id, Locale locale) throws ParseException {
		Client client;
		System.err.println("json before- " + id);
		id = id.replaceAll("`", ",");
		System.err.println("json after- " + id);
		try {
			Gson gson = new GsonBuilder().create();
			client = gson.fromJson(id, Client.class);
			System.err.println(client);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " "
					+ e.getMessage());
			client = new Client();
			client.setId(Long.parseLong(id));
		}
		return client;
	}
}
