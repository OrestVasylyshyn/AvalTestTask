package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Param extends BasicModel implements Serializable {

	private static final long serialVersionUID = 6860848942309269940L;
	
	private Function function;
	private String name;
	private String description;
	private LocalDateTime cTime;

	public Param(Long id, Function function, String name, String description, LocalDateTime cTime) {
		this.setId(id);
		this.function = function;
		this.name = name;
		this.description = description;
		this.cTime = cTime;
	}

	public Param(String key, Function function, String name, String description, LocalDateTime cTime) {
		this.setKey(key);
		this.function = function;
		this.name = name;
		this.description = description;
		this.cTime = cTime;
	}

	public Function getFunction() {
		return function;
	}

	public void setFunction(Function function) {
		this.function = function;
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
