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
			log.error("Error on loading travelFood", e);
		}

		return null;
	}
	
	@Override
	public List<TravelItem> list(){
		return em.createQuery("from TravelFood", TravelItem.class).getResultList();
	}

}
