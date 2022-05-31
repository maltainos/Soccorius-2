CREATE TABLE users(
	id integer not null primary key unique,
	email varchar(128) not null unique,
	first_name varchar(64) not null,
	last_name varchar(64) not null,
	email_verification_status boolean default false,
	image varchar(128) not null,
	created_on datetime not null,
	updated_on datetime
);