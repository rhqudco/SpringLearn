package hello.core.singleton;

public class SingletonService {
    // 자기 자신을 static으로 내부에 private하게 하나 가지고 있다
    // 클래스 레벨에 올라가기 때문에 딱 하나만 존재하게 됨
    // 1. static영역에 객체를 딱 1개만 생성
    private static final SingletonService instance = new SingletonService();
    // 2. public으로 열어서 객체 인스턴스가 필요하면 static 메소드를 통해서만 조회하도록 허용
    public static SingletonService getInstance() {
        return instance;
    }
    // 3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService() {
    }

    public void login() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}