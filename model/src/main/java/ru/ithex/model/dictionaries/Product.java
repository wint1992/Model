package ru.ithex.model.dictionaries;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import static ru.ithex.model.utils.Serialization.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

import javax.persistence.*;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Product")
@Entity
@Table(name = "product", schema = "mdm")
@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Product implements Externalizable {
	private static final long serialVersionUID = 1l;

	@Id
	@SequenceGenerator(name = "seq_gen_product", sequenceName = "product_id_seq")
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "seq_gen_product")
	@Column(name = "product_id")
	@XmlAttribute
	protected Integer productID;

	@Column(name = "product_name")
	@XmlAttribute
	protected String productName;

	@Column(name = "product_cost")
	@XmlAttribute
	protected BigDecimal productCost;

	@Column(name = "product_brand")
	@XmlAttribute
	protected String productBrand;

	@Column(name = "product_count")
	@XmlAttribute
	protected Integer productCount;

	@Column(name = "product_weight")
	@XmlAttribute
	protected BigDecimal productWeight;

	@Column(name = "product_length")
	@XmlAttribute
	protected BigDecimal productLength;

	@Column(name = "product_volume")
	@XmlAttribute
	protected BigDecimal productVolume;

	@JsonIgnore
	@XmlTransient
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_specification_id")
	protected ProductSpecification productSpecification;

	@XmlAttribute
	public String getType(){return productSpecification != null ? productSpecification.getType() : null;}

	@XmlAttribute
	public String getTypeCode(){return productSpecification != null ? productSpecification.getTypeCode() : null;}

	// Группа (Group)
	@XmlAttribute
	public String getG1(){return productSpecification != null ? productSpecification.getG1() : null;}

	@XmlAttribute
	public String getG1Code(){return productSpecification != null ? productSpecification.getG1Code() : null;}

	// Подгруппа (Subgroup)
	@XmlAttribute
	public String getG2(){return productSpecification != null ? productSpecification.getG2() : null;}

	@XmlAttribute
	public String getG2Code(){return productSpecification != null ? productSpecification.getG2Code() : null;}

	// Вид (Species)
	@XmlAttribute
	public String getG3(){return productSpecification != null ? productSpecification.getG3() : null;}

	@XmlAttribute
	public String getG3Code(){return productSpecification != null ? productSpecification.getG3Code() : null;}

	// Разновидность (Variety)
	@XmlAttribute
	public String getG4(){return productSpecification != null ? productSpecification.getG4() : null;}

	@XmlAttribute
	public String getG4Code(){return productSpecification != null ? productSpecification.getG4Code() : null;}

	public void writeExternal(ObjectOutput out) throws IOException {
		writeNullableObject(out, productID);
		writeNullableObject(out, productName);
		writeNullableObject(out, productCost);
		writeNullableObject(out, productBrand);
		writeNullableObject(out, productCount);
		writeNullableObject(out, productWeight);
		writeNullableObject(out, productLength);
		writeNullableObject(out, productVolume);
		productSpecification.writeExternal(out);
	}

	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		productID = readIntFromObjectInput(in);
		productName = readStringFromObjectInput(in);
		productCost = readBigDecimalFromObjectInput(in);
		productBrand = readStringFromObjectInput(in);
		productCount = readIntFromObjectInput(in);
		productWeight = readBigDecimalFromObjectInput(in);
		productLength = readBigDecimalFromObjectInput(in);
		productVolume = readBigDecimalFromObjectInput(in);
		productSpecification = new ProductSpecification();
		productSpecification.readExternal(in);
	}
}