package me.refluxo.api.utils.authlib;

import me.refluxo.api.utils.authlib.properties.PropertyMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

public class GameProfile {
	private final UUID id;
	private final String name;
	private final PropertyMap properties = new PropertyMap();

	public GameProfile(UUID id, String name) {
		if ((id == null) && (StringUtils.isBlank(name)))
			throw new IllegalArgumentException(
					"Name and ID cannot both be blank");

		this.id = id;
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public PropertyMap getProperties() {
		return this.properties;
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if ((o == null) || (super.getClass() != o.getClass()))
			return false;

		GameProfile that = (GameProfile) o;

		if(this.id != null && this.name != null) {
			return this.id.equals(that.id) && this.name.equals(that.name);
		}

		if(this.id != null) {
			return this.id.equals(that.id);
		}

		if(this.name != null) {
			return this.name.equals(that.name);
		}

		return false;
	}

	public int hashCode() {
		int result = (this.id != null) ? this.id.hashCode() : 0;
		result = 31 * result + ((this.name != null) ? this.name.hashCode() : 0);
		return result;
	}

	public String toString() {
		return new ToStringBuilder(this).append("id", this.id)
				.append("name", this.name)
				.append("properties", this.properties)
				.append("legacy", false).toString();
	}

}
