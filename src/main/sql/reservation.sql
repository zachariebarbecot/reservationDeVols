DROP VIEW IF EXISTS client_groupe;
DROP TABLE IF EXISTS site;
DROP TABLE IF EXISTS cl_adr;
DROP TABLE IF EXISTS cl_tel;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS vol_avn;
DROP TABLE IF EXISTS vol;
DROP TABLE IF EXISTS cpn_adr;
DROP TABLE IF EXISTS cpn_tel;
DROP TABLE IF EXISTS avn_cpn;
DROP TABLE IF EXISTS compagnie;
DROP TABLE IF EXISTS avion;
DROP TABLE IF EXISTS adresse;
DROP TABLE IF EXISTS telephone;
DROP TABLE IF EXISTS groupe;
DROP TABLE IF EXISTS client;

CREATE TABLE site (
	s_id SERIAL PRIMARY KEY NOT NULL,
	s_nom VARCHAR(50) NOT NULL,
	s_description TEXT
);
INSERT INTO site(s_nom, s_description) VALUES ('VoyageOnline', 'Site de réservations de vols');
/*
*	CLIENT
*/

CREATE TABLE client (
	cl_id SERIAL PRIMARY KEY NOT NULL,
	cl_prenom VARCHAR(25) NOT NULL,
	cl_nom VARCHAR(32) NOT NULL,
	cl_email VARCHAR(100) NOT NULL,
	cl_password VARCHAR(255) NOT NULL
);
INSERT INTO client (cl_prenom, cl_nom, cl_email, cl_password) VALUES ('Zacharie', 'BARBECOT', 'z.barbecot@gmail.com', 'ab4f63f9ac65152575886860dde480a1');
INSERT INTO client (cl_prenom, cl_nom, cl_email, cl_password) VALUES ('Vladimir', 'POUTINE', 'v.poutine@gmail.com', 'ab4f63f9ac65152575886860dde480a1');

CREATE TABLE groupe (
        g_id SERIAL PRIMARY KEY NOT NULL,
	g_nom VARCHAR(20) NOT NULL,
	cl_id INT REFERENCES client(cl_id)
);
INSERT INTO groupe (g_nom, cl_id) VALUES ('Admin', 1);
INSERT INTO groupe (g_nom, cl_id) VALUES ('Client', 2);

CREATE VIEW client_groupe AS
    SELECT c.cl_email as email, c.cl_password as password, g.g_nom as gr FROM client c, groupe g WHERE c.cl_id = g.cl_id;

CREATE TABLE adresse (
	adr_id SERIAL PRIMARY KEY NOT NULL,
	adr_numero INT NOT NULL,
	adr_rue VARCHAR(255) NOT NULL,
	adr_zip INT NOT NULL,
	adr_ville VARCHAR(50) NOT NULL
);
INSERT INTO adresse (adr_numero, adr_rue, adr_zip, adr_ville) VALUES (10, 'Avenue de la Résistance', 93100, 'MONTREUIL');
INSERT INTO adresse (adr_numero, adr_rue, adr_zip, adr_ville) VALUES (23, 'Ilyinka Street', 103132, 'Moscow');
INSERT INTO adresse (adr_numero, adr_rue, adr_zip, adr_ville) VALUES (5959, 'Boulevard de la Côte-Vertu', 666, 'Montréal');
INSERT INTO adresse (adr_numero, adr_rue, adr_zip, adr_ville) VALUES (45, 'Rue de Paris', 95747, 'Roissy CDG Cedex');
INSERT INTO adresse (adr_numero, adr_rue, adr_zip, adr_ville) VALUES (2, 'Aeroport CDG Avenue Charles de Gaulle', 95700, 'Roissy');

CREATE TABLE telephone (
	tel_id SERIAL PRIMARY KEY NOT NULL,
	tel_numero VARCHAR(20)
);
INSERT INTO telephone (tel_numero) VALUES ('06.35.50.56.62');
INSERT INTO telephone (tel_numero) VALUES ('118.218');
INSERT INTO telephone (tel_numero) VALUES ('0.825.120.248');
INSERT INTO telephone (tel_numero) VALUES ('01.41.56.78.00');
INSERT INTO telephone (tel_numero) VALUES ('0.826.46.09.50');

CREATE TABLE cl_adr (
	cl_id INT REFERENCES client(cl_id),
	adr_id INT REFERENCES adresse(adr_id)
);
INSERT INTO cl_adr VALUES (1, 1);
INSERT INTO cl_adr VALUES (2, 2);

CREATE TABLE cl_tel (
	cl_id INT REFERENCES client(cl_id),
	tel_id INT REFERENCES telephone(tel_id)
);
INSERT INTO cl_tel VALUES (1 , 1);
INSERT INTO cl_tel VALUES (2 , 2);
/*
*	COMPAGNIE
*/
CREATE TABLE avion (
	avn_id SERIAL PRIMARY KEY NOT NULL,
	avn_type VARCHAR(50) NOT NULL,
	avn_capacite INT NOT NULL
);
INSERT INTO avion (avn_type, avn_capacite) VALUES ('A380', 853);
INSERT INTO avion (avn_type, avn_capacite) VALUES ('Boeing 777', 550);

CREATE TABLE compagnie (
	cpn_id SERIAL PRIMARY KEY NOT NULL,
	cpn_nom VARCHAR(150) NOT NULL,
	cpn_email VARCHAR(100) NOT NULL
);
INSERT INTO compagnie (cpn_nom, cpn_email) VALUES ('Air Transat', 'air@transat.com');
INSERT INTO compagnie (cpn_nom, cpn_email) VALUES ('Air France', 'air@france.com');
INSERT INTO compagnie (cpn_nom, cpn_email) VALUES ('American Airlines', 'america@airlines.com');

CREATE TABLE cpn_adr (
	cpn_id INT REFERENCES compagnie(cpn_id),
	adr_id INT REFERENCES adresse(adr_id)
);
INSERT INTO cpn_adr VALUES (1, 3);
INSERT INTO cpn_adr VALUES (2, 4);
INSERT INTO cpn_adr VALUES (3, 5);

CREATE TABLE cpn_tel (
	cpn_id INT REFERENCES compagnie(cpn_id),
	tel_id INT REFERENCES telephone(tel_id)
);
INSERT INTO cpn_tel VALUES (1, 3);
INSERT INTO cpn_tel VALUES (2, 4);
INSERT INTO cpn_tel VALUES (3, 5);

CREATE TABLE avn_cpn (
	avn_cpn_id SERIAL PRIMARY KEY NOT NULL,
	cpn_id INT REFERENCES compagnie(cpn_id),
	avn_id INT REFERENCES avion(avn_id)
);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (1, 1);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (1, 1);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (1, 1);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (1, 2);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (2, 1);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (2, 2);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (3, 1);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (3, 2);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (3, 2);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (3, 2);
INSERT INTO avn_cpn (cpn_id, avn_id) VALUES (3, 2);
/*
*	VOL
*/
CREATE TABLE vol (
	vol_id SERIAL PRIMARY KEY NOT NULL,
	vol_depart VARCHAR(100) NOT NULL,
	vol_arrivee VARCHAR(100) NOT NULL,
	vol_prix INT NOT NULL
);
INSERT INTO vol (vol_depart, vol_arrivee, vol_prix) VALUES ('Paris', 'Londres', 50);
INSERT INTO vol (vol_depart, vol_arrivee, vol_prix) VALUES ('Londres', 'Paris', 50);
INSERT INTO vol (vol_depart, vol_arrivee, vol_prix) VALUES ('Paris', 'Pekin', 900);
INSERT INTO vol (vol_depart, vol_arrivee, vol_prix) VALUES ('Pekin', 'Paris', 900);
INSERT INTO vol (vol_depart, vol_arrivee, vol_prix) VALUES ('Berlin', 'New-York', 650);
INSERT INTO vol (vol_depart, vol_arrivee, vol_prix) VALUES ('New-York', 'Berlin', 650);

CREATE TABLE vol_avn (
	vol_avn_id SERIAL PRIMARY KEY NOT NULL,
	vol_id INT REFERENCES vol(vol_id),
	avn_cpn_id INT REFERENCES avn_cpn(avn_cpn_id),
	vol_avn_dateD DATE NOT NULL,
	vol_avn_dateA DATE NOT NULL
);
INSERT INTO vol_avn (vol_id, avn_cpn_id, vol_avn_dateD, vol_avn_dateA) VALUES (1, 6, '2015-06-25', '2015-06-25');
/*
*	RESERVATION
*/
CREATE TABLE reservation (
	rsv_id SERIAL PRIMARY KEY NOT NULL,
	cl_id INT REFERENCES client(cl_id),
	vol_avn_id INT REFERENCES vol_avn(vol_avn_id),
	rsv_date DATE NOT NULL
);
INSERT INTO reservation (cl_id, vol_avn_id, rsv_date) VALUES (1, 1, '2015-05-23');
INSERT INTO reservation (cl_id, vol_avn_id, rsv_date) VALUES (2, 1, '2015-05-20');