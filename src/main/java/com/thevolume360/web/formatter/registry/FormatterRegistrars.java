package com.thevolume360.web.formatter.registry;

import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

import com.thevolume360.web.formatter.ClientFormatter;
import com.thevolume360.web.formatter.LabourFormatter;
import com.thevolume360.web.formatter.WageTypeFormatter;

public class FormatterRegistrars implements FormatterRegistrar {

	@Override
	public void registerFormatters(FormatterRegistry registry) {
		registry.addFormatter(new LabourFormatter());
		registry.addFormatter(new ClientFormatter());
		registry.addFormatter(new WageTypeFormatter());
	}

}
