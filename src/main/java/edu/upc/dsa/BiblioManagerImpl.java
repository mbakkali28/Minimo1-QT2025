package edu.upc.dsa;

import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Libro;
import edu.upc.dsa.models.Prestec;

import org.apache.log4j.Logger;

import java.util.*;

public class BiblioManagerImpl implements BiblioManager {

    private static final Logger logger = Logger.getLogger(BiblioManagerImpl.class);

    private HashMap<String, Lector> lectors;
    private Queue<Stack<Libro>> magatzem;
    private Map<String, Libro> cataleg;

    private BiblioManagerImpl(){
        this.lectors = new HashMap<>();
        this.magatzem = new LinkedList<>();
        this.cataleg = new HashMap<>();
    }

    private static BiblioManagerImpl instance;

    public static BiblioManagerImpl getInstance() {
        if (instance == null) {
            instance = new BiblioManagerImpl();
        }
        return instance;
    }

    @Override
    public void addLector(String id, String nom, String cognoms, String dni, String dataNaixement, String llocNaixement, String adreca) {
        logger.info("addLector(id=" + id + ", nom=" + nom + ")");

        Lector lector = new Lector(id, nom, cognoms, dni, dataNaixement, llocNaixement, adreca);
        this.lectors.put(id, lector);

        logger.info("Fin addLector(). Lector afegit/actualitzat: " + lector.getNom());
    }

    public void emmagatzemarLlibre(Libro llibre) {
        logger.info("emmagatzemarLlibre(titol=" + llibre.getTitol() + ")");

        Stack<Libro> ultimMunt = ((LinkedList<Stack<Libro>>) this.magatzem).peekLast();

        if (ultimMunt == null || ultimMunt.size() == 10) {
            logger.info("Creant un nou munt");
            ultimMunt = new Stack<>();
            this.magatzem.add(ultimMunt);
        }

        ultimMunt.push(llibre);
        logger.info("Fin emmagatzemarLlibre(). Llibre apilat a l'últim munt.");
    }

    public Libro catalogarLlibre() {
        logger.info("Iniciando catalogarLlibre");

        Stack<Libro> primerMunt = this.magatzem.peek();

        if(primerMunt == null){
            logger.error("No hi ha cap llibre pendent de catalogar");
            return null;
        }

        Libro libropendiente = primerMunt.pop();
        logger.info("Llibre desempilat: " + libropendiente.getTitol());

        if (primerMunt.isEmpty()) {
            logger.info("El munt ha quedat buit. Eliminant munt de la cua.");
            this.magatzem.poll();
        }

        Libro librocataleg = this.cataleg.get(libropendiente.getIsbn());

        if(librocataleg == null){
            libropendiente.setCantidad(1);
            this.cataleg.put(libropendiente.getIsbn(), libropendiente);
            logger.info("Nou llibre afegit al catàleg: " + libropendiente.getTitol());
        }
        else {
            librocataleg.incremengtacantidad(1);
            logger.info("Cantidad incrementada del libro" + librocataleg.getTitol());
        }
        logger.info("Fin CatalogarLLibre()");
        return libropendiente;
    }

    public Prestec prestarLlibre(String id, String lectorId, String isbn, String dataPrestec, String dataDevolucio){
        logger.info("prestarLlibre(lectorId=" + lectorId + ", llibreIsbn=" + isbn + ")");

        Lector lector = this.lectors.get(lectorId);
        Libro llibre = this.cataleg.get(isbn);

        if (lector == null) {
            logger.error("El lector " + lectorId + " no existeix.");
            return null;
        }
        if (llibre == null) {
            logger.error("El llibre amb isbn: " + isbn + " no existeix al catàleg.");
            return null;
        }

        if (llibre.getCantidad() == 0) {
            logger.error("No hi ha exemplars disponibles de " + llibre.getTitol());
            return null;
        }

        llibre.descrementaCantidad(1);

        Prestec prestec = new Prestec(id, lectorId, isbn, dataPrestec, dataDevolucio);

        lector.addPrestec(prestec);

        logger.info("Fin prestarLlibre(). Préstec creat amb ID: " + prestec.getId());
        return prestec;
    }

    public List<Prestec> consultarPrestecsLector(String id) {
        logger.info("consultarPrestecsLector(lectorId=" + id + ")");

        Lector lector = this.lectors.get(id);

        logger.info("Fin consultarPrestecsLector(). Trobats " + lector.getPrestecs().size() + " préstecs.");
        return lector.getPrestecs();
    }

    public Libro getLlibreCatalogat(String isbn) {
        return this.cataleg.get(isbn);
    }

    public Lector getLector(String id) {
        return this.lectors.get(id);
    }

    public int numLlibresAlMagatzem() {
        int total = 0;
        for (Stack<Libro> munt : this.magatzem) {
            total += munt.size();
        }
        return total;
    }

    public void clear() {
        this.lectors.clear();
        this.magatzem.clear();
        this.cataleg.clear();
    }
}
