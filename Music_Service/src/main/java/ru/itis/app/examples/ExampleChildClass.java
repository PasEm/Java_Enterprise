package ru.itis.app.examples;

import lombok.*;
import ru.itis.models.Track;
import ru.itis.services.TrackCollectionService;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class ExampleChildClass extends ExampleClass {
    private String name;
    private Track track;
    private TrackCollectionService service;

    public ExampleChildClass(int id, Long number, boolean gender, double cost, String name, Track track, TrackCollectionService service) {
        super(id, number, gender, cost);
        this.name = name;
        this.track = track;
        this.service = service;
    }
}
