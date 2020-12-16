package fr.eni.ENIEnchere.dal;

import fr.eni.ENIEnchere.BusinessException;
import fr.eni.ENIEnchere.bo.Retrait;

public interface RetraitDao {
	public Retrait select_retrait(int no_article) throws BusinessException;
}
