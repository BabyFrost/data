package com.docteurfrost.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docteurfrost.data.categorie.Categorie;
import com.docteurfrost.data.categorie.OptionArticle;
import com.docteurfrost.data.categorie.OptionCategorie;
import com.docteurfrost.data.categorie.ValeurOption;
import com.docteurfrost.data.conteneur.Conteneur;
import com.docteurfrost.data.model.Client;
import com.docteurfrost.data.model.Marque;
import com.docteurfrost.data.model.Panier;
import com.docteurfrost.data.model.Retour;
import com.docteurfrost.data.model.Utilisateur;
import com.docteurfrost.data.model.Vente;
import com.docteurfrost.data.model.article.Article;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.ClientRepository;
import com.docteurfrost.data.repository.ConteneurRepository;
import com.docteurfrost.data.repository.MarqueRepository;
import com.docteurfrost.data.repository.OptionArticleRepository;
import com.docteurfrost.data.repository.OptionCategorieRepository;
import com.docteurfrost.data.repository.PanierRepository;
import com.docteurfrost.data.repository.RetourRepository;
import com.docteurfrost.data.repository.UtilisateurRepository;
import com.docteurfrost.data.repository.ValeurOptionRepository;
import com.docteurfrost.data.repository.VenteRepository;

@Component
public class DbInit {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private MarqueRepository marqueRepository;
	
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
	
	@Autowired
	private ValeurOptionRepository valeurOptionRepository;
	
	@PostConstruct
    private void postConstruct() {
		
		Conteneur conteneur = new Conteneur( 1, "Germany1", "Allemagne", null, null, null);
        conteneurRepository.save(conteneur);
    	
        Categorie categorie = new Categorie("TV", "Televisions");
        categorieRepository.save(categorie);
        
        Categorie categorie2 = new Categorie("Tel", "telephones");
        categorieRepository.save(categorie2);
        
        Marque marque = new Marque("lg", "LG");
        marqueRepository.save(marque);
        
        Article article = new Article("TV1", "Tv ecran un peu fissuree en haut a droite", null, categorie, conteneur, marque, 50000, 0, 0, 100000, null, new Date() );
        article.decharger();
        article.deballer();
        articleRepository.save(article);
        
        Article article2 = new Article("TV2", "Tv propre 100 neuve", null, categorie, conteneur, marque, 80000, 0, 0, 200000, null, new Date() );
        articleRepository.save(article2);
        
        Article article3 = new Article("TV3", "Tv", null, categorie, conteneur, marque, 42000, 0, 0, 70000, null, new Date() );
        articleRepository.save(article3);
        
        Article article4 = new Article("Tel1", "Telephone neuf", null, categorie2, conteneur, marque, 35000, 0, 0, 70000, null, new Date() );
        articleRepository.save(article4);
        
        OptionCategorie option = new OptionCategorie(categorie, "Telecommande", "La tele a elle une telecommande ?", false, false, false);
        optionCategorieRepository.save(option);
        
        ValeurOption val = new ValeurOption( "OUI", option);
        valeurOptionRepository.save(val);
        
        val = new ValeurOption( "NON", option);
        valeurOptionRepository.save(val);
        
        List<Article> articles = new ArrayList<>();
        Optional<Categorie> opt = categorieRepository.findById(categorie.getNom());
        if ( opt.isPresent() ) {
        	categorie = opt.get();
            articles = (List<Article>) categorie.getArticles();
		}
        
        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option, "OUI" );
        	optionArticleRepository.save(optionArticle);
        }
        
        OptionCategorie option2 = new OptionCategorie(categorie, "Satellite", "Est-ce une TV Satelitte ?", false, false, false);
        optionCategorieRepository.save(option2);
        
        val = new ValeurOption( "OUI", option2);
        valeurOptionRepository.save(val);
        
        val = new ValeurOption( "NON", option2);
        valeurOptionRepository.save(val);

        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option2, "NON" );
        	optionArticleRepository.save(optionArticle);
        }
        
        OptionCategorie option3 = new OptionCategorie(categorie, "Pieds", "La tele a elle ses pieds ?", false, false, false);
        optionCategorieRepository.save(option3);
        
        val = new ValeurOption( "OUI", option3);
        valeurOptionRepository.save(val);
        
        val = new ValeurOption( "NON", option3);
        valeurOptionRepository.save(val);

        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option3, "OUI" );
        	optionArticleRepository.save(optionArticle);
        }
        
        OptionCategorie option4 = new OptionCategorie(categorie, "Couleur", "Couleur ?", false, true, false);
        optionCategorieRepository.save(option4);
        
        for (int i=0; i < articles.size(); i++) {
        	OptionArticle optionArticle = new OptionArticle( articles.get(i), option4, "NOIR" );
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
		
		OptionCategorie optionXXX = new OptionCategorie(categorie, "Taille", "Taille TV", true, true, false);
        optionCategorieRepository.save(optionXXX);
        
        optionXXX = new OptionCategorie(categorie, "Smart", "La tele est elle Smart ?", false, false, true);
        optionCategorieRepository.save(optionXXX);
        ValeurOption valXXX = new ValeurOption( "OUI", optionXXX);
        valeurOptionRepository.save(valXXX);
        valXXX = new ValeurOption( "NON", optionXXX);
        valeurOptionRepository.save(valXXX);
        
        optionXXX = new OptionCategorie(categorie, "3D", "La tele est elle 3D ?", false, false, true);
        optionCategorieRepository.save(optionXXX);
        valXXX = new ValeurOption( "OUI", optionXXX);
        valeurOptionRepository.save(valXXX);
        valXXX = new ValeurOption( "NON", optionXXX);
        valeurOptionRepository.save(valXXX);
		
        optionXXX = new OptionCategorie(categorie, "VIP", "La tele est elle 3D ?", false, false, true);
        optionCategorieRepository.save(optionXXX);
        valXXX = new ValeurOption( "OUI", optionXXX);
        valeurOptionRepository.save(valXXX);
        valXXX = new ValeurOption( "NON", optionXXX);
        valeurOptionRepository.save(valXXX);
        
        optionXXX = new OptionCategorie(categorie, "SUPPORT", "La tele a elle son support ?", false, false, true);
        optionCategorieRepository.save(optionXXX);
        valXXX = new ValeurOption( "OUI", optionXXX);
        valeurOptionRepository.save(valXXX);
        valXXX = new ValeurOption( "NON", optionXXX);
        valeurOptionRepository.save(valXXX);
        
        optionXXX = new OptionCategorie(categorie, "TECHNOLOGIE", "TECHNO DE LA TV ?", false, false, true);
        optionCategorieRepository.save(optionXXX);
        valXXX = new ValeurOption( "LED", optionXXX);
        valeurOptionRepository.save(valXXX);
        valXXX = new ValeurOption( "PLASMA", optionXXX);
        valeurOptionRepository.save(valXXX);
        valXXX = new ValeurOption( "LCD", optionXXX);
        valeurOptionRepository.save(valXXX);
        
    }
	
}
