package ru.ithex.model.dao;

import static graphql.schema.GraphQLArgument.newArgument;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;
import static graphql.schema.GraphQLList.list;
import static graphql.schema.GraphQLObjectType.newObject;

import graphql.Scalars;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;

//	http://graphql-java.readthedocs.io/en/stable/schema.html
//https://github.com/graphql-java/graphql-java/blob/master/src/test/groovy/graphql/StarWarsSchema.java
public class GraphQLSchemaModel {
	public static GraphQLObjectType PersonType = newObject().name("Person")
			.field(newFieldDefinition().name("personID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("personType").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("firstName").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("lastName").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("patronymicName").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("isNoPatronymic").type(Scalars.GraphQLBoolean))
			.field(newFieldDefinition().name("previousLastName").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("birthPlace").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("birthDate").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("deathDate").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("age").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("sex").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("citizenship").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("maritalStatus").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("education").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("email").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("workEmail").type(Scalars.GraphQLString)).build();

	public static GraphQLObjectType AppDataType = newObject().name("AppData")
			.field(newFieldDefinition().name("appDateTime").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("appCloseDateTime").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("applicationStatus").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("appType").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("appChannel").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("deliveryDateTime").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("deliveryFactDateTime").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("ordersTotalCost").type(Scalars.GraphQLBigDecimal)).build();

	public static GraphQLObjectType RequestedInfoType = newObject().name("RequestedInfo")
			.field(newFieldDefinition().name("requestTimestamp").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("stepCode").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("currentDate").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("stepParameter").type(Scalars.GraphQLInt)).build();

	public static GraphQLObjectType ReasonCodeType = newObject().name("ReasonCode")
			.field(newFieldDefinition().name("reasonCodeID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("firedTimestamp").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("decisionReasonCode").type(Scalars.GraphQLString)).build();

	public static GraphQLObjectType NextStepType = newObject().name("NextStep")
			.field(newFieldDefinition().name("nextStepID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("stepCode").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("stepParameter").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("stepPlaneDate").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("stepPriority").type(Scalars.GraphQLInt)).build();

	public static GraphQLObjectType CallResultType = newObject().name("CallResult")
			.field(newFieldDefinition().name("callResultID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("callType").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("isActive").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("callDateTime").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("callDecision").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("reasonCodes").type(list(ReasonCodeType)))
			.field(newFieldDefinition().name("nextSteps").type(list(NextStepType))).build();

	public static GraphQLObjectType ApplicationType = newObject().name("Application")
			.field(newFieldDefinition().name("applicationID").type(Scalars.GraphQLInt))
			.field(newFieldDefinition().name("appTimestamp").type(Scalars.GraphQLString))
			.field(newFieldDefinition().name("manager").type(PersonType))
			.field(newFieldDefinition().name("appData").type(AppDataType))
			.field(newFieldDefinition().name("requestedInfo").type(RequestedInfoType))
			.field(newFieldDefinition().name("callResults").type(list(CallResultType))).build();

	public static GraphQLObjectType queryType = newObject().name("QueryType").field(newFieldDefinition()
			.name("application").type(ApplicationType).argument(newArgument().name("id").type(Scalars.GraphQLInt)))
			.build();

	public static GraphQLSchema starWarsSchema = GraphQLSchema.newSchema().query(queryType).build();
}
