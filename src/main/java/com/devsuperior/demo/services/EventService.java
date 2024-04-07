package com.devsuperior.demo.services;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;

	public Page<EventDTO> findAll(Pageable page) {
		Page<Event> list = repository.findAll(page);

		return list.map(x -> new EventDTO(x));
	}

	@Transactional
	public EventDTO insert(EventDTO dto) {
		Event entity = new Event();

		City city = new City();
	    city.setId(dto.getCityId());
	    
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
		entity.setCity(city);
		
		entity = repository.save(entity);
		return new EventDTO(entity);
	}

}
