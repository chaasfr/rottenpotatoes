package fr.cours.centrale.rottenpotatoes;

import java.util.List;

/**
 * Created by christian on 04/02/16.
 */
public class Film {
    private int id = 0;
    private String titre = null;
    private String titre_ori = null;
    private String affiche = null;
    private String web = null;
    private int duree = 0;
    private String distributeur = null;
    private String participants = null;
    private String realisateur = null;
    private String synopsis = null;
    private int annee = 0;
    private String date_sortie = null;
    private String info = null;
    private Boolean is_visible = false;
    private Boolean is_vente = false;
    private int genreid = 0;
    private int categorieid = 0;
    private String genre = null;
    private String categorie = null;
    private String ReleaseNumber = null;
    private String pays = null;
    private String share_url = null;
    private List<Medias> medias = null;
    private List<Videos> videos = null;
    private Boolean is_avp = false;
    private Boolean is_alaune = false;
    private Boolean is_lastWeek = false;

    public Film(int id, String titre, String titre_ori, String affiche, String web, int duree, String distributeur, String participants, String realisateur, String synopsis, int annee,
                String date_sortie, String info, Boolean is_visible, Boolean is_vente, int genreid, int categorieid, String genre, String categorie, String ReleaseNumber, String pays, String share_url,
                List<Medias> medias, List<Videos> videos, Boolean is_avp, Boolean is_alaune, Boolean is_lastWeek){
        this.id = id;
        this.titre = titre;
        this.titre_ori = titre_ori;
        this.affiche = affiche;
        this.web = web;
        this.duree = duree;
        this.distributeur = distributeur;
        this.participants = participants;
        this.realisateur = realisateur;
        this.synopsis = synopsis;
        this.annee = annee;
        this.date_sortie = date_sortie;
        this.info = info;
        this.is_visible = is_visible;
        this.is_vente = is_vente;
        this.genreid = genreid;
        this.categorieid = categorieid;
        this.genre = genre;
        this.categorie = categorie;
        this.ReleaseNumber = ReleaseNumber;
        this.pays = pays;
        this.share_url = share_url;
        this.medias = medias;
        this.videos = videos;
        this.is_avp = is_avp;
        this.is_alaune = is_alaune;
        this.is_lastWeek = is_lastWeek;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getTitre_ori() {
        return titre_ori;
    }

    public void setTitre_ori(String titre_ori) {
        this.titre_ori = titre_ori;
    }

    public String getAffiche() {
        return affiche;
    }

    public void setAffiche(String affiche) {
        this.affiche = affiche;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getDistributeur() {
        return distributeur;
    }

    public void setDistributeur(String distributeur) {
        this.distributeur = distributeur;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getRealisateur() {
        return realisateur;
    }

    public void setRealisateur(String realisateur) {
        this.realisateur = realisateur;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getDate_sortie() {
        return date_sortie;
    }

    public void setDate_sortie(String date_sortie) {
        this.date_sortie = date_sortie;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Boolean getIs_visible() {
        return is_visible;
    }

    public void setIs_visible(Boolean is_visible) {
        this.is_visible = is_visible;
    }

    public Boolean getIs_vente() {
        return is_vente;
    }

    public void setIs_vente(Boolean is_vente) {
        this.is_vente = is_vente;
    }

    public int getGenreid() {
        return genreid;
    }

    public void setGenreid(int genreid) {
        this.genreid = genreid;
    }

    public int getCategorieid() {
        return categorieid;
    }

    public void setCategorieid(int categorieid) {
        this.categorieid = categorieid;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getReleaseNumber() {
        return ReleaseNumber;
    }

    public void setReleaseNumber(String ReleaseNumber) {
        this.ReleaseNumber = ReleaseNumber;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public  List<Videos> getVideos() {
        return videos;
    }

    public void setVideos( List<Videos> videos) {
        this.videos = videos;
    }

    public Boolean getIs_avp() {
        return is_avp;
    }

    public void setIs_avp(Boolean is_avp) {
        this.is_avp = is_avp;
    }

    public Boolean getIs_alaune() {
        return is_alaune;
    }

    public void setIs_alaune(Boolean is_alaune) {
        this.is_alaune = is_alaune;
    }

    public Boolean getIs_lastWeek() {
        return is_lastWeek;
    }

    public void setIs_lastWeek(Boolean is_lastWeek) {
        this.is_lastWeek = is_lastWeek;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public List<Medias> getMedias() {
        return medias;
    }

    public void setMedias(List<Medias> medias) {
        this.medias = medias;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id=" + id +
                ", titre='" + titre + '\'' +
                ", titre_ori='" + titre_ori + '\'' +
                ", affiche='" + affiche + '\'' +
                ", web='" + web + '\'' +
                ", duree=" + duree +
                ", distributeur='" + distributeur + '\'' +
                ", participants='" + participants + '\'' +
                ", realisateur='" + realisateur + '\'' +
                ", synopsis='" + synopsis + '\'' +
                ", annee=" + annee +
                ", date_sortie='" + date_sortie + '\'' +
                ", info='" + info + '\'' +
                ", is_visible=" + is_visible +
                ", is_vente=" + is_vente +
                ", genreid=" + genreid +
                ", categorieid=" + categorieid +
                ", genre='" + genre + '\'' +
                ", categorie='" + categorie + '\'' +
                ", ReleaseNumber='" + ReleaseNumber + '\'' +
                ", pays='" + pays + '\'' +
                ", share_url='" + share_url + '\'' +
                ", medias=" + medias +
                ", videos=" + videos +
                ", is_avp=" + is_avp +
                ", is_alaune=" + is_alaune +
                ", is_lastWeek=" + is_lastWeek +
                '}';
    }
}
