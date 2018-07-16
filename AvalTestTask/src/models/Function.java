package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Function extends BasicModel implements Serializable {

	private static final long serialVersionUID = 5649588387005983257L;
	
	private GroupFunction group;
	private String name;
	private String description;
	private LocalDateTime cTime;

	public Function(Long id, GroupFunction group, String name, String description, LocalDateTime cTime) {
		this.setId(id);
		this.group = group;
		this.name = name;
		this.description = description;
		this.cTime = cTime;
	}

	public Function(String key, GroupFunction group, String name, String description, LocalDateTime cTime) {
		this.setKey(key);
		this.group = group;
		this.name = name;
		this.description = description;
		this.cTime = cTime;
	}

	public GroupFunction getGroup() {
		return group;
	}

	public void setGroup(GroupFunction group) {
		this.group = group;
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

}
