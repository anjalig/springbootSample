package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Person;

@Repository("personDao")
public class PersonDataAccessService implements PersonDAO{
	
	public static List<Person> DB = new ArrayList<>();

	@Override
	public int insertPerson(UUID id, Person person) {
		DB.add(new Person(id, person.getName()));
		return 1;
	}

	@Override
	public List<Person> selectAllPeople() {
		
		return DB;
	}

	@Override
	public Optional<Person> selectPersonById(UUID id) {
		return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
		
	}

	@Override
	public int deletePerson(UUID id) {
		Optional<Person> person = selectPersonById(id);
		if(!person.isPresent())
			return 0;
		DB.remove(person.get());
		return 1; 
	}

	@Override
	public int updatePersonById(UUID id, Person person) {
		return selectPersonById(id).map(p -> {
			int indexOfPerson = DB.indexOf(p);
			if(indexOfPerson >= 0 ) {
				DB.set(indexOfPerson, new Person(id, person.getName()));
				return 1;
				
			}
			return 0;
		}).orElse(0);
		
		}
	

}
