package com.homework.travel.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.homework.travel.dao.TravelItemDao;
import com.homework.travel.enumeration.SeasonType;
import com.homework.travel.model.TravelItem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TravelItemDaoImpl extends BasicJpaDao<TravelItem> implements TravelItemDao {

	@Override
	public List<TravelItem> list(SeasonType season, long distance) {
		try {
			  List<TravelItem> list = em.createQuery("from TravelItem tf where tf.season =:season and tf.distance <=:distance", TravelItem.class)
			 	.setParameter("season", season)
			 	.setParameter("distance", distance)
			 	.getResultList();
			 	
			return list;
		} catch(Throwable e) {
			log.error("Error on loading travelItem", e);
		}

		return null;
	}
	
	@Override
	public List<TravelItem> list(){
		return em.createQuery("from TravelItem", TravelItem.class).getResultList();
	}

	@Override
	public TravelItem findById(Long id) {
		return em.find(TravelItem.class, id);
	}
	
	@Override
	public void deleteById(Long id) {
		em.createQuery("delete from TravelItem where id = :id")
		  .setParameter("id", id)
		  .executeUpdate();
	}

}
