package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.AddressType;

import static ru.ithex.model.utils.Serialization.*;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractAddress implements Externalizable {
	private static final long serialVersionUID = 1l;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "address_type_id", insertable = false, updatable = false)
	@XmlTransient
	protected AddressType addressType;

	@XmlAttribute
	public Integer getAddressTypeId(){ return addressType != null ? addressType.getAddressTypeId() : null;}

	@XmlAttribute
	public String getAddressType(){ return addressType != null ? addressType.getAddressTypeName() : null;}

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
	protected Boolean ownership;

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, country);
		writeNullableObject(out, regionType);
		writeNullableObject(out, regionName);
		writeNullableObject(out, districtType);
		writeNullableObject(out, districtName);
		writeNullableObject(out, microdistrict);
		writeNullableObject(out, locationType);
		writeNullableObject(out, cityType);
		writeNullableObject(out, city);
		writeNullableObject(out, streetType);
		writeNullableObject(out, street);
		writeNullableObject(out, house);
		writeNullableObject(out, construction);
		writeNullableObject(out, building);
		writeNullableObject(out, apartment);
		writeNullableObject(out, postIndex);
		writeNullableObject(out, registrationDate);
		writeNullableObject(out, startLivingDate);
		writeNullableObject(out, gps);
		writeNullableObject(out, fullAddress);
		writeNullableObject(out, codeKLADR);
		writeNullableObject(out, timeZone);
		writeNullableObject(out, codeKLADE);
		writeNullableObject(out, autonomyType);
		writeNullableObject(out, autonomyName);
		writeNullableObject(out, location);
		writeNullableObject(out, intracityDistrict);
		writeNullableObject(out, ownership);
		addressType.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		country = readStringFromObjectInput(in);
		regionType = readStringFromObjectInput(in);
		regionName = readStringFromObjectInput(in);
		districtType = readStringFromObjectInput(in);
		districtName = readStringFromObjectInput(in);
		microdistrict = readStringFromObjectInput(in);
		locationType = readStringFromObjectInput(in);
		cityType = readStringFromObjectInput(in);
		city = readStringFromObjectInput(in);
		streetType = readStringFromObjectInput(in);
		street = readStringFromObjectInput(in);
		house = readStringFromObjectInput(in);
		construction = readStringFromObjectInput(in);
		building = readStringFromObjectInput(in);
		apartment = readStringFromObjectInput(in);
		postIndex = readStringFromObjectInput(in);
		registrationDate = readLongToDateFromObjectInput(in);
		startLivingDate = readLongToDateFromObjectInput(in);
		gps = readStringFromObjectInput(in);
		fullAddress = readStringFromObjectInput(in);
		codeKLADR = readStringFromObjectInput(in);
		timeZone = readIntFromObjectInput(in);
		codeKLADE = readStringFromObjectInput(in);
		autonomyType = readStringFromObjectInput(in);
		autonomyName = readStringFromObjectInput(in);
		location = readStringFromObjectInput(in);
		intracityDistrict = readStringFromObjectInput(in);
		ownership = readBooleanFromObjectInput(in);

		addressType = new AddressType();
		addressType.readExternal(in);
	}
}
