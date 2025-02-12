package com.koreait.lombok_practice_02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeContoller {
    @GetMapping("a")
    @ResponseBody
    public String plus(@RequestParam("a") String num1Str, @RequestParam("b") String num2Str) {
        int num1 = Integer.parseInt(num1Str);
        int num2 = Integer.parseInt(num2Str);

        System.out.println("a : " + num1Str);
        System.out.println("b : " + num2Str);

        return "a + b = %d".formatted(num1 + num2);
    }

    @GetMapping("b")
    @ResponseBody
    public String plus(
            //http://localhost:8080/b?a=10&b=20
            @RequestParam("a") int num1,
            @RequestParam("b") int num2,
            @RequestParam(name= "c", defaultValue = "10") int num3
    ) {

        System.out.println("a : " + num1);
        System.out.println("b : " + num2);
        System.out.println("c : " + num3);

        return "2: a + b + c = %d".formatted(num1 + num2 + num3);
    }

    @GetMapping("c")
    @ResponseBody
    public String c(
            Boolean isMarried
    ) {
        if(isMarried == null) return "정보 입력해";

        return isMarried ? "Married" : "Unmarried";
    }

   public static class Person{
        private String name;
        private int age;

       public String getName() {
           return name;
       }

       public void setName(String name) {
           this.name = name;
       }

       public void setAge(int age) {
           this.age = age;
       }

       public int getAge() {
           return age;
       }

       public Person(String name, int age) {
           this.name = name;
           this.age = age;
       }

       @Override
       public String toString() {
           return "Person{" +
                   "name='" + name + '\'' +
                   ", age=" + age +
                   '}';
       }
   }

    @GetMapping("person")
    @ResponseBody
    public String person1(
            String name,
            int age
    ) {
        Person person = new Person(name, age);

        return person.toString();
    }

    @GetMapping("person2")
    @ResponseBody
    public String person1(
            Person person
    ) {
        return person.toString();
    }

}
