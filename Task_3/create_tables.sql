-- Adminer 4.8.1 PostgreSQL 14.2 (Debian 14.2-1.pgdg110+1) dump

\connect "public";

DROP TABLE IF EXISTS "customer_details";
CREATE TABLE "public"."customer_details" (
    "id" bigint NOT NULL,
    "Customer_id" bigint NOT NULL,
    "Age" integer NOT NULL,
    "Sexe" character(1) NOT NULL,
    "Address_id" bigint NOT NULL,
    "Created_at" timestamp NOT NULL,
    "Updated_at" timestamp NOT NULL
) WITH (oids = false);

INSERT INTO "customer_details" ("id", "Customer_id", "Age", "Sexe", "Address_id", "Created_at", "Updated_at") VALUES
(2322,	3241,	22,	'M',	2154,	'2006-12-16 23:26:22',	'2006-12-17 10:26:22'),
(1233,	3244,	35,	'M',	2222,	'2016-12-17 09:33:54',	'2016-12-17 09:33:54'),
(3422,	2345,	51,	'M',	3254,	'2017-01-01 21:42:51',	'2017-02-01 18:15:24'),
(1323,	3453,	24,	'F',	3247,	'2017-05-11 17:42:20',	'2017-05-11 17:42:20'),
(3533,	4564,	18,	'M',	1589,	'2017-04-12 01:31:05',	'2017-04-12 01:31:05');

DROP TABLE IF EXISTS "customers";
CREATE TABLE "public"."customers" (
    "id" bigint NOT NULL,
    "First_name" character varying(30) NOT NULL,
    "Last_name" character varying(30) NOT NULL,
    "Country" character varying(30) NOT NULL,
    "Created_at" timestamp NOT NULL
) WITH (oids = false);

INSERT INTO "customers" ("id", "First_name", "Last_name", "Country", "Created_at") VALUES
(3241,	'Jamy',	'Oliver',	'United States',	'2006-12-16 03:26:22'),
(3244,	'Francesco',	'Perma',	'Italy',	'2016-12-17 09:33:54'),
(2345,	'Ivan',	'Ivanov',	'Bulgaria',	'2017-01-01 21:42:51'),
(3453,	'Rebecca',	'Smith',	'United Kingdom',	'2017-05-11 17:42:20'),
(4564,	'Daniel',	'Fischer',	'United States',	'2017-04-12 01:31:05');