
drop table purchase_items;
drop table purchase_groups;
drop table event_signups;
--Nov 8
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
        coupon_code varchar(255),
        price numeric (10,2),
        min_purchase integer,
        description text,
        open_date timestamp without time zone,
	close_date timestamp without time zone
);

alter table TICKET_GROUPS
	add constraint GROUPS_EVENTS
	foreign key (event)
	references events;

alter table TICKET_GROUPS
	add constraint GROUPS_SIGNUPS
	foreign key (event_signup)
	references event_signups;

ALTER TABLE TICKET_GROUPS
	OWNER TO devnexus;

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

