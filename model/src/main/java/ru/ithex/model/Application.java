package ru.ithex.model;

import static ru.ithex.model.TransformData.transformTimestamp;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Application {

	public Application() {
		super();
	}

	protected Integer applicationID;
	protected Date appTimestamp;

	protected Person manager;
	protected AppData appData;
	protected RequestedInfo requestedInfo;
	protected Set<CallResult> callResults;
	protected boolean fake;

	public AppData getAppData() {
		return appData;
	}

	public void setAppData(AppData value) {
		this.appData = value;
	}

	public RequestedInfo getRequestedInfo() {
		return requestedInfo;
	}

	public void setRequestedInfo(RequestedInfo value) {
		this.requestedInfo = value;
	}

	public Set<CallResult> getCallResults() {
		if (callResults == null) {
			callResults = new HashSet<CallResult>();
		}
		return this.callResults;
	}

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer value) {
		this.applicationID = value;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person value) {
		this.manager = value;
	}

	public Date getAppTimestamp() {
		return appTimestamp;
	}

	public void setAppTimestamp(Date value) {
		this.appTimestamp = value;
	}

	public void setCallResults(Set<CallResult> callResults) {
		this.callResults = callResults;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("{");
		try {
			boolean hasFirstProperty = false;
			Field[] fields = this.getClass().getDeclaredFields();
			for (int i = 0; i < fields.length - 1; i++) {
				if (fields[i].get(this) != null) {
					if (fields[i].getType().equals(String.class)) {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
								.append(fields[i].get(this).toString()).append("\"");
						hasFirstProperty = true;
					} else if (fields[i].getType().equals(Date.class)) {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": \"")
								.append(transformTimestamp((Date) fields[i].get(this)).toString()).append("\"");
						hasFirstProperty = true;
					} else if (fields[i].getType().equals(Set.class) || fields[i].getType().equals(List.class)) {
						if (((Collection) fields[i].get(this)).size() > 0) {
							if (hasFirstProperty)
								sb.append(",");
							sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
									.append(fields[i].get(this).toString());
							hasFirstProperty = true;
						}
					} else {
						if (hasFirstProperty)
							sb.append(",");
						sb.append("\"").append(fields[i].getName()).append("\"").append(": ")
								.append(fields[i].get(this).toString());
						hasFirstProperty = true;
					}
				}
			}
		} catch (Exception e) {
			sb.append("null");
		}
		return sb.append("}").toString();
	}
}