package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@ToString(exclude = "creator")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "track")
public class Track {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long duration;
    private String name;
    private String genre;
    private String url;

    //private Integer viewCount;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    //private Boolean liked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "tracks")
    private List<Playlist> playlists;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "track")
    private List<Comment> comments;
}
