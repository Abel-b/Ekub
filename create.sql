CREATE DATABASE ekub;
\c ekub;

CREATE TABLE IF NOT EXISTS usertable(
    id serial PRIMARY KEY,
    username VARCHAR,
    password VARCHAR,
    email VARCHAR,
    phone int,
    groupid int,
);

CREATE TABLE IF NOT EXISTS grouptable(
    id serial PRIMARY KEY,
    groupname VARCHAR,
    groupsize int,
    groupround VARCHAR,
    grouppayment int,
);

CREATE TABLE IF NOT EXISTS contribution(
    id serial PRIMARY KEY,
    payment boolean,

)