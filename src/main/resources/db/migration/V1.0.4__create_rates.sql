CREATE TABLE rates (
    id serial not null,
    schoolId integer not null,
    stars integer not null default 0,
    ip varchar,
    createdAt timestamp with time zone,
    PRIMARY KEY (id)
);

ALTER TABLE rates ADD constraint rates_schools_fk FOREIGN KEY (schoolId) REFERENCES schools ON DELETE CASCADE;
CREATE INDEX rates_schoolId_idx ON rates(schoolId);