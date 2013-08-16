CREATE TABLE Users
(
  Id bigserial,
  Username text,
  FirstName text,
  MiddleName text,
  LastName text,
  CONSTRAINT User_PK PRIMARY KEY (Id),
  CONSTRAINT User_Username_UK UNIQUE (Username)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE Users
  OWNER TO anaz;
  
INSERT INTO users(
            username, firstname, middlename, lastname)
    VALUES ('hanaanaz', 'Fathmath', 'Hana', 'Anaz');
