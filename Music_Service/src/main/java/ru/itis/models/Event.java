package ru.itis.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "music_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private String city;
    private String country;
    private String avatar;

    @Column(name = "sale_site")
    private String saleSite;

    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "event_participant",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<User> participants;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<Comment> comments;
}