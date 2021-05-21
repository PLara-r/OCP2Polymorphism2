public class Main extends Primate implements HasTail {
    public int age = 10;
    public boolean isTailStriped() {
        return false;
    }
    public static void main(String[] args) {
        Main lemur = new Main();
        System.out.println(lemur.age);
        HasTail hasTail = lemur;
        System.out.println(hasTail.isTailStriped());
        Primate primate = lemur;
        System.out.println(primate.hasHair());
    }}

    //10
    //false
   //true
   //Полиморфизм также позволяет одному объекту принимать различные формы. доступ к Java-объекту можно получить,
   // используя ссылку того же типа, что и объект, ссылку, которая является суперклассом объекта, или ссылку,
   // которая определяет интерфейс, который реализует объект, либо напрямую, либо через суперкласс.
   // Кроме того, приведение не требуется, если объект переназначается на супертип или интерфейс объекта.
