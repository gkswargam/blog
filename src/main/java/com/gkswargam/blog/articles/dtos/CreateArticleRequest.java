package com.gkswargam.blog.articles.dtos;

import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {
    @NonNull
    private String title;
    @NonNull
    private String body;
    @Nullable
    private String subTitle;
}
