package edu.upc.dsa.services;

import edu.upc.dsa.BiblioManager;
import edu.upc.dsa.BiblioManagerImpl;
import edu.upc.dsa.models.Lector;
import edu.upc.dsa.models.Libro;
import edu.upc.dsa.models.Prestec;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Api(value = "/biblioteca", description = "Endpoint to Biblioteca Service")
@Path("/biblioteca")
public class BiblioService {

    private BiblioManager bm;

    public BiblioService() {
        this.bm = BiblioManagerImpl.getInstance();

        if (this.bm.numLlibresAlMagatzem() == 0 && this.bm.getLector("lector1") == null) {

            this.bm.addLector("lector1", "Joan", "Soler", "111A", "01/01/1990", "Barcelona", "C/Fals 123");

            bm.emmagatzemarLlibre(new Libro("JV1", "978-0486268685", "Journey to the Center of the Earth", "Dover", 1864, 1, "Jules Verne", "Adventures"));
            bm.emmagatzemarLlibre(new Libro("JV3", "978-1516887907", "Around the World in Eighty Days", "CreateSpace", 1872, 1, "Jules Verne", "Adventures"));
            bm.emmagatzemarLlibre(new Libro("JV1b", "978-0486268685", "Journey to the Center of the Earth", "Dover", 1864, 1, "Jules Verne", "Adventures"));
        }
    }

    @POST
    @ApiOperation(value = "Afegir o actualitzar un lector", notes = ".....")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Lector afegit/actualitzat", response = Lector.class),
            @ApiResponse(code = 400, message = "Falten dades")
    })
    @Path("/lectors")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addLector(Lector lector) {
        if (lector.getId() == null || lector.getNom() == null || lector.getDni() == null) {
            return Response.status(400).entity("Falten dades").build();
        }

        this.bm.addLector(lector.getId(), lector.getNom(), lector.getLastname(), lector.getDni(),
                lector.getAnynaixement(), lector.getLlocdenaixement(), lector.getAdresse());

        return Response.status(201).entity(lector).build();
    }

    @POST
    @ApiOperation(value = "Emmagatzemar un llibre", notes = "......")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Llibre emmagatzemat correctament"),
            @ApiResponse(code = 400, message = "Falten dades del llibre")
    })
    @Path("/magatzem/llibres")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response emmagatzemarLlibre(Libro llibre) {
        if (llibre.getId() == null || llibre.getIsbn() == null || llibre.getTitol() == null) {
            return Response.status(400).entity("Falten dades").build();
        }
        this.bm.emmagatzemarLlibre(llibre);
        return Response.status(201).entity(llibre).build();
    }

    @POST
    @ApiOperation(value = "Catalogar un llibre", notes = ".....")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Llibre catalogat", response = Libro.class),
            @ApiResponse(code = 404, message = "No hi ha llibres per catalogar")
    })
    @Path("/magatzem/catalogar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response catalogarLlibre() {
        Libro llibreCatalogat = this.bm.catalogarLlibre();

        if (llibreCatalogat == null) {
            return Response.status(404).entity("No hi ha llibres per catalogar").build();
        }

        return Response.status(200).entity(llibreCatalogat).build();
    }

    @POST
    @ApiOperation(value = "Realitzar un préstec", notes = "......")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Préstec realitzat", response = Prestec.class),
            @ApiResponse(code = 404, message = "Lector o Llibre no trobat o no hi ha exemplars disponibles"),
    })
    @Path("/prestecs")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response prestarLlibre(Prestec prestec) {
        if (prestec.getId() == null || prestec.getLectorid() == null || prestec.getLlibreisbn() == null) {
            return Response.status(400).entity("Falten dades").build();
        }

        Prestec p = this.bm.prestarLlibre(prestec.getId(), prestec.getLectorid(), prestec.getLlibreisbn(),
                prestec.getDataprestec(), prestec.getDatadevolucio());

        if (p == null) {
            return Response.status(404).entity("Lector o libre no trobats o no hi ha exemplars disponibles").build();
        }

        return Response.status(201).entity(p).build();
    }

    @GET
    @ApiOperation(value = "Consultar préstecs d'un lector", notes = "....")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful", response = Prestec.class, responseContainer = "List"),
            @ApiResponse(code = 404, message = "Lector no trobat")
    })
    @Path("/prestecs/{lectorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response consultarPrestecsLector(@PathParam("lectorId") String lectorId) {

        Lector lector = this.bm.getLector(lectorId);
        if (lector == null) {
            return Response.status(404).entity("Lector no trobat").build();
        }

        List<Prestec> prestecs = this.bm.consultarPrestecsLector(lectorId);
        GenericEntity<List<Prestec>> entity = new GenericEntity<List<Prestec>>(prestecs) {};

        return Response.status(200).entity(entity).build();
    }
}