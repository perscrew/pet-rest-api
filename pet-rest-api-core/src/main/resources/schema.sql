DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS PET;
DROP SEQUENCE IF EXISTS S_PET;

create sequence S_PET START WITH 0;

/*==============================================================*/
/* Table : PET		                                        */
/*==============================================================*/
CREATE TABLE PET (
	ID 				    BIGINT           not null,
	NAME 				VARCHAR(150)     not null,
	QUANTITY            INT              not null,
	CAT_ID              BIGINT           not null,
	CONSTRAINT PK_PET PRIMARY KEY (ID)
);


/*==============================================================*/
/* Table : GATEGORY		                                        */
/*==============================================================*/
CREATE TABLE CATEGORY (
	ID 				    BIGINT           not null,
	NAME 				VARCHAR(150)     not null,
	CONSTRAINT PK_CATEGORY PRIMARY KEY (ID)
);