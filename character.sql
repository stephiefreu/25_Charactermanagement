DROP TABLE character CASCADE CONSTRAINTS;

CREATE TABLE character(
    charId INTEGER GENERATED ALWAYS AS IDENTITY,
    name VARCHAR2(50),
    age INTEGER,
    gender VARCHAR2(10),
    hairColor VARCHAR(7),
    skinColor VARCHAR(7),
    shirtColor VARCHAR(7),
    trouserColor VARCHAR(7),
    shoeColor VARCHAR(7),
    CONSTRAINT pkChar PRIMARY KEY (charId),
    CONSTRAINT ckAge CHECK (age BETWEEN 0 AND 123),
    CONSTRAINT ckGender CHECK (gender LIKE 'MALE' OR gender LIKE 'FEMALE'),
    CONSTRAINT ckHair CHECK (REGEXP_LIKE(hairColor, '^#[0-9a-fA-F]{6}$')),
    CONSTRAINT ckSkin CHECK (REGEXP_LIKE(skinColor, '^#[0-9a-fA-F]{6}$')),
    CONSTRAINT ckShirt CHECK (REGEXP_LIKE(shirtColor, '^#[0-9a-fA-F]{6}$')),
    CONSTRAINT ckTrouser CHECK (REGEXP_LIKE(trouserColor, '^#[0-9a-fA-F]{6}$')),
    CONSTRAINT ckShoes CHECK (REGEXP_LIKE(shoeColor, '^#[0-9a-fA-F]{6}$'))
);

INSERT INTO character (name, age, gender, hairColor, skinColor, shirtColor, trouserColor, shoeColor) VALUES ('Steve', 12, 'MALE', '#1a1a1a', '#be997a', '#cf4343', '#253936', '#a5514d');
INSERT INTO character (name, age, gender, hairColor, skinColor, shirtColor, trouserColor, shoeColor) VALUES ('Magda', 14, 'FEMALE', '#322b2a', '#e4d3b1', '#246671', '#ffffff', '#2c3873');

COMMIT;