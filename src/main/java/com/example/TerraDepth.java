package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;



/*
*   Interview Questions with Terradepth
*
*   A. Please write a function that receives a name, favorite food. The name and food are both strings that are less than 256 characters. I also want you to store the name and food in 3 different data structures to, as efficiently as possible, support the storage and retrieval by the following functions:
*       1. When you call a findFavoriteFoodFor(name) it returns the favorite food as a string. If the name is not found, return an empty string.
*       2. When you call whoCanWeMealPlanFor() it returns, in the order that they were stored a JSON Object with two elements: 
*           a. The number of people
*           b. An JSON array of the names of those people.
*       3. When whoWasLastStored() is called, it returns a string of the last person who was called with that name. If nobody is stored it will error somehow (you decide how).
*   B. Please also create unit tests to show that the functionality all works. Please get over 85% coverage of your code in unit tests.
*
*
*
*
*
*
*/
public class TerraDepth {

public static void main(String[] args){

Person person1 = new Person(1, "Steve", "Enchilada");
Person person2 = new Person(2, "Lucy", "Steak");
Person person3 = new Person(3, "David", "Hamburger");
Person person4 = new Person(4, "Rachel", "Salmon");
Person person5 = new Person(5, "Suzie", "Spaghetti");

Map<Integer, Person> personMap = new HashMap<>();
personMap.put(person1.getId(), person1);
personMap.put(person2.getId(), person2);
personMap.put(person3.getId(), person3);
personMap.put(person4.getId(), person4);
personMap.put(person5.getId(), person5);

List<Person> personList = new ArrayList<>();
personList.add(person1);
personList.add(person2);
personList.add(person3);
personList.add(person4);
personList.add(person5);


Stack<Person> stack = new Stack<Person>();
stack.add(person1);
stack.add(person2);
stack.add(person3);

//Q1
String rachelsFavoriteFood = findFavoriteFoodFor(4, personMap);
System.out.println("Rachel's favorite food: " + rachelsFavoriteFood);

//Q2
JsonObject jsonObject = whoCanWeMealPlanFor(personList);
System.out.println("Json Object: " + jsonObject);

//Q3
String lastStoredName = whoWasLastStored(stack);
System.out.println("Person last stored: " + lastStoredName);





}

/**
 * 
 * @param name
 * @param collection
 * @return String
 * 
 * Returns favorite food for an individual based on its ID. 
 * Name is not being recommended here for risk of duplicates
 * 
 */
public static String findFavoriteFoodFor(Integer id, Map<Integer, Person> personMap){
    if(id == null || personMap == null){
        return "";
    }
    
    Person person = personMap.get(id);
    if(person == null){
        return "";
    }

    return person.getFavoriteFood();
}


/**
 * 
 * @param collection
 * @return JsonObject
 * 
 * Returns a JsonObject containing the number of persons on the meal plan
 * along with the list of names to meal plan for.
 */
public static JsonObject whoCanWeMealPlanFor(List<Person> personList){
    if(personList == null){
        return null;
    }

    JsonArrayBuilder jsonPersonArrayBuilder = Json.createArrayBuilder();

    for(int i = 0; i < personList.size(); i++){
        jsonPersonArrayBuilder.add(personList.get(i).getName());                 
    }

    JsonObject mealPlan = Json.createObjectBuilder()
                            .add("mealPlan",Json.createObjectBuilder()
                                .add("numberOfPersons", personList.size())
                                .add("names", jsonPersonArrayBuilder)
                                .build())
                            .build();

    return mealPlan;
}

/**
 * 
 * @param stack
 * @return String
 * 
 * Identifies the name of the person last stored in a stack.
 * 
 */
public static String whoWasLastStored(Stack<Person> stack){
    if(stack == null){
        return "Noone stored";
    }
    return stack.peek().getName();
}



/**
 * Person class that has information on their favorite food.
 * 
 */
public static class Person {

    private Integer id;
    private String name;
    private String favoriteFood;

    public Person(Integer id, String name, String favoriteFood){
        this.id = id;
        this.name = name;
        this.favoriteFood = favoriteFood;
    }

    public Integer getId(){
        return this.id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getFavoriteFood(){
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood){
        this.favoriteFood = favoriteFood;
    }
    

}





    
}
