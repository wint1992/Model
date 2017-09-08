package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToBoolean;
import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Address {

	public Address() {
		super();
	}

	protected Integer addressID;
	protected Integer addressType;
	protected String country;
	protected String regionType;
	protected String regionName;
	protected String districtType;
	protected String districtName;
	protected String microdistrict;
	protected String locationType;
	protected String cityType;
	protected String city;
	protected String streetType;
	protected String street;
	protected String house;
	protected String construction;
	protected String building;
	protected String apartment;
	protected String postIndex;
	protected Date registrationDate;
	protected Date startLivingDate;
	protected String gps;
	protected String fullAddress;
	protected String codeKLADR;
	protected Integer timeZone;
	protected String codeKLADE;
	protected String autonomyType;
	protected String autonomyName;
	protected String location;
	protected String intracityDistrict;
	protected Boolean isNotPhone;
	protected Boolean ownership;

	protected Phone phone;
	protected boolean fake;

	public Integer getAddressID() {
		return addressID;
	}

	public void setAddressID(Integer value) {
		this.addressID = value;
	}

	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer value) {
		this.addressType = value;
	}

	public void setAddressType(BigDecimal value) {
		this.addressType = bigDecimalToInteger(value);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String value) {
		this.country = value;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String value) {
		this.regionType = value;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String value) {
		this.regionName = value;
	}

	public String getDistrictType() {
		return districtType;
	}

	public void setDistrictType(String value) {
		this.districtType = value;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String value) {
		this.districtName = value;
	}

	public String getMicrodistrict() {
		return microdistrict;
	}

	public void setMicrodistrict(String value) {
		this.microdistrict = value;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String value) {
		this.locationType = value;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String value) {
		this.cityType = value;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String value) {
		this.city = value;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String value) {
		this.streetType = value;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String value) {
		this.street = value;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String value) {
		this.house = value;
	}

	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String value) {
		this.construction = value;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String value) {
		this.building = value;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String value) {
		this.apartment = value;
	}

	public String getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(String value) {
		this.postIndex = value;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date value) {
		this.registrationDate = value;
	}

	public Date getStartLivingDate() {
		return startLivingDate;
	}

	public void setStartLivingDate(Date value) {
		this.startLivingDate = value;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String value) {
		this.gps = value;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone value) {
		this.phone = value;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String value) {
		this.fullAddress = value;
	}

	public String getCodeKLADR() {
		return codeKLADR;
	}

	public void setCodeKLADR(String value) {
		this.codeKLADR = value;
	}

	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer value) {
		this.timeZone = value;
	}

	public String getCodeKLADE() {
		return codeKLADE;
	}

	public void setCodeKLADE(String value) {
		this.codeKLADE = value;
	}

	public String getAutonomyType() {
		return autonomyType;
	}

	public void setAutonomyType(String value) {
		this.autonomyType = value;
	}

	public String getAutonomyName() {
		return autonomyName;
	}

	public void setAutonomyName(String value) {
		this.autonomyName = value;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String value) {
		this.location = value;
	}

	public String getIntracityDistrict() {
		return intracityDistrict;
	}

	public void setIntracityDistrict(String value) {
		this.intracityDistrict = value;
	}

	public Boolean isIsNotPhone() {
		return isNotPhone;
	}

	public void setIsNotPhone(Boolean value) {
		this.isNotPhone = value;
	}

	public void setIsNotPhone(BigDecimal value) {
		this.isNotPhone = bigDecimalToBoolean(value);
	}

	public Boolean isOwnership() {
		return ownership;
	}

	public void setOwnership(Boolean value) {
		this.ownership = value;
	}

	public void setOwnership(BigDecimal value) {
		this.ownership = bigDecimalToBoolean(value);
	}

	public Boolean getIsNotPhone() {
		return isNotPhone;
	}

	public Boolean getOwnership() {
		return ownership;
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
								.append(transformDate((Date) fields[i].get(this)).toString()).append("\"");
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