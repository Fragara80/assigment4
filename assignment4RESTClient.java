package com.fran.assignment4;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fran.assignment4.model.Course;


public class assignment4RESTClient {
	private static final String COURSE_SERVICE_URL = "http://localhost:8080/assignment2/services/courseService";

	public static void main(String[] args) {
		
		Client client = ClientBuilder.newClient();
		
		//GET(get course)
		WebTarget target = client.target(COURSE_SERVICE_URL).path("/courses").path("/{id}").resolveTemplate("id", 123);
				
		Builder request = target.request();
		Course course = request.get(Course.class);
		System.out.println(course.getName());
		System.out.println(course.getPrice());
		System.out.println(course.getRating());
		System.out.println(course.getTaughtBy());
		
		//PUT(update patient)
		course.setName("Course11");

		WebTarget putTarget = client.target(COURSE_SERVICE_URL).path("/courses");
		Response updateResponse = putTarget.request().put(Entity.entity(course, MediaType.APPLICATION_XML));
		System.out.println(updateResponse.getStatus());
		updateResponse.close();
		
		//POST(create patient)
		Course newCourse = new Course();
		newCourse.setName("Course2");
		newCourse.setPrice(1000);
		newCourse.setRating(5);
		newCourse.setTaughtBy("Paul");
		
		WebTarget postTarget = client.target(COURSE_SERVICE_URL).path("/courses");
		Course createCourse = postTarget.request().post(Entity.entity(newCourse, MediaType.APPLICATION_XML),Course.class);
		System.out.println(createCourse.getId());
		
		//DELETE patient
		WebTarget deleteTarget = client.target(COURSE_SERVICE_URL).path("/courses").path("/{id}").resolveTemplate("id", 129);
		Response deleteResponse = deleteTarget.request().delete();
		System.out.println(deleteResponse.getStatus());
		
		//Close connection
		client.close();

	}

}
