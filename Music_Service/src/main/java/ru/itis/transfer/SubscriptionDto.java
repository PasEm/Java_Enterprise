package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Subscription;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubscriptionDto {
    private UserDto user;

    public static SubscriptionDto from(Subscription subscription) {
        return SubscriptionDto.builder()
                .user(UserDto.from(subscription.getUser()))
                .build();
    }

    public static List<SubscriptionDto> from(List<Subscription> subscriptions) {
        return subscriptions.stream().map(SubscriptionDto::from).collect(Collectors.toList());
    }
}
