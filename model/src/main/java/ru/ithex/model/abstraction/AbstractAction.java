package ru.ithex.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import ru.ithex.model.dictionaries.ActionResult;
import ru.ithex.model.dictionaries.ActionType;

import javax.persistence.*;
import javax.xml.bind.annotation.*;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

import static ru.ithex.model.utils.Serialization.*;

@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public abstract class AbstractAction implements Externalizable {
    private static final long serialVersionUID = 1l;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_type_id")
    @XmlTransient
    protected ActionType actionType;

    @XmlAttribute
    public Integer getActionTypeId(){ return actionType != null ? actionType.getActionTypeId() : null;}

    @XmlAttribute
    public String getActionType(){ return actionType != null ? actionType.getActionTypeName() : null;}

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "action_result_id")
    @XmlTransient
    protected ActionResult actionResult;

    @XmlAttribute
    public Integer getActionResultId(){ return actionResult != null ? actionResult.getActionResultId() : null;}

    @XmlAttribute
    public String getActionResult(){ return actionResult != null ? actionResult.getActionResultName() : null;}

    @Column(name = "action_manager_id")
    @XmlAttribute
    protected Integer actionManagerID;

    @Column(name = "action_previous_manager_id")
    @XmlAttribute
    protected Integer actionPreviousManagerID;

    @Column(name = "action_date_time")
    @XmlAttribute
    @XmlSchemaType(name = "dateTime")
    protected Date actionDateTime;

    @Column(name = "action_comment")
    @XmlAttribute
    protected String actionComment;

    public void writeExternal(ObjectOutput out) throws IOException {
        writeNullableObject(out, actionManagerID);
        writeNullableObject(out, actionPreviousManagerID);
        writeNullableObject(out, actionDateTime);
        writeNullableObject(out, actionComment);
        actionType.writeExternal(out);
        actionResult.writeExternal(out);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        actionManagerID = readIntFromObjectInput(in);
        actionPreviousManagerID = readIntFromObjectInput(in);
        actionDateTime = readLongToDateFromObjectInput(in);
        actionComment = readStringFromObjectInput(in);

        actionType = new ActionType();
        actionType.readExternal(in);

        actionResult = new ActionResult();
        actionResult.readExternal(in);
    }
}