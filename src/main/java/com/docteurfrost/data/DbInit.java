package com.docteurfrost.data;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.docteurfrost.data.model.Article;
import com.docteurfrost.data.model.Categorie;
import com.docteurfrost.data.model.Option;
import com.docteurfrost.data.repository.ArticleRepository;
import com.docteurfrost.data.repository.CategorieRepository;
import com.docteurfrost.data.repository.OptionRepository;

@Component
public class DbInit {

	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private CategorieRepository categorieRepository;
	
	@Autowired
	private OptionRepository optionRepository;
	
	@PostConstruct
    private void postConstruct() {
    	
        Categorie categorie = new Categorie("TV", "Televisions");
        categorieRepository.save(categorie);
        
        Categorie categorie2 = new Categorie("Telephones", "telephones");
        categorieRepository.save(categorie2);
        
        Option option = new Option(categorie, "Telecommande", "La tele a elle une telecommande ?");
        optionRepository.save(option);
        
        Option option2 = new Option(categorie, "Satelitte", "Est-ce une TV Satelitte ?");
        optionRepository.save(option2);
        
        Option option3 = new Option(categorie, "Pieds", "La tele a elle ses pieds ?");
        optionRepository.save(option3);
        
        Option option4 = new Option(categorie, "Couleur", "Couleur ?");
        optionRepository.save(option4);
        
        Article article = new Article("TV01", "Tv ecran un peu fissuree en haut a droite", categorie, 50000, 100000 );
        articleRepository.save(article);
        
        Article article2 = new Article("TV02", "Tv propre 100 neuve", categorie, 50000, 100000 );
        articleRepository.save(article2);
        
        Article article3 = new Article("TV03", "Tv", categorie, 50000, 100000 );
        articleRepository.save(article3);
        
        Article article4 = new Article("Tel01", "Telephone neuf", categorie2, 35000, 70000 );
        articleRepository.save(article4);
        
    }
	
}
