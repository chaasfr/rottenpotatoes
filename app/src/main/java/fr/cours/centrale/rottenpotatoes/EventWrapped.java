package fr.cours.centrale.rottenpotatoes;

import java.util.List;

/**
 * Created by christian on 15/02/16.
 */
public class EventWrapped {
    private String type;
    private List<Event> events;
    private String titre;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public EventWrapped(String type, List<Event> events, String titre) {
        this.type = type;
        this.events = events;
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "EventWrapped{" +
                "type='" + type + '\'' +
                ", events=" + events +
                ", titre='" + titre + '\'' +
                '}';
    }
}
