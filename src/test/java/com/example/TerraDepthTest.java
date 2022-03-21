package com.example;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import javax.json.JsonObject;
import com.example.TerraDepth.Person;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

/**
 * Unit tests for TerraDepth class.
 */
public class TerraDepthTest 
{
    TerraDepth terraDepth;
    Map<Integer, Person> personMap;
    List<Person> personList;
    Stack<Person> personStack;

@Before
public void setup() {
    terraDepth = new TerraDepth();
    
    Person person1 = new Person(1, "Steve", "Enchilada");
    Person person2 = new Person(2, "Lucy", "Steak");
    Person person3 = new Person(3, "David", "Hamburger");
    Person person4 = new Person(4, "Rachel", "Salmon");
    Person person5 = new Person(5, "Suzie", "Spaghetti");

    personMap = new HashMap<>();
    personMap.put(person1.getId(), person1);
    personMap.put(person2.getId(), person2);
    personMap.put(person3.getId(), person3);
    personMap.put(person4.getId(), person4);
    personMap.put(person5.getId(), person5);

    personList = new ArrayList<>();
    personList.add(person1);
    personList.add(person2);
    personList.add(person3);
    personList.add(person4);
    personList.add(person5);


    personStack = new Stack<Person>();
    personStack.add(person1);
    personStack.add(person2);
    personStack.add(person3);

}

    @Test
    public void shouldReturnNoFavoriteFoodForNullId()
    {
        String favoriteFood = terraDepth.findFavoriteFoodFor(null,personMap);
        Assert.assertEquals("", favoriteFood);
    }

    @Test
    public void shouldReturnNoFavoriteFoodForNullPersonMap()
    {
        String favoriteFood = terraDepth.findFavoriteFoodFor(1,null);
        Assert.assertEquals("", favoriteFood);
    }

    @Test
    public void shouldReturnNoFavoriteFoodForNegativeId()
    {
        String favoriteFood = terraDepth.findFavoriteFoodFor(-2,personMap);
        Assert.assertEquals("", favoriteFood);
    }

    @Test
    public void shouldReturnNoFavoriteFoodForIdNotInRange()
    {
        String favoriteFood = terraDepth.findFavoriteFoodFor(8,personMap);
        Assert.assertEquals("", favoriteFood);
    }


    @Test
    public void shouldReturnFavoriteFoodForSteve()
    {
        String favoriteFood = terraDepth.findFavoriteFoodFor(1,personMap);
        Assert.assertEquals("Enchilada", favoriteFood);
    }

    @Test
    public void shouldReturnNoMealPlanForNullPersonList()
    {

        JsonObject jsonObject = terraDepth.whoCanWeMealPlanFor(null);
        Assert.assertEquals(null, jsonObject);
    }

    @Test
    public void shouldReturnMealPlanForPersonList()
    {

        JsonObject jsonObject = terraDepth.whoCanWeMealPlanFor(personList);
        String expected = "{\"mealPlan\":{\"numberOfPersons\":5,\"names\":[\"Steve\",\"Lucy\",\"David\",\"Rachel\",\"Suzie\"]}}";

        Assert.assertEquals(expected, jsonObject.toString());
    }

    @Test
    public void shouldReturnMealPlanForPersonListNoPersons()
    {

        JsonObject jsonObject = terraDepth.whoCanWeMealPlanFor(new ArrayList<Person>());
        String expected = "{\"mealPlan\":{\"numberOfPersons\":0,\"names\":[]}}";

        Assert.assertEquals(expected, jsonObject.toString());
    }

    @Test
    public void shouldReturnNooneStored()
    {
        String name = terraDepth.whoWasLastStored(null);
        Assert.assertEquals("Noone stored", name);
    }

    @Test
    public void shouldReturnLastStored()
    {
        String name = terraDepth.whoWasLastStored(personStack);
        Assert.assertEquals("David", name);
    }

   
}
