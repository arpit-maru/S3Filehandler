
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

public class DeviceType {

	@JsonProperty("id")
	public String id;
	@JsonProperty("application_id")
	public String applicationId;
	@JsonProperty("category")
	public String category;
	@JsonProperty("codec")
	public String codec;
	@JsonProperty("data_type")
	public String dataType;
	@JsonProperty("description")
	public String description;
	@JsonProperty("manufacturer")
	public String manufacturer;
	@JsonProperty("model")
	public String model;
	@JsonProperty("name")
	public String name;
	@JsonProperty("parent_constraint")
	public String parentConstraint;
	@JsonProperty("proxy_handler")
	public String proxyHandler;
	@JsonProperty("subcategory")
	public String subcategory;
	@JsonProperty("transport_protocol")
	public String transportProtocol;
	@JsonProperty("version")
	public String version;
	@JsonProperty("created_at")
	public String createdAt;
	@JsonProperty("updated_at")
	public String updatedAt;
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
