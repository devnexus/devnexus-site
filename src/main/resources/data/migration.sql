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
