package com.te.blogmanagement.enums;

public enum Status {
	ACTIVE("ACTIVE"), INACTIVE("INACTIVE");

	private final String status;

	private Status(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
