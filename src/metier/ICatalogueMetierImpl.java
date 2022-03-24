package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ICatalogueMetierImpl implements ICatalogueMetier 
{

	private Map<Long,Categorie> categories = new HashMap<Long,Categorie>();
	private Map<Long,Produit> produits = new HashMap<Long,Produit>();
	
	
	@Override
	public Categorie saveCategorie(Categorie cat) 
	{
		categories.put(cat.getIdCategorie(), cat);
		return cat;
	}

	@Override
	public List<Categorie> saveProduitParCategorie(Categorie cat, Produit p) 
	{
		categories.put(cat.getIdCategorie(), cat);
		
		return new ArrayList<Categorie>(categories.values());
	}

	@Override
	public Produit saveProduit(Produit p) 
	{
		p.setCategorie(getCategorie(p.getCategorie().getIdCategorie()));
		produits.put(p.getIdProduit(), p);
		return p;
	}

	@Override
	public List<Categorie> listcategories() 
	{
		return new ArrayList<Categorie>(categories.values());
	}

	@Override
	public List<Produit> listProduitParCategorie(Long idCat) 
	{
		
		List<Produit> prods = new ArrayList<>();
		for(Produit p : produits.values())
		{
			if(p.getCategorie().getIdCategorie().equals(idCat))
			{
				prods.add(p);
			}
		}
		return prods;
		
	}

	@Override
	public List<Produit> listProduit() 
	{
		return new ArrayList<>(produits.values());
	}

	@Override
	public List<Produit> listProduitParMotCle(String motCle) 
	{
		List<Produit> prods = new ArrayList<>();
		for(Produit p : produits.values())
		{
			if(p.getDesignation().contains(motCle))
			{
				prods.add(p);
			}
		}
		return prods;
	}

	@Override
	public Categorie updateCategorie(Categorie c) 
	{
		categories.put(c.getIdCategorie(), c);
		return c;
	}

	@Override
	public Produit updateProduit(Produit p) 
	{
		produits.put(p.getIdProduit(), p);
		return p;
	}

	@Override
	public boolean deleteProduit(Long idProduit) 
	{
		Produit pDel = getProduit(idProduit);
		if(pDel!= null)
		{
			produits.remove(idProduit);
			return true;
		}else
		{
			throw new RuntimeException("Produit introuvale");
		}
		
		
	}

	@Override
	public Categorie getCategorie(Long idCat)
	{
		
		return categories.get(idCat);
	}

	@Override
	public Produit getProduit(Long idProduit) 
	{
		return produits.get(idProduit);
	}

	@Override
	public boolean deleteCategorie(Long idCat) 
	{
		if(getCategorie(idCat)!=null)
		{
			categories.remove(idCat);
			return true;
			
		}else
		{
			throw new RuntimeException("Catalogue introuvable");
		}
		
	}
	
	public void initialiserCatalogue()
	{
		saveCategorie(new Categorie(1L,"Ordinateur","Ordinateur.jpg"));
		saveCategorie(new Categorie(2L,"Imprimantes","Imprimentes.jpg"));
		saveCategorie(new Categorie(3L,"Television","Television.jpg"));
		
		produits.put(1L, new Produit(1L, "HP pavillon",650,"hp.jpg",(getCategorie(1L))));
		produits.put(2L, new Produit(2L, "ASUS PC reconditionne",650,"ordi2.jpg", (getCategorie(1L))));
		produits.put(3L, new Produit(3L, "Lenovo noteBook",1600,"ordi3.jpg", (getCategorie(1L))));
		produits.put(4L, new Produit(4L, "HP IMPRIMANTE",450,"ORDI4.jpg", (getCategorie(2L))));
		produits.put(5L, new Produit(5L, "Canon pixema NG",2399,"ordi5.jpg", (getCategorie(2L))));
		produits.put(6L, new Produit(6L, "Dell inspiron",850,"del.jpg", (getCategorie(3L))));
	}


}
