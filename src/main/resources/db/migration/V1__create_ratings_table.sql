CREATE TABLE rankigns (
  id SERIAL NOT NULL,
  ranking INTEGER NOT NULL,
  user_id INTEGER NOT NULL,
  school_id INTEGER NOT NULL,
  PRIMARY KEY (id)
)