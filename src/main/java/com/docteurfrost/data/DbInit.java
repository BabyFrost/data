package com.docteurfrost.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.model.Categorie;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.model.OptionArticle;
import com.docteurfrost.data.model.OptionCategorie;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.model.Retour;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.ClientRepository;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.repository.OptionArticleRepository;
import com.docteurfrost.data.repository.OptionCategorieRepository;
import com.docteurfrost.data.repository.PanierRepository;
import com.docteurfrost.data.repository.RetourRepository;
import com.docteurfrost.data.repository.UtilisateurRepository;
import com.docteurfrost.data.repository.VenteRepository;

@Component
public class DbInit {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private ConteneurRepository conteneurRepository;
	
	@Autowired
	private OptionCategorieRepository optionCategorieRepository;
	
	@Autowired
	private OptionArticleRepository optionArticleRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Autowired
	private PanierRepository panierRepository;
	
	@Autowired
	private VenteRepository venteRepository;
	
	@Autowired
	private RetourRepository retourRepository;
	
	@PostConstruct
    private void postConstruct() {
		
		Conteneur conteneur = new Conteneur( 1, "Germany1", "Allemagne", null, null, null);
        conteneurRepository.save(conteneur);
    	
        Categorie categorie = new Categorie("TV", "Televisions");
        categorieRepository.save(categorie);
        
        Categorie categorie2 = new Categorie("Telephones", "telephones");
        categorieRepository.save(categorie2);
        
        Article article = new Article("TV1", "Tv ecran un peu fissuree en haut a droite", categorie, conteneur, 50000, 100000, null );
        articleRepository.save(article);
        
        Article article2 = new Article("TV2", "Tv propre 100 neuve", categorie, conteneur, 80000, 200000, null );
        articleRepository.save(article2);
        
        Article article3 = new Article("TV3", "Tv", categorie, conteneur, 42000, 70000, null );
        articleRepository.save(article3);
        
        Article article4 = new Article("Tel1", "Telephone neuf", categorie2, conteneur, 35000, 70000, null );
        articleRepository.save(article4);
        
        OptionCategorie option = new OptionCategorie(categorie, "Telecommande", "La tele a elle une telecommande ?");
        optionCategorieRepository.save(option);
        
        List<Article> articles = new ArrayList<>();
        Optional<Categorie> opt = categorieRepository.findByNom(categorie.getNom());
        if ( opt.isPresent() ) {
        	categorie = opt.get();
            articles = (List<Article>) categorie.getArticles();
		}
        
        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option, "Oui" );
        	optionArticleRepository.save(optionArticle);
        }
        
        OptionCategorie option2 = new OptionCategorie(categorie, "Satelitte", "Est-ce une TV Satelitte ?");
        optionCategorieRepository.save(option2);

        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option2, "Non" );
        	optionArticleRepository.save(optionArticle);
        }
        
        OptionCategorie option3 = new OptionCategorie(categorie, "Pieds", "La tele a elle ses pieds ?");
        optionCategorieRepository.save(option3);

        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option3, "Oui" );
        	optionArticleRepository.save(optionArticle);
        }
        
        OptionCategorie option4 = new OptionCategorie(categorie, "Couleur", "Couleur ?");
        optionCategorieRepository.save(option4);
        
        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option4, "Noir" );
        	optionArticleRepository.save(optionArticle);
        }
        
        Utilisateur utilisateur = new Utilisateur ( "Voufack", "Harold", new Date(), 7280193, "haroldvoufack@gmail.com", 699147539, "login", "password" );
        utilisateurRepository.save(utilisateur);
        
        Client client = new Client ( "Nana", "Arthur", new Date(), 1973561, "nanaarthur@gmail.com", 651460743 );
        clientRepository.save(client);
        
        
        Panier panier = new Panier( new Date() );
		panierRepository.save( panier );
		
		Vente vente = new Vente ( "Vente "+article.getNom(), client, article, utilisateur, panier );
		article.vendre();
		articleRepository.save( article );
		venteRepository.save(vente);
		
		Retour retour = new Retour ( "La TV s'eteint toute seule", utilisateur, vente );
		article.retourner();
		articleRepository.save( article );
		retourRepository.save(retour);
		
//		article = articleRepository.findById( article.getId() ).get();
//		List<OptionArticle> listOptions =  new ArrayList<>( article.getOptions() );
//		System.out.println( "eee "+article.getOptions().size() );
//		for ( int i = 0; i < listOptions.size(); i++ ) {
//			OptionArticle optionArticle = optionArticleRepository.findById( listOptions.get(i).getId() ).get();
//			optionArticleRepository.deleteById( optionArticle.getId() );	
//			System.out.println( "ddd" );
//		}
		
//		articleRepository.deleteById(article.getId());
//		articleRepository.save( article );
		
		
        
    }
	
}
