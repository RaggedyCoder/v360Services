package com.thevolume360.web.formatter;

import static com.thevolume360.web.ApplicationContextProvider.getObjectMapper;
import static com.thevolume360.web.ApplicationContextProvider.getObjectWriter;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thevolume360.domain.Client;

public class ClientFormatter implements Formatter<Client> {

	@Override
	public String print(Client client, Locale locale) {
		String json = null;
		try {
			json = getObjectWriter().writeValueAsString(client);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		json = json.replaceAll(",", "`");
		return json;

	}

	@Override
	public Client parse(String json, Locale locale) throws ParseException {
		Client client = new Client();
		json = json.replaceAll("`", ",");
		if (json.isEmpty()) {
			return client;
		}
		try {
			client = getObjectMapper().readValue(json, Client.class);
			System.err.println(client);
		} catch (Exception e) {
			System.out.println(e.getClass().getSimpleName() + " " + e.getMessage());
		}
		return client;
	}
}
