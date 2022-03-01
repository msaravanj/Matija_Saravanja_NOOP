# Završni projekt - Napredno objektno progamiranje

Projekt sadrži Java Swing GUI aplikaciju u kojoj su korišteni sljedeći predlošci dizajna: predložak MVC (Model-View-Controller) i predložak Command. Aplikacija se zove 'Chess Players & Rating Calculator App' što znači da postoje dvije funkcionalnosti: unos novih šahista koje je moguće spremati u datoteke na računalo i isto tako ih učitavati iz spremljenih datoteka, no igrači se također mogu spremati na server u MySQL bazu podataka. Prije spremanja i dohvaćanja podataka sa servera, potrebno je prvo se povezati sa serverom. Svim opisanim akcijama moguće je pristupiti putem Menu trake unutar GUI-a. GUI se sastoji od panela: lijevi panel koji služi za unos novih šahista koje je zatim moguće spremati u datoteku ili na server (**u slučaju da želimo dodavati nove šahiste na server potrebno je prethodno spojiti se na server (connect) i učitati (load) podatke sa servera i tek onda kreirati nove šahiste jer bi se prilikom kreiranja i spremanja na server bez prethodnog učitavanja, ID-ijevi igrača preklapali i došlo bi do update-a igrača**). Desni panel GUI-a služi za izračun promjene rejtinga igrača ovisno o rejtingu protivnika, rezultatu meča i koeficijentu promjene rejtinga kojeg posjeduje igrač. Rejtinge dvaju igrača moguće je dohvatiti iz API-ja spajanjem na Internet tražeći rejting po FIDE id-u određenog igrača. Sadržaj baze podataka prikazuje se na trećem panelu: prezentacijskom panelu unutar tekstnog područja. 

Predložak MVC korišten je kako bi se odvojio prezentacijski (view) od podatkovnog (model) dijela aplikacije s tim da se između njih nalazi Controller koji predstavlja posrednika na način da delegira njihovim metodama kako ne bi bili u izravnom doticaju.
Predložak Command korišten je kako bi se korištenjem jednog objekta dinamično izmjenjivale naredbe za pojedini JMenuItem u Menu traci.

U aplikaciji korišteni su MySQL server i sljedeći API: https://github.com/xRuiAlves/fide-ratings-scraper/blob/master/README.md#api . API je korišten za dohvat rejtinga igrača koji se pronalazi pomoću FIDE id-a igrača. U MySQL bazu spremaju se podaci o šahistima koji se prethodno unose u lijevom panelu GUI-a.
Također su korištene dvije vanjske biblioteke: mysql connector java 8.0.28 i json_20211205.jar.
MySQL Connector/J dostupan je za preuzimanje na: https://dev.mysql.com/downloads/connector/j/ , a dokumentacija za njegovo korištenje na: https://dev.mysql.com/doc/connector-j/8.0/en/ . Korišten je za uspostavu konekcije sa serverom.
JSON jar file moguće je preuzeti na: https://mvnrepository.com/artifact/org.json/json , a njegova dokumentacija za korištenje na: https://stleary.github.io/JSON-java/index.html . Korišten je za komunikaciju s API-jem.

**Aplikaciju klonirati i pokrenuti po sljedećim uputama:**
  * ```git clone https://github.com/msaravanj/Matija_Saravanja_NOOP```
  * ```(projekt Projekt_Matija_Saravanja otvoriti u IntelliJ IDEA ili Eclipse IDE)```
  * ```(pokrenuti klasu App.java koja se nalazi unutar src.view paketa)```
