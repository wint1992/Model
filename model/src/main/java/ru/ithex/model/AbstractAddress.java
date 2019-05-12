package ru.ithex.model;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class AbstractAddress implements Externalizable {
	private static final long serialVersionUID = 1l;
	transient protected TransformData td = new TransformData();

	public AbstractAddress() {
		super();
	}

	@Column(name = "country")
	@XmlAttribute
	protected String country;

	@Column(name = "region_type")
	@XmlAttribute
	protected String regionType;

	@Column(name = "region_name")
	@XmlAttribute
	protected String regionName;

	@Column(name = "district_type")
	@XmlAttribute
	protected String districtType;

	@Column(name = "district_name")
	@XmlAttribute
	protected String districtName;

	@Column(name = "microdistrict")
	@XmlAttribute
	protected String microdistrict;

	@Column(name = "location_type")
	@XmlAttribute
	protected String locationType;

	@Column(name = "city_type")
	@XmlAttribute
	protected String cityType;

	@Column(name = "city")
	@XmlAttribute
	protected String city;

	@Column(name = "street_type")
	@XmlAttribute
	protected String streetType;

	@Column(name = "street")
	@XmlAttribute
	protected String street;

	@Column(name = "house")
	@XmlAttribute
	protected String house;

	@Column(name = "construction")
	@XmlAttribute
	protected String construction;

	@Column(name = "building")
	@XmlAttribute
	protected String building;

	@Column(name = "apartment")
	@XmlAttribute
	protected String apartment;

	@Column(name = "post_index")
	@XmlAttribute
	protected String postIndex;

	@Column(name = "registration_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date registrationDate;

	@Column(name = "start_living_date")
	@XmlAttribute
	@XmlSchemaType(name = "date")
	protected Date startLivingDate;

	@Column(name = "gps")
	@XmlAttribute
	protected String gps;

	@Column(name = "full_address")
	@XmlAttribute
	protected String fullAddress;

	@Column(name = "code_kladr")
	@XmlAttribute
	protected String codeKLADR;

	@Column(name = "time_zone")
	@XmlAttribute
	protected Integer timeZone;

	@Column(name = "code_klade")
	@XmlAttribute
	protected String codeKLADE;

	@Column(name = "autonomy_type")
	@XmlAttribute
	protected String autonomyType;

	@Column(name = "autonomy_name")
	@XmlAttribute
	protected String autonomyName;

	@Column(name = "location")
	@XmlAttribute
	protected String location;

	@Column(name = "intracity_district")
	@XmlAttribute
	protected String intracityDistrict;

	@Column(name = "ownership")
	@XmlAttribute
	protected Byte ownership;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getRegionType() {
		return regionType;
	}

	public void setRegionType(String regionType) {
		this.regionType = regionType;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getDistrictType() {
		return districtType;
	}

	public void setDistrictType(String districtType) {
		this.districtType = districtType;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getMicrodistrict() {
		return microdistrict;
	}

	public void setMicrodistrict(String microdistrict) {
		this.microdistrict = microdistrict;
	}

	public String getLocationType() {
		return locationType;
	}

	public void setLocationType(String locationType) {
		this.locationType = locationType;
	}

	public String getCityType() {
		return cityType;
	}

	public void setCityType(String cityType) {
		this.cityType = cityType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreetType() {
		return streetType;
	}

	public void setStreetType(String streetType) {
		this.streetType = streetType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getHouse() {
		return house;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public String getPostIndex() {
		return postIndex;
	}

	public void setPostIndex(String postIndex) {
		this.postIndex = postIndex;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getStartLivingDate() {
		return startLivingDate;
	}

	public void setStartLivingDate(Date startLivingDate) {
		this.startLivingDate = startLivingDate;
	}

	public String getGps() {
		return gps;
	}

	public void setGps(String gps) {
		this.gps = gps;
	}

	public String getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(String fullAddress) {
		this.fullAddress = fullAddress;
	}

	public String getCodeKLADR() {
		return codeKLADR;
	}

	public void setCodeKLADR(String codeKLADR) {
		this.codeKLADR = codeKLADR;
	}

	public Integer getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(Integer timeZone) {
		this.timeZone = timeZone;
	}

	public String getCodeKLADE() {
		return codeKLADE;
	}

	public void setCodeKLADE(String codeKLADE) {
		this.codeKLADE = codeKLADE;
	}

	public String getAutonomyType() {
		return autonomyType;
	}

	public void setAutonomyType(String autonomyType) {
		this.autonomyType = autonomyType;
	}

	public String getAutonomyName() {
		return autonomyName;
	}

	public void setAutonomyName(String autonomyName) {
		this.autonomyName = autonomyName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getIntracityDistrict() {
		return intracityDistrict;
	}

	public void setIntracityDistrict(String intracityDistrict) {
		this.intracityDistrict = intracityDistrict;
	}

	public Byte getOwnership() {
		return ownership;
	}

	public void setOwnership(Byte ownership) {
		this.ownership = ownership;
	}

	public void writeExternal(ObjectOutput out) throws IOException {
		td.writeNullableObject(out, country);
		td.writeNullableObject(out, regionType);
		td.writeNullableObject(out, regionName);
		td.writeNullableObject(out, districtType);
		td.writeNullableObject(out, districtName);
		td.writeNullableObject(out, microdistrict);
		td.writeNullableObject(out, locationType);
		td.writeNullableObject(out, cityType);
		td.writeNullableObject(out, city);
		td.writeNullableObject(out, streetType);
		td.writeNullableObject(out, street);
		td.writeNullableObject(out, house);
		td.writeNullableObject(out, construction);
		td.writeNullableObject(out, building);
		td.writeNullableObject(out, apartment);
		td.writeNullableObject(out, postIndex);
		td.writeNullableObject(out, registrationDate);
		td.writeNullableObject(out, startLivingDate);
		td.writeNullableObject(out, gps);
		td.writeNullableObject(out, fullAddress);
		td.writeNullableObject(out, codeKLADR);
		td.writeNullableObject(out, timeZone);
		td.writeNullableObject(out, codeKLADE);
		td.writeNullableObject(out, autonomyType);
		td.writeNullableObject(out, autonomyName);
		td.writeNullableObject(out, location);
		td.writeNullableObject(out, intracityDistrict);
		td.writeNullableObject(out, ownership);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		country = in.readBoolean() == true ? in.readUTF() : null;
		regionType = in.readBoolean() == true ? in.readUTF() : null;
		regionName = in.readBoolean() == true ? in.readUTF() : null;
		districtType = in.readBoolean() == true ? in.readUTF() : null;
		districtName = in.readBoolean() == true ? in.readUTF() : null;
		microdistrict = in.readBoolean() == true ? in.readUTF() : null;
		locationType = in.readBoolean() == true ? in.readUTF() : null;
		cityType = in.readBoolean() == true ? in.readUTF() : null;
		city = in.readBoolean() == true ? in.readUTF() : null;
		streetType = in.readBoolean() == true ? in.readUTF() : null;
		street = in.readBoolean() == true ? in.readUTF() : null;
		house = in.readBoolean() == true ? in.readUTF() : null;
		construction = in.readBoolean() == true ? in.readUTF() : null;
		building = in.readBoolean() == true ? in.readUTF() : null;
		apartment = in.readBoolean() == true ? in.readUTF() : null;
		postIndex = in.readBoolean() == true ? in.readUTF() : null;
		registrationDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		startLivingDate = in.readBoolean() == true ? new Date(in.readLong()) : null;
		gps = in.readBoolean() == true ? in.readUTF() : null;
		fullAddress = in.readBoolean() == true ? in.readUTF() : null;
		codeKLADR = in.readBoolean() == true ? in.readUTF() : null;
		timeZone = in.readBoolean() == true ? in.readInt() : null;
		codeKLADE = in.readBoolean() == true ? in.readUTF() : null;
		autonomyType = in.readBoolean() == true ? in.readUTF() : null;
		autonomyName = in.readBoolean() == true ? in.readUTF() : null;
		location = in.readBoolean() == true ? in.readUTF() : null;
		intracityDistrict = in.readBoolean() == true ? in.readUTF() : null;
		ownership = in.readBoolean() == true ? in.readByte() : null;
	}
}
