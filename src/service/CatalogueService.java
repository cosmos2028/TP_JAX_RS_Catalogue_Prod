package service;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import metier.Categorie;
import metier.ICatalogueMetierImpl;
import metier.Produit;

/*si toutes mes methodes renvoi json , on peut mettre directement produces sur la class
 *pour factoriser
 *
 *@Singleton : permet d'avoir une seule instance du webService de la class CatalogueService
 *et favorise le partage des données à tous les objets de cette class
 *
 *@PathParam(value="idcat") : ce sont les parametre envoyer dans le formulaire exemple :
 *idcat=1&idProd=2 etc
 *
 *@Produces(MediaType.APPLICATION_JSON) : fournir un type de ficheir au client notamment ici
 *JSON
 *
 *@Consumes(MediaType.APPLICATION_JSON) : imposer au client de nous envoyer un type de format
 *de document spécifique et ici c'est JSON
 *
 *@QueryParam(value="motCle") : permet d'envoyer les paramettre dans une requete
 *exemple : url?motcle="bonjour"
 *
 *
 *@PathParam(value="idcat") permet d'envoyer une requete avec les paramettre
 *exemple : url/1 ou url/5 etc
 * 
 * */
//@Produces(MediaType.APPLICATION_JSON)
@Singleton
@Path("/catalogue")
@Produces(MediaType.APPLICATION_JSON)
public class CatalogueService 
{
	private ICatalogueMetierImpl metier;
	
	public CatalogueService()
	{
		metier = new ICatalogueMetierImpl();
		metier.initialiserCatalogue();
	}
	
	@GET
	@Path("/categories")
//	@Produces(MediaType.APPLICATION_JSON)
	public List<Categorie> consulterCategorie()
	{
		return metier.listcategories();
	}
	
	@GET
	@Path("/allProduits")
	public List<Produit>  getAllProduits()
	{
		return metier.listProduit();
	}
	
	@GET
	@Path("/categories/{idcat}/produits")
//	@Produces(MediaType.APPLICATION_JSON)
	public List<Produit> produitSParCategorie(@PathParam(value="idcat")  Long idcat)
	{
		return metier.listProduitParCategorie(idcat);
	}
	
	@GET
	@Path("/produits")
//	@Produces(MediaType.APPLICATION_JSON)
	public List<Produit> produitSParMotCle(@QueryParam(value="motCle") String  motCle)
	{
		return metier.listProduitParMotCle(motCle);
	}
	
	@GET
	@Path("/categorie/{idCat}")
//	@Produces(MediaType.APPLICATION_JSON)
	public Categorie getCategorie(@PathParam(value="idCat") Long idCat)
	{
		return metier.getCategorie(idCat);
	}
	
	@GET
	@Path("/produit/{idProd}")
//	@Produces(MediaType.APPLICATION_JSON)
	public Produit getProduit(@PathParam(value="idProd") Long idProd)
	{
		return metier.getProduit(idProd);
	}
	
	@POST
	@Path("/categorie")
	@Consumes(MediaType.APPLICATION_JSON)
	public Categorie saveCategorie(Categorie c)
	{
		return metier.saveCategorie(c);
	}
	@POST
	@Path("/produit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Produit saveProduit(Produit p)
	{
		return metier.saveProduit(p);
	}
	@DELETE
	@Path("/produit")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public boolean deleteCategorie(@FormParam(value = "idProd") Long idProd)
	{
		System.out.println("\n idProd \t"+idProd+"\n");
		return metier.deleteProduit(idProd);
	}
	@PUT
	@Path("/produit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Produit updateProduit(Produit p)
	{
		return metier.saveProduit(p);
	}
	
	
}
