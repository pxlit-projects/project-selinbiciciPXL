# Architecture

:heavy_check_mark:_(COMMENT) Add a description of the architecture of your application and create a diagram like the one below. Link to the diagram in this document._

![My Architecture](https://github.com/pxlit-projects/project-selinbiciciPXL/blob/main/architecture/Architectuur.svg)

# Synchrone communicatie:

Wanneer je een post op id ophaalt van de postservice, worden de review op id  opgehaald van de reviewservice.
Wanneer je post opent moet je uw comments kunnen zien, dus hiervoor haal je de post met id op via postservice met de bijhorende comments. En de comments op id worden opgehaald van de commentservice.
Wanneer een post word goedgekeurd of afgewezen via de reviewservice, word de status van het post bewerkt via postservice.

# Asynchrone communicatie:
Wanneer eenn post wordt goedgekeurd of afgekeurd met de reviewservice, worden deze reviews van het post naar de wachtrij gstuurd zodat de Postservice deze kan lezen. 


[Source](https://docs.microsoft.com/en-us/dotnet/architecture/cloud-native/introduce-eshoponcontainers-reference-app)
