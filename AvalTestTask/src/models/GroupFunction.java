package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class GroupFunction extends BasicModel implements Serializable {
	
	
	private static final long serialVersionUID = 7142404779555413725L;
	
	private String name;
	private String description;
	private LocalDateTime cTime;
	private Integer functionsCount;

	public GroupFunction(Long id, String name, String description, Integer functionsCount) {
		this.setId(id);
		this.name = name;
		this.description = description;
		this.functionsCount = functionsCount;
	}
	
	public GroupFunction(String key, String name, String description, Integer functionsCount) {
		this.setKey(key);
		this.name = name;
		this.description = description;
		this.functionsCount = functionsCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getcTime() {
		return cTime;
	}

	public void setcTime(LocalDateTime cTime) {
		this.cTime = cTime;
	}

	public Integer getFunctionsCount() {
		return functionsCount;
	}

	public void setFunctionsCount(Integer functionsCount) {
		this.functionsCount = functionsCount;
	}

}
