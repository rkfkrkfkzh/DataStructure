import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        // Integer 타입을 저장하는 SLinkedList 클래스의 객체 original을 생성
        SLinkedList<Integer> original = new SLinkedList<>();
        original.add(10);    // original 객체에 10을 추가합니다.

        // original 객체를 참조하는 copy 객체를 생성
        SLinkedList<Integer> copy = original;
        // original 객체를 복제한 clone 객체를 생성
        SLinkedList<Integer> clone = (SLinkedList<Integer>) original.clone();

        copy.add(20);    // copy 객체에 20을 추가합니다.
        clone.add(30);    // clone 객체에 30을 추가합니다.

        // original, copy, clone 객체의 각 요소를 출력
        System.out.println("original list");
        for (int i = 0; i < original.size(); i++) {
            System.out.println("index " + i + " data = " + original.get(i));
        }

        System.out.println("\ncopy list");
        for (int i = 0; i < copy.size(); i++) {
            System.out.println("index " + i + " data = " + copy.get(i));
        }

        System.out.println("\nclone list");
        for (int i = 0; i < clone.size(); i++) {
            System.out.println("index " + i + " data = " + clone.get(i));
        }

        System.out.println("\noriginal list reference : " + original);
        System.out.println("copy list reference : " + copy);
        System.out.println("clone list reference : " + clone + "\n");

        // Student 타입을 저장하는 SLinkedList 클래스의 객체 list를 생성
        SLinkedList<Student> list = new SLinkedList<>();
        // list 객체에 Student 타입의 객체를 추가
        list.add(new Student("김자바", 92));
        list.add(new Student("이시플", 72));
        list.add(new Student("조시샵", 98));
        list.add(new Student("파이손", 51));
        // list 객체의 요소를 비교할 comparator(customComp)를 이용하여 정렬
        list.sort(customComp);    // Comparator을 파라미터로 넘겨준다.
        // list 객체의 요소를 출력
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
    // Student 클래스의 요소를 비교할 comparator로, 람다식을 이용하여 오름차순으로 정렬하도록 구현
    // 비교 결과는 성적(score)을 기준
    static Comparator<Student> customComp = new Comparator<Student>() {
        @Override
        public int compare(Student o1, Student o2) {
            return o2.score - o1.score;
        }
    };
}
//  학생 정보를 저장하는 클래스로, 이름(name)과 성적(score)을 저장
class Student {
    String name;
    int score;
    Student(String name, int score) {
        this.name = name;
        this.score = score;
    }
    public String toString() {
        return "이름 : " + name + "\t성적 : " + score;
    }
}