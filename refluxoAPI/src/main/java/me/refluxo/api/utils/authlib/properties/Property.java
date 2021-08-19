package me.refluxo.api.utils.authlib.properties;

public class Property {
	private final String name;
	private final String value;
	private final String signature;

	public Property(String value, String name) {
		this(value, name, null);
	}

	public Property(String name, String value, String signature) {
		this.name = name;
		this.value = value;
		this.signature = signature;
	}

	public String getName() {
		return this.name;
	}

	public String getValue() {
		return this.value;
	}

	public String getSignature() {
		return this.signature;
	}

	public boolean hasSignature() {
		return (this.signature != null);
	}
}
