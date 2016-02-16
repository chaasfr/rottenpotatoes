package fr.cours.centrale.rottenpotatoes;

/**
 * Created by christian on 15/02/16.
 */
public class Medias {
    private String path;

    public Medias(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String paths) {
        this.path = paths;
    }

    @Override
    public String toString() {
        return "Medias{" +
                "paths=" + path +
                '}';
    }
}
