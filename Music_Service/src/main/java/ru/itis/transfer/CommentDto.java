package ru.itis.transfer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Comment;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    private String description;

    private UserDto user;

    public static CommentDto from(Comment comment) {
        return CommentDto.builder()
                .description(comment.getDescription())
                .user(UserDto.from(comment.getUser()))
                .build();
    }

    public static List<CommentDto> from(List<Comment> comments) {
        return comments.stream().map(CommentDto::from).collect(Collectors.toList());
    }
}
