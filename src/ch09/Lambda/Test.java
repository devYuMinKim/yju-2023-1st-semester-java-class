import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    Person p1 = new Person("고길동", LocalDate.of(2000, 1, 4), "gdhon@gmail.com", Sex.MALE);
    Person p2 = new Person("일지매", LocalDate.of(1995, 2, 12), "gmil@gmail.com", Sex.FEMALE);
    Person p3 = new Person("박문수", LocalDate.of(1988, 5, 31), "mspark@gmail.com", Sex.MALE);

    // List는 인터페이스, 이 인터페이스를 구현하는 클래스로 ArrayList, LinkedList 등이 있다.
    // Person 타입의 객체를 원소로 가질 수 있는 리스트를 생성
    // ArrayList<Person> persons = new ArrayList<>(); 도 가능
    // List<Person> list = new ArrayList<Person>(); 도 가능
    List<Person> list = new ArrayList<>(); // 생략도 가능하다.
    list.add(p1);
    list.add(p2);
    list.add(p3);

    // 고길동 : 23세
    // 일지매 : 28세
    // 박문수 : 35세

    printPersonOlderThan(list, 23);
    /**
     * Name: 고길동, Email: gdhon@gmail.com, Gender: MALE, BirthDate: 2000-01-04, Age:
     * 23
     * 
     * Name: 일지매, Email: gmil@gmail.com, Gender: FEMALE, BirthDate: 1995-02-12, Age:
     * 28
     * 
     * Name: 박문수, Email: mspark@gmail.com, Gender: MALE, BirthDate: 1988-05-31, Age:
     * 35
     */
    printPersonWithinAgeRange(list, 28, 40);
    /**
     * Name: 일지매, Email: gmil@gmail.com, Gender: FEMALE, BirthDate: 1995-02-12,
     * Age:
     * 28
     * 
     * Name: 박문수, Email: mspark@gmail.com, Gender: MALE, BirthDate: 1988-05-31,
     * Age:
     * 35
     */
    printPersons(list, new CheckPersonMale18to25());
    /**
     * Name: 고길동, Email: gdhon@gmail.com, Gender: MALE, BirthDate: 2000-01-04, Age:
     * 23
     */
    printPersons(list, new CheckPersonFemale20to30());
    /**
     * Name: 일지매, Email: gmil@gmail.com, Gender: FEMALE, BirthDate: 1995-02-12, Age:
     * 28
     */
  }

  // collection framework: List, Set, Map, Stack, Queue
  // collection framework는 여러 개의 값을 저장하는 자료구조
  // 이 자료구조에 들어갈 수 있는 원소 타입을 자바의 generic 이라는 기능을 이용해 저장할 수 있다.
  // List<Person>, Set<Integer>, Map<String, Person>, ...
  public static void printPersonOlderThan(List<Person> persons, int age) {
    // persons 리스트의 각 원소에 대해서 반복문을 수행한다.
    // 각 원소를 Person 타입의 변수 P에 저장
    for (Person p : persons) {
      if (p.getAge() >= age) {
        p.printPerson();
        System.out.println();
      }
    }
  }

  // List<Person> list, int low, int high
  // 위 세 개의 매개변수를 통해 Person 객체 리스트와, 나이의 범위(low 이상, high 미만)를 입력으로 받아, 저 나이 범위의
  // Person 정보만 출력하는 메서드
  public static void printPersonWithinAgeRange(List<Person> persons, int low, int high) {
    for (Person p : persons) {
      if (low <= p.getAge() && p.getAge() < high) {
        p.printPerson();
        System.out.println();
      }
    }
  }

  // Person 객체의 리스트와 CheckPerson 인터페이스를 구현한 객체(tester라 가정)를 전달받고,
  // 리스트의 각 Person 객체를 tester.text 메서드의 인자로 전달해, 그 반환 값이 true이면
  // 그 Person 객체의 정보를 출력.
  public static void printPersons(List<Person> list, CheckPerson tester) {
    for (Person p : list) {
      if (tester.test(p)) {
        p.printPerson();
        System.out.println();
      }
    }
  }

  interface CheckPerson {
    boolean test(Person p);
  }

  static class CheckPersonMale18to25 implements CheckPerson {
    public boolean test(Person p) {
      return p.getGender() == Sex.MALE && p.getAge() >= 18 && p.getAge() < 25;
    }
  }

  static class CheckPersonFemale20to30 implements CheckPerson {
    public boolean test(Person p) {
      return p.getGender() == Sex.FEMALE && p.getAge() >= 20 && p.getAge() < 30;
    }
  }
}
