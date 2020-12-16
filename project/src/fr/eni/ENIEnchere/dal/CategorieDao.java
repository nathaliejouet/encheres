package fr.eni.ENIEnchere.dal;

import java.util.List;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Categorie;

public interface CategorieDao {

	
	List<Categorie> selectAll() throws BusinessException;
	Categorie selectCategorieById(int id) throws BusinessException;
}
