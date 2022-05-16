# Entities and Relationships

### artwork (entity)

- **arkId**
Data type: string of characters. \
Description: ark name of the URL identifying each entry.\
Example: cl010061995
    
    **url**
    Data type: string of characters.\
    Description: URL of the Collections site entry.\
    Example: https://collections.louvre.fr/ark:/53355/cl010061995
    

- **title**
Data type: string of characters.\
Description: denomination/title heading.\
Example: Pèlerinage à l'île de Cythère

- **materialsAndTechniques**
Data type: string of characters.\
Description: materials and techniques.\
Example: huile sur bois

- **index**
Data type: data table.\
Description: indexation data corresponding to terms of controlled vocabulary.

- **objectType**: type of object,

### collection (entity)

**~~collection~~ → name**
Data type: string of characters.\
Description: collection to which the object belongs.\
Example: Department of Paintings of the Louvre\
8 possibilities: Egyptian antiquities, Near Eastern antiquities, Greek Etruscan and Roman, Islamic art, Sculptures, Decorative arts, Painting, Prints and drawings)

**wikidata**
Data type: varchar[8]\
Description: ****indicates the Wikidata corresponding to the person***\
Example Q3044768

### creator (entity)

**creator**
Data type: structured data table on the declared attribution of the object. (→ càd type d’entité)\
~~"AttributionLevel" specifies the attribution level where appropriate. It can be a current attribution, a proposed attribution, a former attribution, another attribution or an inventory name.~~\
~~"LinkType" qualifies the type of link between the creator and the artwork.~~\
"Dates" is a multi-valued data table specifying the dates the creator was alive or active.\
"CreatorRole" specifies the role of the creator where appropriate. *zusätzliche CreatorRoles können über die Wikidata und den Wikidata Query Service hinzugefügt werden*\
"AuthenticationType" indicates possible type of authentication.\
"Doubt" is a boolean ( "" | "?" ) indicating possible doubt on the statement.\
~~"AttributedBy" and "attributedYear" are specific to the entries of the Department of Prints and Drawings; they indicate the name of the attributor and the date of attribution.~~\
"Wikidata" indicates the Wikidata corresponding to the person.
Multi-valued property.

Example: `"creator": [{\
            "label": "HOLBEIN Hans le Vieux",\
            "attributionLevel": "attribution actuelle",\
            "linkType": "original",\
            "dates": [{\
                    "date": "vers 1465",\
                    "place": "",\
                    "type": "date 1"\
                }, {\
                    "date": "1524",\
                    "place": "",\\
                    "type": "date 2"\
                }\
            ],\
            "creatorRole": "",\
            "authenticationType": "",\
            "doubt": "",\
            "attributedBy": "Walter Gay",\
            "attributedYear": 1938,\
            "wikidata": "Q49987"\
        }\
]`

### dateCreated (entity)

**dateCreated**
Data type: data table.\
Description: structured data table on the dates and periods of creation.\
"Type" indicates the type of date."StartYear" and "endYear" indicate the start and end years in digital format and "text" indicates the period in textual format.~~"Imprecision" indicates the degree of imprecision.~~Multi-valued property."Doubt" is a boolean ( null | "?" ) ) indicating possible doubt on the statement.\
Example: `"dateCreated": [{\
            "startYear": 395,\
            "endYear": 499,\
            ~~"imprecision": "vers",~~\
            "text": "\u00e9poque byzantine",\
            "type": "Date de cr\u00e9ation\/fabrication",\
            "doubt": null\
        }\
    ]`

### wasCreated (relationship between artwork and dateCreated)

**placeOfCreation**
Data type: string of characters.\
Description: place of creation.\
Example: Damas (Proche-Orient arabe->Syrie)

### museum (entity derivated from heldBy and longTermLoanTo)

- **Entity derivation method**
    
    **Problem:**
    
    Nicht ausgestellt: `”currentLocation = non exposé”`
    
    In einem anderen Museum ausgestellt: \
    `”currentLocation = city (country), museumName, room"`\
    - Saint Paul et saint Barnabé refusant les honneurs divins à Lystre: ****`Arras (France), Mus\u00e9e des Beaux-Arts, salle d\u0027exposition`”; \
    - Étude de tête de femme: `"Gray (France), Mus\u00e9e Baron Martin, salle d\u0027exposition"`;
    
    Im Louvre ausgestellt:
    ”`currentLocation = wing, [collection] roomID - room name”`\
    - Joconde: `"Denon, [Peint] Salle 711 - Salle de la Joconde, Salle 711 - (Salle des Etats)"`\
    -Vénus de Milo: `"Sully, [AGER] Salle 345 - Art grec classique et hell\u00e9nistique (V\u00e9nus de Milo), Hors vitrine "`
    
    **Solution:**
    → wenn im Louvre ausgestellt `(heldBy[0]=Mus\u00e9e du Louvre)`, Informationen aus “room” JSON-properties mit Format `[room], [wing], [level]` auswählen.\
    → wenn in einem anderen Museum ausgestellt d.h. “en dépot / deposited” `(heldBy[0]=Mus\u00e9e du Louvre &&``"longTermLoanTo":"museumName, city")`, \
    → wenn nicht ausgestellt `(currentLocation=”non exposé”)`
    
- **Attributs**
    
    **wikidata**
    Data type: varchar[7]\
    Description: ****indicates the Wikidata corresponding to the currently exposing museum\
    Example: Q516697
    
    **name**
    Data type: string of characters\
    Description: ****name of the currently exposing museum\
    Example: “Château de Compiègne”
    
    **city**
    Data type: string of characters\
    Description: city of the currenty exposing museum\
    Example: “Compiègne”
    
    **country**
    Data type: string of characters\
    Description: coutry of the currenty exposing museum\
    Example: “France”
    ****
