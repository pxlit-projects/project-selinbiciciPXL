package be.pxl.services.controller;

import be.pxl.services.client.PostClient;
import be.pxl.services.controller.dto.PostRequest;
import be.pxl.services.controller.dto.ReviewResponse;
import be.pxl.services.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final PostClient postClient;



    // postmapping /review/approve
    // postmapping /review/reject
    //ReviewController te plaatsen als de reviewservice echt verantwoordelijk is voor het beoordelen van posts

    //Gebruik ReviewController: Als je de verantwoordelijkheid van goedkeuren/afkeuren logisch vindt passen bij de reviewservice, en je architectuur streeft naar gedecentraliseerde verantwoordelijkheden.
    //Gebruik PostController: Als je de statuswijziging beschouwt als een kernonderdeel van de postservice en je een directe aanpak prefereert.

   /* @PostMapping("/{id}/approve")
    @ResponseStatus(HttpStatus.OK)
    public void approvePost(@PathVariable Long id){
        reviewService.approvePost(id);
    }*/
   // Goedkeuren van een post
   @PostMapping("/{id}/approve")
   public ResponseEntity<PostRequest> approvePost(@PathVariable Long id , @RequestBody PostRequest postRequest) {
       try {
           postClient.approvePost(id, postRequest);
           return ResponseEntity.ok(postRequest);
       } catch (IllegalArgumentException e) {
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(postRequest);
       }
   }
    /**
     * Endpoint voor het ophalen van goedgekeurde reviews.
     */


   /* @PostMapping("/{id}/reject")
    @ResponseStatus(HttpStatus.OK)
    public void rejectPost(@PathVariable Long id){
        reviewService.rejectPost(id);
    }*/




}
