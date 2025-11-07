package edu.upc.dsa.models;

import java.util.*;

public class Lector{
    private String id;
    private String nom;
    private String dni;
    private String anynaixement;
    private String llocdenaixement;
    private String lastname;
    private String adresse;
    private List<Prestec> prestecs;

    public Lector(){
        this.prestecs = new ArrayList<>();
    }

    public Lector(String id, String nom, String lastname, String dni, String anynaixement, String llocdenaixement, String adresse) {
        this.id = id;
        this.nom = nom;
        this.lastname = lastname;
        this.dni = dni;
        this.anynaixement = anynaixement;
        this.llocdenaixement = llocdenaixement;
        this.lastname = lastname;
        this.adresse = adresse;
        this.prestecs = new ArrayList<>();
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }
    public String getLlocdenaixement(){
        return this.llocdenaixement;
    }
    public void setLlocdenaixement(String llocdenaixement) {
        this.llocdenaixement = llocdenaixement;
    }

    public String getAnynaixement() {
        return anynaixement;
    }

    public void setAnynaixement(String anynaixement) {
        this.anynaixement = anynaixement;
    }
    public List<Prestec> getPrestecs() {
        return prestecs;
    }
    public void setPrestecs(List<Prestec> prestecs) {
        this.prestecs = prestecs;
    }

    public void addPrestec(Prestec p) {
        this.prestecs.add(p);
    }
}
