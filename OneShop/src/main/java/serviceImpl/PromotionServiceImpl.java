package serviceImpl;

import java.util.List;

import dao.PromotionDAO;
import daoImpl.PromotionDAOImpl;
import models.Promotion;
import service.PromotionService;

public class PromotionServiceImpl implements PromotionService{
	
	PromotionDAO promotionDAO = new PromotionDAOImpl();
	
	@Override
	public void addPromotion(Promotion promotion) {
		// TODO Auto-generated method stub
		promotionDAO.addPromotion(promotion);
	}

	@Override
	public void updatePromotion(Promotion promotion) {
		// TODO Auto-generated method stub
		promotionDAO.updatePromotion(promotion);
	}

	@Override
	public void deletePromotion(String promotionId) {
		// TODO Auto-generated method stub
		promotionDAO.deletePromotion(promotionId);
	}
	
	@Override
	public Promotion getPromotionById(String promotionId) {
		// TODO Auto-generated method stub
		return promotionDAO.getPromotionById(promotionId);
	}

	@Override
	public List<Promotion> getAllPromotion(String userId) {
		// TODO Auto-generated method stub
		return promotionDAO.getAllPromotion(userId);
	}
}
