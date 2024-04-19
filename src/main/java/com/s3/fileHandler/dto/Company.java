
package com.s3.fileHandler.dto;

import java.util.LinkedHashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Company {

	@JsonProperty("id")
	public Integer id;
	@JsonProperty("address")
	public String address;
	@JsonProperty("city")
	public String city;
	@JsonProperty("country")
	public String country;
	@JsonProperty("created_at")
	public String createdAt;
	@JsonProperty("industry")
	public String industry;
	@JsonProperty("latitude")
	public Float latitude;
	@JsonProperty("longitude")
	public Float longitude;
	@JsonProperty("name")
	public String name;
	@JsonProperty("state")
	public String state;
	@JsonProperty("status")
	public Integer status;
	@JsonProperty("timezone")
	public String timezone;
	@JsonProperty("updated_at")
	public String updatedAt;
	@JsonProperty("user_id")
	public String userId;
	@JsonProperty("zip")
	public String zip;
	@JsonProperty("external_id")
	public String externalId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

}
