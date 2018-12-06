package com.flp.model;

import java.util.List;

public class ExcelUser {
User Student;
List<User> parents;


public User getStudent() {
	return Student;
}
public void setStudent(User student) {
	Student = student;
}
public List<User> getParents() {
	return parents;
}
public void setParents(List<User> parents) {
	this.parents = parents;
}
}
