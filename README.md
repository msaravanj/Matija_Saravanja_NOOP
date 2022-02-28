# Završni projekt - Napredno objektno progamiranje

Projekt sadrži Java Swing GUI aplikaciju u kojoj su korišteni sljedeći predlošci dizajna: predložak MVC (Model-View-Controller) i predložak Command. Aplikacija se zove 'Chess Players & Rating Calculator App' što znači da postoje dvije funkcionalnosti: unos novih šahovskih igrača koje je moguće spremati u datoteke na računalo i isto tako ih učitavati iz spremljenih datoteka, no igrači se također mogu spremati na server u MySQL bazu podataka. Prije spremanja i dohvaćanja podataka sa servera, potrebno je prvo se povezati sa serverom. Svim opisanim akcijama moguće je pristupiti putem Menu trake unutar GUI-a. GUI se sastoji od dviju cjelina: lijevi dio koji služi za unos novih šahista koje je zatim moguće spremati u datoteku ili na server (**u slučaju da želimo dodavati nove šahiste na server potrebno je prethodno spojiti se na server (connect) i učitati (load) podatke sa servera i tek onda kreirati nove šahiste jer bi se prilikom kreiranja i spremanja na server bez prethodnog učitavanja ID igrača preklapali i došlo bi do update-a igrača**). Desni dio GUI-a služi za izračun promjene rejtinga igrača ovisno o rejtingu protivnika, rezultatu meča i koeficijentu promjene rejtinga kojeg posjeduje igrač. Rejtinge dvaju igrača moguće je dohvatiti iz API-ja spajanjem na Internet tražeći po FIDE id-u određenog igrača.

**Aplikaciju klonirati i pokrenuti po sljedećim uputama:**
  * ```git clone https://github.com/msaravanj/Matija_Saravanja_NOOP/Projekt_Matija_Saravanja```
  * ```(projekt otvoriti u IntelliJ IDEA ili Eclipse IDE)```
  * ```(pokrenuti klasu App.java koja se nalazi unutar src.view paketa)```


U aplikaciji korišten je sljedeći API: https://github.com/xRuiAlves/fide-ratings-scraper/blob/master/README.md#api .
Također su korištene dvije vanjske biblioteke: mysql connector java 8.0.28 i json_20211205.jar.
