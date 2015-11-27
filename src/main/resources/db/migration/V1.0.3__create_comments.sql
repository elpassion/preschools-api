CREATE TABLE comments (
    id serial NOT NULL,
    schoolId integer NOT NULL,
    nick varchar NOT NULL,
    body varchar NOT NULL,
    created_at timestamp with time zone NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE comments ADD constraint comments_schools_fk FOREIGN KEY (schoolId) REFERENCES schools ON DELETE CASCADE;
CREATE INDEX comments_schoolId_idx ON comments(schoolId);