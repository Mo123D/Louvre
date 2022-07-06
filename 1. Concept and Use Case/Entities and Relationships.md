# Entities and Relationships

### artwork (entity)

- **Entity derivation method**
    
    `artwork.arkId` from JSON Documentation `arkId`
    
    `artwork.title` from JSON Documentation `title`
    
    `artwork.materialAndTechniques` from JSON Documentation `materialAndTechniques`
    
    `artwork.objectType` from JSON Documentation `index[8]`
    
- **Attributs**
    
    **arkId**
    Data type: string of characters.
    Description: ark name of the URL identifying each entry.
    Example: cl010061995
    *we deduce the URL bzw. the JSON file by adding [https://collections.louvre.fr/ark:/53355/](https://collections.louvre.fr/ark:/53355/) before bzw .json after*
    
    **title**
    Data type: string of characters.
    Description: denomination/title heading.
    Example: Pèlerinage à l'île de Cythère
    
    **materialsAndTechniques**
    Data type: string of characters.
    Description: materials and techniques.
    Example: huile sur bois
    
    **objectType**
    Data type: string of characters.
    Description: type of object
    Example: 
    

### collection (entity)

- **Entity derivation method**
    
    `collection.name` from JSON Documentation `collection`
    
    `collection.wikidata` query from Wikidata documentation  `wikidata` for our `collection.name`
    
- **Attributs**
    
    **name**
    Data type: string of characters.
    Description: collection to which the object belongs.
    Example: Département des Peintures
    *8 possibilities: Egyptian antiquities, Near Eastern antiquities, Greek Etruscan and Roman, Islamic art, Sculptures, Decorative arts, Painting, Prints and drawings)*
    
    **wikidata**
    Data type: varchar[8]
    Description: ****indicates the Wikidata corresponding to the person
    Example: Q3044768
    

### creator (entity)

- **Entity derivation method**
    
    `creator.name` from JSON Documentation `creator[0]`
    
    `creator.coutryOfCitizenship` query from Wikidata documentation  `wikidata` for our `creator.wikidata`
    
    `creator.dates` from JSON Documentation `creator[3]`
    
    `creator.wikidata` from JSON Documentation `creator[9]`
    
- **Attributs**
    
    **name**
    Data type: string of characters.
    Description: collection to which the object belongs.
    Example: HOLBEIN Hans Le Vieux
    
    **countryOfCitizenship**
    Data type: string of characters
    Description: ****nationality of the creator
    Example: Germany
    
    **dates**
    Data type: ****multi-valued data table 
    Description: dates the creator was alive
    Example: 1465 1525
    
    **wikidata**
    Data type: varchar[6]
    Description: indicates the Wikidata corresponding to the person
    Example: Q49987
    

### dateCreated (entity)

- **Entity derivation method**
    
    `dateCreated.startYear` from JSON Documentation `dateCreated[0]`
    
    `dateCreated.endYear` from JSON Documentation `dateCreated[1]`
    
    `dateCreated.text` from JSON Documentation `dateCreated[3]`
    
    `dateCreated.doubt` from JSON Documentation `dateCreated[5]`
    
- **Attributs**
    
    **startYear**
    Data type: integer
    Description: date the creator started the artwork
    Example: 395
    
    **endYear**
    Data type: integer
    Description: date the creator finished the artwork
    Example: 499
    
    **text**
    Data type: string of characters
    Description: indicates the period in textual format
    Example: Époque byzantine
    
    **doubt**
    Data type: boolean
    Description: indicating possible doubt on the statement (`null` || `?`)
    Example: null
    

### wasCreated (relationship between [artwork](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74) and [dateCreated](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74))

**placeOfCreation**
Data type: string of characters.
Description: place of creation.
Example: Damas (Proche-Orient arabe->Syrie)

### museum (entity derivated from JSON Documentation heldBy and longTermLoanTo)

- **Entity derivation method**
    
    **Problem:**
    
    Nicht ausgestellt: `”currentLocation = non exposé”`
    
    In einem anderen Museum ausgestellt: 
    `”currentLocation = city (country), museumName, room"`
    - Saint Paul et saint Barnabé refusant les honneurs divins à Lystre: ****`Arras (France), Mus\u00e9e des Beaux-Arts, salle d\u0027exposition`”; 
    - Étude de tête de femme: `"Gray (France), Mus\u00e9e Baron Martin, salle d\u0027exposition"`;
    
    Im Louvre ausgestellt:
    ”`currentLocation = wing, [collection] roomID - room name”`
    - Joconde: `"Denon, [Peint] Salle 711 - Salle de la Joconde, Salle 711 - (Salle des Etats)"`
    -Vénus de Milo: `"Sully, [AGER] Salle 345 - Art grec classique et hell\u00e9nistique (V\u00e9nus de Milo), Hors vitrine "`
    
    **Solution:**
    → wenn im Louvre ausgestellt `(heldBy[0]=Mus\u00e9e du Louvre)`, Informationen aus “room” JSON-properties mit Format `[room], [wing], [level]` auswählen.
    → wenn in einem anderen Museum ausgestellt d.h. “en dépot / deposited” `(heldBy[0]=Mus\u00e9e du Louvre &&``"longTermLoanTo":"museumName, city")`, 
    → wenn nicht ausgestellt `(currentLocation=”non exposé”)`
    
- **Attributs**
    
    **wikidata**
    Data type: varchar[7]
    Description: ****indicates the Wikidata corresponding to the currently exposing museum
    Example: Q516697
    
    **name**
    Data type: string of characters
    Description: ****name of the currently exposing museum
    Example: “Château de Compiègne”
    
    **city**
    Data type: string of characters
    Description: city of the currenty exposing museum
    Example: “Compiègne”
    
    **country**
    Data type: string of characters
    Description: coutry of the currenty exposing museum
    Example: “France”
    

### room

- **Entity derivation method**
    
    `room.name` from JSON Documentation `room.split(”,”)[0]`
    
    `room.wing` from JSON Documentation `room.split(”,”)[1]`
    
    `room.level` from JSON Documentation `room.split(”,”)[2]`
    
- **Attributs**
    
    **roomId**
    Data type: varchar[3]
    Description: ****name of the exposing room
    Example: 618
    
    **wing**
    Data type: string of characters
    Description: wing of the exposing room
    Example: “Aile Sully”
    
    **level**
    Data type: varchar[1]
    Description: level of the exposing room
    Example: 1
    

### worksAs (relationship between [creator](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74) and their attributs)

- **Entity derivation method**
    
    active and activeEnd from ****`“creator"->"dates[0]"` and `“creator"->"dates[3]"`
    
    creationRole from JSON Documentation `“creator"->"creatorRole"` and if empty query from Wikidata documentation  `occupation` for our `creator.wikidata`
    
- **Attributs**
    
    **activeStart**
    Data type: integer
    Description: date the creator started being active
    Example: 1465
    
    **activeEnd**
    Data type: integer
    Description: date the creator stopped being active
    Example: 1524
    
    **creationRole**
    Data type: string of characters
    Description: specifies the role of the creator
    Example: painter, xylographer, illustrator
    

### isPartOf (relationship between [artwork](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74) and [collection](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74))

No attributs, MC to 1 cardinality.

### isFoundIn (relationship between [artwork](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74) and [room](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74))

No attributs, MC to 1 cardinality.

### isLocatedIn (relationship between [room](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74) and [museum](https://www.notion.so/Entities-and-Relationships-c106d18d273b42f39fd91b71060e8e74))

No attributs, MC to 1 cardinality.