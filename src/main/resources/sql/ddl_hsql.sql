alter table EMPLOYEE drop constraint FK75C8D6AEA791D268;
alter table PROGRAM drop constraint FK185AAA64F5EE9485;
alter table PROGRAM_Planning drop constraint FK5760C2F83A157A65;
alter table PROGRAM_Planning drop constraint FK5760C2F8E9E8AC0F;

drop table COMPANY if exists;
drop table EMPLOYEE if exists;
drop table MUNICIPALITY if exists;
drop table PROGRAM if exists;
drop table PROGRAM_Planning if exists;
drop table Planning if exists;

create table COMPANY (companyId integer not null, address varchar(255), city varchar(255), industry varchar(255), name varchar(255), state varchar(255), zip varchar(255), primary key (companyId));

create table EMPLOYEE (employeeId integer not null, email varchar(255), firstName varchar(255), lastName varchar(255), phone varchar(255), title varchar(255), company_companyId integer, primary key (employeeId));

alter table EMPLOYEE add constraint FK75C8D6AEA791D268 foreign key (company_companyId) references COMPANY;

create table MUNICIPALITY ( id integer generated by default as identity (start with 1), name varchar(255), postalCode varchar(255), primary key (id) );

create table PROGRAM ( id integer generated by default as identity (start with 1), bureauDesignationDate timestamp, bureauName varchar(255), contractType integer, councilDate timestamp, grantDate timestamp, managerDesignationDate timestamp, managerName varchar(255), name varchar(255), notificationDate timestamp, reference varchar(255), municipality_id integer, primary key (id) );

create table PROGRAM_Planning ( PROGRAM_id integer not null, planning_id integer not null, primary key (PROGRAM_id, planning_id), unique (planning_id) );

create table Planning ( id integer generated by default as identity (start with 1), date timestamp, description varchar(255), primary key (id) );

alter table PROGRAM add constraint FK185AAA64F5EE9485 foreign key (municipality_id) references MUNICIPALITY;

alter table PROGRAM_Planning add constraint FK5760C2F83A157A65 foreign key (planning_id) references Planning;

alter table PROGRAM_Planning add constraint FK5760C2F8E9E8AC0F foreign key (PROGRAM_id) references PROGRAM;





