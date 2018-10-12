package com.jokes.springdemo.dao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.jokes.springdemo.entity.Category;
import com.jokes.springdemo.entity.Joke;

@Repository
public class JokesDAOImpl implements JokesDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Joke> getJokes() {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// create a query
		Query<Joke> theJokequery = currentSession.createQuery("from Joke", Joke.class);

//		// execute query and get result list
		List<Joke> jokes = theJokequery.getResultList();
		jokes.sort(new Comparator<Joke>() {

			@Override
			public int compare(Joke j1, Joke j2) {
				return j2.getDifference() - j1.getDifference();
			}

		});

		// return the results
		return jokes;
	}

	@Override
	@Transactional
	public void likeJoke(int jokeId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// like object with primary key
		Joke joke = currentSession.get(Joke.class, jokeId);
		int likes = joke.getLikes();
		likes++;
		joke.setLikes(likes);

	}

	@Override
	@Transactional
	public void dislikeJoke(int jokeId) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// dislike object with primary key
		Joke joke = currentSession.get(Joke.class, jokeId);
		int dislikes = joke.getDislikes();
		dislikes++;
		joke.setDislikes(dislikes);

	}

	@Override
	@Transactional
	public void saveJoke(Joke theJoke) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();

		// save the joke ...

		Query<Category> theQuery = currentSession.createQuery("from Category where lower(name) like :theName",
				Category.class);
		theQuery.setParameter("theName", "%" + theJoke.getCategory().getName() + "%");

		// execute query and get result list
		List<Category> listcategory = theQuery.getResultList();
		Category category;
		if (listcategory.size() != 0) {
			category = listcategory.get(0);
		} else {
			category = new Category();
			category.setName(theJoke.getCategory().getName());
			currentSession.save(category);
		}

		category.addJoke(theJoke);

		currentSession.save(theJoke);

	}

}
