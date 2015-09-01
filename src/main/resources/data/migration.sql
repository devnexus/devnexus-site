
-- 2014 - Nov 9
ALTER TABLE speakers ADD COLUMN cfp_speaker_id bigint;
ALTER TABLE presentations ADD COLUMN cfp_id bigint;

-- 2014 - Dec 7

-- Table: sponsors

-- DROP TABLE sponsors;

CREATE TABLE sponsors
(
id bigint NOT NULL,
event bigint,
name character varying(255),
sponsor_level integer,
sort_order integer,
logo integer,
created_date timestamp without time zone,
updated_date timestamp without time zone,
version integer,
link character varying(255),
CONSTRAINT sponsors_pkey PRIMARY KEY (id),
CONSTRAINT "SPONSOR_LOGO_DATA" FOREIGN KEY (logo)
	  REFERENCES file_data (id) MATCH SIMPLE
	  ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
OIDS=FALSE
);
ALTER TABLE sponsors
OWNER TO devnexus;

--August 14 2015



--Nov 8

drop table purchase_items;
drop table purchase_groups;
drop table event_signups;

create table EVENT_SIGNUPS (
	id bigint primary key NOT NULL,
	created_date timestamp without time zone,
	updated_date timestamp without time zone,
	version integer,
		event integer
);

alter table EVENT_SIGNUPS
	add constraint SIGNUP_EVENTS
	foreign key (event)
	references events;


create table TICKET_GROUPS (
	id bigint primary key NOT NULL,
	created_date timestamp without time zone,
	updated_date timestamp without time zone,
	version integer,
	event integer,
	event_signup integer,
	label varchar(255),
	register_form_url varchar(255),
	price numeric (10,2),
	min_purchase integer,
	max_available_tickets integer,
	description text,
	open_date timestamp without time zone,
	close_date timestamp without time zone
);


create table TICKET_ADD_ONS (
	id bigint primary key NOT NULL,
	created_date timestamp without time zone,
	updated_date timestamp without time zone,
	version integer,
	label varchar(255),
	price numeric (10,2),
	max_available_tickets integer,
        event_signup integer,
	event integer
);

create table COUPON_CODES (
	id bigint primary key NOT NULL,
	created_date timestamp without time zone,
	updated_date timestamp without time zone,
	version integer,
	ticket_group integer,
	price numeric (10,2),
	code varchar(255)
);

alter table TICKET_GROUPS
	add constraint GROUPS_EVENTS
	foreign key (event)
	references events;

alter table TICKET_GROUPS
	add constraint GROUPS_SIGNUPS
	foreign key (event_signup)
	references event_signups;


alter table COUPON_CODES
	add constraint CODE_GROUP
	foreign key (ticket_group)
	references ticket_groups;


alter table TICKET_ADD_ONS
	add constraint ADD_ON_EVENT
	foreign key (event)
	references events;

alter table TICKET_ADD_ONS
	add constraint ADD_ON_EVENT_SIGNUP
	foreign key (event_signup)
	references EVENT_SIGNUPS;

ALTER TABLE TICKET_GROUPS
	OWNER TO devnexus;

    create table REGISTRATION (
        ID bigint primary key NOT NULL,
        CREATED_DATE timestamp without time zone,
        UPDATED_DATE timestamp without time zone,
        VERSION integer,
        COUPON_CODE varchar(255),
        PAYMENT_STATE varchar(255),
        INVOICE varchar(255),
        PAYPAL varchar(255),
CONTACT_EMAIL_ADDRESS varchar(255),
CONTACT_PHONE_NUMBER varchar(255),
CONTACT_NAME  varchar(255),
        REGISTRATION_FORM_KEY varchar(255),
        TICKET_COUNT integer,
        TICKET_GROUP bigint,
        FINAL_COST numeric (10,2),
        EVENT bigint
    );


    create table TICKET_ORDER_DETAILS (
        ID bigint primary key NOT NULL,
        CREATED_DATE timestamp without time zone,
        UPDATED_DATE timestamp without time zone,
        VERSION integer,
        CITY varchar(255),
        COMPANY varchar(255),
        COUNTRY varchar(255),
        EMAIL_ADDRESS varchar(255),
        FIRST_NAME varchar(255),
        JOB_TITLE varchar(255),
        LAST_NAME varchar(255),
        STATE varchar(255),
        T_SHIRT_SIZE varchar(255),
        VEGETARIAN varchar(255),
        SPONSOR_MAY_CONTACT varchar(255),
        TICKET_ADD_ON bigint,
        REGISTRATION bigint
    );

    alter table REGISTRATION 
        add constraint REGISTRATION_UNIQUE_KEY unique (REGISTRATION_FORM_KEY);

    create index REGISTRATION_FORM_KEY_IDX on REGISTRATION (REGISTRATION_FORM_KEY);
    create index TICKET_ORDER_DETAILS_KEY_ADD_ON on ticket_order_details (TICKET_ADD_ON);


ALTER TABLE REGISTRATION
	OWNER TO devnexus;

create index REGISTRATION_FK_IDX on TICKET_ORDER_DETAILS (REGISTRATION );

ALTER TABLE TICKET_ORDER_DETAILS
	OWNER TO devnexus;

alter table TICKET_ORDER_DETAILS
	add constraint FK_e5hscu8ud3o7s2p0vti8h703j
	foreign key (REGISTRATION)
	references REGISTRATION;

ALTER TABLE EVENT_SIGNUPS
	OWNER TO devnexus;



create table PAY_PAL_PAYMENTS (
	ID bigint primary key NOT NULL,
	CREATED_DATE timestamp without time zone,
	UPDATED_DATE timestamp without time zone,
	VERSION integer,
        PAYPAL_ID varchar(255),
        PAYER_ID varchar(255),
        PAYMENT_ID varchar(255),
        REGISTRATION_KEY varchar(255)
);


create table PAYPAL_LINKS (
	ID bigint primary key NOT NULL,
	CREATED_DATE timestamp without time zone,
	UPDATED_DATE timestamp without time zone,
	VERSION integer,
        HREF varchar(255),
        REL varchar(255),
        HTTP_METHOD varchar(255),
	payment bigint
);

create index PAYMENT_REGISTRATION_FORM_KEY_IDX on PAY_PAL_PAYMENTS (REGISTRATION_KEY);

ALTER TABLE PAY_PAL_PAYMENTS
	OWNER TO devnexus;

create index PAYMENT_LINKS_FK_IDX on PAYPAL_LINKS (payment );

ALTER TABLE PAYPAL_LINKS
	OWNER TO devnexus;

alter table PAYPAL_LINKS
	add constraint PAYPAL_LINKS_FK
	foreign key (PAYMENT)
	references PAY_PAL_PAYMENTS;