package kr.or.ddit.mvc.annotation;

/**
 *  immutable 객체로 정의함
 *  @URIMapping 과 1:1 관계
 */
public class URIMappingCondition {
	private String url;
	private HttpMethod method;
	
	public URIMappingCondition(String url, HttpMethod method) {
		super();
		this.url = url;
		this.method = method;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((method == null) ? 0 : method.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
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
		URIMappingCondition other = (URIMappingCondition) obj;
		if (method != other.method)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
	public String getUrl() {
		return url;
	}
	
	public HttpMethod getMethod() {
		return method;
	}

	@Override
	public String toString() {
		return String.format("%s [%s]", url, method);
	}
}