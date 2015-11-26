CREATE TABLE schools (
    id serial,
    name varchar,
    address varchar,
    postCode varchar,
    post varchar,
    city varchar,
    regon varchar,
    schoolType varchar,
    ownershipType varchar,
    email varchar,
    phone varchar,
    rspo varchar,
    PRIMARY KEY (id)
);

CREATE UNIQUE INDEX schools_rspo_idx ON schools (rspo);