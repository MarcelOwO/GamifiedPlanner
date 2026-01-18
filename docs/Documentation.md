# Dokumentation

## 1. Projektübersicht

Die Applikation GamifiedPlanner ist eine Android-Applikation in Kotlin entwickelt. Das Projekt setzt Die Option 5: Gamifizierter Planer um.

## 2. Funktionale Anforderungen

    - 1.TaskManagement (CRUD): Nutzer koennen Tasks erstellen und diese Bearbeiten und Loeschen.
      Zum Erstellen und Bearbeiten wird dabei ein Popup Fenster erstellt, wo man die Pficht und Optionalen Felder ausfuellen kann. Die Daten werden in Firebase gespeichert
    
    - 2.Tagesansicht: Im Homescreen der App kann man auswaehlen nach welche Tasks man sehen moechte. Dabei gibt es momentan die Wahl zwischen Heute und Alle

    - 3.Statusanpassung: Man kann an jedem Task den Status anpassen, jedoch gibt es derzeit keine Farbaenderung. Falls man ein Task zu fertif wechelt wird dieser abgeschlossen
    
    - 4.Fortschrittsbalken: Im Header der App wird ein Balken angezeiget der Zeigt wie viel der Heutigen Tasks man bereits abgeschlossen hat
    
    - 5.Punkts-System: Beim Abschliessen eines Tasks werden dem Nutzer Coins zugewiesen, mit den der Nutzer sachen aus dem Shop kaufen kann. Jedoch ist der Shop momentan noch nicht fertig implementiert
    
    - 6.Tagesabschluss-Bildschirm: Momentan kann man unter dem Stats Bildschirm die fertigen Tasks beobachenten.
    
    - 7.Benachrichtigungen: Momentan ist dies nicht funktionsfaehig
    
    - 8.Cloud-basiert: Alle Daten des Nutzers werden in Firebase/Firestore abgespeichert

    *Optional:*
    - 9.Erweiterte Visualizierung: Nicht implementiert
    - 10.Zeitplan-Funktion: Nicht implementiert
    - 11.Pomodoro-Timer:  Nicht implementiert
    - 12.Badges und Achievements: W.I.P.
    - 13.Streak- und Level-System: 
    - 14.Sharing-Funktion: Nicht implementiert

## 3. Software Architektur

Die Verwendete Architektur ist MVVM (Model-View-ViewModel)
Dieser Code ist im Directory: `app/src/main/java/marcel/gamifiedplanner/`

- UI Layer (View): `./ui`
  Beinhaltet die Benutzeroberflaeche, welche mit Jetpack Compose erstellt ist.

- ViewModel Layer: `./ui`
  Beinhaltet die ViewModels welche in der App als Bruecke zwischen den UseCases und den Views darstellen und dabei in der Regel den Stande der Views kontrollieren

- Domain Layer (UseCase): `./domain`
  Beinhaltet die haupt domain Logik der Applikation, von den Interfaces fuer die in der Data Layer implementierten Repositories bis zu den verschiedenen UseCases.

- Data Layer (Repository): `./data`
  Beinhaltet die Repository implementationen, welche direkt mit Firebase/Firestore kommunizieren

## 4. Anmerkungen

Im momentanen Stand der Applikation, sind noch einige Funktionen W.I.P.
Unteranderem werden derzeit die Notifikationen nicht gespawnt.

Ausserdem sind derzeit die Tests noch nicht implementiert.
