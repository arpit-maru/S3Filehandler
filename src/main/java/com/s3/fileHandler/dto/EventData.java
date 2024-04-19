
package com.s3.fileHandler.dto;

import java.util.LinkedHashMap;
import java.util.List;
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

public class EventData {

	@JsonProperty("correlation_id")
	public String correlationId;
	@JsonProperty("device_id")
	public String deviceId;
	@JsonProperty("user_id")
	public String userId;
	@JsonProperty("payload")
	public List<Payload> payload;
	@JsonProperty("gateways")
	public List<Gateway> gateways;
	@JsonProperty("fcnt")
	public Integer fcnt;
	@JsonProperty("fport")
	public Integer fport;
	@JsonProperty("raw_payload")
	public String rawPayload;
	@JsonProperty("raw_format")
	public String rawFormat;
	@JsonProperty("client_id")
	public String clientId;
	@JsonProperty("hardware_id")
	public String hardwareId;
	@JsonProperty("timestamp")
	public Long timestamp;
	@JsonProperty("application_id")
	public String applicationId;
	@JsonProperty("device_type_id")
	public String deviceTypeId;
	@JsonProperty("lora_datarate")
	public Integer loraDatarate;
	@JsonProperty("freq")
	public Float freq;
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
