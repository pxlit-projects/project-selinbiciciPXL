package be.pxl.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * Hello world!
 *
 */

//Spring Cloud Gateway biedt drie basiscomponenten die worden gebruikt voor configuratie: routes, predicates en filters.
// Route is de basisbouwsteen van de gateway. Het bevat een bestemmings-URI en een lijst met gedefinieerde predikaten en filters.
// Predicate is verantwoordelijk voor het matchen van alles van het inkomende HTTP-verzoek, zoals headers of parameters.
// Filter kan het verzoek en het antwoord wijzigen voor en na het verzenden naar downstream-services.
// Al deze componenten kunnen worden ingesteld met behulp van configuratie-eigenschappen.
// We zullen het bestand gateway-service.yml maken en op de configuratieserver plaatsen met de routes
// die zijn gedefinieerd voor onze voorbeeld microservices.

@CrossOrigin(origins = {"http://localhost:4200"})
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayServiceApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
//http://localhost:8085/post/api/posts

//Hoe verwerkt de Gateway requests?
//Een Gateway verwerkt verzoeken door als tussenpersoon op te treden tussen clients (zoals browsers of andere applicaties) en backend-services.
// Het doel van een Gateway is om verzoeken te routeren, te beheren en te transformeren voordat ze worden doorgestuurd naar de juiste service.

//1.ontvangst van verzoek(HTTP, HTTPS, .. van client)
//2.Authenticatie en autorisatie(identeit van client verifieren)
//3.Routing(via basis configuratie of logica welke backend-service het verzoek moet verwerken.)

//Voorbeelden van Gateways: GEBRUIKT IN FRONTEND
//NGINX of HAProxy (veel gebruikt als omgekeerde proxy en load balancer).