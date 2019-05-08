package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.User;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExtendedUserDto {
    private String surname;
    private String firstName;
    private String country;
    private String login;

    private Integer subscribers;

    private LocalDate birthdate;

    private List<PlaylistDto> playlists;

    private List<TrackDto> tracks;

    private List<SubscriptionDto> subscriptions;

    private List<EventDto> events;

    public static ExtendedUserDto from(User user) {
        return ExtendedUserDto.builder()
                .surname(user.getSurname())
                .firstName(user.getFirstName())
                .country(user.getCountry())
                .login(user.getLogin())
                .playlists(PlaylistDto.from(user.getPlaylists()))
                .tracks(TrackDto.from(user.getTracks()))
                .subscribers(user.getSubscribers().size())
                .subscriptions(SubscriptionDto.from(user.getSubscriptions()))
                .build();
    }

    public static List<ExtendedUserDto> from(List<User> users) {
        return users.stream().map(ExtendedUserDto::from).collect(Collectors.toList());
    }
}
