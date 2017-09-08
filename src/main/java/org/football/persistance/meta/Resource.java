package org.football.persistance.meta;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * client-side mirror to the Spring HATEOAS Resource type so I don't need to
 * link to all of Spring HATEOAS.
 */

public class Resource {
	
	@JsonProperty("_links")
	private Map<String, Link> links = new HashMap<>();

	@Transient
	public Map<String, Link> getLinks() {
		return links;
	}

	public void setLinks(Map<String, Link> links) {
		this.links = links;
	}
}
