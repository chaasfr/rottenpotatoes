package fr.cours.centrale.rottenpotatoes;

/**
 * Created by christian on 04/02/16.
 */
public class Seance {
    private int id;
    private String actual_date;
    private String show_time;
    private boolean is_troisd;
    private boolean is_malentendant;
    private boolean is_handicape;
    private String nationality;
    private int cinemaid;
    private int filmid;
    private String titre;
    private int categorieid;
    private int performanceid;
    private String cinema_salle;

    public Seance(int id, String actual_date, String show_time, boolean is_troisd, boolean is_malentendant, boolean is_handicape, String nationality, int cinemaid,
                  int filmid, String titre, int categorieid, int performanceid, String cinema_salle) {
        this.id = id;
        this.actual_date = actual_date;
        this.show_time = show_time;
        this.is_troisd = is_troisd;
        this.is_malentendant = is_malentendant;
        this.is_handicape = is_handicape;
        this.nationality = nationality;
        this.cinemaid = cinemaid;
        this.filmid = filmid;
        this.titre = titre;
        this.categorieid = categorieid;
        this.performanceid = performanceid;
        this.cinema_salle = cinema_salle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getActual_date() {
        return actual_date;
    }

    public void setActual_date(String actual_date) {
        this.actual_date = actual_date;
    }

    public String getShow_time() {
        return show_time;
    }

    public void setShow_time(String show_time) {
        this.show_time = show_time;
    }

    public boolean getIs_troisd() {
        return is_troisd;
    }

    public void setIs_troisd(boolean is_troisd) {
        this.is_troisd = is_troisd;
    }

    public boolean getIs_malentendant() {
        return is_malentendant;
    }

    public void setIs_malentendant(boolean is_malentendant) {
        this.is_malentendant = is_malentendant;
    }

    public boolean getIs_handicape() {
        return is_handicape;
    }

    public void setIs_handicape(boolean is_handicape) {
        this.is_handicape = is_handicape;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public int getCinemaid() {
        return cinemaid;
    }

    public void setCinemaid(int cinemaid) {
        this.cinemaid = cinemaid;
    }

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public int getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public int getPerformanceid() {
        return performanceid;
    }

    public void setPerformanceid(int performanceid) {
        this.performanceid = performanceid;
    }

    public String getCinema_salle() {
        return cinema_salle;
    }

    public void setCinema_salle(String cinema_salle) {
        this.cinema_salle = cinema_salle;
    }

    @Override
    public String   toString() {
        return "Seance{" +
                "id=" + id +
                ", actual_date='" + actual_date + '\'' +
                ", show_time='" + show_time + '\'' +
                ", is_troisd=" + is_troisd +
                ", is_malentendant=" + is_malentendant +
                ", is_handicape=" + is_handicape +
                ", nationality='" + nationality + '\'' +
                ", cinemaid=" + cinemaid +
                ", filmid=" + filmid +
                ", titre='" + titre + '\'' +
                ", categorieid=" + categorieid +
                ", performanceid=" + performanceid +
                ", cinema_salle='" + cinema_salle + '\'' +
                '}';
    }
}
