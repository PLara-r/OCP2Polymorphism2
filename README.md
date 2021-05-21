# OCP2Polymorphism2

Полиморфизм также позволяет одному объекту принимать различные формы. Как вы, возможно, помните, изучая экзамен OCA,
доступ к Java-объекту можно получить, используя ссылку того же типа, что и объект, ссылку, которая является суперклассом объекта, или ссылку, 
которая определяет интерфейс, который реализует объект, либо напрямую, либо через суперкласс. Кроме того, приведение не требуется,
если объект переназначается на супертип или интерфейс объекта.
Следующий пример иллюстрирует это полиморфное свойство:

public class Primate { 
  public boolean hasHair() {   
   return true;  
 }}
 public interface HasTail {   
public boolean isTailStriped();
} 
public class Lemur extends Primate implements HasTail {  
 public int age = 10;  
  public boolean isTailStriped() {    
  return false; 
  }    
public static void main(String[] args) {  
    Lemur lemur = new Lemur();   
   System.out.println(lemur.age);      
 HasTail hasTail = lemur;     
 System.out.println(hasTail.isTailStriped());
Primate primate = lemur;    
  System.out.println(primate.hasHair());
   }}
Этот код компилируется и выполняется без проблем и выдает следующий вывод:

10
False
True


Самое важное, что следует отметить в этом примере, это то, что только один объект,
Lemurсоздается и на него ссылаются. Способность Lemurобъекта передаваться как экземпляр интерфейса, который он реализует,
HasTailа также как экземпляр одного из его суперклассов Primate, является природой полиморфизма.
Если вы используете переменную для ссылки на объект, то только методы или переменные,
которые являются частью ссылочного типа переменной, могут быть вызваны без явного приведения. Например, следующие фрагменты кода не будут компилироваться:

asTail hasTail = lemur;
System.out.println(hasTail.age);              // DOES NOT COMPILE 
Primate primate = lemur;
System.out.println(primate.isTailStriped());  // DOES NOT COMPILE


В этом примере ссылка hasTailимеет прямой доступ только к методам, определенным HasTailинтерфейсом; следовательно,
он не знает, что переменная ageявляется частью объекта. Аналогично, ссылка primateимеет доступ только к методам,
определенным в Primateклассе, и не имеет прямого доступа к isTailStriped()методу.

  Различение между объектом и ссылкой
  
  
В Java все объекты доступны по ссылке, поэтому, как разработчик, вы никогда не имеете прямого доступа к памяти самого объекта.
Концептуально, однако, вы должны рассматривать объект как сущность, которая существует в памяти, выделенной средой выполнения Java.
Независимо от типа ссылки на объект в памяти, сам объект не изменяется. Например, поскольку все объекты наследуются, все java.lang.Object,
они могут быть переназначены java.lang.Object, как показано в следующем примере:
Lemur lemur = new Lemur();
 Object lemurAsObject = lemur;
Даже если Lemurобъекту была присвоена ссылка другого типа, сам объект не изменился и все еще существует как Lemurобъект в памяти. 
Что изменилось, так это наша способность обращаться к методам в Lemurклассе с помощью lemurAsObjectссылки. Без явного приведения к Lemur, 
как вы увидите в следующем разделе, у нас больше нет доступа к Lemurсвойствам объекта.
Мы можем обобщить этот принцип следующими двумя правилами:
1.	Тип объекта определяет, какие свойства существуют в объекте в памяти.
2.	Тип ссылки на объект определяет, какие методы и переменные доступны для программы Java.
Отсюда следует, что успешное изменение ссылки на объект на новый тип ссылки может дать вам доступ к новым свойствам объекта,
но эти свойства существовали до того, как произошло изменение ссылки.
Мы снова проиллюстрируем это свойство, используя предыдущий пример, как показано на рисунке 2.3 .
Как видно на рисунке, один и тот же объект существует в памяти независимо от того, какая ссылка на него указывает.
В зависимости от типа ссылки, мы можем иметь доступ только к определенным методам. Например, hasTailссылка имеет доступ к методу isTailStriped(),
но не имеет доступа к переменной, ageопределенной в Lemurклассе. Как вы узнаете в следующем разделе, можно восстановить доступ к переменной age,
явно приведя hasTailссылку на Lemurссылку.

Рисунок 2.3 Объект против ссылки

 Ссылки на объекты	
 
 
В предыдущем примере мы создали один экземпляр Lemurобъекта и получили к нему доступ через ссылки на суперкласс и интерфейс.
Однако после изменения ссылочного типа мы потеряли доступ к более конкретным методам, определенным в подклассе, который все еще существует в объекте.
Мы можем восстановить эти ссылки, приведя объект обратно к определенному подклассу, из которого он получен:


Primate primate = lemur; 
Lemur lemur2 = primate; // DOES NOT COMPILE
 Lemur lemur3 = (Lemur)primate;
System.out.println(lemur3.age);
В этом примере мы сначала пытаемся преобразовать primateссылку обратно в lemurссылку lemur2, без явного приведения. В результате код не будет компилироваться.
Во втором примере, однако, мы явно приводим объект к подклассу объекта Primateи получаем доступ ко всем методам, доступным для Lemurкласса.
Вот несколько основных правил, которые следует учитывать при приведении переменных:
1.	Приведение объекта из подкласса в суперкласс не требует явного приведения.
2.	Преобразование объекта из суперкласса в подкласс требует явного приведения.
3.	Компилятор не разрешает приведение к несвязанным типам.
4.	Даже когда код компилируется без проблем, во время выполнения может быть сгенерировано исключение, если объект на самом деле не является экземпляром этого класса.
Третье правило важно; Экзамен может попытаться обмануть вас актерами, которые не разрешены компилятором. Например, мы смогли привести Primateссылку на Lemurссылку,
Lemurкоторая является подклассом Primateи, следовательно, связана.
Рассмотрим этот пример:


public class Bird {} 
public class Fish {  
 public static void main(String[] args) {  
    Fish fish = new Fish();  
    Bird bird = (Fish)bird;  // DOES NOT COMPILE 
  }}
В этом примере классы Fishи Birdне связаны через какую-либо иерархию классов; поэтому код не будет компилироваться.
Кастинг не без ограничений. Несмотря на то, что два класса имеют общую иерархию, это не означает, что экземпляр одного класса может быть автоматически приведен к другому. 
Вот пример:

public class Rodent {}
 public class Capybara extends Rodent { 
  public static void main(String[] args) {    
  Rodent rodent = new Rodent();   
   Capybara capybara = (Capybara)rodent;  // Throws ClassCastException at  // runtime  
 }}
Этот код создает экземпляр Rodentи затем пытается привести его к подклассу Rodent, Capybara. Хотя этот код будет компилироваться без проблем,
он будет выдавать ClassCastExceptionво время выполнения, поскольку объект, на который ссылаются, не является экземпляром Capybaraкласса.
Как вы помните из главы 1, вы можете использовать instanceofоператор перед приведением объекта во избежание выброса ClassCastExceptionво время выполнения:

if(rodent instanceof Capybara) { 
  Capybara capybara = (Capybara)rodent;
}