package edu.upc.dsa.models;

public class Prestec {

    private String id;
    private String lectorid;
    private String llibreisbn;
    private String dataprestec;
    private String datadevolucio;
    private String estado;

    public Prestec() {

    }

    public Prestec(String id, String lectorid, String llibreisbn, String dataprestec, String datadevolucio) {
        this.id = id;
        this.lectorid = lectorid;
        this.llibreisbn = llibreisbn;
        this.dataprestec = dataprestec;
        this.datadevolucio = datadevolucio;
        this.estado = "En tramite";
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getLlibreisbn() {
        return llibreisbn;
    }

    public void setLlibreisbn(String llibreisbn) {
        this.llibreisbn = llibreisbn;
    }

    public String getLectorid() {
        return this.lectorid;
    }
    public void setLectorid(String lectorid) {
        this.lectorid = lectorid;
    }

    public String getDataprestec() {
        return dataprestec;
    }

    public void setDataprestec(String dataprestec) {
        this.dataprestec = dataprestec;
    }

    public String getDatadevolucio() {
        return datadevolucio;
    }

    public void setDatadevolucio(String datadevolucio) {
        this.datadevolucio = datadevolucio;
    }

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
