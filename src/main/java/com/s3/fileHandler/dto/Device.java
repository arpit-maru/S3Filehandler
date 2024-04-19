
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

public class Device {

	@JsonProperty("id")
	public Integer id;
	@JsonProperty("thing_name")
	public String thingName;
	@JsonProperty("sensor_use")
	public String sensorUse;
	@JsonProperty("created_at")
	public String createdAt;
	@JsonProperty("updated_at")
	public String updatedAt;
	@JsonProperty("status")
	public Integer status;
	@JsonProperty("external_id")
	public String externalId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

}
