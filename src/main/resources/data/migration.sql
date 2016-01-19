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