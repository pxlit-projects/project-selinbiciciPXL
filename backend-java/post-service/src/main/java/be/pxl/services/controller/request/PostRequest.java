package be.pxl.services.controller.request;

import be.pxl.services.domain.PostStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;


@Data //server leest data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostRequest {

    private Long id;
    @NotBlank(message = "Title is required.")
    private String title;
    @NotBlank(message = "Content is required.")
    private String content;
    @NotBlank(message = "Author is required.")
    private String author;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdDate;
    private PostStatus status;

    //createdDate en updatedDate hoeven niet in de JSON te zitten omdat je die automatisch invult in de service.

}
