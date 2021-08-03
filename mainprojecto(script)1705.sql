CREATE DATABASE Mainprojeto;
USE  Mainprojeto;

CREATE TABLE socios(
id_socio INT Not NULL AUTO_INCREMENT,
nome VARCHAR(45),
nif INT,
anoN INT,
dirigente boolean,
id_tipo INT NOT NULL,
id_enc INT NOT NULL,
PRIMARY KEY (id_socio)
);

CREATE TABLE tipoS(
id_tipo INT NOT NULL AUTO_INCREMENT,
descricao VARCHAR(45),
PRIMARY KEY (id_tipo)
);

CREATE TABLE enc(
id_enc INT NOT NULL AUTO_INCREMENT,
nome_enc VARCHAR(45),
PRIMARY KEY (id_enc)
);

CREATE TABLE mensal(
id_mensal INT NOT NULL AUTO_INCREMENT,
mes VARCHAR(20),
nAulas INT,
id_socio INT,
PRIMARY KEY (id_mensal)
);

ALTER TABLE socios
ADD FOREIGN KEY (id_tipo) REFERENCES tipoS (id_tipo);

INSERT INTO tipoS (descricao)VALUES ('Menor');
INSERT INTO tipoS (descricao)VALUES ('Adulto');
INSERT INTO tipoS (descricao)VALUES ('Senior');

ALTER TABLE socios
ADD FOREIGN KEY (id_enc) REFERENCES enc (id_enc);

ALTER TABLE mensal
ADD FOREIGN KEY( id_socio) REFERENCES socios(id_socio);


