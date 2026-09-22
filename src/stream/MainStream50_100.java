package stream;
import java.util.*;
import java.util.stream.*;
import java.util.function.*;

public class MainStream50_100 {

	public static void main(String[] args) {
		List<Employee> list = new ArrayList<>();
		

		// HR
		list.add(new Employee("Sumit", 121, "HR"));
		list.add(new Employee("Neha", 224, "HR"));
		list.add(new Employee("Sneha", 300, "HR"));
		list.add(new Employee("Karan", 250, "HR"));
		list.add(new Employee("Riya", 190, "HR"));

		// IT
		list.add(new Employee("Rahul", 142, "IT"));
		list.add(new Employee("Priya", 225, "IT"));
		list.add(new Employee("Pooja", 108, "IT"));
		list.add(new Employee("Arjun", 280, "IT"));
		list.add(new Employee("Vivek", 210, "IT"));

		// Finance
		list.add(new Employee("Amit", 183, "Finance"));
		list.add(new Employee("Ankit", 107, "Finance"));
		list.add(new Employee("Nikhil", 260, "Finance"));
		list.add(new Employee("Kunal", 215, "Finance"));
		list.add(new Employee("Meera", 175, "Finance"));

		// Sales
		list.add(new Employee("Rohit", 126, "Sales"));
		list.add(new Employee("Vikas", 119, "Sales"));
		list.add(new Employee("Manish", 240, "Sales"));
		list.add(new Employee("Pankaj", 195, "Sales"));
		list.add(new Employee("Kavita", 160, "Sales"));
		
		// 51. Highest-paid employee in each department
		Map<String,Employee> result51 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.collectingAndThen( Collectors.maxBy(Comparator.comparingInt(Employee::getSalary)), o->o.get())));
		System.out.println(result51);
		
		//52. Average salary by department
		Map<String, Double> result52 = list.stream().collect(Collectors.groupingBy(Employee::getDepartment,Collectors.collectingAndThen( Collectors.averagingInt(Employee ::getSalary), x -> Math.round(x*100)/100.0)));
		System.out.println(result52);
		
		//53. Total salary by department
		Map<String, Integer> result53 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.summingInt(Employee :: getSalary)));
		System.out.println(result53);
		
		//54. Count employees in each department
		Map<String , Long> result54 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.counting()));
		System.out.println(result54);
		
		//55. Find the department with the highest average salary
		String result55_1 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.averagingInt(Employee :: getSalary)))
				.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry :: getKey).get();
		System.out.println(result55_1);
		
		//56. Find the department with the highest total salary
		String result56 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.summingInt(Employee :: getSalary)))
								.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry :: getKey).orElse(null);
		System.out.println(result56);
		
		//57. Find the second-highest salary in each department
		Map<String, Integer> result57 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.collectingAndThen(Collectors.toList(),emps -> emps.stream().sorted(Comparator.comparingInt(Employee :: getSalary).reversed()).skip(1).findFirst().map(Employee :: getSalary).orElse(Integer.MIN_VALUE) )));
		System.out.println(result57);	
		
		//58. Find employees earning more than the average salary
		List<Employee> result58  = list.stream().filter( emp -> emp.getSalary() > (list.stream().mapToInt(Employee :: getSalary).average().orElse(Double.MIN_VALUE))).toList();
		System.out.println(result58);
		
		//59. Find the highest-paid employee overall using max()
		Employee result59 =  list.stream().max(Comparator.comparingInt(Employee :: getSalary)).orElse(null);
		System.out.println(result59);
		
		//60. Find the lowest-paid employee overall using min()
		Employee result60 = list.stream().min(Comparator.comparingInt(Employee :: getSalary)).orElse(null);
		System.out.println(result60);
		
		//61. Partition employees based on salary > 200
		Map<Boolean , List<Employee>> result61 = list.stream().collect(Collectors.partitioningBy(emp -> emp.getSalary() > 200 ));
		System.out.println(result61);
		
		//62. Partition numbers into even and odd
		Map<Boolean , List<Integer>> result62 =  Arrays.asList(1,2,3,4,5,6,7,8,9).stream().collect(Collectors.partitioningBy(n -> n%2 == 0));
		System.out.println(result62);
		
		//63. Partition employees into: salary >= 200 salary < 200
		Map<Boolean , List<Employee>> result63 = list.stream().collect(Collectors.partitioningBy(emp -> emp.getSalary() > 200 ));
		System.out.println(result63);
		
		//64. Count employees earning more than 200 and less than/equal to 200
		Map<Boolean ,Long> result64_1 = list.stream().collect(Collectors.partitioningBy(emp -> emp.getSalary() > 200, Collectors.counting()));
		System.out.println(result64_1);
		Map<String , Long> result64_2 = list.stream().collect(Collectors.groupingBy(emp ->{
			if(emp.getSalary() < 200) return "200-Minus";
			else  return "200+Plus";
		}, Collectors.counting() ));
		System.out.println(result64_2);
		
		//65. Partition employees based on whether their name starts with "A"
		Map<Boolean, List<Employee>> result65 = list.stream().collect(Collectors.partitioningBy( emp -> emp.getName().startsWith("A")));
		System.out.println(result65);
		
		//66. Group employees by salary ( 200-Minus && 200+Plus) and then by department
		Map<String, Map<String,List<Employee>>> result66 =  list.stream().collect(Collectors.groupingBy(emp ->{ if(emp.getSalary() < 200) return "200-Minus";
			else  return "200+Plus";}, Collectors.collectingAndThen(Collectors.toList(), emps -> emps.stream().collect(Collectors.groupingBy(Employee :: getDepartment)))));
		System.out.println(result66);
		
		//67. Group employees by department and then calculate average salary
		Map<String, Double> result67 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.collectingAndThen(Collectors.averagingInt(Employee :: getSalary), x ->Math.round(x*100)/100.0)));
		System.out.println(result67);
		
		//68. Group employees by department and find the highest salary	
		Map<String, Integer> result68 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee:: getSalary)), o -> o.get().getSalary())));
		System.out.println(result68);
		
		//69. Group employees by salary ( 200-Minus && 200+Plus) and find the number of employees in each.
		Map<String, Long>  result69 = list.stream().collect(Collectors.groupingBy(emp ->{
			if(emp.getSalary() < 200) return "200-Minus";
			else  return "200+Plus";
		}, Collectors.counting()));
		System.out.println(result69);
		
		//70. Group employees by department and find the highest-paid employee in each 
	
		Map<String, Integer> result70 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee:: getSalary)), o -> o.get().getSalary())));
		System.out.println(result70);
		
		//71. Find the first repeated character in a string
		String str71 = "programming";
		
		int result71 = str71.chars().filter(s-> str71.indexOf(s) != str71.lastIndexOf(s)).findFirst().getAsInt();
		System.out.println((char)result71);
		
		//72. Find the first non-repeated character
		int result72 = str71.chars().filter(s-> str71.indexOf(s) == str71.lastIndexOf(s)).findFirst().getAsInt();
		System.out.println((char)result72);
		
		//73. Find all repeated characters
		List<Character> result73 = str71.chars().filter(s-> str71.indexOf(s) != str71.lastIndexOf(s)).mapToObj(ch -> (char)ch).toList();
		System.out.println(result73);
		
		
		//74. Find characters appearing only once
		List<Character> result74 = str71.chars().filter(s-> str71.indexOf(s) == str71.lastIndexOf(s)).mapToObj(ch -> (char)ch).toList();
		System.out.println(result74);
		
		//75. Find the character with the highest frequency
		String str75 = "programming";
		Map<Character, Long> result75 = str75.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(result75);
		char result75_2 = result75.entrySet().stream().sorted(Map.Entry.<Character,Long>comparingByValue().reversed()).findFirst().map(Map.Entry :: getKey).get();
		System.out.println(result75_2);
		
		//76. Find the character with the lowest frequency
		char result76 = str75.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream().sorted(Map.Entry.<Character,Long>comparingByValue()).findFirst().map(Map.Entry :: getKey).get();
		System.out.println(result76);
		
		//77. Count vowels in a string using Streams
		Long result77 = str75.chars().mapToObj(c -> (char)c).filter(c ->{
			if( c== 'a' || c=='i' || c=='e' || c=='o' || c=='u') return true ;
			else return false;}).count();
		System.out.println(result77);
		
		//78. Count consonants in a string
		Long result78 = str75.chars().mapToObj(c -> (char)c).filter(c ->{
			if( c== 'a' || c=='i' || c=='e' || c=='o' || c=='u') return false ;
			else return true;}).count();
		System.out.println(result78);
		
		//79. Remove duplicate characters from a string
		String result79 = str75.chars().mapToObj(c -> String.valueOf((char)c)).filter(c -> str75.indexOf(c) == str75.lastIndexOf(c)).reduce((a,b)-> a+b).get();
		System.out.println(result79);
		
		//80. Sort characters of a string by frequency
		Map<Character, Long> result80 = str75.chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
					.entrySet().stream().sorted(Map.Entry.<Character,Long>comparingByValue()).collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue,(a,b)->a , LinkedHashMap :: new));
		System.out.println(result80);
		
		//81. Convert List<List<Integer>> into List<Integer>
		List<Integer> list1 = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		List<Integer> list2 = Arrays.asList(1,2,11,12,13,14,15,16,17,18,19,20);
		List<Integer> list3 = Arrays.asList(11,12,21,22,23,24,25,26,27,28,29,30);
		
		List<List<Integer>> list80 = Arrays.asList(list1, list2,list3);
		
		List<Integer> result81 = list80.stream().flatMap(l ->l.stream()).toList();
		System.out.println(result81);
		
		//82. Find all unique numbers from nested lists
		List<Integer> result82 = list80.stream().flatMap(l ->l.stream()).distinct().toList();
		System.out.println(result82);
		
		//83. Find duplicate numbers from nested lists
		List<Integer> result83 = list80.stream().flatMap(l->l.stream()).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().filter(e -> e.getValue()>1).map(Map.Entry :: getKey).toList();
		System.out.println(result83);
		
		//84. Find the maximum number from nested lists
		
		int result84 = list80.stream().flatMap(l->l.stream()).max(Comparator.comparingInt(n->n)).orElse(-1);
		System.out.println(result84);
		
		//85. Find the sum of all numbers from nested lists
		int result85 = list80.stream().flatMap(l -> l.stream()).reduce((a,b)->a+b).orElse(-1);
		System.out.println(result85);
		
		//86. Convert List<List<String>> into a single list of strings
		List<List<String>> list86 = Arrays.asList(
			    Arrays.asList("Java", "Spring", "Hibernate"),
			    Arrays.asList("Kafka", "Redis"),
			    Arrays.asList("Docker", "AWS", "Git")
			);
		List<String> result86 =  list86.stream().flatMap(s->s.stream()).toList();
		System.out.println(result86);
		
		//87. Find all unique words from multiple sentences
		List<String> list87 = Arrays.asList(
			    "Java is powerful",
			    "Java is popular",
			    "Streams are powerful"
			);
		List<String> result87 = list87.stream().flatMap(s->Arrays.stream(s.split(" "))).distinct().toList();
		System.out.println(result87);
		
		//88. Find the frequency of each word across multiple sentences
		Map<String , Long> result88 =  list87.stream().flatMap(s-> Arrays.stream(s.split(" "))).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		System.out.println(result88);
		
		//89. Find the longest word from multiple sentences
		String result89 =  list87.stream().flatMap(s->Arrays.stream(s.split(" "))).max(Comparator.comparingInt(String :: length)).orElse(null);
		System.out.println(result89);
		
		
		//90. Find duplicate words across multiple sentences
		List<String> result90 =  list87.stream().flatMap(s-> Arrays.stream(s.split(" "))).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
				.entrySet().stream().filter(e-> e.getValue() > 1).map(Map.Entry :: getKey).toList();
		System.out.println(result90);
		
		//91. Find the second-highest salary without sorting
		int  result91  = list.stream().reduce(new int[] {Integer.MIN_VALUE, Integer.MIN_VALUE},
				(arr,emp)->{
					if(emp.getSalary() >= arr[0]) {
						arr[1]= arr[0];
						arr[0] = emp.getSalary(); 
					}
					else if(emp.getSalary() >arr[1]){
						arr[1] = emp.getSalary();
						
					}
					return arr;
				},(arr1, arr2) ->arr1)[1];
		System.out.println(result91); 
		
		//92. Find the maximum number without using max()
		int result92 = list3.stream().reduce(Integer.MIN_VALUE, (a, e)-> {  return a > e ? a : e;});
		System.out.println(result92);
		
		//93. Find the sum of numbers without using sum()
		int result93 = list3.stream().reduce(0, (a,b)-> a+b);
		System.out.println(result93);
		
		//94. Find the product of all numbers using reduce()
		int result94 = list3.stream().reduce(1, (a, e)-> a*e);
		System.out.println(result94);
		
		//95. Find the employee with the second-highest salary without using sorted()
		Employee  result95  = list.stream().reduce(new Employee[] {new Employee(null, Integer.MIN_VALUE, null), new Employee(null, Integer.MIN_VALUE, null)},
				(arr,emp)->{
					if(emp.getSalary() >= arr[0].getSalary()) {
						arr[1]= arr[0];
						arr[0] = emp; 
					}
					else if(emp.getSalary() >arr[1].getSalary()){
						arr[1] = emp;
						
					}
					return arr;
				},(arr1,arr2)->arr1)[1];
		System.out.println(result95); 
		
		//96. Convert employees into: Map<String, List<String>> key   = department, value = employee names
		Map<String, List<String> > result96 = list.stream().collect(Collectors.groupingBy(Employee:: getDepartment, Collectors.collectingAndThen(Collectors.toList(), emps -> emps.stream().map(Employee::getName).toList())));
		System.out.println(result96);
		 
		//97. Convert employees into: Map<String, Employee> :: key = department , value = highest-paid employee
		Map<String,Employee> result97 =  list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingInt(Employee :: getSalary)), Optional :: get)));
		System.out.println(result97);
		
		//98. Find the top 3 highest-paid employees
		List<Employee> result98 = list.stream().sorted(Comparator.comparingInt(Employee :: getSalary).reversed()).limit(3).toList();
		System.out.println(result98);
		
		List<Employee> result98_2 = list.stream().sorted(Comparator.comparingInt(Employee ::getSalary).reversed()).collect(Collectors.toMap(e->e.getSalary(), e->e, (a,b)->a, LinkedHashMap::new )).values().stream().limit(3).toList();
		System.out.println(result98_2);
		
		//99. Find the top 3 highest-paid employees from each department
		Map<String, List<Employee>> result99 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment, Collectors.collectingAndThen(Collectors.toList(),
				emps-> emps.stream().sorted(Comparator.comparingInt(Employee :: getSalary).reversed()).collect(Collectors.toMap(Employee :: getSalary, e->e, (a,b)->a, LinkedHashMap :: new)).values().stream().limit(3).toList()
				)));
		System.out.println(result99);
		
		//100. Find the highest-paid employee from each department, but return only employee names
		
		Map<String, String> result100 = list.stream().collect(Collectors.groupingBy(Employee :: getDepartment ,Collectors.collectingAndThen( Collectors.maxBy(Comparator.comparingInt(Employee :: getSalary)),  o -> o.get().getName())));
		System.out.println(result100);
	}

}
class Employee {
    String name;
    int salary;
    String department;
    
    
    
    public Employee(String name, int salary , String department) {
    	this.name = name;
    	this.salary = salary;
    	this.department = department;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "["+name + "," + salary + "," + department + "]";
	}
    
    
}
