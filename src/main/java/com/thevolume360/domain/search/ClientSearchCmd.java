package com.thevolume360.domain.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.thevolume360.domain.enums.ClientType;

public class ClientSearchCmd {

	static final Logger LOG = LoggerFactory.getLogger(ClientSearchCmd.class);

	private String name;

	private ClientType clientType;

	public ClientSearchCmd() {
		this(null, null);
	}

	public ClientSearchCmd(String name, ClientType clientType) {
		super();
		this.name = name;
		this.clientType = clientType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ClientType getClientType() {
		return clientType;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType;
	}

	@Override
	public String toString() {
		return "ClientSearchCmd [name=" + name + ", clientType=" + clientType + "]";
	}

}
