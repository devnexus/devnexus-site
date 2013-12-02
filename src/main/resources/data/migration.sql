alter table events add created_date timestamp without time zone;
alter table events add updated_date timestamp without time zone;
alter table events add version integer;

alter table users add created_date timestamp without time zone;
alter table users add updated_date timestamp without time zone;
alter table users add version integer;

CREATE TABLE cfp_submissions
(
  id bigint NOT NULL,
  created_date timestamp without time zone,
  updated_date timestamp without time zone,
  version integer,
  bio character varying(255),
  first_name character varying(255),
  google_plus_id character varying(255),
  last_name character varying(255),
  linked_in_id character varying(255),
  twitter_id character varying(255),
  description character varying(255),
  email character varying(255),
  phone character varying(255),
  picture_file2 bytea,
  presentation_type bigint,
  session_recording_approved boolean NOT NULL,
  skill_level bigint,
  slot_preference character varying(255),
  title character varying(255),
  topic character varying(255),
  tshirt_size character varying(255),
  picture bigint,
  event bigint,
  CONSTRAINT cfp_submissions_pkey PRIMARY KEY (id),CONSTRAINT fk_93jnud4hv6d4pykxfur3luak1 FOREIGN KEY (event)
      REFERENCES events (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_srgk1qvqa9tx8hrnfa703bifc FOREIGN KEY (picture)
      REFERENCES file_data (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cfp_submissions
  OWNER TO devnexus;

ALTER TABLE organizers ADD COLUMN linked_in_id character varying(255);

ALTER TABLE speakers ADD COLUMN linked_in_id character varying(255);

-- 2013 -Oct 22
ALTER TABLE users ADD COLUMN created_date timestamp without time zone;
-- Change column name to 'updated_date'
ALTER TABLE users ADD COLUMN updated_date timestamp without time zone;
ALTER TABLE users ADD COLUMN version integer;

-- 2013 -Oct 26
ALTER TABLE cfp_submissions ALTER COLUMN bio TYPE character varying(10000);
ALTER TABLE cfp_submissions ALTER COLUMN description TYPE character varying(10000);
ALTER TABLE cfp_submissions ALTER COLUMN slot_preference TYPE character varying(1000);

-- 2013 - Nov 29

create table USER_AUTHORITIES (
	ID int8 not null,
	CREATED_DATE timestamp,
	UPDATED_DATE timestamp,
	VERSION int4,
	AUTHORITY int8,
	USER_ID int8,
	primary key (ID)
);

create index USER_AUTHORITIES_IDX on USER_AUTHORITIES (AUTHORITY)

alter table USER_AUTHORITIES
	add constraint FK_USER_AUTHORITIES_USERS
	foreign key (USER_ID)
	references USERS;

CREATE TABLE user_calendars
(
  id bigint NOT NULL,
  created_date timestamp without time zone,
  updated_date timestamp without time zone,
  version integer,
  schedule_item_id integer,
  event_key character varying(255),
  username character varying(255),
  from_time timestamp without time zone,
  fixed boolean,
  template boolean
);


ALTER TABLE user_calendar ADD CONSTRAINT user_calendar_pkey PRIMARY KEY (id);
CREATE INDEX "user_calendar_lookup" ON user_calendar USING btree (username, event_key, from_time);

ALTER TABLE user_calendars ADD CONSTRAINT user_calendars_pkey PRIMARY KEY (id);
CREATE INDEX "user_calendars_lookup" ON user_calendars USING btree (username, event_key, from_time);

ALTER TABLE user_calendars ADD CONSTRAINT calendar_item FOREIGN KEY (schedule_item_id)
      REFERENCES schedule_items (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;
  

insert into user_calendars (id, event_key, from_time, schedule_item_id, fixed, template) select row_number() OVER (ORDER BY from_time), 'devnexus2013', from_time,case when min(schedule_item_type)=400 then null else min(id) end, case when min(schedule_item_type)=400 then false else true end,true from "public".schedule_items where event = 1388  group by from_time order by from_time asc;
-- 2013 - Dec 04
update events set version = '1' where version is null

-- 2013 - Dec 19
ALTER TABLE cfp_submissions ADD COLUMN status character varying(30);

-- 2013 - Dec 30
update users set version='1' where version is null

-- 2013 - Dec 31
ALTER TABLE organizers ADD COLUMN github_id character varying(255);
ALTER TABLE speakers   ADD COLUMN github_id character varying(255);

ALTER TABLE organizers ADD COLUMN lanyrd_id character varying(255);
ALTER TABLE speakers   ADD COLUMN lanyrd_id character varying(255);

-- 2014 - Jan 5

create table TRACKS (
	ID int8 not null,
	CREATED_DATE timestamp,
	UPDATED_DATE timestamp,
	VERSION int4,
	CSS_STYLE_NAME varchar(255),
	DESCRIPTION varchar(255),
	NAME varchar(255),
	TRACK_ORDER int4,
	EVENT int8,
	primary key (ID)
);

ALTER TABLE tracks OWNER TO devnexus;

alter table TRACKS
	add constraint FK_TRACKS_EVENT
	foreign key (EVENT)
	references EVENTS;

ALTER TABLE PRESENTATIONS ADD COLUMN TRACK_ID integer;

alter table PRESENTATIONS
	add constraint FK_PRESENTATIONS_TRACK_ID
	foreign key (TRACK_ID)
	references TRACKS;

create table PRESENTATIONS_PRESENTATION_TAGS (
	PRESENTATIONS int8 not null,
	PRESENTATION_TAGS int8 not null,
	EVENT int8,
	primary key (PRESENTATIONS, PRESENTATION_TAGS)
);

ALTER TABLE presentations_presentation_tags OWNER TO devnexus;

create table PRESENTATION_TAGS (
	ID int8 not null,
	CREATED_DATE timestamp,
	UPDATED_DATE timestamp,
	VERSION int4,
	NAME varchar(255),
	primary key (ID)
);

ALTER TABLE presentation_tags OWNER TO devnexus;

alter table PRESENTATIONS_PRESENTATION_TAGS
	add constraint FK_PPTAGS_PRESENTATION_TAGS
	foreign key (PRESENTATION_TAGS)
	references PRESENTATION_TAGS;

alter table PRESENTATIONS_PRESENTATION_TAGS
	add constraint FK_PPTAGS_PRESENTATIONS
	foreign key (PRESENTATIONS)
	references PRESENTATIONS;

INSERT INTO tracks VALUES (1, NULL, NULL, 1, 'track-1', NULL, 'HTML5 + JavaScript', 1, 1735);
INSERT INTO tracks VALUES (2, NULL, NULL, 1, 'track-2', NULL, 'Alternative Languages', 2, 1735);
INSERT INTO tracks VALUES (3, NULL, NULL, 1, 'track-3', NULL, 'Cloud', 3, 1735);
INSERT INTO tracks VALUES (4, NULL, NULL, 1, 'track-4', NULL, 'Agile + Tools', 4, 1735);
INSERT INTO tracks VALUES (5, NULL, NULL, 1, 'track-5', NULL, 'Mobile', 5, 1735);
INSERT INTO tracks VALUES (6, NULL, NULL, 1, 'track-6', NULL, 'Java/JavaEE/Spring', 6, 1735);
INSERT INTO tracks VALUES (7, NULL, NULL, 1, 'track-7', NULL, 'Web/Misc', 7, 1735);
INSERT INTO tracks VALUES (8, NULL, NULL, 1, 'track-8', NULL, 'Data + Integration', 8, 1735);
INSERT INTO tracks VALUES (9, NULL, NULL, 1, 'track-9', NULL, 'User Experience', 9, 1735);
INSERT INTO tracks VALUES (10, NULL, NULL, 1, 'track-10', NULL, 'Workshop', 10, 1735);

CREATE SEQUENCE tracks_id_seq start with 11;
ALTER SEQUENCE tracks_id_seq OWNED BY user.devnexus;

nsert into tracks (id, version, css_style_name, name, event)
 select nextval('tracks_id_seq') as id, 1 as version, css_style_name, track, 1388 as event from "public".rooms where event = 1388 and description is not null;

update presentations set track_id = up.track_id from
(select presentation_id, track_id from public.schedule_items sched inner join (select track.id as track_id, room.id as room_id from tracks as track inner join rooms as room on room.css_style_name = track.css_style_name where room.event = 1388) track_map on track_map.room_id = sched.room_id where event = 1388) up
where presentations.id = up.presentation_id;

-- 2014 - Feb 4
ALTER TABLE rooms ADD COLUMN color character varying(255);
ALTER TABLE tracks ADD COLUMN color character varying(255);
