package com.asif.model;
import java.util.ArrayList;
import java.util.List;

public class Student {

	    private String id;
	    private String name;
	    private List<Integer> scores;

	    public Student(String id, String name) {
	        this.id = id.trim();
	        this.name = name.trim();
	        this.scores = new ArrayList<>();
	    }

	    public String getId() { return id; }
	    
	    public String getName() { return name; }
	    
	    public List<Integer> getScores() { return scores; }

	    public void setName(String name) { this.name = name.trim(); }
	    
	    public void addScore(int score) { scores.add(score); }

	    public double average() {
	        if (scores.isEmpty()) return 0.0;
	        double sum = 0;
	        for (int s : scores) sum += s;
	        return sum / scores.size();
	    }

	    public int highestScore() {
	        if (scores.isEmpty()) return Integer.MIN_VALUE;
	        int max = Integer.MIN_VALUE;
	        for (int s : scores) if (s > max) max = s;
	        return max;
	    }

	    public int lowestScore() {
	        if (scores.isEmpty()) return Integer.MAX_VALUE;
	        int min = Integer.MAX_VALUE;
	        for (int s : scores) if (s < min) min = s;
	        return min;
	    }

	    @Override
	    public String toString() {
	        return String.format("ID: %s | Name: %s | Scores: %s | Avg: %.2f",
	                id, name, scores.toString(), average());
	    }

}
