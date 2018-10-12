package com.jokes.springdemo.dao;

import java.util.List;

import com.jokes.springdemo.entity.Joke;

public interface JokesDao {
	
	public List<Joke> getJokes();

	public void likeJoke(int jokeId);

	public void dislikeJoke(int jokeId);

	public void saveJoke(Joke theJoke);

}
