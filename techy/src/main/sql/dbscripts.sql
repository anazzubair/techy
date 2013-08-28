-- Table: users

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id bigserial NOT NULL,
  username text,
  firstname text,
  middlename text,
  lastname text,
  isactive character(1) NOT NULL DEFAULT 'N'::bpchar,
  createdon timestamp without time zone NOT NULL DEFAULT now(),
  createdby bigint NOT NULL,
  modifiedon timestamp without time zone NOT NULL DEFAULT now(),
  modifiedby bigint NOT NULL,
  rowversion bigint NOT NULL DEFAULT 0,
  CONSTRAINT user_pk PRIMARY KEY (id),
  CONSTRAINT user_username_uk UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO anaz;

