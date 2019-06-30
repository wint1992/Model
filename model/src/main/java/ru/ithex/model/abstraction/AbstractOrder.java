package ru.ithex.model.abstraction;

import lombok.Data;
import ru.ithex.model.dictionaries.Product;
import static ru.ithex.model.utils.Serialization.*;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractOrder implements Externalizable {
    private static final long serialVersionUID = 1l;

    @Column(name = "product_count")
    @XmlAttribute
    protected int productCount;

    @Column(name = "order_cost")
    @XmlAttribute
    protected BigDecimal orderCost;

    @Column(name = "order_comment")
    @XmlAttribute
    protected String orderComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    @XmlElement(name = "Product")
    protected Product product;

    public Product getProduct() {
        if (product == null) product = new Product();
        return product;
    }

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, productCount);
        writeNullableObject(out, orderCost);
        writeNullableObject(out, orderComment);
        getProduct().writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        productCount = readIntFromObjectInput(in);
        orderCost = readBigDecimalFromObjectInput(in);
        orderComment = readStringFromObjectInput(in);
        getProduct().readExternal(in);
    }
}
