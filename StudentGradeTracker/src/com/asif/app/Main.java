package com.asif.app;

import java.util.Optional;
import java.util.Scanner;

import com.asif.model.Student;
import com.asif.service.StudentDataManagement;

public class Main {
	
	private static final Scanner scanner = new Scanner(System.in);
	    private static final StudentDataManagement manager = new StudentDataManagement();

	    public static void main(String[] args) {
	        boolean quit = false;
	        while (!quit) {
	            printMenu();
	            String choice = prompt("Enter choice").trim();
	            switch (choice) {
	                case "1": addStudent(); break;
	                case "2": addScore(); break;
	                case "3": removeStudent(); break;
	                case "4": listStudents(); break;
	                case "5": printSummary(); break;
	                case "0": quit = true; break;
	                default: System.out.println("Invalid choice. Try again.");
	            }
	        }
	        System.out.println("Goodbye!");
	    }

	    private static void printMenu() {
	        System.out.println("\n=== Student Grade Tracker ===");
	        System.out.println("1. Add new student");
	        System.out.println("2. Add score to student");
	        System.out.println("3. Remove student");
	        System.out.println("4. List students");
	        System.out.println("5. Summary report (avg/high/low)");
	        System.out.println("0. Exit");
	    }

	    private static String prompt(String msg) {
	        System.out.print(msg + ": ");
	        return scanner.nextLine();
	    }

	    private static void addStudent() {
	        String id = prompt("Student ID");
	        String name = prompt("Student Name");
	        if (manager.addStudent(new Student(id, name))) {
	            System.out.println("Student added.");
	        } else {
	            System.out.println("ID already exists.");
	        }
	    }

	    private static void addScore() {
	        String id = prompt("Student ID");
	        Optional<Student> opt = manager.findById(id);
	        if (opt.isEmpty()) {
	            System.out.println("Student not found.");
	            return;
	        }
	        try {
	            int score = Integer.parseInt(prompt("Score (0-100)"));
	            if (score < 0 || score > 100) {
	                System.out.println("Invalid score range.");
	                return;
	            }
	            opt.get().addScore(score);
	            System.out.println("Score added.");
	        } catch (NumberFormatException e) {
	            System.out.println("Invalid number.");
	        }
	    }

	    private static void removeStudent() {
	        String id = prompt("Student ID");
	        if (manager.removeStudent(id)) {
	            System.out.println("Student removed.");
	        } else {
	            System.out.println("No such student.");
	        }
	    }

	    private static void listStudents() {
	        if (manager.getStudents().isEmpty()) {
	            System.out.println("No students yet.");
	            return;
	        }
	        manager.getStudents().forEach(System.out::println);
	    }

	    private static void printSummary() {
	        System.out.println(manager.summaryReport());
	    }

}
