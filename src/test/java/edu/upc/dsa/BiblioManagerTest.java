package edu.upc.dsa;

import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Libro;
import edu.upc.dsa.models.Prestec;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class BiblioManagerTest {

    BiblioManager bm;

    @Before
    public void setUp() {
        this.bm = BiblioManagerImpl.getInstance();

        this.bm.addLector("lector1", "Joan", "Soler", "111A", "01/01/1990", "Barcelona", "C/Fals 123");

        bm.emmagatzemarLlibre(new Libro("JV7d", "978-1605062234", "The Steam House", "Forgotten Books", 1880, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV4a", "978-1435149408", "The Mysterious Island", "Barnes & Noble Classics", 1874, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV1", "978-0486268685", "Journey to the Center of the Earth", "Dover Publications", 1864, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV3", "978-1516887907", "Around the World in Eighty Days", "CreateSpace", 1872, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV4c", "978-1435149408", "The Mysterious Island", "Barnes & Noble Classics", 1874, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV8", "978-1103325575", "The Begum's Fortune", "BiblioBazaar", 1879, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV7c", "978-1605062234", "The Steam House", "Forgotten Books", 1880, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV5", "978-1853260257", "The Adventures of Captain Hatteras", "Wordsworth Editions", 1866, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV2b", "978-0451530960", "Twenty Thousand Leagues Under the Sea", "Signet Classics", 1870, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV2c", "978-0451530960", "Twenty Thousand Leagues Under the Sea", "Signet Classics", 1870, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV2a", "978-0451530960", "Twenty Thousand Leagues Under the Sea", "Signet Classics", 1870, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV6", "978-0199538474", "From the Earth to the Moon", "Oxford University Press", 1865, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV7a", "978-1605062234", "The Steam House", "Forgotten Books", 1880, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV4b", "978-1435149408", "The Mysterious Island", "Barnes & Noble Classics", 1874, 1, "Jules Verne", "Adventures"));
        bm.emmagatzemarLlibre(new Libro("JV7b", "978-1605062234", "The Steam House", "Forgotten Books", 1880, 1, "Jules Verne", "Adventures"));
    }

    @After
    public void tearDown() {
        this.bm.clear();
    }

    @Test
    public void testEmmagatzemarLlibres() {
        Assert.assertEquals(15, this.bm.numLlibresAlMagatzem());
    }

    @Test
    public void testCatalogarLlibres() {
        Assert.assertEquals(15, this.bm.numLlibresAlMagatzem());

        Libro llibre1 = this.bm.catalogarLlibre();
        Assert.assertEquals("JV2c", llibre1.getId());

        Libro llibre2 = this.bm.catalogarLlibre();
        Assert.assertEquals("JV2b", llibre2.getId());

        for (int i = 0; i < 8; i++) {
            this.bm.catalogarLlibre();
        }

        Assert.assertEquals(5, this.bm.numLlibresAlMagatzem());

        Libro llibre11 = this.bm.catalogarLlibre();
        Libro llibre12 = this.bm.catalogarLlibre();
        Libro llibre13 = this.bm.catalogarLlibre();
        Libro llibre14 = this.bm.catalogarLlibre();
        Libro llibre15 = this.bm.catalogarLlibre();

        Assert.assertEquals("JV7b", llibre11.getId());
        Assert.assertEquals("JV2a", llibre15.getId());

        Assert.assertEquals(0, this.bm.numLlibresAlMagatzem());

        Assert.assertEquals(4, this.bm.getLlibreCatalogat("978-1605062234").getCantidad());
        Assert.assertEquals(3, this.bm.getLlibreCatalogat("978-0451530960").getCantidad());
        Assert.assertEquals(3, this.bm.getLlibreCatalogat("978-1435149408").getCantidad());
    }

    @Test
    public void testPrestarLlibre(){

        testCatalogarLlibres();

        Assert.assertEquals(3, this.bm.getLlibreCatalogat("978-0451530960").getCantidad());

        Prestec p = this.bm.prestarLlibre("p1", "lector1", "978-0451530960", "07/11/2025", "15/11/2025");

        Assert.assertNotNull(p);
        Assert.assertEquals("En tramite", p.getEstado());

        Assert.assertEquals(2, this.bm.getLlibreCatalogat("978-0451530960").getCantidad());

        List<Prestec> prestecs = this.bm.consultarPrestecsLector("lector1");
        Assert.assertEquals(1, prestecs.size());
        Assert.assertEquals("p1", prestecs.get(0).getId());
    }
}