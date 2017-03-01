DROP TABLE IF EXISTS CATEGORY;
DROP TABLE IF EXISTS PET;

create sequence S_CATEGORY START WITH 1;
create sequence S_PET START WITH 1

/*==============================================================*/
/* Table : CATEGORY		                                        */
/*==============================================================*/
CREATE TABLE CATEGORY (
	ID 				VARCHAR(16) 	not null,
	NAME 				VARCHAR(100)		,
	CAT_ID 				VARCHAR(3)  	,
	CONSTRAINT PK_CATEGORY PRIMARY KEY (ID)
);