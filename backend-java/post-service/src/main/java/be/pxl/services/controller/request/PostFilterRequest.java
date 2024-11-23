package be.pxl.services.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data //server leest data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostFilterRequest {
    private String author;
    private String title;
    private LocalDateTime creationDate;
}
