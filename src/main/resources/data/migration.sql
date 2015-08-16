
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
	ticket_group integer
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
	add constraint ADD_ON_GROUP
	foreign key (ticket_group)
	references ticket_groups;


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
	REGISTRATION_FORM_KEY varchar(255),
	TICKET_COUNT integer,
	TICKET_GROUP bigint,
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
	REGISTRATION bigint
);

alter table REGISTRATION
	add constraint UK_5q9q45ncyv753dlfbt2paq8mx unique (REGISTRATION_FORM_KEY);

create index REGISTRATION_FORM_KEY_IDX on REGISTRATION (REGISTRATION_FORM_KEY);

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
