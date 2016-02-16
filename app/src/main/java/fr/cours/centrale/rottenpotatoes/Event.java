package fr.cours.centrale.rottenpotatoes;

import java.util.List;

/**
 * Created by christian on 04/02/16.
 */
public class Event {
    private String id;
    private String titre;
    private String soustitre;
    private String affiche;
    private String description;
    private String vad_condition;
    private String partenaire;
    private String date_deb;
    private String date_fin;
    private String heure;
    private String contact;
    private String web_label;
    private String evenementtypeid;
    private List<Film> films;

    public Event(String id, String titre, String soustitre, String affiche, String description, String vad_condition, String partenaire, String date_deb, String date_fin, String heure, String contact, String web_label, String evenementtypeid, List<Film> films) {
        this.id = id;
        this.titre = titre;
        this.soustitre = soustitre;
        this.affiche = affiche;
        this.description = description;
        this.vad_condition = vad_condition;
        this.partenaire = partenaire;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.heure = heure;
        this.contact = contact;
        this.web_label = web_label;
        this.evenementtypeid = evenementtypeid;
        this.films = films;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getSoustitre() {
        return soustitre;
    }

    public void setSoustitre(String soustitre) {
        this.soustitre = soustitre;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVad_condition() {
        return vad_condition;
    }

    public void setVad_condition(String vad_condition) {
        this.vad_condition = vad_condition;
    }

    public String getPartenaire() {
        return partenaire;
    }

    public void setPartenaire(String partenaire) {
        this.partenaire = partenaire;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getWeb_label() {
        return web_label;
    }

    public void setWeb_label(String web_label) {
        this.web_label = web_label;
    }

    public String getEvenementtypeid() {
        return evenementtypeid;
    }

    public void setEvenementtypeid(String evenementtypeid) {
        this.evenementtypeid = evenementtypeid;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", titre='" + titre + '\'' +
                ", soustitre='" + soustitre + '\'' +
                ", affiche='" + affiche + '\'' +
                ", description='" + description + '\'' +
                ", vad_condition='" + vad_condition + '\'' +
                ", partenaire='" + partenaire + '\'' +
                ", date_deb='" + date_deb + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", heure='" + heure + '\'' +
                ", contact='" + contact + '\'' +
                ", web_label='" + web_label + '\'' +
                ", evenementtypeid='" + evenementtypeid + '\'' +
                ", films=" + films +
                '}';
    }
}
