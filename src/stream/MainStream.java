package stream;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class MainStream {

	public static void main(String[] args) {
	    //1. Filter even numbers
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8);
List<Integer> even = numbers.stream().filter(n -> n%2==0).toList();
System.out.println(even);

//2. Filter numbers greater than 10
List<Integer> numbers2 = Arrays.asList(5, 12, 8, 20, 3, 15);
List<Integer> greater10 = numbers2.stream().filter(n -> n>10).toList();
System.out.println(greater10);

//3. Convert strings to uppercasem
List<String> names = Arrays.asList("sumit", "rahul", "amit", "rohit");
List<String> upperName = names.stream().map(x -> x.toUpperCase()).toList();
System.out.println(upperName);

//4. Find names starting with "A"
List<String> names2 = Arrays.asList("Amit", "Rahul", "Ankit", "Sumit", "Ajay");
List<String> aStart = names2.stream().filter(x -> x.startsWith("A")).toList();
System.out.println(aStart);

//5. Count elements
List<Integer> numbers3 = Arrays.asList(10, 20, 30, 40, 50);
System.out.println(numbers3.size());
long c = numbers3.stream().count();
System.out.println(c);

//6. Square every number
List<Integer> numbers4 = Arrays.asList(2, 3, 4, 5); 
List<Integer> sqNum = numbers4.stream().map(n -> n*n).toList();
System.out.println(sqNum);

//7. Sort numbers
List<Integer> numbers5 = Arrays.asList(5, 1, 8, 3, 2);
List<Integer> sortNum = numbers5.stream().sorted().toList();
List<Integer> sortNum2 = numbers5.stream().sorted(Comparator.reverseOrder()).toList();
System.out.println(sortNum);
System.out.println(sortNum2);

//8. Sort strings by length
List<String> names3 =Arrays.asList("Sumit", "Raj", "Amitabh", "Ram", "Rahul");
List<String> sortStr = names3.stream().sorted(Comparator.comparingInt(String::length)).toList();
System.out.println(sortStr);

//9. Find maximum
List<Integer> numbers6 = Arrays.asList(10, 25, 5, 40, 15);
int max = numbers6.stream().max((a,b)->a-b).get();
System.out.println(max);

//10. Find Minimum
int min = numbers6.stream().min((a,b)->a-b).get();
System.out.println(min);

//11. Remove duplicates
List<Integer> numbers7 = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 5);
List<Integer> distinct = numbers7.stream().distinct().toList();
System.out.println(distinct);

//12. Find the first number greater than 50
List<Integer> numbers8 = Arrays.asList(10, 30, 55, 20, 80, 40);
int num51 = numbers8.stream().filter(n -> n>50).findFirst().get();
System.out.println(num51);

//13. Check whether any number is negative
List<Integer> numbers9 = Arrays.asList(10, 20, -5, 30, 40);
boolean first = numbers9.stream().anyMatch(n -> n<0);
System.out.println(first);

//14. Check whether all numbers are positive
List<Integer> numbers10 = Arrays.asList(10, 20, 5, 30, 40);
 first = numbers10.stream().allMatch(n -> n>0);
System.out.println(first);

//15. Calculate the sum
List<Integer> numbers11 = Arrays.asList(10, 20, 30, 40, 50);
int sum = numbers11.stream().mapToInt(Integer::intValue).sum();
System.out.println(sum);

// 16. Find duplicate numbers
List<Integer> numbers12 = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);
Set<Integer> set = new HashSet<>();
List<Integer> du = numbers12.stream().filter( n -> !set.add(n)).toList();
System.out.println(du);

//17. Find numbers appearing only once
List<Integer> numbers13 = Arrays.asList(1, 2, 3, 2, 4, 5, 3, 6);
List<Integer> onlyOnce = numbers13.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().filter( e -> e.getValue()==1).map(e-> e.getKey()).toList();
System.out.println(onlyOnce);

//18. Find the second-highest number
List<Integer> numbers14 = Arrays.asList(10, 50, 20, 40, 50, 30);
int sH = numbers14.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
System.out.println(sH);

//19. Find the longest name
List<String> names4 = Arrays.asList("Sumit", "Rahul", "Amitabh", "Raj", "Vivek");
String nl = names4.stream().sorted(Comparator.comparingInt(String ::length).reversed()).findFirst().get();
System.out.println(nl);


	
//20. Count names starting with "A"
List<String> names5 = Arrays.asList("Amit", "Rahul", "Ankit", "Sumit", "Ajay");
long k = names5.stream().filter(x -> x.startsWith("A")).count();
System.out.println(k);

// 21. Count frequency of each number
List<Integer> numbers15 = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4);
Map<Integer, Long> frequency1 = numbers15.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
System.out.println(frequency1);

//22. Find the most frequent number
List<Integer> numbers16 = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4,4,4);
int x = numbers16.stream()
.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
.entrySet().stream()
.max(Map.Entry.comparingByValue()).get().getKey();
System.out.println(x);

//23. Group names by their length
List<String> names6 =  Arrays.asList("Ram", "Raj", "Sumit", "Amit", "Rahul");
Map<Integer, List<String>> map= names6.stream().collect(Collectors.groupingBy(s-> s.length()));
System.out.println(map);

//24. Join all names with comma
List<String> names7 = Arrays.asList("Sumit", "Rahul", "Amit", "Rohit");
String n = names7.stream().collect(Collectors.joining(","));
System.out.println(n);

//25. Find the first non-repeated character

String str = "swiss";
char ch = str.chars().mapToObj(x1 ->(char)x1).filter(x1 ->str.indexOf(x1) == str.lastIndexOf(x1)).findFirst().get();
System.out.println(ch);

//26. Second-highest number
List<Integer> numbers17 = Arrays.asList(10, 50, 20, 80, 50, 70, 80);
int m = numbers17.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst().get();
System.out.println(m);

// 27. Longest name using max()
List<String> names8 =  Arrays.asList("Raj", "Sumit", "Rahul", "Amitabh", "Vivek");
String mL = names8.stream().max(Comparator.comparingInt(String ::length)).get();
System.out.println(mL);

//28. Find duplicate characters
String str2 = "programming";
List<Character>  duChar = str2.chars().mapToObj(xx->(char)xx).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
 .entrySet().stream().filter(e->e.getValue() > 1).map(e -> e.getKey()).toList();
System.out.println(duChar);

//29. Character frequency
String str3 = "programming";
Map<Character, Long> map1 = str3.chars().mapToObj(obj -> (char)obj).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
System.out.println(map1);

//30. Highest-paid employee
class Employee {
    private String name;
    private int salary;
    private String city;

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }
    
    public Employee(String name, int salary, String city) {
        this.name = name;
        this.salary = salary;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }
    
    public String getCity(){
        return city;
    }
}

List<Employee> employees = Arrays.asList( new Employee("Sumit", 80000),new Employee("Rahul", 60000),new Employee("Amit", 90000),
                                             new Employee("Rohit", 75000));
Employee hSE = employees.stream().max(Comparator.comparingInt(Employee :: getSalary)).get();
System.out.println(hSE.getName());

//33. Calculate total salary
int totalSalary = employees.stream().mapToInt(Employee :: getSalary).sum();
System.out.println(totalSalary);

//34. Group employees by salary
Map<Integer, List<Employee>> mapE = employees.stream().collect(Collectors.groupingBy(Employee :: getSalary));
System.out.println(mapE);

//35. Find the highest-paid employee in each city
List<Employee> employees2 = Arrays.asList(
new Employee("Sumit", 80000 , "Delhi"),
new Employee("Rahul", 90000 , "Delhi"),
new Employee("Amit", 70000 , "Pune"),
new Employee("Rohit", 85000 , "Pune"),
new Employee("Vivek", 75000 , "Delhi") );

Map<String, Optional<Employee>> map3 = employees2.stream().collect(Collectors.groupingBy(Employee :: getCity, Collectors.maxBy(Comparator.comparingInt(Employee::getSalary))));
System.out.println(map3);

// 36. Find the employee with the second-highest salary
List<Employee> employees3 = Arrays.asList(
new Employee("Sumit", 80000),
new Employee("Rahul", 90000),
new Employee("Amit", 70000),
new Employee("Sohit", 90000),
new Employee("Vivek", 75000) );
Set<Integer> set2 = new HashSet<>();

Employee sHS = employees3.stream().filter(e-> set.add(e.getSalary())).sorted(Comparator.comparingInt(Employee :: getSalary).reversed()).skip(1).findFirst().orElse(null);
System.out.println(sHS.getName());

// 37. Find employees whose salary is greater than 75000
// Expected: Sumit, Rahul, Rohit
List<Employee> list76 = employees3.stream().filter(e-> e.getSalary() > 75000).toList();
list76.forEach(e-> System.out.print(e.getName()+ " "));

//// 38. Find employees whose name starts with "S"
System.out.println();
employees3.stream().filter(e -> e.getName().startsWith("S")).forEach(e-> System.out.print(e.getName() + " "));

//// 39. Sort employees by salary ascending
System.out.println();
employees3.stream().sorted(Comparator.comparingInt(Employee::getSalary)).forEach(e-> System.out.print(e.getName()+ " "));

//// 39. Sort employees by salary descending
System.out.println();
employees3.stream().sorted(Comparator.comparingInt(Employee::getSalary).reversed()).forEach(e-> System.out.print(e.getName()+ " "));

// 41. Find the average salary of employees
System.out.println();
double avg = employees3.stream().mapToInt(e->e.getSalary()).average().orElse(-1);
System.out.println(avg);

// 43. Convert List<Employee> into Map<String, Integer>
// key   = employee name
// value = salary

Map<String, Integer> map4 = employees3.stream().collect(Collectors.toMap(Employee :: getName , Employee :: getSalary));
System.out.println(map4);

// 44. Find the employee with the lowest salary
Employee lES = employees3.stream().sorted(Comparator.comparingInt(Employee :: getSalary)).findFirst().orElse(null);
System.out.println(lES.getName());

// 45. Count employees whose salary is greater than 75000
long count75 = employees3.stream().filter(e-> e.getSalary() > 75000).count();
System.out.println(count75);

// 46. Group employees by salary range
//
// < 75000       -> "LOW"
// 75000-85000   -> "MEDIUM"
// > 85000       -> "HIGH"
System.out.println("Low ::");
employees3.stream().filter(e-> e.getSalary() < 75000).forEach(e-> System.out.print(e.getName() +" "));
System.out.println("\nMedium::");
employees3.stream().filter(e-> e.getSalary() >= 75000 && e.getSalary() <= 85000).forEach(e-> System.out.print(e.getName() +" "));
System.out.println("\nHigh::");
employees3.stream().filter(e-> e.getSalary() > 85000).forEach(e-> System.out.print(e.getName() +" "));
System.out.println();

// 47. Find duplicate employee salaries

Map<Integer, Long> map5 = employees3.stream().collect(Collectors.groupingBy(Employee :: getSalary, Collectors.counting()))
.entrySet().stream().filter(e -> e.getValue()>1).collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue));
System.out.println(map5);

// 48. Find the highest salary in each city
Map<String, Optional<Employee>> map6 = employees2.stream().collect(Collectors.groupingBy(Employee :: getCity, Collectors.maxBy(Comparator.comparingInt(Employee :: getSalary))));
map6.entrySet().stream().forEach(e-> System.out.println(e.getKey() +" "+ e.getValue().get().getName()));

//// 49. Find the average salary of employees in each city
Map<String, Double> avgCitySalary = employees2.stream().collect(Collectors.groupingBy(Employee :: getCity, Collectors.averagingInt(Employee :: getSalary)));
System.out.println(avgCitySalary);

//// 50. Find the number of employees in each city
Map<String, Long> countCityEmployee = employees2.stream().collect(Collectors.groupingBy(Employee :: getCity , Collectors.counting()));
System.out.println(countCityEmployee);
}

}
