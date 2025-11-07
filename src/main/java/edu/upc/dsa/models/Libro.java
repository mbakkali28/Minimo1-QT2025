package edu.upc.dsa.models;

public class Libro {

    private String id;
    private String isbn;
    private String titol;
    private String editorial;
    private int any;
    private int edicion;
    private String autor;
    private String tematica;
    private int cantidad;

    public Libro() {
        this.cantidad = 0;
    }

    public Libro(String id, String isbn, String titol, String editorial, int any, int edicion, String autor, String tematica) {
        this.id = id;
        this.isbn = isbn;
        this.titol = titol;
        this.editorial = editorial;
        this.any = any;
        this.edicion = edicion;
        this.autor = autor;
        this.tematica = tematica;
        this.cantidad = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public int getAny(){
        return any;
    }

    public void setAny(int any){
        this.any = any;
    }

    public int getEdicion(){
        return edicion;
    }
    public void setEdicion(int edicion){
        this.edicion = edicion;
    }

    public String getAutor(){
        return autor;
    }
    public void setAutor(String autor){
        this.autor = autor;
    }

    public String getTematica() {
        return tematica;
    }
    public void setTematica(String tematica) {
        this.tematica = tematica;
    }
    public  String getEditorial() {
        return editorial;
    }
    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public int getCantidad() {
        return cantidad;
    }

    public void incremengtacantidad(int cantidad) {
        this.cantidad += cantidad;
    }
    public void descrementaCantidad(int cantidad) {
        this.cantidad -= cantidad;
    }

}
