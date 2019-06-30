drop schema mdm cascade;
drop schema prerequisite cascade;

--=================================================================         Справочники         =====================================================================
CREATE SCHEMA mdm;

CREATE SEQUENCE mdm.promotion_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.promotion(
    PROMOTION_ID integer NOT NULL DEFAULT nextval('mdm.promotion_id_seq'::regclass),
    TITLE varchar(1000),
    CODE varchar(20),
    BEGIN_DATE_TIME timestamp,
    END_DATE_TIME timestamp,
    DESCRIPTION text,
    CONSTRAINT promotion_pkey PRIMARY KEY (PROMOTION_ID)
);
comment on column mdm.promotion.TITLE is 'Название акции';
comment on column mdm.promotion.CODE is 'Промокод';
comment on column mdm.promotion.DESCRIPTION is 'Описание акции';


CREATE SEQUENCE mdm.partner_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.partner(
    partner_id integer NOT NULL DEFAULT nextval('mdm.partner_id_seq'::regclass),
    VALUE varchar(1000),
    CONSTRAINT partner_pkey PRIMARY KEY (partner_id)
);

CREATE SEQUENCE mdm.product_specification_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.product_specification(
    product_specification_id integer NOT NULL DEFAULT nextval('mdm.product_specification_id_seq'::regclass),
    product_specification_name varchar(100),
    product_specification_code varchar(20),
    type_id integer,
    g1_id integer,
    g2_id integer,
    g3_id integer,
    CONSTRAINT product_specification_pkey PRIMARY KEY (product_specification_id)
);
comment on column mdm.product_specification.product_specification_name is '[productSpecificationName] Название типа/групп/продукта (тип ~ товар, услуга, и т.п)';
comment on column mdm.product_specification.product_specification_code is '[productSpecificationCode] Код типа/групп/продукта';
comment on column mdm.product_specification.type_id is '[typeId] ID типа группы';
comment on column mdm.product_specification.g1_id is '[g1Id] ID группы 1 уровня';
comment on column mdm.product_specification.g2_id is '[g2Id] ID группы 2 уровня';
comment on column mdm.product_specification.g3_id is '[g3Id] ID группы 3 уровня';

create view mdm.product_specification_view(product_specification_id, type, type_code, g1, g1_code, g2, g2_code, g3, g3_code, g4, g4_code) as
SELECT g4.product_specification_id,
       ps_type.product_specification_name AS type,
       ps_type.product_specification_code AS type_code,
       g1.product_specification_name AS g1,
       g1.product_specification_code AS g1_code,
       g2.product_specification_name AS g2,
       g2.product_specification_code AS g2_code,
       g3.product_specification_name AS g3,
       g3.product_specification_code AS g3_code,
       g4.product_specification_name AS g4,
       g4.product_specification_code AS g4_code
FROM mdm.product_specification g4
inner JOIN mdm.product_specification g3 ON g3.product_specification_id = g4.g3_id
inner JOIN mdm.product_specification g2 ON g2.product_specification_id = g3.g2_id
inner JOIN mdm.product_specification g1 ON g1.product_specification_id = g2.g1_id
inner JOIN mdm.product_specification ps_type ON ps_type.product_specification_id = g1.g1_id;

CREATE SEQUENCE mdm.product_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.product(
    PRODUCT_ID integer NOT NULL DEFAULT nextval('mdm.product_id_seq'::regclass),
    PRODUCT_NAME varchar(400),
    PRODUCT_COST numeric,
    product_specification_id integer,
    PRODUCT_BRAND varchar(200),
    PRODUCT_COUNT integer,
    PRODUCT_WEIGHT numeric,
    PRODUCT_LENGTH numeric,
    PRODUCT_VOLUME numeric,
    ORGANIZATION_ID integer
);

comment on column mdm.PRODUCT.PRODUCT_ID is '[productID] ';
comment on column mdm.PRODUCT.PRODUCT_NAME is '[productName] ';
comment on column mdm.PRODUCT.PRODUCT_COST is '[productCost] ';
comment on column mdm.PRODUCT.product_specification_id is '[productSpecificationId] FK to mdm.product_specification. Спецификациия продукта';
comment on column mdm.PRODUCT.PRODUCT_BRAND is '[productBrand] Торговая марка товара (только для товара)';
comment on column mdm.PRODUCT.PRODUCT_COUNT is '[productCount] Количество товаров (только для товара)';
comment on column mdm.PRODUCT.PRODUCT_WEIGHT is '[productWeight] Масса товара (только для товара)';
comment on column mdm.PRODUCT.PRODUCT_LENGTH is '[productLength] Длина товара (только для товара)';
comment on column mdm.PRODUCT.PRODUCT_VOLUME is '[productVolume] Объем товара (только для товара)';
comment on column mdm.PRODUCT.ORGANIZATION_ID is '[ORGANIZATION_ID] FK to Organization; Компания к которой относится продукт';
create index IDX_PRODUCT_ORGANIZATION_ID on mdm.product (ORGANIZATION_ID);

CREATE SEQUENCE mdm.organization_status_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.organization_status(
    organization_status_id integer NOT NULL DEFAULT nextval('mdm.organization_status_id_seq'::regclass),
    organization_status_name varchar(100),
    CONSTRAINT organization_status_pkey PRIMARY KEY (organization_status_id)
);

CREATE SEQUENCE mdm.person_type_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.person_type(
    person_type_id integer NOT NULL DEFAULT nextval('mdm.person_type_id_seq'::regclass),
    person_type_name varchar(100),
    CONSTRAINT person_type_pkey PRIMARY KEY (person_type_id)
);

CREATE SEQUENCE mdm.CITIZENSHIP_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.CITIZENSHIP(
    CITIZENSHIP_id integer NOT NULL DEFAULT nextval('mdm.CITIZENSHIP_id_seq'::regclass),
    CITIZENSHIP_name varchar(100),
    CONSTRAINT CITIZENSHIP_pkey PRIMARY KEY (CITIZENSHIP_id)
);

CREATE SEQUENCE mdm.MARITAL_STATUS_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.MARITAL_STATUS(
    MARITAL_STATUS_id integer NOT NULL DEFAULT nextval('mdm.MARITAL_STATUS_id_seq'::regclass),
    MARITAL_STATUS_name varchar(100),
    CONSTRAINT MARITAL_STATUS_pkey PRIMARY KEY (MARITAL_STATUS_id)
);

CREATE SEQUENCE mdm.EDUCATION_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.EDUCATION(
    EDUCATION_id integer NOT NULL DEFAULT nextval('mdm.EDUCATION_id_seq'::regclass),
    EDUCATION_name varchar(100),
    CONSTRAINT EDUCATION_pkey PRIMARY KEY (EDUCATION_id)
);

CREATE SEQUENCE mdm.document_type_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.document_type(
    document_type_id integer NOT NULL DEFAULT nextval('mdm.document_type_id_seq'::regclass),
    document_type_name varchar(100),
    CONSTRAINT document_type_pkey PRIMARY KEY (document_type_id)
);

CREATE SEQUENCE mdm.person_SEGMENT_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.person_SEGMENT(
    person_SEGMENT_id integer NOT NULL DEFAULT nextval('mdm.person_SEGMENT_id_seq'::regclass),
    person_SEGMENT_name varchar(100),
    CONSTRAINT person_SEGMENT_pkey PRIMARY KEY (person_SEGMENT_id)
);

CREATE SEQUENCE mdm.PHONE_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.PHONE_TYPE(
    PHONE_TYPE_id integer NOT NULL DEFAULT nextval('mdm.PHONE_TYPE_id_seq'::regclass),
    PHONE_TYPE_name varchar(100),
    CONSTRAINT PHONE_TYPE_pkey PRIMARY KEY (PHONE_TYPE_id)
);

CREATE SEQUENCE mdm.NETWORK_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.NETWORK_TYPE(
    NETWORK_TYPE_id integer NOT NULL DEFAULT nextval('mdm.NETWORK_TYPE_id_seq'::regclass),
    NETWORK_TYPE_name varchar(100),
    CONSTRAINT NETWORK_TYPE_pkey PRIMARY KEY (NETWORK_TYPE_id)
);

CREATE SEQUENCE mdm.MESSENGER_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.MESSENGER_TYPE(
    MESSENGER_TYPE_id integer NOT NULL DEFAULT nextval('mdm.MESSENGER_TYPE_id_seq'::regclass),
    MESSENGER_TYPE_name varchar(100),
    CONSTRAINT MESSENGER_TYPE_pkey PRIMARY KEY (MESSENGER_TYPE_id)
);

CREATE SEQUENCE mdm.EMPLOYMENT_POSITION_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.EMPLOYMENT_POSITION(
    EMPLOYMENT_POSITION_id integer NOT NULL DEFAULT nextval('mdm.EMPLOYMENT_POSITION_id_seq'::regclass),
    EMPLOYMENT_POSITION_name varchar(100),
    CONSTRAINT EMPLOYMENT_POSITION_pkey PRIMARY KEY (EMPLOYMENT_POSITION_id)
);

CREATE SEQUENCE mdm.INTEREST_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.INTEREST_TYPE(
    INTEREST_TYPE_id integer NOT NULL DEFAULT nextval('mdm.INTEREST_TYPE_id_seq'::regclass),
    INTEREST_TYPE_name varchar(100),
    CONSTRAINT INTEREST_TYPE_pkey PRIMARY KEY (INTEREST_TYPE_id)
);

CREATE SEQUENCE mdm.APPLICATION_STATUS_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.APPLICATION_STATUS(
    APPLICATION_STATUS_id integer NOT NULL DEFAULT nextval('mdm.APPLICATION_STATUS_id_seq'::regclass),
    APPLICATION_STATUS_name varchar(100),
    CONSTRAINT APPLICATION_STATUS_pkey PRIMARY KEY (APPLICATION_STATUS_id)
);

CREATE SEQUENCE mdm.APPLICATION_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.APPLICATION_TYPE(
    APPLICATION_TYPE_id integer NOT NULL DEFAULT nextval('mdm.APPLICATION_TYPE_id_seq'::regclass),
    APPLICATION_TYPE_name varchar(100),
    CONSTRAINT APPLICATION_TYPE_pkey PRIMARY KEY (APPLICATION_TYPE_id)
);

CREATE SEQUENCE mdm.APPLICATION_CHANNEL_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.APPLICATION_CHANNEL(
    APPLICATION_CHANNEL_id integer NOT NULL DEFAULT nextval('mdm.APPLICATION_CHANNEL_id_seq'::regclass),
    APPLICATION_CHANNEL_name varchar(100),
    CONSTRAINT APPLICATION_CHANNEL_pkey PRIMARY KEY (APPLICATION_CHANNEL_id)
);

CREATE SEQUENCE mdm.ADDRESS_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.ADDRESS_TYPE(
    ADDRESS_TYPE_id integer NOT NULL DEFAULT nextval('mdm.ADDRESS_TYPE_id_seq'::regclass),
    ADDRESS_TYPE_name varchar(100),
    CONSTRAINT ADDRESS_TYPE_pkey PRIMARY KEY (ADDRESS_TYPE_id)
);


CREATE SEQUENCE mdm.ACTION_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.ACTION_TYPE(
    ACTION_TYPE_id integer NOT NULL DEFAULT nextval('mdm.ACTION_TYPE_id_seq'::regclass),
    ACTION_TYPE_name varchar(100),
    CONSTRAINT ACTION_TYPE_pkey PRIMARY KEY (ACTION_TYPE_id)
);

CREATE SEQUENCE mdm.ACTION_RESULT_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.ACTION_RESULT(
    ACTION_RESULT_id integer NOT NULL DEFAULT nextval('mdm.ACTION_RESULT_id_seq'::regclass),
    ACTION_RESULT_name varchar(100),
    CONSTRAINT ACTION_RESULT_pkey PRIMARY KEY (ACTION_RESULT_id)
);

CREATE SEQUENCE mdm.CALL_TYPE_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.CALL_TYPE(
    CALL_TYPE_id integer NOT NULL DEFAULT nextval('mdm.CALL_TYPE_id_seq'::regclass),
    CALL_TYPE_name varchar(100),
    CONSTRAINT CALL_TYPE_pkey PRIMARY KEY (CALL_TYPE_id)
);

CREATE SEQUENCE mdm.REASON_CODE_TYPE_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table mdm.REASON_CODE_TYPE(
    REASON_CODE_TYPE_ID integer NOT NULL DEFAULT nextval('mdm.REASON_CODE_TYPE_ID_seq'::regclass),
    REASON_CODE_DECISION integer,
    REASON_CODE_VALUE varchar(10),
    REASON_CODE_DESCRIPTION varchar(200),
    CONSTRAINT REASON_CODE_TYPE_pkey PRIMARY KEY (REASON_CODE_TYPE_ID)
);

--===================================================================================================================================================================

CREATE SCHEMA prerequisite;

CREATE SEQUENCE prerequisite.organization_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.organization(
    ORGANIZATION_ID integer NOT NULL DEFAULT nextval('prerequisite.organization_id_seq'::regclass),
    CLIENT_ID integer,
    organization_status_id integer,
    REGISTRATION_DATE date,
    CLOSE_DATE date,
    INN varchar(50),
    OGRN varchar(50),
    OKOPF numeric,
    OKVED text,
    FULL_NAME varchar(400),
    OKPO text,
    EMPLOYEE_NUMBER integer,
    WEB_SITE varchar(400),
    IS_EMPLOYMENT boolean,
    CONSTRAINT organization_pkey PRIMARY KEY (ORGANIZATION_ID)
);

comment on column prerequisite.ORGANIZATION.ORGANIZATION_ID is '[organizationID] ID организации';
comment on column prerequisite.ORGANIZATION.organization_status_id is '[status] Статус организации';
comment on column prerequisite.ORGANIZATION.REGISTRATION_DATE is '[registrationDate] Дата регистрации организации';
comment on column prerequisite.ORGANIZATION.CLOSE_DATE is '[closeDate] Дата прекращения деятельности организации';
comment on column prerequisite.ORGANIZATION.INN is '[inn] ИНН организации';
comment on column prerequisite.ORGANIZATION.OGRN is '[ogrn] ОГРН организации';
comment on column prerequisite.ORGANIZATION.OKOPF is '[okopf] Организационно-правовая форма организации';
comment on column prerequisite.ORGANIZATION.OKVED is '[okved] Вид деятельности организации';
comment on column prerequisite.ORGANIZATION.FULL_NAME is '[fullName] Полное наименование организации';
comment on column prerequisite.ORGANIZATION.OKPO is '[okpo] ОКПО';
comment on column prerequisite.ORGANIZATION.EMPLOYEE_NUMBER is '[employeeNumber] Количество сотрудников в организации';
comment on column prerequisite.ORGANIZATION.WEB_SITE is '[webSite] web сайт';
comment on column prerequisite.ORGANIZATION.IS_EMPLOYMENT is '[isEmployment] Признак, что организация указана у кого-либо из клиентов, как компания, в которой он работает';

CREATE SEQUENCE prerequisite.person_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.PERSON(
    PERSON_ID integer NOT NULL DEFAULT nextval('prerequisite.person_id_seq'::regclass),
    ORGANIZATION_ID integer,
    CLIENT_ID integer,
    person_type_id integer,
    FIRST_NAME varchar(200),
    LAST_NAME varchar(200),
    PATRONYMIC_NAME varchar(200),
    IS_NO_PATRONYMIC boolean,
    PREVIOUS_LAST_NAME varchar(200),
    BIRTH_PLACE varchar(400),
    BIRTH_DATE date,
    DEATH_DATE date,
    AGE integer,
    SEX integer,
    CITIZENSHIP_id integer,
    MARITAL_STATUS_id integer,
    EDUCATION_id integer,
    EMAIL varchar(200),
    WORK_EMAIL varchar(200),
    RA_COUNTRY varchar(200),
    RA_REGION_TYPE varchar(200),
    RA_REGION_NAME varchar(400),
    RA_DISTRICT_TYPE varchar(200),
    RA_DISTRICT_NAME varchar(400),
    RA_MICRODISTRICT varchar(400),
    RA_LOCATION_TYPE varchar(200),
    RA_CITY_TYPE varchar(200),
    RA_CITY varchar(200),
    RA_STREET_TYPE varchar(200),
    RA_STREET varchar(400),
    RA_HOUSE varchar(50),
    RA_CONSTRUCTION varchar(50),
    RA_BUILDING varchar(50),
    RA_APARTMENT varchar(50),
    RA_POST_INDEX varchar(200),
    RA_REGISTRATION_DATE date,
    RA_START_LIVING_DATE date,
    RA_GPS varchar(200),
    RA_FULL_ADDRESS varchar(2000),
    RA_CODE_KLADR varchar(200),
    RA_TIME_ZONE numeric,
    RA_CODE_KLADE varchar(200),
    RA_AUTONOMY_TYPE varchar(200),
    RA_AUTONOMY_NAME varchar(400),
    RA_LOCATION varchar(400),
    RA_INTRACITY_DISTRICT varchar(400),
    RA_OWNERSHIP boolean,
    FA_COUNTRY varchar(200),
    FA_REGION_TYPE varchar(200),
    FA_REGION_NAME varchar(400),
    FA_DISTRICT_TYPE varchar(200),
    FA_DISTRICT_NAME varchar(400),
    FA_MICRODISTRICT varchar(400),
    FA_LOCATION_TYPE varchar(200),
    FA_CITY_TYPE varchar(200),
    FA_CITY varchar(200),
    FA_STREET_TYPE varchar(200),
    FA_STREET varchar(400),
    FA_HOUSE varchar(50),
    FA_CONSTRUCTION varchar(50),
    FA_BUILDING varchar(50),
    FA_APARTMENT varchar(50),
    FA_POST_INDEX varchar(200),
    FA_REGISTRATION_DATE date,
    FA_START_LIVING_DATE date,
    FA_GPS varchar(200),
    FA_FULL_ADDRESS varchar(2000),
    FA_CODE_KLADR varchar(200),
    FA_TIME_ZONE numeric,
    FA_CODE_KLADE varchar(200),
    FA_AUTONOMY_TYPE varchar(200),
    FA_AUTONOMY_NAME varchar(400),
    FA_LOCATION varchar(400),
    FA_INTRACITY_DISTRICT varchar(400),
    FA_OWNERSHIP boolean,
    PARENT_PERSON_id integer,
    PC_person_SEGMENT_id integer,
    PC_TOTAL_INCOME numeric,
    CONSTRAINT PERSON_pkey PRIMARY KEY (PERSON_ID)
);

comment on column prerequisite.PERSON.ORGANIZATION_ID is '[ORGANIZATION_ID] FK to Organization; Компания';
comment on column prerequisite.PERSON.PERSON_ID is '[personID] ID человека';
comment on column prerequisite.PERSON.person_type_id is '[personType] Тип родства с человеком [PERSON_FK]';
comment on column prerequisite.PERSON.FIRST_NAME is '[firstName] Имя';
comment on column prerequisite.PERSON.LAST_NAME is '[lastName] Фамилия';
comment on column prerequisite.PERSON.PATRONYMIC_NAME is '[patronymicName] Отчество';
comment on column prerequisite.PERSON.IS_NO_PATRONYMIC is '[isNoPatronymic] Признак подтверждения отсутствия отчеcтва';
comment on column prerequisite.PERSON.PREVIOUS_LAST_NAME is '[previousLastName] Предыдущая фамилия';
comment on column prerequisite.PERSON.BIRTH_PLACE is '[birthPlace] Место рождения (по паспорту)';
comment on column prerequisite.PERSON.BIRTH_DATE is '[birthDate] Дата рождения';
comment on column prerequisite.PERSON.DEATH_DATE is '[deathDate] Дата смерти';
comment on column prerequisite.PERSON.AGE is '[age] Возраст в месяцах';
comment on column prerequisite.PERSON.SEX is '[sex] Пол';
comment on column prerequisite.PERSON.CITIZENSHIP_id is '[citizenship] Гражданство';
comment on column prerequisite.PERSON.MARITAL_STATUS_id is '[maritalStatus] Семейное положение';
comment on column prerequisite.PERSON.EDUCATION_id is '[education] Образование';
comment on column prerequisite.PERSON.EMAIL is '[email] Домашний email';
comment on column prerequisite.PERSON.WORK_EMAIL is '[workEmail] Рабочий email';
comment on column prerequisite.PERSON.RA_COUNTRY is '[RegAddress.country] Страна';
comment on column prerequisite.PERSON.RA_REGION_TYPE is '[RegAddress.regionType] Тип субъекта РФ (1 уровень КЛАДР)';
comment on column prerequisite.PERSON.RA_REGION_NAME is '[RegAddress.regionName] Регион';
comment on column prerequisite.PERSON.RA_DISTRICT_TYPE is '[RegAddress.districtType] Тип района';
comment on column prerequisite.PERSON.RA_DISTRICT_NAME is '[RegAddress.districtName] Наименование района';
comment on column prerequisite.PERSON.RA_MICRODISTRICT is '[RegAddress.microdistrict] Микрорайон';
comment on column prerequisite.PERSON.RA_LOCATION_TYPE is '[RegAddress.locationType] Тип населенного пункта ';
comment on column prerequisite.PERSON.RA_CITY_TYPE is '[RegAddress.cityType] ';
comment on column prerequisite.PERSON.RA_CITY is '[RegAddress.city] Населенный пукнт (3 уровень КЛАДР)';
comment on column prerequisite.PERSON.RA_STREET_TYPE is '[RegAddress.streetType] Тип улицы (дороги)';
comment on column prerequisite.PERSON.RA_STREET is '[RegAddress.street] Название улицы';
comment on column prerequisite.PERSON.RA_HOUSE is '[RegAddress.house] Дом';
comment on column prerequisite.PERSON.RA_CONSTRUCTION is '[RegAddress.construction] Корпус';
comment on column prerequisite.PERSON.RA_BUILDING is '[RegAddress.building] Строение ';
comment on column prerequisite.PERSON.RA_APARTMENT is '[RegAddress.apartment] Квартира';
comment on column prerequisite.PERSON.RA_POST_INDEX is '[RegAddress.postIndex] Почтовый индекс';
comment on column prerequisite.PERSON.RA_REGISTRATION_DATE is '[RegAddress.registrationDate] Дата регистрации по адресу';
comment on column prerequisite.PERSON.RA_START_LIVING_DATE is '[RegAddress.startLivingDate] Дата заселения по адресу проживания';
comment on column prerequisite.PERSON.RA_GPS is '[RegAddress.gps] Координаты GPS';
comment on column prerequisite.PERSON.RA_FULL_ADDRESS is '[RegAddress.fullAddress] Адрес единой строкой';
comment on column prerequisite.PERSON.RA_CODE_KLADR is '[RegAddress.codeKLADR] КЛАДР-код';
comment on column prerequisite.PERSON.RA_TIME_ZONE is '[RegAddress.timeZone] Часовой пояс';
comment on column prerequisite.PERSON.RA_CODE_KLADE is '[RegAddress.codeKLADE] КЛАДЭ-код';
comment on column prerequisite.PERSON.RA_AUTONOMY_TYPE is '[RegAddress.autonomyType] Тип автономии';
comment on column prerequisite.PERSON.RA_AUTONOMY_NAME is '[RegAddress.autonomyName] Наименование автономии';
comment on column prerequisite.PERSON.RA_LOCATION is '[RegAddress.location] Наименование населенного пункта';
comment on column prerequisite.PERSON.RA_INTRACITY_DISTRICT is '[RegAddress.intracityDistrict] ';
comment on column prerequisite.PERSON.RA_OWNERSHIP is '[RegAddress.ownership] ';
comment on column prerequisite.PERSON.FA_COUNTRY is '[FactAddress.country] Страна';
comment on column prerequisite.PERSON.FA_REGION_TYPE is '[FactAddress.regionType] Тип субъекта РФ (1 уровень КЛАДР)';
comment on column prerequisite.PERSON.FA_REGION_NAME is '[FactAddress.regionName] Регион';
comment on column prerequisite.PERSON.FA_DISTRICT_TYPE is '[FactAddress.districtType] Тип района';
comment on column prerequisite.PERSON.FA_DISTRICT_NAME is '[FactAddress.districtName] Наименование района';
comment on column prerequisite.PERSON.FA_MICRODISTRICT is '[FactAddress.microdistrict] Микрорайон';
comment on column prerequisite.PERSON.FA_LOCATION_TYPE is '[FactAddress.locationType] Тип населенного пункта ';
comment on column prerequisite.PERSON.FA_CITY_TYPE is '[FactAddress.cityType] ';
comment on column prerequisite.PERSON.FA_CITY is '[FactAddress.city] Населенный пукнт (3 уровень КЛАДР)';
comment on column prerequisite.PERSON.FA_STREET_TYPE is '[FactAddress.streetType] Тип улицы (дороги)';
comment on column prerequisite.PERSON.FA_STREET is '[FactAddress.street] Название улицы';
comment on column prerequisite.PERSON.FA_HOUSE is '[FactAddress.house] Дом';
comment on column prerequisite.PERSON.FA_CONSTRUCTION is '[FactAddress.construction] Корпус';
comment on column prerequisite.PERSON.FA_BUILDING is '[FactAddress.building] Строение ';
comment on column prerequisite.PERSON.FA_APARTMENT is '[FactAddress.apartment] Квартира';
comment on column prerequisite.PERSON.FA_POST_INDEX is '[FactAddress.postIndex] Почтовый индекс';
comment on column prerequisite.PERSON.FA_REGISTRATION_DATE is '[FactAddress.registrationDate] Дата регистрации по адресу';
comment on column prerequisite.PERSON.FA_START_LIVING_DATE is '[FactAddress.startLivingDate] Дата заселения по адресу проживания';
comment on column prerequisite.PERSON.FA_GPS is '[FactAddress.gps] Координаты GPS';
comment on column prerequisite.PERSON.FA_FULL_ADDRESS is '[FactAddress.fullAddress] Адрес единой строкой';
comment on column prerequisite.PERSON.FA_CODE_KLADR is '[FactAddress.codeKLADR] КЛАДР-код';
comment on column prerequisite.PERSON.FA_TIME_ZONE is '[FactAddress.timeZone] Часовой пояс';
comment on column prerequisite.PERSON.FA_CODE_KLADE is '[FactAddress.codeKLADE] КЛАДЭ-код';
comment on column prerequisite.PERSON.FA_AUTONOMY_TYPE is '[FactAddress.autonomyType] Тип автономии';
comment on column prerequisite.PERSON.FA_AUTONOMY_NAME is '[FactAddress.autonomyName] Наименование автономии';
comment on column prerequisite.PERSON.FA_LOCATION is '[FactAddress.location] Наименование населенного пункта';
comment on column prerequisite.PERSON.FA_INTRACITY_DISTRICT is '[FactAddress.intracityDistrict] ';
comment on column prerequisite.PERSON.FA_OWNERSHIP is '[FactAddress.ownership] ';
comment on column prerequisite.PERSON.CLIENT_ID is '[CLIENT_ID] FK to Client; Клиент';
comment on column prerequisite.PERSON.PERSON_id is '[PERSON_id] FK to Person; Человек';
comment on column prerequisite.PERSON.PC_person_SEGMENT_id is '[PersonCalcs.segment] ';
comment on column prerequisite.PERSON.PC_TOTAL_INCOME is '[PersonCalcs.totalIncome] ';
create index IDX_PERSON_ORGANIZATION_id on prerequisite.PERSON (ORGANIZATION_id);
create index IDX_PERSON_CLIENT_ID on prerequisite.PERSON (CLIENT_ID);
create index IDX_PERSON_PERSON_id on prerequisite.PERSON (PERSON_id);

CREATE SEQUENCE prerequisite.document_id_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.document(
    document_id integer NOT NULL DEFAULT nextval('prerequisite.document_id_seq'::regclass),
    PERSON_id integer,
    document_type_id integer,
    ISSUE_DATE date,
    SERIAL varchar(40),
    NUMBER varchar(40),
    SUB_DIVISION_CODE varchar(40),
    ISSUER varchar(400),
    IS_ORIGINAL boolean,
    AMOUNT_PAGES integer,
    LINK varchar(400),
    VERSION varchar(40),
    CONSTRAINT document_pkey PRIMARY KEY (document_id)
);

comment on column prerequisite.DOCUMENT.document_id is '[documentID] ID докумаента';
comment on column prerequisite.DOCUMENT.document_type_id is '[documentType] Тип документа';
comment on column prerequisite.DOCUMENT.ISSUE_DATE is '[issueDate] Дата выдачи';
comment on column prerequisite.DOCUMENT.SERIAL is '[serial] Серия';
comment on column prerequisite.DOCUMENT.NUMBER is '[number] Номер';
comment on column prerequisite.DOCUMENT.SUB_DIVISION_CODE is '[subDivisionCode] Код подразделения';
comment on column prerequisite.DOCUMENT.ISSUER is '[issuer] Кем выдан документ';
comment on column prerequisite.DOCUMENT.IS_ORIGINAL is '[isOriginal] Признак оригинала документа';
comment on column prerequisite.DOCUMENT.AMOUNT_PAGES is '[amountPages] Количество страниц в документе';
comment on column prerequisite.DOCUMENT.LINK is '[link] Ссылка на документ (расположение на SFTP или скан-копию)';
comment on column prerequisite.DOCUMENT.VERSION is '[version] Версия документа';
create index IDX_DOCUMENT_PERSON_id on prerequisite.DOCUMENT (PERSON_id);

CREATE SEQUENCE prerequisite.PHONE_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.PHONE(
    PHONE_ID integer NOT NULL DEFAULT nextval('prerequisite.PHONE_ID_seq'::regclass),
    PERSON_id integer,
    PHONE_TYPE_id integer,
    PHONE_NUMBER varchar(40),
    IS_ACTUAL boolean,
    CALLS_COUNT integer,
    CONTACTS_COUNT integer,
    PHONE_CONTACT_DATE_TIME timestamp,
    OPERATOR varchar(200),
    ORGANIZATION_id integer,
    CONSTRAINT PHONE_pkey PRIMARY KEY (PHONE_ID)
);

comment on column prerequisite.PHONE.PERSON_id is '[PERSON_id] FK to Person; Человек';
comment on column prerequisite.PHONE.PHONE_ID is '[phoneID] ';
comment on column prerequisite.PHONE.PHONE_TYPE_id is '[phoneType] ';
comment on column prerequisite.PHONE.PHONE_NUMBER is '[phoneNumber] ';
comment on column prerequisite.PHONE.IS_ACTUAL is '[isActual] ';
comment on column prerequisite.PHONE.CALLS_COUNT is '[callsCount] Количество звонков по номеру';
comment on column prerequisite.PHONE.CONTACTS_COUNT is '[contactsCount] Количество контактов по номеру';
comment on column prerequisite.PHONE.PHONE_CONTACT_DATE_TIME is '[phoneContactDateTime] Дата последнего контакта по номеру';
comment on column prerequisite.PHONE.OPERATOR is '[operator] ';
comment on column prerequisite.PHONE.ORGANIZATION_id is '[ORGANIZATION_id] FK to Organization; Компания';
create index IDX_PHONE_PERSON_id on prerequisite.PHONE (PERSON_id);
create index IDX_PHONE_ORGANIZATION_id on prerequisite.PHONE (ORGANIZATION_id);

CREATE SEQUENCE prerequisite.SOCIAL_NETWORK_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.SOCIAL_NETWORK(
    SOCIAL_NETWORK_ID integer NOT NULL DEFAULT nextval('prerequisite.SOCIAL_NETWORK_ID_seq'::regclass),
    PERSON_id integer,
    NETWORK_TYPE_id integer,
    NETWORK_ACCOUNT varchar(400),
    INTEREST_SEGMENT1 varchar(400),
    INTEREST_SEGMENT2 varchar(400),
    INTEREST_SEGMENT3 varchar(400),
    FRIENDS_NUM integer,
    CONSTRAINT SOCIAL_NETWORK_pkey PRIMARY KEY (SOCIAL_NETWORK_ID)
);

comment on column prerequisite.SOCIAL_NETWORK.SOCIAL_NETWORK_ID is '[networkID] Соц. сеть';
comment on column prerequisite.SOCIAL_NETWORK.NETWORK_TYPE_id is '[networkType] ';
comment on column prerequisite.SOCIAL_NETWORK.NETWORK_ACCOUNT is '[networkAccount] Аккаунт';
comment on column prerequisite.SOCIAL_NETWORK.INTEREST_SEGMENT1 is '[interestSegment1] Сегмент интересов 1';
comment on column prerequisite.SOCIAL_NETWORK.INTEREST_SEGMENT2 is '[interestSegment2] Сегмент интересов 2';
comment on column prerequisite.SOCIAL_NETWORK.INTEREST_SEGMENT3 is '[interestSegment3] Сегмент интересов 3';
comment on column prerequisite.SOCIAL_NETWORK.FRIENDS_NUM is '[friendsNum] Количество друзей';
create index IDX_SO_NE_PERSON_id on prerequisite.SOCIAL_NETWORK (PERSON_id);

CREATE SEQUENCE prerequisite.MESSENGER_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.MESSENGER(
    MESSENGER_ID integer NOT NULL DEFAULT nextval('prerequisite.MESSENGER_ID_seq'::regclass),
    PERSON_id integer,
    MESSENGER_TYPE_id integer,
    MESSENGER_ACCOUNT varchar(50),
    CONSTRAINT MESSENGER_pkey PRIMARY KEY (MESSENGER_ID)
);

create index IDX_MESSENGER_PERSON_id on prerequisite.MESSENGER (PERSON_id);

CREATE SEQUENCE prerequisite.AUTO_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.AUTO(
    AUTO_ID integer NOT NULL DEFAULT nextval('prerequisite.AUTO_ID_seq'::regclass),
    PERSON_id integer,
    MAKE varchar(400),
    MODEL varchar(400),
    YEAR integer,
    IS_CREDIT boolean,
    COST numeric,
    CAR_NUMBER varchar(40),
    AUTO_REG_SERIAL_NUMBER varchar(100),
    VIN varchar(40),
    CONSTRAINT AUTO_pkey PRIMARY KEY (AUTO_ID)
);

comment on column prerequisite.AUTO.AUTO_ID is '[autoID] ';
comment on column prerequisite.AUTO.MAKE is '[make] Марка автомобиля';
comment on column prerequisite.AUTO.MODEL is '[model] Модель автомобиля';
comment on column prerequisite.AUTO.YEAR is '[year] Год выпуска автомобиля';
comment on column prerequisite.AUTO.IS_CREDIT is '[isCredit] Признак покупки авто в кредит';
comment on column prerequisite.AUTO.COST is '[cost] Оценочная стоимость автомобиля';
comment on column prerequisite.AUTO.CAR_NUMBER is '[carNumber] Госномер автомобиля';
comment on column prerequisite.AUTO.AUTO_REG_SERIAL_NUMBER is '[autoRegSerialNumber] Номер свидетельства о регистрации ТС';
comment on column prerequisite.AUTO.VIN is '[vin] ВИН номер автомобиля';
create index IDX_AUTO_PERSON_id on prerequisite.AUTO (PERSON_id);

CREATE SEQUENCE prerequisite.EMPLOYMENT_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.EMPLOYMENT(
    EMPLOYMENT_ID integer NOT NULL DEFAULT nextval('prerequisite.EMPLOYMENT_ID_seq'::regclass),
    PERSON_id integer,
    EMPLOYMENT_POSITION_id integer,
    INCOME_AMOUNT numeric,
    START_WORKING_DATE date,
    END_WORKING_DATE date,
    ORGANIZATION_id integer,
    CONSTRAINT EMPLOYMENT_pkey PRIMARY KEY (EMPLOYMENT_ID)
);

comment on column prerequisite.EMPLOYMENT.EMPLOYMENT_ID is '[employmentID] ';
comment on column prerequisite.EMPLOYMENT.EMPLOYMENT_POSITION_id is '[position] Тип должности';
comment on column prerequisite.EMPLOYMENT.INCOME_AMOUNT is '[incomeAmount] Дохад от данной занятости';
comment on column prerequisite.EMPLOYMENT.START_WORKING_DATE is '[startWorkingDate] Дата начала работы в организации';
comment on column prerequisite.EMPLOYMENT.END_WORKING_DATE is '[endWorkingDate] Дата окончания работы в организации';
comment on column prerequisite.EMPLOYMENT.ORGANIZATION_id is '[Organization.organizationID] Компания; ID организации';
create index IDX_EMPLOYMENT_PERSON_id on prerequisite.EMPLOYMENT (PERSON_id);

CREATE SEQUENCE prerequisite.CLIENT_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.CLIENT(
    CLIENT_ID integer NOT NULL DEFAULT nextval('prerequisite.CLIENT_ID_seq'::regclass),
    ORGANIZATION_id integer,
    RI_REQUEST_TIMESTAMP timestamp,
    RI_STEP_CODE integer,
    RI_CURRENT_DATE date,
    PERSON_id integer,
    CONSTRAINT CLIENT_pkey PRIMARY KEY (CLIENT_ID)
);

create index IDX_CLIENT_CLIENT_ID on prerequisite.CLIENT (CLIENT_ID);
create index IDX_CLIENT_PERSON_ID on prerequisite.CLIENT (PERSON_id);
create index IDX_CLIENT_ORGANIZATION_ID on prerequisite.CLIENT (ORGANIZATION_id);
comment on column prerequisite.CLIENT.RI_REQUEST_TIMESTAMP is '[RequestedInfo.requestTimestamp] ';
comment on column prerequisite.CLIENT.RI_STEP_CODE is '[RequestedInfo.stepCode] ';
comment on column prerequisite.CLIENT.RI_CURRENT_DATE is '[RequestedInfo.currentDate] ';

CREATE SEQUENCE prerequisite.INTEREST_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.INTEREST(
    INTEREST_ID integer NOT NULL DEFAULT nextval('prerequisite.INTEREST_ID_seq'::regclass),
    INTEREST_TYPE_id integer,
    PERSON_id integer,
    CONSTRAINT INTEREST_pkey PRIMARY KEY (INTEREST_ID)
);

create index IDX_INTEREST_PERSON_id on prerequisite.INTEREST (PERSON_id);

CREATE SEQUENCE prerequisite.APPLICATION_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.APPLICATION(
    APPLICATION_ID integer NOT NULL DEFAULT nextval('prerequisite.APPLICATION_ID_seq'::regclass),
    CLIENT_ID integer,
    MANAGER_ID integer,
    IS_ACTUAL boolean,
    APPLICATION_DATE_TIME timestamp,
    APPLICATION_CLOSE_DATE_TIME timestamp,
    APPLICATION_STATUS_id integer,
    APPLICATION_TYPE_id integer,
    APPLICATION_CHANNEL_id integer,
    DELIVERY_DATE_TIME timestamp,
    DELIVERY_FACT_DATE_TIME timestamp,
    AC_ORDERS_TOTAL_COST numeric,
    PROMOTION_ID integer,
    PARTNER_ID integer,
    CONSTRAINT APPLICATION_pkey PRIMARY KEY (APPLICATION_ID)
);

comment on column prerequisite.APPLICATION.APPLICATION_ID is '[applicationID] ';
comment on column prerequisite.APPLICATION.MANAGER_ID is '[managerID] ';
comment on column prerequisite.APPLICATION.IS_ACTUAL is '[isActual] Признак последней заявки';
comment on column prerequisite.APPLICATION.APPLICATION_DATE_TIME is '[applicationDateTime] ';
comment on column prerequisite.APPLICATION.APPLICATION_CLOSE_DATE_TIME is '[applicationCloseDateTime] Дата и время закрытия заявки';
comment on column prerequisite.APPLICATION.APPLICATION_STATUS_id is '[applicationStatus] Cтатус заявки';
comment on column prerequisite.APPLICATION.APPLICATION_TYPE_id is '[applicationType] Тип обработки заявки';
comment on column prerequisite.APPLICATION.APPLICATION_CHANNEL_id is '[applicationChannel] Канал привлечения ';
comment on column prerequisite.APPLICATION.DELIVERY_DATE_TIME is '[deliveryDateTime] ';
comment on column prerequisite.APPLICATION.DELIVERY_FACT_DATE_TIME is '[deliveryFactDateTime] ';
comment on column prerequisite.APPLICATION.AC_ORDERS_TOTAL_COST is '[ApplicationCalcs.ordersTotalCost] ';
comment on column prerequisite.APPLICATION.PROMOTION_ID is '[promotionID] ID промоакции из справочника';
comment on column prerequisite.APPLICATION.PARTNER_ID is '[partnerID] ID партнера из справочника (DICT_PARTNER)';
create index IDX_APPLICATION_CLIENT_ID on prerequisite.APPLICATION (CLIENT_ID);
create index IDX_APPLICATION_ADT on prerequisite.APPLICATION (APPLICATION_DATE_TIME);

CREATE SEQUENCE prerequisite.ADDRESS_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.ADDRESS(
    ADDRESS_ID integer NOT NULL DEFAULT nextval('prerequisite.ADDRESS_ID_seq'::regclass),
    ADDRESS_TYPE_id integer,
    COUNTRY varchar(200),
    REGION_TYPE varchar(200),
    REGION_NAME varchar(200),
    DISTRICT_TYPE varchar(200),
    DISTRICT_NAME varchar(200),
    MICRODISTRICT varchar(200),
    LOCATION_TYPE varchar(200),
    CITY_TYPE varchar(200),
    CITY varchar(200),
    STREET_TYPE varchar(200),
    STREET varchar(200),
    HOUSE varchar(200),
    CONSTRUCTION varchar(200),
    BUILDING varchar(200),
    APARTMENT varchar(200),
    POST_INDEX varchar(200),
    REGISTRATION_DATE date,
    START_LIVING_DATE date,
    GPS varchar(200),
    FULL_ADDRESS varchar(200),
    CODE_KLADR varchar(200),
    TIME_ZONE numeric,
    CODE_KLADE varchar(200),
    AUTONOMY_TYPE varchar(200),
    AUTONOMY_NAME varchar(200),
    LOCATION varchar(200),
    INTRACITY_DISTRICT varchar(200),
    OWNERSHIP boolean,
    APPLICATION_id integer,
    ORGANIZATION_id integer,
    CONSTRAINT ADDRESS_pkey PRIMARY KEY (ADDRESS_ID)
);

comment on column prerequisite.ADDRESS.ADDRESS_ID is '[addressID] ID адреса';
comment on column prerequisite.ADDRESS.ADDRESS_TYPE_id is '[addressType] Тип адреса';
comment on column prerequisite.ADDRESS.COUNTRY is '[country] Страна';
comment on column prerequisite.ADDRESS.REGION_TYPE is '[regionType] Тип субъекта РФ (1 уровень КЛАДР)';
comment on column prerequisite.ADDRESS.REGION_NAME is '[regionName] Регион';
comment on column prerequisite.ADDRESS.DISTRICT_TYPE is '[districtType] Тип района';
comment on column prerequisite.ADDRESS.DISTRICT_NAME is '[districtName] Наименование района';
comment on column prerequisite.ADDRESS.MICRODISTRICT is '[microdistrict] Микрорайон';
comment on column prerequisite.ADDRESS.LOCATION_TYPE is '[locationType] Тип населенного пункта ';
comment on column prerequisite.ADDRESS.CITY_TYPE is '[cityType] ';
comment on column prerequisite.ADDRESS.CITY is '[city] Населенный пукнт (3 уровень КЛАДР)';
comment on column prerequisite.ADDRESS.STREET_TYPE is '[streetType] Тип улицы (дороги)';
comment on column prerequisite.ADDRESS.STREET is '[street] Название улицы';
comment on column prerequisite.ADDRESS.HOUSE is '[house] Дом';
comment on column prerequisite.ADDRESS.CONSTRUCTION is '[construction] Корпус';
comment on column prerequisite.ADDRESS.BUILDING is '[building] Строение ';
comment on column prerequisite.ADDRESS.APARTMENT is '[apartment] Квартира';
comment on column prerequisite.ADDRESS.POST_INDEX is '[postIndex] Почтовый индекс';
comment on column prerequisite.ADDRESS.REGISTRATION_DATE is '[registrationDate] Дата регистрации по адресу';
comment on column prerequisite.ADDRESS.START_LIVING_DATE is '[startLivingDate] Дата заселения по адресу проживания';
comment on column prerequisite.ADDRESS.GPS is '[gps] Координаты GPS';
comment on column prerequisite.ADDRESS.FULL_ADDRESS is '[fullAddress] Адрес единой строкой';
comment on column prerequisite.ADDRESS.CODE_KLADR is '[codeKLADR] КЛАДР-код';
comment on column prerequisite.ADDRESS.TIME_ZONE is '[timeZone] Часовой пояс';
comment on column prerequisite.ADDRESS.CODE_KLADE is '[codeKLADE] КЛАДЭ-код';
comment on column prerequisite.ADDRESS.AUTONOMY_TYPE is '[autonomyType] Тип автономии';
comment on column prerequisite.ADDRESS.AUTONOMY_NAME is '[autonomyName] Наименование автономии';
comment on column prerequisite.ADDRESS.LOCATION is '[location] Наименование населенного пункта';
comment on column prerequisite.ADDRESS.INTRACITY_DISTRICT is '[intracityDistrict] ';
comment on column prerequisite.ADDRESS.OWNERSHIP is '[ownership] ';
comment on column prerequisite.ADDRESS.APPLICATION_id is '[APPLICATION_id] FK to Application; Заявка';
comment on column prerequisite.ADDRESS.ORGANIZATION_id is '[ORGANIZATION_id] FK to Organization; Компания';
create index IDX_ADDRESS_APPLICATION_id on prerequisite.ADDRESS (APPLICATION_id);
create index IDX_ADDRESS_ORGANIZATION_id on prerequisite.ADDRESS (ORGANIZATION_id);

CREATE SEQUENCE prerequisite.ORDER_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.ORDER(
    ORDER_ID integer NOT NULL DEFAULT nextval('prerequisite.ORDER_ID_seq'::regclass),
    APPLICATION_id integer,
    PRODUCT_COUNT integer,
    ORDER_COST numeric,
    ORDER_COMMENT text,
    PRODUCT_id integer,
    CONSTRAINT ORDER_pkey PRIMARY KEY (ORDER_ID)
);

create index IDX_ORDER_APPLICATION_id on prerequisite.ORDER (APPLICATION_id);

CREATE SEQUENCE prerequisite.ACTION_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.ACTION(
    ACTION_ID integer NOT NULL DEFAULT nextval('prerequisite.ACTION_ID_seq'::regclass),
    APPLICATION_id integer,
    ACTION_TYPE_id integer,
    ACTION_RESULT_id integer,
    ACTION_MANAGER_ID integer,
    ACTION_PREVIOUS_MANAGER_ID integer,
    ACTION_DATE_TIME timestamp,
    ACTION_COMMENT text,
    CONSTRAINT ACTION_pkey PRIMARY KEY (ACTION_ID)
);

create index IDX_ACTION_APPLICATION_id on prerequisite.ACTION (APPLICATION_id);

CREATE SEQUENCE prerequisite.CALL_RESULT_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.CALL_RESULT(
    CALL_RESULT_ID integer NOT NULL DEFAULT nextval('prerequisite.CALL_RESULT_ID_seq'::regclass),
    APPLICATION_id integer,
    CALL_TYPE_id integer,
    IS_ACTIVE boolean,
    CALL_DECISION integer,
    CALL_DATE_TIME timestamp,
    CONSTRAINT CALL_RESULT_pkey PRIMARY KEY (CALL_RESULT_ID)
);

create index IDX_CA_RE_APPLICATION_id on prerequisite.CALL_RESULT (APPLICATION_id);

CREATE SEQUENCE prerequisite.REASON_CODE_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.REASON_CODE(
    REASON_CODE_ID integer NOT NULL DEFAULT nextval('prerequisite.REASON_CODE_ID_seq'::regclass),
    CALL_RESULT_id integer,
    REASON_CODE_TYPE_ID integer,
    FIRED_TIMESTAMP timestamp,
    CONSTRAINT REASON_CODE_pkey PRIMARY KEY (REASON_CODE_ID)
);

create index IDX_RE_CO_CALL_RESULT_id on prerequisite.REASON_CODE (CALL_RESULT_id);

CREATE SEQUENCE prerequisite.NEXT_STEP_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.NEXT_STEP(
    NEXT_STEP_ID integer NOT NULL DEFAULT nextval('prerequisite.NEXT_STEP_ID_seq'::regclass),
    CALL_RESULT_id integer,
    STEP_CODE integer,
    STEP_PRIORITY integer,
    STEP_PLANE_DATE_TIME timestamp,
    CONSTRAINT NEXT_STEP_pkey PRIMARY KEY (NEXT_STEP_ID)
);

create index IDX_NE_ST_CALL_RESULT_id on prerequisite.NEXT_STEP (CALL_RESULT_id);

CREATE SEQUENCE prerequisite.RANDOM_VALUE_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.RANDOM_VALUE(
    RANDOM_VALUE_ID bigint NOT NULL DEFAULT nextval('prerequisite.RANDOM_VALUE_ID_seq'::regclass),
    APPLICATION_id integer,
    VALUE numeric,
    CREATE_DATE_TIME timestamp,
    WHERE_USED varchar(200),
    CONSTRAINT RANDOM_VALUE_pkey PRIMARY KEY (RANDOM_VALUE_ID)
);

comment on column prerequisite.RANDOM_VALUE.VALUE is '[value] Значение случайного числа';
comment on column prerequisite.RANDOM_VALUE.CREATE_DATE_TIME is '[whereUsed] Формальное название облисти использования';
comment on column prerequisite.RANDOM_VALUE.WHERE_USED is '[createDateTime] Дата присвоения';
create index IDX_RV_APPLICATION_id on prerequisite.RANDOM_VALUE (APPLICATION_id);

CREATE SEQUENCE prerequisite.STEP_PARAMETER_ID_seq
    INCREMENT 1
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

create table prerequisite.STEP_PARAMETER(
    STEP_PARAMETER_ID bigint NOT NULL DEFAULT nextval('prerequisite.STEP_PARAMETER_ID_seq'::regclass),
    CLIENT_ID integer,
    NEXT_STEP_id integer,
    STEP_PARAMETER_CODE integer,
    STEP_PARAMETER_VALUE varchar(200),
    CONSTRAINT STEP_PARAMETER_pkey PRIMARY KEY (STEP_PARAMETER_ID)
);

comment on column prerequisite.STEP_PARAMETER.STEP_PARAMETER_CODE is '[stepParameterCode] Код параметра для внешней системы - определяет к какому параметру относится заданное значение';
create index IDX_SP_CLIENT_ID on prerequisite.STEP_PARAMETER (CLIENT_ID);
create index IDX_RV_NEXT_STEP_id on prerequisite.STEP_PARAMETER (NEXT_STEP_id);
create index IDX_RV_PARAMETR_ID on prerequisite.STEP_PARAMETER (STEP_PARAMETER_ID);
