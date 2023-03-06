class Node<E> {
    E data; // data: E 타입의 데이터를 저장하는 변수
    Node<E> next; // next: Interface_form.Node<E> 타입의 다음 노드를 가리키는 Reference 변수입니다.

    // Interface_form.Node 클래스는 다음과 같은 생성자를 가지고 있습니다.
// Interface_form.Node(E data): data 값을 받아서 새로운 Interface_form.Node 객체를 생성합니다.
// 이 때, next는 null로 초기화됩니다.
    Node(E data) {
        this.data = data;
        this.next = null;
    }

}
// 제네릭 클래스(Interface_form.Node)를 정의하는 코드입니다.
// 이 클래스는 링크드 리스트(linked list)를 구현할 때 사용
//즉, Interface_form.Node 클래스는 데이터(data)와 다음 노드(next)를 가리키는 포인터로 이루어진 하나의 노드를 나타내는 클래스입니다.
// 링크드 리스트에서 각 노드는 다음 노드를 가리키는 포인터를 가지고 있어서, 리스트의 처음부터 끝까지 순차적으로 접근할 수 있습니다.
