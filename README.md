# Platforma E-Learning

Aplicatie Java pentru gestionarea unei platforme de e-learning.
Implementata cu OOP, JDBC si PostgreSQL.

---

## Structura proiectului

```
Platforma/
├── src/
│   ├── config/         - Conexiune la baza de date
│   ├── model/          - Clasele principale
│   ├── repository/     - Operatii CRUD
│   ├── service/        - Logica aplicatiei si CLI
│   └── Main.java
├── lib/
│   └── postgresql-42.7.11.jar
└── audit.csv
```

---

## Cum se ruleaza

1. Adauga `postgresql-42.7.11.jar` in folderul `lib`
2. Configureaza conexiunea in `config/DatabaseConnection.java`
3. Creeaza baza de date:

```sql
CREATE DATABASE platforma_elearning;
```

4. Creeaza tabelele:

```sql
CREATE TABLE utilizatori (
    id SERIAL PRIMARY KEY,
    nume VARCHAR(100),
    email VARCHAR(100),
    tip VARCHAR(20)
);

CREATE TABLE cursuri (
    id SERIAL PRIMARY KEY,
    titlu VARCHAR(200),
    instructor_id INT REFERENCES utilizatori(id)
);

CREATE TABLE inscrieri (
    id SERIAL PRIMARY KEY,
    cursant_id INT REFERENCES utilizatori(id),
    curs_id INT REFERENCES cursuri(id),
    finalizat BOOLEAN DEFAULT FALSE
);

CREATE TABLE lectii (
    id SERIAL PRIMARY KEY,
    titlu VARCHAR(200),
    continut TEXT,
    curs_id INT REFERENCES cursuri(id)
);
```

5. Ruleaza `Main.java`

---

## Audit

Fiecare actiune este inregistrata automat in `audit.csv`:


adaugaUtilizator, 2026-05-29 13:40:10
adaugaCurs, 2026-05-29 13:40:11
