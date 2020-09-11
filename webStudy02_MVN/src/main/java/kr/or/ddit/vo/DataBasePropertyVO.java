package kr.or.ddit.vo;

/**
 * Domain Layer : model data, 정보의 속성과 의미 부여 및 영역 제한
 * 
 */
public class DataBasePropertyVO {
	private String property_name;
	private String property_value;
	private String description;
	
	private DataBasePropertyVO() {
		super();
	}

	private DataBasePropertyVO(String property_name, String property_value, String description) {
		super();
		this.property_name = property_name;
		this.property_value = property_value;
		this.description = description;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getProperty_value() {
		return property_value;
	}

	public void setProperty_value(String property_value) {
		this.property_value = property_value;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "DataBasePropertyVO [property_name=" + property_name + ", property_value=" + property_value
				+ ", description=" + description + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((property_name == null) ? 0 : property_name.hashCode());
		result = prime * result + ((property_value == null) ? 0 : property_value.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DataBasePropertyVO other = (DataBasePropertyVO) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (property_name == null) {
			if (other.property_name != null)
				return false;
		} else if (!property_name.equals(other.property_name))
			return false;
		if (property_value == null) {
			if (other.property_value != null)
				return false;
		} else if (!property_value.equals(other.property_value))
			return false;
		return true;
	}
	
	public static class DataBasePropertyVOBuilder {
		private String property_name;
		private String property_value;
		private String description;
		
		public DataBasePropertyVOBuilder property_name(String property_name) {
			this.property_name = property_name;
			return this;
		}
		
		public DataBasePropertyVOBuilder property_value(String property_value) {
			this.property_value = property_value;
			return this;
		}
		
		public DataBasePropertyVOBuilder description(String description) {
			this.description = description;
			return this;
		}
		
		public DataBasePropertyVO build() {
			return new DataBasePropertyVO(property_name, property_value, description);
		}
	}
	
	public static DataBasePropertyVOBuilder getBuilder() {
		return new DataBasePropertyVOBuilder();
	}
}