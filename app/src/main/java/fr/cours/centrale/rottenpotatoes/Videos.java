package fr.cours.centrale.rottenpotatoes;

/**
 * Created by christian on 15/02/16.
 */
public class Videos {
    private String titre;
    private int type;
    private String url;

    public Videos(String titre, int type, String url) {
        this.titre = titre;
        this.type = type;
        this.url = url;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "titre='" + titre + '\'' +
                ", type=" + type +
                ", url='" + url + '\'' +
                '}';
    }
}
