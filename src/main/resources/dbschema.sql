CREATE MEMORY TABLE PUBLIC.OWNERS(
    ID BIGINT NOT NULL auto_increment,
    CITY VARCHAR(255),
    FIRST_NAME VARCHAR(255),
    LAST_NAME VARCHAR(255)
);

CREATE MEMORY TABLE PUBLIC.PETS(
    ID BIGINT NOT NULL auto_increment,
    BIRTHDAY VARCHAR(255),
    NAME VARCHAR(255),
    OWNER_ID BIGINT
);