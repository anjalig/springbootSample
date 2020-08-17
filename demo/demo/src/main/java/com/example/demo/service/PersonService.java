package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.dao.PersonDAO;
import com.example.demo.model.Person;

@Service
public class PersonService {
	
	private final PersonDAO personDao;
	
	@Autowired
	public PersonService(@Qualifier("personDao") PersonDAO personDao) {
		super();
		this.personDao = personDao;
	}

	public int addPerson(Person person) {
		return personDao.insertPerson(person);
	}
	
	public List<Person> getAllPeople()
	{
		return personDao.selectAllPeople();
	}
	
	public Optional<Person> getPersonById(UUID id){
		return personDao.selectPersonById(id);
	}
	
	public int deletePerson(UUID id)
	{
		return personDao.deletePerson(id);
	}
	
	public int updatePerson(UUID id, Person updated) {
		return personDao.updatePersonById(id, updated);
	}

}
