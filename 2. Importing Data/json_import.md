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

Zusammungfassung:

\set content `cat /home/linux/Desktop/lourve.json`------ for linuk
oder 2:\set content `type C:\Users\moham\Desktop\lourve.json` -------- for windows