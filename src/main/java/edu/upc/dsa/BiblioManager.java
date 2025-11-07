package edu.upc.dsa;

import edu.upc.dsa.models.Libro;
import edu.upc.dsa.models.Prestec;
import edu.upc.dsa.models.Lector;

import java.util.List;

public interface BiblioManager {

    public void addLector(String id, String nom, String dni, String anynaixement, String lastname, String adresse, String llocdenaixement);

    public void emmagatzemarLlibre(Libro llibre);

    public Libro catalogarLlibre();

    public Prestec prestarLlibre(String id, String lectorId, String isbn, String dataPrestec, String dataDevolucio);

    public List<Prestec> consultarPrestecsLector(String id);

    public void clear();

    public int numLlibresAlMagatzem();

    public Libro getLlibreCatalogat(String isbn);

    public Lector getLector(String id);

}
