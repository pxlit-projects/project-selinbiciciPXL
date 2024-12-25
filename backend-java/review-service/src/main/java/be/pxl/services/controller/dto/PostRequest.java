package be.pxl.services.controller.dto;


import be.pxl.services.domain.PostStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data //server leest data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private Long id;

    private String title;

    private String content;

    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;
    private PostStatus status;
}
