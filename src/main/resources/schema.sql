create table if not exists customer (
	id  uuid PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	password VARCHAR(50) NOT NULL,
	email VARCHAR(50) UNIQUE NOT NULL
);
