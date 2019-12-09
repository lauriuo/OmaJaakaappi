DROP DATABASE IF EXISTS omajaakaappi;
CREATE DATABASE omajaakaappi;
USE omajaakaappi;

CREATE TABLE tuote
(
  tuote_id INT NOT NULL AUTO_INCREMENT,
  tuote_nimi VARCHAR(100) NOT NULL,
  tuote_yksikko VARCHAR(100),
  tuote_kcal DOUBLE,
  tuote_suola DOUBLE,
  PRIMARY KEY (tuote_id)
);

CREATE TABLE jaakaappi
(
  jaakaappi_id INT NOT NULL AUTO_INCREMENT,
  tuote_pvm DATE,
  tuote_maara DOUBLE,
  tuote_status VARCHAR(100) NOT NULL,
  tuote_id INT NOT NULL,
  PRIMARY KEY (jaakaappi_id),
  FOREIGN KEY (tuote_id) REFERENCES tuote(tuote_id)
);
CREATE TABLE ostoslista
(
  ostoslista_id INT NOT NULL AUTO_INCREMENT,
  tuote_maara DOUBLE,
  tuote_id INT NOT NULL,
  PRIMARY KEY (ostoslista_id),
  FOREIGN KEY (tuote_id) REFERENCES tuote(tuote_id)
);

CREATE TABLE resepti
(
  resepti_id INT NOT NULL AUTO_INCREMENT,
  resepti_nimi VARCHAR(100) NOT NULL,
  resepti_ohje VARCHAR(4000),
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

CREATE TABLE aines
(
  aines_id INT NOT NULL AUTO_INCREMENT,
  aines_maara DOUBLE,
  tuote_id INT NOT NULL,
  resepti_id INT NOT NULL,
  PRIMARY KEY (aines_id),
  FOREIGN KEY (tuote_id) REFERENCES tuote(tuote_id),
  FOREIGN KEY (resepti_id) REFERENCES resepti(resepti_id)
);

INSERT INTO tuote (tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) VALUES ("Egg", "kg", 140, 0.14);
INSERT INTO tuote (tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) VALUES ("Parmesan cheese", "kg", 420, 2.0);
INSERT INTO tuote (tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) VALUES ("Chicken breast", "kg", 190, 0.09);
INSERT INTO tuote (tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) VALUES ("Potato", "kg", 90, 0.008);
INSERT INTO tuote (tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) VALUES ("Pea", "kg", 70, 0.002);
INSERT INTO tuote (tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) VALUES ("Olive oil", "l", 820, 0.002);
INSERT INTO tuote (tuote_nimi, tuote_yksikko, tuote_kcal, tuote_suola) VALUES ("Spinach", "kg", 20, 0.06);
INSERT INTO jaakaappi (tuote_pvm, tuote_maara, tuote_status, tuote_id) VALUES (14/07/2019, 0.5, "Käytettävissä", 1);
INSERT INTO jaakaappi (tuote_pvm, tuote_maara, tuote_status, tuote_id) VALUES (13/07/2019, 0.8, "Käytettävissä", 3);
INSERT INTO resepti (resepti_nimi, resepti_ohje) VALUES ("Parmesan spring chicken",
"Heat grill to medium and line the grill pan with foil. Beat the egg white on a plate with a little salt and pepper. Tip the parmesan onto another plate. Dip the chicken first in egg white, then in the cheese. Grill the coated chicken for 10-12 mins, turning once until browned and crisp.
Meanwhile, boil the potatoes for 10 mins, adding the peas for the final 3 mins, then drain. Toss the vegetables with the spinach leaves, vinegar, oil and seasoning to taste. Divide between four warm plates, then serve with the chicken.");
INSERT INTO aines (aines_maara, tuote_id, resepti_id) VALUES (0.05, 1, 1);
INSERT INTO aines (aines_maara, tuote_id, resepti_id) VALUES (0.02, 2, 1);
INSERT INTO aines (aines_maara, tuote_id, resepti_id) VALUES (0.4, 3, 1);
INSERT INTO aines (aines_maara, tuote_id, resepti_id) VALUES (0.4, 4, 1);
INSERT INTO aines (aines_maara, tuote_id, resepti_id) VALUES (0.15, 5, 1);
INSERT INTO aines (aines_maara, tuote_id, resepti_id) VALUES (0.014, 6, 1);
INSERT INTO aines (aines_maara, tuote_id, resepti_id) VALUES (0.18, 7, 1);