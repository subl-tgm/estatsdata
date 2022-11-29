DROP TABLE IF EXISTS testdata;
CREATE TABLE testdata(
   region               VARCHAR(20) NOT NULL
  ,stromverbrauchanteil NUMERIC(20,9) NOT NULL
  ,stromverbrauch       NUMERIC(20,4) NOT NULL
  ,strompreis           BIGINT NOT NULL
  ,CO2Emissionen        NUMERIC(20,9) NOT NULL
  ,Stromimport          NUMERIC(20,9) NOT NULL
  ,Stromexport          NUMERIC(20,9) NOT NULL
  ,date                 INTEGER  NOT NULL
  ,PRIMARY KEY(region,date)
);
