CREATE TYPE EtatProjet AS ENUM ('ENCOURS', 'TERMINE', 'SUSPENDU', 'ANNULE');
CREATE TYPE TypeComposant AS ENUM ('MATERIEL', 'MAINDOEUVRE');

CREATE TABLE clients
(
    id               UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom              VARCHAR(255) NOT NULL,
    adresse          VARCHAR(255),
    telephone        VARCHAR(20),
    estProfessionnel boolean
);

CREATE TABLE projets
(
    id         UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom        VARCHAR(255) NOT NULL,
    surface    FLOAT,
    coutTotal  DECIMAL(15,2),
    etatProjet EtatProjet,
    clientId   UUID,
    FOREIGN KEY (clientId) REFERENCES clients (id)
);

CREATE TABLE composants
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nom           VARCHAR(255) NOT NULL,
    tauxTVA       FLOAT,
    typeComposant TypeComposant,
    projetId      UUID,
    FOREIGN KEY (projetId) REFERENCES projets (id)
);

CREATE TABLE materiaux
(
    id                 UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    coutUnitaire       DECIMAL(15,2),
    quantite           FLOAT,
    coutTransport      DECIMAL(10,2),
    coefficientQualite FLOAT,
    composantId        UUID,
    FOREIGN KEY (composantId) REFERENCES composants (id)
);

CREATE TABLE mainDOeuvres
(
    id                  UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    tauxHoraire         DECIMAL(10,2),
    heuresTravail       FLOAT,
    productiviteOuvrier FLOAT,
    composantId         UUID,
    FOREIGN KEY (composantId) REFERENCES composants (id)
);

CREATE TABLE devis
(
    id            UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    montantEstime DECIMAL(10,2),
    dateEmission  DATE,
    dateValidite  DATE,
    TVA           FLOAT,
    accepte       BOOLEAN,
    projetId      UUID,
    FOREIGN KEY (projetId) REFERENCES projets (id)
)


