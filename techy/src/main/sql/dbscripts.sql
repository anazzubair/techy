-- Table: users

DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  id bigserial NOT NULL,
  username text NOT NULL,
  firstname text NOT NULL,
  middlename text,
  lastname text NOT NULL,
  password text NOT NULL,
  isactive character(1) NOT NULL DEFAULT 'N'::bpchar,
  createdon timestamp without time zone NOT NULL DEFAULT now(),
  createdby bigint NOT NULL,
  modifiedon timestamp without time zone NOT NULL DEFAULT now(),
  modifiedby bigint NOT NULL,
  rowversion bigint NOT NULL DEFAULT 0,
  language text DEFAULT 'ENGLISH'::text, -- Possible values: ENGLISH, FRENCH
  CONSTRAINT user_pk PRIMARY KEY (id),
  CONSTRAINT user_username_uk UNIQUE (username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE users
  OWNER TO techy;
COMMENT ON COLUMN users."language" IS 'Possible values: ENGLISH, FRENCH';


-- Index: users_isactive_idx

DROP INDEX IF EXISTS users_isactive_idx;

CREATE INDEX users_isactive_idx
  ON users
  USING btree
  (isactive COLLATE pg_catalog."default");

  
-- CREATE EXTENSION pgcrypto //Needs to be run as superuser
-- SELECT crypt('password', gen_salt('bf', 10)) //This is the function to get bcrypt hashed password  
  

-- Table: roles

DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
  id bigserial NOT NULL,
  name text NOT NULL,
  isactive character(1) NOT NULL DEFAULT 'Y'::bpchar,
  createdon timestamp without time zone NOT NULL DEFAULT now(),
  createdby bigint NOT NULL,
  modifiedon timestamp without time zone NOT NULL DEFAULT now(),
  modifiedby bigint NOT NULL,
  rowversion bigint NOT NULL DEFAULT 0,
  CONSTRAINT roles_pkey PRIMARY KEY (id),
  CONSTRAINT roles_name_key UNIQUE (name)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE roles
  OWNER TO techy;
  
  
-- Table: userroles

DROP TABLE IF EXISTS userroles;

CREATE TABLE userroles
(
  id bigserial NOT NULL,
  userid bigint NOT NULL,
  roleid bigint NOT NULL,
  createdon timestamp without time zone NOT NULL DEFAULT now(),
  createdby bigint NOT NULL,
  modifiedon timestamp without time zone NOT NULL DEFAULT now(),
  modifiedby bigint NOT NULL,
  rowversion bigint NOT NULL DEFAULT 0,
  CONSTRAINT userroles_pkey PRIMARY KEY (id),
  CONSTRAINT userroles_roleid_fkey FOREIGN KEY (roleid)
      REFERENCES roles (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT userroles_userid_fkey FOREIGN KEY (userid)
      REFERENCES users (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT userroles_userid_roleid_key UNIQUE (userid, roleid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE userroles
  OWNER TO techy;


  
-- Table: messageresources

DROP TABLE IF EXISTS messageresources;

CREATE TABLE messageresources
(
  id bigserial NOT NULL,
  code text NOT NULL,
  en text NOT NULL,
  fr text NOT NULL,
  createdon timestamp without time zone DEFAULT now(),
  createdby bigint,
  modifiedon timestamp without time zone DEFAULT now(),
  modifiedby bigint,
  rowversion bigint NOT NULL DEFAULT 0,
  CONSTRAINT messageresources_pkey PRIMARY KEY (id),
  CONSTRAINT messageresources_code_key UNIQUE (code)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE messageresources
  OWNER TO techy;
COMMENT ON TABLE messageresources
  IS 'I18N Resources for the application';
