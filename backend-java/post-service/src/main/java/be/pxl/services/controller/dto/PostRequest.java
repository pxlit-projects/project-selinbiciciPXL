package be.pxl.services.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private Long id;
    private String title;
    private String content;
    private String author;
    private LocalDateTime createdDate;
}
