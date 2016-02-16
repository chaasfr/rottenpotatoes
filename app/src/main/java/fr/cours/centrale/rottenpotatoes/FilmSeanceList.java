package fr.cours.centrale.rottenpotatoes;

import java.util.List;

/**
 * Created by christian on 04/02/16.
 */
public class FilmSeanceList {
    private String current;
    private String next;
    private List<Film> films;

    public FilmSeanceList(String current, String next, List<Film> Films){
        this.current = current;
        this.next = next;
        this.films=Films;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<Film> getFilmsSeanceList() {
        return films;
    }

    public void setFilmsSeanceList(List<Film> filmsSeanceList) {
        films = filmsSeanceList;
    }


    @Override
    public String toString() {
        return "FilmSeanceList{" +
                "current='" + current + '\'' +
                ", next='" + next + '\'' +
                ", films=" + films +
                '}';
    }
}
