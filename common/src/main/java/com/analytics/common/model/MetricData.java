package com.analytics.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MetricData {
    
    @JsonProperty
    private String id;
    
    @JsonProperty
    @NotNull
    private String name;
    
    @JsonProperty
    @NotNull
    private Double value;
    
    @JsonProperty
    private Date timestamp;
    
    @JsonProperty
    private Map<String, String> tags;
    
    @JsonProperty
    private String source;
    
    @JsonProperty
    private String unit;
    
    @JsonProperty
    private String description;

    public MetricData() {
        this.timestamp = new Date();
        this.tags = new HashMap<>();
    }

    public MetricData(String name, Double value) {
        this();
        this.name = name;
        this.value = value;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getValue() { return value; }
    public void setValue(Double value) { this.value = value; }
    
    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
    
    public Map<String, String> getTags() { return tags; }
    public void setTags(Map<String, String> tags) { this.tags = tags; }
    
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    
    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}