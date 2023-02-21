DROP TABLE IF EXISTS estats;
CREATE TABLE estats(
   region               VARCHAR(20) NOT NULL
  ,verbrauchAnteil      decimal(20,4) NOT NULL
  ,verbrauch            decimal(20,4) NOT NULL
  ,preis                BIGINT NOT NULL
  ,emission             decimal(20,4) NOT NULL
  ,import               decimal(20,4) NOT NULL
  ,export               decimal(20,4) NOT NULL
  ,date                 datetime  NOT NULL
  ,PRIMARY KEY(region,date)
);

DROP TABLE IF EXISTS voraussagen;
CREATE TABLE voraussagen(
  region					VARCHAR(20) NOT NULL
  ,vorverbrauch             decimal(20,5) NOT NULL
  ,vorpreis      			decimal(20,5) NOT NULL
  ,voremission            	decimal(20,5) NOT NULL
  ,vorimport                decimal(20,5) NOT NULL
  ,vorexport             	decimal(20,5) NOT NULL
  ,PRIMARY KEY(region)
);
