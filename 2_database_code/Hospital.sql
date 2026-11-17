create database Hospital
use Hospital

create table Doctor (

DSSN varchar(20) primary key ,
Name nvarchar(50) not null,
Specialization nvarchar(50) not null

);

create table Patient(

SSN varchar(20) primary key ,
Name nvarchar(50) not null,
Date_admitted datetime not null,
Check_out datetime not null ,
Medical_insur nvarchar(100) not null

);

create table DoctorPatient (

DSSN varchar(20) foreign key references Doctor (DSSN) ,
SSN varchar(20) foreign key  references Patient  (SSN)

);

create table Test(

ID varchar(20) primary key ,
Name nvarchar(50) not null ,
Result nvarchar(50) not null ,
Date_Time DateTime not null  
 
);

create table  DoctorTest (

DSSN varchar(20) foreign key (DSSN) references Doctor (DSSN) ,
ID varchar(20) foreign key (ID) references Test (ID)

);
create table PatientTest(
PSSN varchar(20) foreign key references Patient (SSN),
TestID varchar(20) foreign key references Test (ID));

