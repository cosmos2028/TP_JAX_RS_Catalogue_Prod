package metier;

import java.util.List;

public interface ICatalogueMetier 
{

	public Categorie saveCategorie(Categorie cat);
	public Produit saveProduit(Produit p);
	public List<Categorie> listcategories();
	public List<Produit> listProduitParCategorie(Long idCat);
	public List<Produit> listProduit();
	public List<Produit> listProduitParMotCle(String motCle);
	public Categorie updateCategorie(Categorie c);
	public Produit updateProduit(Produit p);
	public boolean deleteProduit(Long idProduit);
	public Categorie getCategorie(Long idCat);
	public Produit getProduit(Long idProduit);
	public List<Categorie>  saveProduitParCategorie(Categorie cat,Produit p);
	public boolean deleteCategorie(Long idCat);
	
	
}
