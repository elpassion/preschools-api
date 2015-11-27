CREATE EXTENSION IF NOT EXISTS plpgsql;

ALTER TABLE schools ADD COLUMN stars NUMERIC(8,2) NOT NULL DEFAULT 0;

CREATE OR REPLACE FUNCTION calculate_stars() RETURNS TRIGGER AS
$BODY$
BEGIN
    update schools set stars = (select avg(stars) from rates where schoolId = NEW.schoolId) WHERE id = NEW.schoolId;
    RETURN NEW;
END;
$BODY$ LANGUAGE plpgsql VOLATILE
COST 100;

CREATE TRIGGER calculate_stars_trigger
  AFTER INSERT
  ON rates
  FOR EACH ROW
  EXECUTE PROCEDURE calculate_stars();
