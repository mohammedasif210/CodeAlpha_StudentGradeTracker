package com.asif.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.asif.model.Student;
public class StudentDataManagement {

	    private List<Student> students = new ArrayList<>();

	    public List<Student> getStudents() { return students; }

	    public boolean addStudent(Student s) {
	        if (findById(s.getId()).isPresent()) return false;
	        students.add(s);
	        return true;
	    }

	    public Optional<Student> findById(String id) {
	        return students.stream()
	                .filter(s -> s.getId().equalsIgnoreCase(id.trim()))
	                .findFirst();
	    }

	    public boolean removeStudent(String id) {
	        Optional<Student> student = findById(id);
	        if (student.isPresent()) {
	            students.remove(student.get());
	            return true;
	        }
	        return false;
	    }

	    public double classAverage() {
	        List<Integer> allScores = allScores();
	        if (allScores.isEmpty()) return 0.0;
	        double sum = 0;
	        for (int s : allScores) sum += s;
	        return sum / allScores.size();
	    }

	    public int classHighest() {
	        List<Integer> allScores = allScores();
	        return allScores.isEmpty() ? Integer.MIN_VALUE : Collections.max(allScores);
	    }

	    public int classLowest() {
	        List<Integer> allScores = allScores();
	        return allScores.isEmpty() ? Integer.MAX_VALUE : Collections.min(allScores);
	    }

	    private List<Integer> allScores() {
	        List<Integer> all = new ArrayList<>();
	        for (Student s : students) all.addAll(s.getScores());
	        return all;
	    }

	    public String summaryReport() {
	        StringBuilder sb = new StringBuilder("---- STUDENT SUMMARY ----\n");
	        if (students.isEmpty()) {
	            sb.append("No students available.\n");
	            return sb.toString();
	        }
	        for (Student s : students) {
	            sb.append(String.format("ID: %s | Name: %s | Scores: %s | Avg: %.2f | High: %s | Low: %s\n",
	                    s.getId(), s.getName(),
	                    s.getScores(),
	                    s.average(),
	                    s.getScores().isEmpty() ? "N/A" : s.highestScore(),
	                    s.getScores().isEmpty() ? "N/A" : s.lowestScore()
	            ));
	        }
	        sb.append(String.format("\nClass Average: %.2f\n", classAverage()));
	        sb.append(String.format("Class Highest: %s\n", classHighest() == Integer.MIN_VALUE ? "N/A" : classHighest()));
	        sb.append(String.format("Class Lowest: %s\n", classLowest() == Integer.MAX_VALUE ? "N/A" : classLowest()));
	        sb.append("-------------------------\n");
	        return sb.toString();
	    }

}
