package others;

/**
 * @ClassName Person
 * @Description TODO
 * @Author thinkpad
 * @Date 2020/3/13 17:23
 * @Version 1.0
 **/
public class Person {
    public String name;
    private int age;
    protected float score;
    Person p;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", score=" + score +
                '}';
    }
}
