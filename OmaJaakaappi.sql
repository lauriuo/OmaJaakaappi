DROP DATABASE IF EXISTS omajaakaappi;
CREATE DATABASE omajaakaappi;
USE omajaakaappi;

CREATE TABLE tuote
(
  tuote_id INT NOT NULL AUTO_INCREMENT,
  tuote_nimi VARCHAR(100) NOT NULL,
  tuote_yksikko VARCHAR(100),
  tuote_kcal INT,
  PRIMARY KEY (tuote_id)
);

CREATE TABLE jaakaappi
(
  jaakaappi_id INT NOT NULL AUTO_INCREMENT,
  tuote_pvm DATE,
  tuote_maara INT,
  tuote_status VARCHAR(100) NOT NULL,
  tuote_id INT NOT NULL,
  PRIMARY KEY (jaakaappi_id),
  FOREIGN KEY (tuote_id) REFERENCES tuote(tuote_id)
);

CREATE TABLE resepti
(
  resepti_id INT NOT NULL AUTO_INCREMENT,
  resepti_ohje VARCHAR(1000) NOT NULL,
  PRIMARY KEY (resepti_id)
);

CREATE TABLE rpk
(
  rpk_id INT NOT NULL AUTO_INCREMENT,
  rpk_pvm DATE NOT NULL,
  jaakaappi_id INT NOT NULL,
  PRIMARY KEY (rpk_id),
  FOREIGN KEY (jaakaappi_id) REFERENCES jaakaappi(jaakaappi_id)
);

CREATE TABLE ainekset
(
  aines_id INT NOT NULL AUTO_INCREMENT,
  aines_maara INT,
  tuote_id INT NOT NULL,
  resepti_id INT NOT NULL,
  PRIMARY KEY (aines_id),
  FOREIGN KEY (tuote_id) REFERENCES tuote(tuote_id),
  FOREIGN KEY (resepti_id) REFERENCES resepti(resepti_id)
);
