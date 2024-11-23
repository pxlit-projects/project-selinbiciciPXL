package be.pxl.services.controller;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //server leest data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RejectRequest {

    private String comment;
}
