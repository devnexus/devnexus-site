-- Create Android Tokens Table
CREATE TABLE mobile_sign_ins
(
  id bigint NOT NULL,
  created_date timestamp without time zone,
  updated_date timestamp without time zone,
  version integer,
user_id bigint,
token varchar(255),

  CONSTRAINT mobile_sign_ins_pk PRIMARY KEY (id),
  CONSTRAINT mobile_user_fk FOREIGN KEY (user_id)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE user_schedule_items
  OWNER TO devnexus;

CREATE UNIQUE INDEX ON mobile_sign_ins (token);

#July 28, 2016

CREATE TABLE public.cfp_submissions_cfp_submission_speakers
(
  cfp_submissions bigint NOT NULL,
  cfp_submission_speakers bigint NOT NULL,
  CONSTRAINT pk_cfp_submissions_cfp_submission_speakers PRIMARY KEY (cfp_submissions, cfp_submission_speakers),
  CONSTRAINT fk_cfp_submissions FOREIGN KEY (cfp_submissions)
      REFERENCES public.cfp_submissions (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_cfp_submission_speakers FOREIGN KEY (cfp_submission_speakers)
      REFERENCES public.cfp_submission_speakers (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cfp_submissions_cfp_submission_speakers
  OWNER TO devnexus;

ALTER TABLE public.cfp_submission_speakers ADD COLUMN event bigint;
ALTER TABLE public.cfp_submission_speakers
  ADD CONSTRAINT fk_cfp_submission_speakers_events FOREIGN KEY (event)
      REFERENCES public.events (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE public.cfp_submission_speakers ADD COLUMN created_by_user bigint;

ALTER TABLE public.cfp_submission_speakers
  ADD CONSTRAINT fk_cfp_submission_speakers_users FOREIGN KEY (created_by_user)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

ALTER TABLE public.cfp_submissions ADD COLUMN created_by_user bigint;

ALTER TABLE public.cfp_submissions
  ADD CONSTRAINT fk_cfp_submissions_users FOREIGN KEY (created_by_user)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;


-- Aug 3 2016

all tables: event --> event_id
sponsors: logo --. logo_id
presentations presentation_file --> presentation_file_id
presentations_speakers: presentations --> presentations_id
presentations_speakers: speakers --> speakers_id
speakers: picture --> picture_id
presentations_presentation_tags: presentations --> presentations_id
presentations_presentation_tags: presentation_tags --> presentation_tags_id
events_speakers: events --> events_id
events_speakers: speakers --> events_id

cfp_submissions_cfp_submission_speakers: cfp_submissions_id
cfp_submissions_cfp_submission_speakers: cfp_submission_speakers_id
cfp_submissions: picture --> picture_id
cfp_submission_speakers: picture --> picture_id
cfp_submission_speakers: cfp_speaker_image --> cfp_speaker_image_id
cfp_submission_speakers: created_by_user --> created_by_user_id
cfp_submissions: created_by_user --> created_by_user_id

ALTER TABLE public.cfp_submission_speakers DROP COLUMN cfp_submission;

-- Aug 5 2016

CREATE TABLE public.conference_days
(
  id bigint NOT NULL,
  created_date timestamp without time zone NOT NULL,
  updated_date timestamp without time zone,
  day date NOT NULL,
  version integer NOT NULL,
  name character varying(255) NOT NULL,
  event_id bigint NOT NULL,
  description character varying(10000),
  CONSTRAINT conference_days_pkey PRIMARY KEY (id),
  CONSTRAINT conference_days_events_id_fk FOREIGN KEY (event_id)
      REFERENCES public.events (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.conference_days
  OWNER TO devnexus;

-- Aug 11, 2016

ALTER TABLE public.cfp_submission_speakers ADD COLUMN available_entire_event boolean;

UPDATE cfp_submission_speakers
SET available_entire_event = true

ALTER TABLE public.cfp_submission_speakers ALTER COLUMN available_entire_event SET NOT NULL;

-- Aug 12 2016

CREATE TABLE cfp_submission_speaker_conference_days
(
  id bigint NOT NULL,
  conference_day_id bigint NOT NULL,
  cfp_submission_speaker_id bigint NOT NULL,
  created_date timestamp without time zone,
  updated_date timestamp without time zone,
  version integer,
  cfp_speaker_availability character varying(30),
  start_time time,
  end_time time,
  CONSTRAINT pk_cfp_submission_speaker_conference_days PRIMARY KEY (id),
  CONSTRAINT fk_cfp_submission_speaker_conference_days_cf_days FOREIGN KEY (conference_day_id)
      REFERENCES public.conference_days (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_cfp_submission_speaker_conference_days_cfp_speakers FOREIGN KEY (cfp_submission_speaker_id)
      REFERENCES public.cfp_submission_speakers (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cfp_submission_speaker_conference_days
  OWNER TO devnexus;

ALTER TABLE public.cfp_submission_speakers ADD COLUMN company character varying(255);

-- Oct 6 2016

ALTER TABLE public.speakers   ADD COLUMN company character varying(255);
ALTER TABLE public.organizers ADD COLUMN company character varying(255);

-- Oct 23, 2016

CREATE TABLE public.cfp_submission_reviews
(
  id bigint NOT NULL,
  created_date timestamp without time zone,
  updated_date timestamp without time zone,
  version integer,
  comment character varying(10000),
  rating integer,
  cfp_submission_id bigint,
  created_by_user_id bigint,
  CONSTRAINT cfp_submission_reviews_pkey PRIMARY KEY (id),
  CONSTRAINT fk_cfp_submissions_reviews_users FOREIGN KEY (created_by_user_id)
      REFERENCES public.users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_cfp_submissions FOREIGN KEY (cfp_submission_id)
      REFERENCES public.cfp_submissions (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.cfp_submission_reviews
  OWNER TO devnexus;

