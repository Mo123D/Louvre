# Datenbanken_Projekt
Backup von alle SQL Queries der Datenbanken Projekt des SoSe2022.

## Aufgaben 
<table>
   <tr>
     <th> Aufgabe </th>
     <th> Deliverables </th>
     <th> Person </th>
     <th> Fälligkeitsdatum </th>
  </tr>
   <tr> <th colspan="4"> Entwurf und Plannung </th> </tr>
  <tr>
    <td>  Relationale Algebra & SQL Abfragen </td>
    <td> Anwendugsfälle mit Joins-Operators </td>
    <td> Marie </td>
    <td> 01.06.22 </td>
  </tr>
   <tr>
    <td> Tabelle Entwurf </td>
    <td> Excel der Tabellensicht des ERM Diagramms </td> 
    <td> Lana </td>
    <td> 04.06.22 </td>
  </tr>
   <tr>
    <td> Abfragen überlegen </td>
    <td>text Datei für künftige Nutzen </td>
    <td> Marie </td>
    <td> 19.05.22 </td>
  </tr>
   <tr>
    <td> aussuchen ~200 kunstwerke </td>
    <td> Excel mit kunstwerke info (arkID ist schlussel) </td>
    <td> Lana </td>
    <td> 25.05.22 </td>
  </tr>
   </tr>
   <tr>
    <td> anwendungsbeispiele </td>
    <td> text datei </td>
    <td> Lana </td>
    <td>  </td>
  </tr>
   <tr> <th colspan="4"> Datenbank </th> </tr>
   <tr>
    <td> Server erstellen </td>
     <td> Name in README geschrieben </td>
    <td> Rachael </td>
    <td> 19.05.22 </td>
  </tr>
   <tr>
    <td> Recherchieren JSON importieren </td>
    <td> Notizen / Links für Reference</td>
    <td> Mo </td>
    <td> </td>
  </tr>
   <tr>
    <td> Importieren JSON Attributen </td>
     <td> </td>
    <td> </td>
    <td> </td>
  </tr>
   <tr>
    <td> Bearbeiten DB nach Entwurf </td>
       <td> </td>
    <td> </td>
    <td> </td>
  </tr>
   <tr>
    <td> Kunstwerke in DB hinzufügen (~200) </td>
       <td> </td>
    <td> </td>
    <td> </td>
  </tr> 
      <tr> <th colspan="4"> Präsentationen </th> </tr>
   <tr>
    <td> 5 min Präsentation vobereitenI</td>
    <td> PPT der Hauptidee und Konzept/ Entwurf </td>
    <td> Rachael, Lana </td>
    <td> 02.06.22</td>
  </tr>
   <tr>
    <td> Finale Präs. vorbereiten </td>
    <td> PPT - Konzept, Entwurf, Probleme/Verbesserung <br> 
         Demo Notizen </td>
    <td> Alle </td>
    <td> </td>
  </tr>
   <tr>
    <td> Finale Präs. </td>
       <td> </td>
    <td> Alle </td>
    <td> 07.07.22, 10:30</td>
  </tr>
   <tr>
    <td> Final Paper </td>
    <td> Text Datei </td>
    <td>  </td>
    <td> 15.08.22 </td>
  </tr>
     
</table>

## Wichtige Links
Datenbank name: ikg_b22_gruppe6_louvre

[Louvre JSON Documentation](https://collections.louvre.fr/en/page/documentationJSON) \
Attribute für unsere eigene Datenbank zu extrahieren: \
*coming soon*


ISNI JSON-LD Documentation [(Beispiel Da Vinci)](https://isni.oclc.org/xslt/DB=1.2//CMD?ACT=SRCH&IKT=8006&TRM=ISN%3A0000%200001%202124%20423X&COOKIE=U51,KENDUSER,I28,B0028++++++,SY,NISNI,D1.2,E8b2bf6ce-28,A,H1,,3-28,,30-41,,43-59,,65-70,,74-75,R141.45.38.160,FY) \
Attribute für unsere eigene Datenbank zu extrahieren:
- Name
- ISNI
- Creation Role

[Wikidata Toolkit](https://www.mediawiki.org/wiki/Wikidata_Toolkit)

[Final Presentation](https://docs.google.com/presentation/d/1O_vaW-7Ez3Slp7ga9NQ1AOItniR5x1waId-zmu3Mpx8/edit?usp=sharing)

[Final Schriftlicheabgabe](https://docs.google.com/document/d/1GxQv2ubj48QhS3BBEMHDFG2V8fcEr8Wt_NWicDrO0FA/edit?usp=sharing)

## Stand des aktuellen ER-Diagramms
![ER-Diagramm](https://github.com/marielaporte/Datenbanken_Projekt/blob/main/ER-Diagramm.png)




json import:
Um ein json dokument zu importieren werden wir diese methode nutzen:
 https://kb.objectrocket.com/postgresql/how-to-import-a-json-file-into-postgresql-database-cluster-1437# 
Wir werden den kompleten json file importieren mit den 3 simplen comandos 
1:CREATE TEMP TABLE new_json(info json);
2:\set content `cat /home/linux/Desktop/lourve.json`------ for linuk
oder 2:\set content `type C:\Users\moham\Desktop\lourve.json` -------- for windows
3: INSERT INTO new_json VALUES(:'content');
Fertig :).
zu uberprüfen schreiben wir SELECT * FROM new_json
dies soll alle inhalte rausspucken ;)

wenn wir z.b nur den url von dem json haben wollen nutzen wir diesen filter:

select :'content'::jsonb -> 'url'; 


und danach alles in eine echte tabele importieren :   
![Screenshot 2022-06-01 185031](https://user-images.githubusercontent.com/104974962/171471331-9773f526-ce15-467f-9e12-c36253077d8c.jpg)








