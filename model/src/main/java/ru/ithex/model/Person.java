package ru.ithex.model;

import static ru.ithex.model.TransformData.bigDecimalToBoolean;
import static ru.ithex.model.TransformData.bigDecimalToInteger;
import static ru.ithex.model.TransformData.transformDate;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Person {

	public Person() {
		super();
	}

	protected Integer personID;
	protected Integer personType;
	protected String firstName;
	protected String lastName;
	protected String patronymicName;
	protected Boolean isNoPatronymic;
	protected String previousLastName;
	protected String birthPlace;
	protected Date birthDate;
	protected Date deathDate;
	protected Integer age;
	protected Integer sex;
	protected Integer citizenship;
	protected Integer maritalStatus;
	protected Integer education;
	protected String email;
	protected String workEmail;

	protected Set<Document> documents;
	// protected Set<Email> emails;
	protected Phone mobile;
	protected Phone regPhone;
	protected Phone factPhone;
	protected SocialNetwork vk;
	protected SocialNetwork fb;
	protected SocialNetwork instagram;
	protected Messanger whatsApp;
	protected Messanger telegram;
	protected Messanger viber;
	protected Set<Employment> employments;
	protected Set<Interest> interests;
	protected Set<Person> thirdPersons;
	protected Auto auto;
	protected PersonCalcs personCalcs;
	protected Document passport;
	protected Address regAddresses;
	protected Address factAddresses;
	protected boolean fake;

	public Set<Document> getDocuments() {
		if (documents == null) {
			documents = new HashSet<Document>();
		}
		return this.documents;
	}

	// public Set<Email> getEmails() {
	// if (emails == null) {
	// emails = new HashSet<Email>();
	// }
	// return this.emails;
	// }

	public Set<Employment> getEmployments() {
		if (employments == null) {
			employments = new HashSet<Employment>();
		}
		return this.employments;
	}

	public Set<Interest> getInterests() {
		if (interests == null) {
			interests = new HashSet<Interest>();
		}
		return this.interests;
	}

	public Set<Person> getThirdPersons() {
		if (thirdPersons == null) {
			thirdPersons = new HashSet<Person>();
		}
		return this.thirdPersons;
	}

	public PersonCalcs getPersonCalcs() {
		return personCalcs;
	}

	public void setPersonCalcs(PersonCalcs value) {
		this.personCalcs = value;
	}

	public Integer getPersonID() {
		return personID;
	}

	public void setPersonID(Integer value) {
		this.personID = value;
	}

	public Integer getPersonType() {
		return personType;
	}

	public void setPersonType(Integer value) {
		this.personType = value;
	}

	public void setPersonType(BigDecimal value) {
		this.personType = bigDecimalToInteger(value);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String value) {
		this.firstName = value;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String value) {
		this.lastName = value;
	}

	public String getPatronymicName() {
		return patronymicName;
	}

	public void setPatronymicName(String value) {
		this.patronymicName = value;
	}

	public Boolean isIsNoPatronymic() {
		return isNoPatronymic;
	}

	public void setIsNoPatronymic(Boolean value) {
		this.isNoPatronymic = value;
	}

	public void setIsNoPatronymic(BigDecimal value) {
		this.isNoPatronymic = bigDecimalToBoolean(value);
	}

	public String getPreviousLastName() {
		return previousLastName;
	}

	public void setPreviousLastName(String value) {
		this.previousLastName = value;
	}

	public String getBirthPlace() {
		return birthPlace;
	}

	public void setBirthPlace(String value) {
		this.birthPlace = value;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date value) {
		this.birthDate = value;
	}

	public Date getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Date value) {
		this.deathDate = value;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer value) {
		this.age = value;
	}

	public void setAge(BigDecimal value) {
		this.age = bigDecimalToInteger(value);
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer value) {
		this.sex = value;
	}

	public void setSex(BigDecimal value) {
		this.sex = bigDecimalToInteger(value);
	}

	public Integer getCitizenship() {
		return citizenship;
	}

	public void setCitizenship(Integer value) {
		this.citizenship = value;
	}

	public void setCitizenship(BigDecimal value) {
		this.citizenship = bigDecimalToInteger(value);
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer value) {
		this.maritalStatus = value;
	}

	public void setMaritalStatus(BigDecimal value) {
		this.maritalStatus = bigDecimalToInteger(value);
	}

	public Integer getEducation() {
		return education;
	}

	public void setEducation(Integer value) {
		this.education = value;
	}

	public void setEducation(BigDecimal value) {
		this.education = bigDecimalToInteger(value);
	}

	public Boolean getIsNoPatronymic() {
		return isNoPatronymic;
	}

	public void setDocuments(Set<Document> documents) {
		this.documents = documents;
	}

	// public void setEmails(Set<Email> emails) {
	// this.emails = emails;
	// }

	public void setEmployments(Set<Employment> employments) {
		this.employments = employments;
	}

	public void setInterests(Set<Interest> interests) {
		this.interests = interests;
	}

	public void setThirdPersons(Set<Person> thirdPersons) {
		this.thirdPersons = thirdPersons;
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

	public Document getPassport() {
		return passport;
	}

	public void setPassport(Document passport) {
		this.passport = passport;
	}

	public Address getRegAddresses() {
		return regAddresses;
	}

	public void setRegAddresses(Address regAddresses) {
		this.regAddresses = regAddresses;
	}

	public Address getFactAddresses() {
		return factAddresses;
	}

	public void setFactAddresses(Address factAddresses) {
		this.factAddresses = factAddresses;
	}

	public Auto getAuto() {
		return auto;
	}

	public void setAuto(Auto auto) {
		this.auto = auto;
	}

	public SocialNetwork getVk() {
		return vk;
	}

	public void setVk(SocialNetwork vk) {
		this.vk = vk;
	}

	public SocialNetwork getFb() {
		return fb;
	}

	public void setFb(SocialNetwork fb) {
		this.fb = fb;
	}

	public SocialNetwork getInstagram() {
		return instagram;
	}

	public void setInstagram(SocialNetwork instagram) {
		this.instagram = instagram;
	}

	public Messanger getWhatsApp() {
		return whatsApp;
	}

	public void setWhatsApp(Messanger whatsApp) {
		this.whatsApp = whatsApp;
	}

	public Messanger getTelegram() {
		return telegram;
	}

	public void setTelegram(Messanger telegram) {
		this.telegram = telegram;
	}

	public Messanger getViber() {
		return viber;
	}

	public void setViber(Messanger viber) {
		this.viber = viber;
	}

	public Phone getMobile() {
		return mobile;
	}

	public void setMobile(Phone mobile) {
		this.mobile = mobile;
	}

	public Phone getRegPhone() {
		return regPhone;
	}

	public void setRegPhone(Phone regPhone) {
		this.regPhone = regPhone;
	}

	public Phone getFactPhone() {
		return factPhone;
	}

	public void setFactPhone(Phone factPhone) {
		this.factPhone = factPhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkEmail() {
		return workEmail;
	}

	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
}