Derby DB (java DB)

Create a DB called JPA on Derby with following URL: jdbc:derby://localhost:1527/JPA;create=true
Username is "app" and password is "password"

MySQL

Create a DB called JPA on Derby with following URL: jdbc:mysql://localhost/JPA
Username is "root" and password is "password"


drop table PERSONS

PERSONS(ID, FIRSTNAME, LASTNAME, DATEOFBIRTH)

CREATE TABLE PERSONS(
	ID			INT 	PRIMARY KEY,
	FIRSTNAME	VARCHAR(30) NOT NULL,
	LASTNAME	VARCHAR(50) NOT NULL,
	DATEOFBIRTH	DATE
)

INSERT INTO PERSONS VALUES(1, 'Mihrimah', 'Kaldiroglu', {d '2004-08-24'})

SELECT * FROM PERSONS

SELECT COUNT(*) FROM PERSONS

Select * from PERSONS Where lastname = 'Kaldiroglu'




****************************************
Drop Table PERSON_ENTITY



Drop Table PERSON_EM
Drop Table CAR_EM

Drop Table PERSON_TX



****************************************

Drop Table CAR_EM

Drop Table EmployeeTemporal;

Drop Table CONSULTANTST
Drop Table CONSULTANT_INHERATNCE_JOINED
Drop Table CONSULTANT_INHERITANCE_JOINED

Drop Table DIRECTOR_INHERITANCE_JOINED_TABLE
Drop Table EMPLOYEE_INHERITANCE_JOINED_TABLE;
Drop Table EMPLOYEE_MAPPEDSUPERCLASS

Drop Table MANAGER_INHERITANCE_JOINED_TABLE
Drop Table PERSON_INHERITANCE_A_SINGLE_TABLE;
Drop Table PERSON_INHERITANCE_JOINED_TABLE;
Drop Table PERSON_INHERITANCE_SINGLE_TABLE;

Drop Table ManagerInheritanceTPC;
Drop Table EmployeeInheritanceTPC;
Drop Table DirectorInheritanceTPC
Drop Table ConsultantInheritanceTPC
Drop Table PersonInheritanceTPC;

Drop Table PERSONS

Drop Table PERSON_EM;
Drop Table PERSON_ENTITY;
Drop Table PERSONENTITY;
Drop Table PERSON_IDENTITY_ASSIGNED;
Drop Table PERSON_IDENTITY_AUTO;
Drop Table IDGeneratorTable;
Drop Table PersonIdentityComposite1;
Drop Table PersonIdentityComposite2;
Drop Table PersonIdentityIdentity;
Drop Table PersonIdentityTable

Drop Table PERSONTX

Drop Table PersonCarListenerBi;
Drop Table PersonListener;
Drop Table CarListener;

Drop Table PersonLob
Drop Table SEQUENCE
drop Table StudentIdentityTable

Drop Table PersonOne2OneBidir
Drop Table PersonOne2ManyBidir

Drop Table PersonLocking
Drop Table Sequence
Drop Table Hibernate_Sequence

Drop Table Person_em

Drop Table PersonOne2OneBidir
Drop Table PersonOne2ManyBidir

Drop Table MANAGER_INHERITANCE_JOINED_TABLE
Drop Table EMPLOYEE_INHERITANCE_JOINED_TABLE
Drop Table PERSON_INHERITANCE_JOINED_TABLE
Drop Table PERSON_INHERITANCE_SINGLE_TABLE

Drop Table CarOne2ManyBiDir;
Drop Table CarOne2OneBiDir;

Drop Table PersonCarMany2ManyBi;
Drop Table PersonMany2ManyBiDir;
Drop Table CarMany2ManyBiDir;

