# Project - Sistem de gestiune al vanzarii biletelor de avion al unui aeroport
**Tema proiectului** - crearea unui sistem de gestiune al vanzarii biletelor de avion al unui aeroport, precum si stocarea eficienta datelor despre zboruri, avioane, angajati etc.

## Prima parte a proiectului - definire entitati, mosteniri

Proiectul este compus din 14 clasele si, in cele ce urmeaza, o sa prezint ierarhia dintre clasele implementate:
- **clasa Document** - clasa abstracta din care sunt **mostenite** urmatoarele clase: **clasa IdentityCard** si **clasa Ticket**;

- **clasa User** -  clasa abstracta din care sunt **mostenite** urmatoarele clase: **clasa Employee** si **clasa Client**. Este de mentionat ca in cadrul clasei User este utilizat conceptul de **compunere**: contine un obiect de tipul IdentityCard. De asemenea, in cadrul clasei Client apare **compunerea**: contine un obiect de tipul Ticket;

- **clasa Seat** - cu ajutorul acesteia retin date despre un loc dintr-un avion;

- **clasa Airplane** - clasa abstracta din care sunt **mostenite** urmatoarele clase: **clasa InLineAirplane** si **clasa LowCostAirplane**. De mentionat ca in cadrul acestor clase este utilizat conceptul de **compunere**: contin o lista de scaune. Diferenta dintre cele doua clase este ca clasa LowCostAirplane contine o lista de scaune compusa doar din locuri Economy, in vreme ce clasa InLineAirplane contine, pe langa cele Economy, si locuri Business;

- **clasa Zbor** - este **compusa** dintr-un obiect de tip Airplane(fie InLineAirplane, fie LowCostAirplane), o lista de obiecte de tip Ticket si atributele aferente;

- **clasa AirportHenriCoanda** - este o clasa construita utilizand conceptul de **design pattern**, aceasta fiind un **singleton** intrucat poate exista cel mult un obiect de tipul acesteia. Este **compusa** dintr-o lista de Zboruri, o lista de angajati, o lista de clienti si un map care arata fiecarui avion ce pista ii corespunde. In cadrul acesteia se folosesc cele mai multe **actiuni/interogari** precum:
  - addEmployee;
  - displayEmployees;
  - addFlight;
  - removeFlight;
  - addRunway;
  - logInClient;
  - buyTicket;
  - removeTicket;
  - unavailableSeats;
  - mostPopularandRecentFlight etc.
  
- **clasa Main** - in cadru acesteia creez un meniu atat pentru angajati, cat si pentru clienti, delimitand accesul doar la anumite interogari pentru fiecare entitate.
  
- **clasa SortByDate** - clasa care implementeaza **Comparator<Flight>** - utilizata pentru a tine sortata lista de zboruri din clasa AirportHenriCoanda.
  
  Colectiile utilizate sunt **Map** si **List**, a caror utilizare am descris-o mai sus. Fiecare clasa contine atribute protected/private/public, sunt definiti constructori, metode de acces(setteri si getteri), precum si alte metode care m-au ajutat la designul sistemului de gestiune. 
  
  **Initial**, sunt citite dintr-un fisier(data.txt) date despre angajati, acestia fiind adaugati in lista de angajati din AirportHenriCoanda. Restul de date se citesc de la tastatura si se tin memorate in colectii de date.
