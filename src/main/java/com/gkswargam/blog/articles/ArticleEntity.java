package com.gkswargam.blog.articles;

import com.gkswargam.blog.users.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Setter
@Builder
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false, unique = true)
    @NonNull
    private String slug;

    @Column
    @Nullable
    private String subtitle;

    @Column(nullable = false)
    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "authordId", nullable = false)
    private UserEntity author;
}
