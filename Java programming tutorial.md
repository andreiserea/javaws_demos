# Java Programming - from 0 to Web ready

## Table of Content

1. About Java  
2. Language basics  
   - Variables
   - Conversions
   - Expressions
   - Control flow statements  
3. Classes and Objects
   - Classes  
     - Declaration
     - Members
     - Constructors
   - More 'advanced' class related concepts
     - Polymorphism (class & method)
     - keywords (this & super)
     - About static and final
     - About abstract
     - Hiding fields & methods  
4. Enums
5. Interfaces
6. Nested classes
     - Static & Instance Inner class
     - Anonymous classes
     - Lambda expressions
     - Usage guideline
7. Exceptions
8. Design and coding guidelines
   - What's a POJO?
   - LISKOV substitution principle
   - Strong on encapsulation (15, 16)
   - Strive for immutability (17)
   - Favor composition over inheritance (18)
   - Code against interfaces

### About Java

Java is a strong-typed, interpreted imperative language that is known for a couple of things:
1. Its infamous verbosity. But this is nota useless cost to pay. Java's verbosity is there to make anti-patterns in programming as difficult to use as possible while promoting good programming principles.
As we'll see, it has very few (although its designers have gave ground to hoards of critics that militated for more) syntactic sugar, very few under-the-hood mechanism that the compiler/VM does. It tries to adhere to the principle: what you see is what you get. This makes the code very readable when compared to other more "colorful" languages.
2. Its garbage collector that falsely promotes it as memory-leak free! Yes, Java can have memory leaks.
3. Its portability.

### Language basics

#### Variables

Variables in java can be of 2 types: 
- Primitive types (byte, short, int, long, floag, double, boolean, char).  
 In addition to the eight primitive data types listed above, the Java programming language also provides special support for character strings via the java.lang.String class. Enclosing your character string within double quotes will automatically create a new String object; for example, String s = "this is a string";. String objects are immutable, which means that once created, their values cannot be changed.
- Reference type.

Default values:
| Data Type | Default Value (for fields) |
|-----------|---------------|
|byte       |0              |
|short      |0              |
|int        |0              |
|long       |0              |
|float      |0.0f           |
|double     |0.0d           |
|char       |'\u0000'       |
|String     |null           |
|boolean    |false          |

Local variables are slightly different; the compiler never assigns a default value to an uninitialized local variable. If you cannot initialize your local variable where it is declared, make sure to assign it a value before you attempt to use it. Accessing an uninitialized local variable will result in a compile-time error.

Some examples of literals

```java
boolean result = true;
char capitalC = 'C';
byte b = 100;
short s = 10000;
int i = 100000;
// The number 26, in decimal
int decVal = 26;
//  The number 26, in hexadecimal
int hexVal = 0x1a;
// The number 26, in binary
int binVal = 0b11010;
double d1 = 123.4;
// same value as d1, but in scientific notation
double d2 = 1.234e2;
float f1  = 123.4f;
// String Sí Señor
String s = "S\u00ED Se\u00F1or"
```

Java 7's underscore syntactic sugar

```java
long creditCardNumber = 1234_5678_9012_3456L;
long socialSecurityNumber = 999_99_9999L;
float pi =  3.14_15F;
long hexBytes = 0xFF_EC_DE_5E;
long hexWords = 0xCAFE_BABE;
long maxLong = 0x7fff_ffff_ffff_ffffL;
byte nybbles = 0b0010_0101;
long bytes = 0b11010010_01101001_10010100_10010010;
```
<details><summary>Some more underscore?</summary>
<p>

```java
// Invalid: cannot put underscores
// adjacent to a decimal point
float pi1 = 3_.1415F;
// Invalid: cannot put underscores 
// adjacent to a decimal point
float pi2 = 3._1415F;
// Invalid: cannot put underscores 
// prior to an L suffix
long socialSecurityNumber1 = 999_99_9999_L;

// OK (decimal literal)
int x1 = 5_2;
// Invalid: cannot put underscores
// At the end of a literal
int x2 = 52_;
// OK (decimal literal)
int x3 = 5_______2;

// Invalid: cannot put underscores
// in the 0x radix prefix
int x4 = 0_x52;
// Invalid: cannot put underscores
// at the beginning of a number
int x5 = 0x_52;
// OK (hexadecimal literal)
int x6 = 0x5_2; 
// Invalid: cannot put underscores
// at the end of a number
int x7 = 0x52_;
```
</p></details>

Every primitive type has a equivalent reference type. byte has Byte, int has Integer, long has Long, etc.
All these are called boxed numerical types and are immutable.

### Conversions

Types of conversions:
1. Widening Primitive Conversion  
19 specific conversions on primitive types are called the widening primitive conversions:  

||
|---|
|byte to short, int, long, float, or double|
|short to int, long, float, or double|
|char to int, long, float, or double|
|int to long, float, or double|
|long to float or double|
|float to double|
A widening primitive conversion does not lose information about the overall magnitude of a numeric value in the following cases, where the numeric value is preserved exactly:
||
|---|
|from an integral type to another integral type|
|from byte, short, or char to a floating point type|
|from int to double|

A widening primitive conversion from int to float, or from long to float, or from long to double, may result in loss of precision - that is, the result may lose some of the least significant bits of the value. In this case, the resulting floating-point value will be a correctly rounded version of the integer value

Example: 
```java
class Test {
    public static void main(String[] args) {
        int big = 1234567890;
        float approx = big;
        System.out.println(big - (int)approx);
    }
}
```
<details><summary>Result explanation</summary>
<p>
Information was lost during the conversion from type int to type float because values of type float are not precise to nine significant digits.
</p>
</details>

2. Narrowing Primitive Conversion
- short to byte or char
- char to byte or short
- int to byte, short, or char
- long to byte, short, char, or int
- float to byte, short, char, int, or long
- double to byte, short, char, int, long, or float

Example:
```java
class Test {
    public static void main(String[] args) {
        float fmin = Float.NEGATIVE_INFINITY;
        float fmax = Float.POSITIVE_INFINITY;
        System.out.println("long: " + (long)fmin +
                           ".." + (long)fmax);
        System.out.println("int: " + (int)fmin +
                           ".." + (int)fmax);
        System.out.println("short: " + (short)fmin +
                           ".." + (short)fmax);
        System.out.println("char: " + (int)(char)fmin +
                           ".." + (int)(char)fmax);
        System.out.println("byte: " + (byte)fmin +
                           ".." + (byte)fmax);
    }
}
```
<details><summary>Result explanation</summary>
<p>
The results for char, int, and long are unsurprising, producing the minimum and maximum representable values of the type.

The results for byte and short lose information about the sign and magnitude of the numeric values and also lose precision. The results can be understood by examining the low order bits of the minimum and maximum int. The minimum int is, in hexadecimal, 0x80000000, and the maximum int is 0x7fffffff. This explains the short results, which are the low 16 bits of these values, namely, 0x0000 and 0xffff; it explains the char results, which also are the low 16 bits of these values, namely, '\u0000' and '\uffff'; and it explains the byte results, which are the low 8 bits of these values, namely, 0x00 and 0xff.
</p>
</details>

Read more about conversions here: https://docs.oracle.com/javase/specs/jls/se8/html/jls-5.html#jls-5.1.4

### Control flow statements

1. if-then and if-then-else
2. switch
<details><summary>Examples</summary>
<p>

```java
public class SwitchDemo {
    public static void main(String[] args) {

        int month = 8;
        String monthString;
        switch (month) {
            case 1:  monthString = "January";
                     break;
            case 2:  monthString = "February";
                     break;
            case 3:  monthString = "March";
                     break;
            case 4:  monthString = "April";
                     break;
            case 5:  monthString = "May";
                     break;
            case 6:  monthString = "June";
                     break;
            case 7:  monthString = "July";
                     break;
            case 8:  monthString = "August";
                     break;
            case 9:  monthString = "September";
                     break;
            case 10: monthString = "October";
                     break;
            case 11: monthString = "November";
                     break;
            case 12: monthString = "December";
                     break;
            default: monthString = "Invalid month";
                     break;
        }
        System.out.println(monthString);
    }
}
```
```java
public class SwitchDemoFallThrough {

    public static void main(String[] args) {
        java.util.ArrayList<String> futureMonths =
            new java.util.ArrayList<String>();

        int month = 8;

        switch (month) {
            case 1:  futureMonths.add("January");
            case 2:  futureMonths.add("February");
            case 3:  futureMonths.add("March");
            case 4:  futureMonths.add("April");
            case 5:  futureMonths.add("May");
            case 6:  futureMonths.add("June");
            case 7:  futureMonths.add("July");
            case 8:  futureMonths.add("August");
            case 9:  futureMonths.add("September");
            case 10: futureMonths.add("October");
            case 11: futureMonths.add("November");
            case 12: futureMonths.add("December");
                     break;
            default: break;
        }

        if (futureMonths.isEmpty()) {
            System.out.println("Invalid month number");
        } else {
            for (String monthName : futureMonths) {
               System.out.println(monthName);
            }
        }
    }
}
```
```java
class SwitchDemo2 {
    public static void main(String[] args) {

        int month = 2;
        int year = 2000;
        int numDays = 0;

        switch (month) {
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                numDays = 31;
                break;
            case 4: case 6:
            case 9: case 11:
                numDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) && 
                     !(year % 100 == 0))
                     || (year % 400 == 0))
                    numDays = 29;
                else
                    numDays = 28;
                break;
            default:
                System.out.println("Invalid month.");
                break;
        }
        System.out.println("Number of Days = "
                           + numDays);
    }
}
```
```java
public class StringSwitchDemo {

    public static int getMonthNumber(String month) {

        int monthNumber = 0;

        if (month == null) {
            return monthNumber;
        }

        switch (month.toLowerCase()) {
            case "january":
                monthNumber = 1;
                break;
            case "february":
                monthNumber = 2;
                break;
            case "march":
                monthNumber = 3;
                break;
            case "april":
                monthNumber = 4;
                break;
            case "may":
                monthNumber = 5;
                break;
            case "june":
                monthNumber = 6;
                break;
            case "july":
                monthNumber = 7;
                break;
            case "august":
                monthNumber = 8;
                break;
            case "september":
                monthNumber = 9;
                break;
            case "october":
                monthNumber = 10;
                break;
            case "november":
                monthNumber = 11;
                break;
            case "december":
                monthNumber = 12;
                break;
            default: 
                monthNumber = 0;
                break;
        }

        return monthNumber;
    }

    public static void main(String[] args) {

        String month = "August";

        int returnedMonthNumber =
            StringSwitchDemo.getMonthNumber(month);

        if (returnedMonthNumber == 0) {
            System.out.println("Invalid month");
        } else {
            System.out.println(returnedMonthNumber);
        }
    }
}
```

</p></details>

3. while and do-while
4. for
5. breaking statements
<details><summary>Examples</summary>
<p>  

```java
class BreakDemo {
    public static void main(String[] args) {

        int[] arrayOfInts = 
            { 32, 87, 3, 589,
              12, 1076, 2000,
              8, 622, 127 };
        int searchfor = 12;

        int i;
        boolean foundIt = false;

        for (i = 0; i < arrayOfInts.length; i++) {
            if (arrayOfInts[i] == searchfor) {
                foundIt = true;
                break;
            }
        }

        if (foundIt) {
            System.out.println("Found " + searchfor + " at index " + i);
        } else {
            System.out.println(searchfor + " not in the array");
        }
    }
}
```
```java
class BreakWithLabelDemo {
    public static void main(String[] args) {

        int[][] arrayOfInts = { 
            { 32, 87, 3, 589 },
            { 12, 1076, 2000, 8 },
            { 622, 127, 77, 955 }
        };
        int searchfor = 12;

        int i;
        int j = 0;
        boolean foundIt = false;

    search:
        for (i = 0; i < arrayOfInts.length; i++) {
            for (j = 0; j < arrayOfInts[i].length;
                 j++) {
                if (arrayOfInts[i][j] == searchfor) {
                    foundIt = true;
                    break search;
                }
            }
        }

        if (foundIt) {
            System.out.println("Found " + searchfor + " at " + i + ", " + j);
        } else {
            System.out.println(searchfor + " not in the array");
        }
    }
}
```
```java
class ContinueDemo {
    public static void main(String[] args) {

        String searchMe = "peter piper picked a " + "peck of pickled peppers";
        int max = searchMe.length();
        int numPs = 0;

        for (int i = 0; i < max; i++) {
            // interested only in p's
            if (searchMe.charAt(i) != 'p')
                continue;

            // process p's
            numPs++;
        }
        System.out.println("Found " + numPs + " p's in the string.");
    }
}
```
```java
class ContinueWithLabelDemo {
    public static void main(String[] args) {

        String searchMe = "Look for a substring in me";
        String substring = "sub";
        boolean foundIt = false;

        int max = searchMe.length() - 
                  substring.length();

    test:
        for (int i = 0; i <= max; i++) {
            int n = substring.length();
            int j = i;
            int k = 0;
            while (n-- != 0) {
                if (searchMe.charAt(j++) != substring.charAt(k++)) {
                    continue test;
                }
            }
            foundIt = true;
                break test;
        }
        System.out.println(foundIt ? "Found it" : "Didn't find it");
    }
}
```
</p></details>

### Classes and objects
#### Classes
```java
public class Bicycle {
        
    // the Bicycle class has
    // three fields
    public int cadence;
    public int gear;
    public int speed;
        
    // the Bicycle class has
    // one constructor
    public Bicycle(int startCadence, int startSpeed, int startGear) {
        gear = startGear;
        cadence = startCadence;
        speed = startSpeed;
    }
        
    // the Bicycle class has
    // four methods
    public void setCadence(int newValue) {
        cadence = newValue;
    }
        
    public void setGear(int newValue) {
        gear = newValue;
    }
        
    public void applyBrake(int decrement) {
        speed -= decrement;
    }
        
    public void speedUp(int increment) {
        speed += increment;
    }

    public void printDescription() {
        System.out.println("\nBike is " + "in gear " + this.gear
            + " with a cadence of " + this.cadence +
            " and travelling at a speed of " + this.speed + ". ");
    }
        
}
```

Class declaration consists of a access modifier (public/private/package-private), a name, (interfaces that it implements and a class that it extends - which we'll discuss about later) and the body. The body is formed by 0* fields, 0* constructors, 0* methods.

##### Members

Members are of 2 types: variables and methods.  

Members have access modifiers. Access modifiers are crucial to Java's compiler control and guidance to a well structured and organized code.
- public modifier - member is accessible from all classes
- private modifier - member is accessible only from within the declaring class
- package-private modifier (default) - member is accessible from anywhere inside the same package as the declaring class
- protected modifier - same as default + extending classes outside the package of the declaring class

A field definition:
```java
private int cadence;
```
A method definition:
```java
public double calculateAnswer(double wingSpan, int numberOfEngines,
                              double length, double grossTons) {
    //do the calculation here
}
```
##### Constructors
A class contains constructors that are invoked to create objects from the class blueprint. Constructor declarations look like method declarations—except that they use the name of the class and have no return type. For example, Bicycle has two constructor, one with 3 args and one with 0:
```java
public Bicycle(int startCadence, int startSpeed, int startGear) {
    gear = startGear;
    cadence = startCadence;
    speed = startSpeed;
}

public Bicycle() {
    gear = 1;
    cadence = 10;
    speed = 0;
}
```
You don't have to provide any constructors for your class, but you must be careful when doing this. The compiler automatically provides a no-argument, default constructor for any class without constructors. This default constructor will call the no-argument constructor of the superclass. In this situation, the compiler will complain if the superclass doesn't have a no-argument constructor so you must verify that it does. If your class has no explicit superclass, then it has an implicit superclass of Object, which does have a no-argument constructor.

Question: How can one prohibit creating an instance of a class from outside it?

### More 'advanced' class related concepts
#### Polymorphism (classes and methods)

Polymorphism - anything that fulfils more than 1 is-a relationship.
In Java, polymorphism can be achieved in a number of ways: by inheritance, by interfaces, by method overloading and method overriding

```java
public class MountainBike extends Bicycle {
    private String suspension;

    public MountainBike(
               int startCadence,
               int startSpeed,
               int startGear,
               String suspensionType){
        super(startCadence,
              startSpeed,
              startGear);
        this.setSuspension(suspensionType);
    }

    public String getSuspension(){
      return this.suspension;
    }

    public void setSuspension(String suspensionType) {
        this.suspension = suspensionType;
    }

    @Override
    public void printDescription() {
        super.printDescription();
        System.out.println("The " + "MountainBike has a" +
            getSuspension() + " suspension.");
    }

    public void printDescription(boolean shortFormat) {
        if (shortFormat) {
            System.out.println("cadence=" + this.startCadence + ",speed=" + this.startSpeed + ",gear=" + this.startGear + ",suspensionType=" + this.suspensionType);
        } else {
            this.printDescription();
        }
    }
} 
```
The method `printDescription` is an overridden method. This is dynamic polymorphism. It is called dynamic because the compiler cannot determine which method of the 2 it should call (the one from the MountainBike class or the one from Bicycle class).  
The same method is also overloaded. Overloading means reusing the same method name in a class but with a different set of parameters. Oposed to overriding, overloading is a static polymorphism example, because this time the compiler can determine which method is should call based on the arguments types.  
Any instance of `MountainBike` is a polymorphic object because it has at least 2 is-a relationships. It is a MountainBike and a Bicycle. It even is a `Object`, this all classes in Java extend the base class `Object`.  
Polymorphic objects can be created also through the use of interfaces which we'll discuss later on.

#### Keywords (this & super)

In the Bicycle class, looking at the setters of its fields we can see that the keyword `this` is used to access fields of the current class. It is not always necessary to use the keyword `this` when trying to access a member of the current class but it is good practice since it makes reading the code easier. Further more, look at the following code:
```java
public void setSuspension(String suspension) {
    this.suspension = suspension;
}
```
We changed the name of the parameter from `suspensionType` to `suspension`. Now, we say that in the low-level scope of the setter method, the parameter `suspension` _shadows_ the class field `suspension`. Variables from lower scope with the same name as variables from higher scope _shadow_ the later ones, meaning that simply refering by name (without an additional classifier like `this`), the compiler will always resolve the name to the lower scope variable.  In this case, the use of `this` keyword is mandatory.

##### The super keyword
If your method overrides one of its superclass's methods, you can invoke the overridden method through the use of the keyword `super`.
```java
public class Superclass {

    public void printMethod() {
        System.out.println("Printed in Superclass.");
    }
}

public class Subclass extends Superclass {

    // overrides printMethod in Superclass
    public void printMethod() {
        super.printMethod();
        System.out.println("Printed in Subclass");
    }
    public static void main(String[] args) {
        Subclass s = new Subclass();
        s.printMethod();    
    }
}
```
<details><summary>Question: What does the above program print?</summary>
<p>

```
Printed in Superclass.  
Printed in Subclass
```
</p></details>

The `super` keyword can also be used in conjunction with constructors.
```java
public MountainBike(int startHeight, 
                    int startCadence,
                    int startSpeed,
                    int startGear) {
    super(startCadence, startSpeed, startGear);
    seatHeight = startHeight;
}   
```
Invocation of a superclass constructor must be the first line in the subclass constructor.

**Note**: _If a constructor does not explicitly invoke a superclass constructor, the Java compiler automatically inserts a call to the no-argument constructor of the superclass. If the super class does not have a no-argument constructor, you will get a compile-time error. Object does have such a constructor, so if Object is the only superclass, there is no problem._

#### About static and final

Static and final are 2 modifiers with great impact on code structure.

|  | Fields | Methods | Top level class | Inner class |
|---|---|---|---|---|
| final | read-only | not overridable | not inheritable | not inheritable |
| static | single value/class | operates at class level | illegal | not bound to instance of outer class |

#### About abstract

An abstract class is a class that is declared abstract—it may or may not include abstract methods. Abstract classes cannot be instantiated, but they can be subclassed.

An abstract method is a method that is declared without an implementation (without braces, and followed by a semicolon), like this:

```java
abstract void moveTo(double deltaX, double deltaY);
```
If a class includes abstract methods, then the class itself must be declared abstract, as in:

```java
public abstract class GraphicObject {
   // declare fields
   // declare nonabstract methods
   abstract void draw();
}
```
When an abstract class is subclassed, the subclass usually provides implementations for all of the abstract methods in its parent class. However, if it does not, then the subclass must also be declared abstract.

#### Hiding fields & methods

Within a class, a field that has the same name as a field in the superclass hides the superclass's field, even if their types are different. Within the subclass, the field in the superclass cannot be referenced by its simple name. Instead, the field must be accessed through `super`. Generally speaking, this is not recommended (hiding fields) as it makes code difficult to read.

To hide a method it is not enough to declare one with the same name & parameters in a subclass, since that is the definition of overriding. Hiding of methods is only possible if the methods are static.

If a subclass defines a static method with the same signature as a static method in the superclass, then the method in the subclass hides the one in the superclass.

The distinction between hiding a static method and overriding an instance method has important implications:
- The version of the overridden instance method that gets invoked is the one in the subclass.
- The version of the hidden static method that gets invoked depends on whether it is invoked from the superclass or the subclass.
Eample
```java
public class Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Animal");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Animal");
    }
}

public class Cat extends Animal {
    public static void testClassMethod() {
        System.out.println("The static method in Cat");
    }
    public void testInstanceMethod() {
        System.out.println("The instance method in Cat");
    }

    public static void main(String[] args) {
        Cat myCat = new Cat();
        Animal myAnimal = myCat;
        Animal.testClassMethod();
        myAnimal.testInstanceMethod();
    }
}
```
<details><summary>Question: Which is the overriden method? Which is the hidden one? What does the code output?</summary>
<p>

```
The static method in Animal
The instance method in Cat
```
</p></details>

## Enums

An enum type is a special data type that enables for a variable to be a set of predefined constants.

An enum type is defined by using the enum keyword

```java
public enum Day {
    SUNDAY, MONDAY, TUESDAY, WEDNESDAY,
    THURSDAY, FRIDAY, SATURDAY 
}
```

You should use enum types any time you need to represent a fixed set of constants.

Example of using an enum:
```java
public class EnumTest {
    Day day;
    
    public EnumTest(Day day) {
        this.day = day;
    }
    
    public void tellItLikeItIs() {
        switch (day) {
            case MONDAY:
                System.out.println("Mondays are bad.");
                break;
                    
            case FRIDAY:
                System.out.println("Fridays are better.");
                break;
                         
            case SATURDAY: case SUNDAY:
                System.out.println("Weekends are best.");
                break;
                        
            default:
                System.out.println("Midweek days are so-so.");
                break;
        }
    }
    
    public static void main(String[] args) {
        EnumTest firstDay = new EnumTest(Day.MONDAY);
        firstDay.tellItLikeItIs();
        EnumTest thirdDay = new EnumTest(Day.WEDNESDAY);
        thirdDay.tellItLikeItIs();
        EnumTest fifthDay = new EnumTest(Day.FRIDAY);
        fifthDay.tellItLikeItIs();
        EnumTest sixthDay = new EnumTest(Day.SATURDAY);
        sixthDay.tellItLikeItIs();
        EnumTest seventhDay = new EnumTest(Day.SUNDAY);
        seventhDay.tellItLikeItIs();
    }
}
```

Enums in Java are compiler assisted, adding automatically extra functionality and support. They:
- can have methods and other fields (since they are in fact instances of the Enum class).
- have a static values() method that gives an iterable collection with all enums.
- also static is valueOf() which returns a single enum instance with the given name and type
- have a name() method
- have a ordinal() method

Example:
```java
public enum Planet {
    MERCURY (3.303e+23, 2.4397e6),
    VENUS   (4.869e+24, 6.0518e6),
    EARTH   (5.976e+24, 6.37814e6),
    MARS    (6.421e+23, 3.3972e6),
    JUPITER (1.9e+27,   7.1492e7),
    SATURN  (5.688e+26, 6.0268e7),
    URANUS  (8.686e+25, 2.5559e7),
    NEPTUNE (1.024e+26, 2.4746e7);

    private final double mass;   // in kilograms
    private final double radius; // in meters
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
    }
    private double mass() { return mass; }
    private double radius() { return radius; }

    // universal gravitational constant  (m3 kg-1 s-2)
    public static final double G = 6.67300E-11;

    double surfaceGravity() {
        return G * mass / (radius * radius);
    }
    double surfaceWeight(double otherMass) {
        return otherMass * surfaceGravity();
    }
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java Planet <earth_weight>");
            System.exit(-1);
        }
        double earthWeight = Double.parseDouble(args[0]);
        double mass = earthWeight/EARTH.surfaceGravity();
        for (Planet p : Planet.values())
           System.out.printf("Your weight on %s is %f%n",
                             p, p.surfaceWeight(mass));
    }
}
```

## Interfaces

Probably the best OOP invented!

There are a number of situations in software engineering when it is important for disparate groups of programmers to agree to a "contract" that spells out how their software interacts. Each group should be able to write their code without any knowledge of how the other group's code is written. Generally speaking, interfaces are such contracts.

In the Java programming language, an interface is a reference type, similar to a class, that can contain only constants, method signatures, default methods, static methods, and nested types. Method bodies exist only for default methods and static methods. Interfaces cannot be instantiated—they can only be implemented by classes or extended by other interfaces. 

An interface declaration consists of modifiers, the keyword interface, the interface name, a comma-separated list of parent interfaces (if any), and the interface body.


Example:
```java
public interface OperateCar {

   // constant declarations, if any

   // method signatures
   
   int turn(Direction direction,
            double radius,
            double startSpeed,
            double endSpeed);
   int changeLanes(Direction direction,
                   double startSpeed,
                   double endSpeed);
   int signalTurn(Direction direction,
                  boolean signalOn);
   int getRadarFront(double distanceToCar,
                     double speedOfCar);
   int getRadarRear(double distanceToCar,
                    double speedOfCar);
         ......
   // more method signatures
}
```

To use an interface, you write a class that implements the interface. When an instantiable class implements an interface, it provides a method body for each of the methods declared in the interface.

Example
```java
public class OperateBMW760i implements OperateCar {

    // the OperateCar method signatures, with implementation --
    // for example:
    int signalTurn(Direction direction, boolean signalOn) {
       // code to turn BMW's LEFT turn indicator lights on
       // code to turn BMW's LEFT turn indicator lights off
       // code to turn BMW's RIGHT turn indicator lights on
       // code to turn BMW's RIGHT turn indicator lights off
    }

    // other members, as needed -- for example, helper classes not 
    // visible to clients of the interface
}
```
The public access specifier indicates that the interface can be used by any class in any package. If you do not specify that the interface is public, then your interface is accessible only to classes defined in the same package as the interface.

An interface can extend other interfaces, just as a class subclass or extend another class. However, whereas a class can extend only one other class, an interface can extend any number of interfaces. The interface declaration includes a comma-separated list of all the interfaces that it extends.

**The interface body**

The interface body can contain `abstract` methods, `default` methods, and `static` methods. An `abstract` method within an interface is followed by a semicolon, but no braces (an `abstract` method does not contain an implementation). Default methods are defined with the `default` modifier, and `static` methods with the static keyword. All `abstract`, `default`, and `static` methods in an interface are implicitly public, so you can omit the `public` modifier.

In addition, an interface can contain constant declarations. All constant values defined in an interface are implicitly public, static, and final. Once again, you can omit these modifiers.

### Implementing an interface

To declare a class that implements an interface, you include an implements clause in the class declaration. Your class can implement more than one interface, so the implements keyword is followed by a comma-separated list of the interfaces implemented by the class. By convention, the implements clause follows the extends clause, if there is one.

A concrete example
```java
public interface Relatable {
        
    // this (object calling isLargerThan)
    // and other must be instances of 
    // the same class returns 1, 0, -1 
    // if this is greater than, 
    // equal to, or less than other
    public int isLargerThan(Relatable other);
}
```

Any class can implement Relatable if there is some way to compare the relative "size" of objects instantiated from the class. For strings, it could be number of characters; for books, it could be number of pages.

**Implementing the Relatable Interface**

```java
public class RectanglePlus 
    implements Relatable {
    public int width = 0;
    public int height = 0;
    public Point origin;

    // four constructors
    public RectanglePlus() {
        origin = new Point(0, 0);
    }
    public RectanglePlus(Point p) {
        origin = p;
    }
    public RectanglePlus(int w, int h) {
        origin = new Point(0, 0);
        width = w;
        height = h;
    }
    public RectanglePlus(Point p, int w, int h) {
        origin = p;
        width = w;
        height = h;
    }

    // a method for moving the rectangle
    public void move(int x, int y) {
        origin.x = x;
        origin.y = y;
    }

    // a method for computing
    // the area of the rectangle
    public int getArea() {
        return width * height;
    }
    
    // a method required to implement
    // the Relatable interface
    public int isLargerThan(Relatable other) {
        RectanglePlus otherRect 
            = (RectanglePlus)other;
        if (this.getArea() < otherRect.getArea())
            return -1;
        else if (this.getArea() > otherRect.getArea())
            return 1;
        else
            return 0;               
    }
}
```

### Using the interface data type

When you define a new interface, you are defining a new reference data type. You can use interface names anywhere you can use any other data type name. If you define a reference variable whose type is an interface, any object you assign to it must be an instance of a class that implements the interface.

```java
public Object findLargest(Relatable obj1, Relatable obj2) {
   if ((obj1).isLargerThan(obj2) > 0)
      return obj1;
   else 
      return obj2;
}
```

### Evolving interfaces

Consider an interface that you have developed called DoIt:

```java
public interface DoIt {
   void doSomething(int i, double x);
   int doSomethingElse(String s);
}
```
Suppose that, at a later time, you want to add a third method to DoIt, so that the interface now becomes:
```java
public interface DoIt {

   void doSomething(int i, double x);
   int doSomethingElse(String s);
   boolean didItWork(int i, double x, String s);
   
}
```
If you make this change, then all classes that implement the old DoIt interface will break because they no longer implement the old interface. Programmers relying on this interface will protest loudly.

Try to anticipate all uses for your interface and specify it completely from the beginning. If you want to add additional methods to an interface, you have several options. You could create a DoItPlus interface that extends DoIt:
```java
public interface DoItPlus extends DoIt {

   boolean didItWork(int i, double x, String s);
   
}
```
Alternatively, you can define your new methods as default methods. The following example defines a default method named didItWork:
```java
public interface DoIt {

   void doSomething(int i, double x);
   int doSomethingElse(String s);
   default boolean didItWork(int i, double x, String s) {
       // Method body 
   }
   
}

```
You must specify an implementation (method body) for default methods of an interface.

### Default and static methods

Second best invention of OOP

Without default methods, evolution of APIs was stumbling to say the least.

Let's go back to the car manufacturer example: What if those computer-controlled car manufacturers add new functionality, such as flight, to their cars (Jetsons anyone?)?  
- They can't be added to the interface as this would break backwards compatibility and all users of the API will not be able to smoothly migrate to the new version
- They can't be added as static since these methods are not part of the API, they will be regarded by the developers as mere utilitiy methods

This is exactly what default methods were added for: to enable you to add new functionality to the interfaces of your libraries and ensure binary compatibility with code written for older versions of those interfaces.

Example:
```java
public interface TimeClient {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second);
    LocalDateTime getLocalDateTime();
}

public class SimpleTimeClient implements TimeClient {
    
    private LocalDateTime dateAndTime;
    
    public SimpleTimeClient() {
        dateAndTime = LocalDateTime.now();
    }
    
    public void setTime(int hour, int minute, int second) {
        LocalDate currentDate = LocalDate.from(dateAndTime);
        LocalTime timeToSet = LocalTime.of(hour, minute, second);
        dateAndTime = LocalDateTime.of(currentDate, timeToSet);
    }
    
    public void setDate(int day, int month, int year) {
        LocalDate dateToSet = LocalDate.of(day, month, year);
        LocalTime currentTime = LocalTime.from(dateAndTime);
        dateAndTime = LocalDateTime.of(dateToSet, currentTime);
    }
    
    public void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second) {
        LocalDate dateToSet = LocalDate.of(day, month, year);
        LocalTime timeToSet = LocalTime.of(hour, minute, second); 
        dateAndTime = LocalDateTime.of(dateToSet, timeToSet);
    }
    
    public LocalDateTime getLocalDateTime() {
        return dateAndTime;
    }
    
    public String toString() {
        return dateAndTime.toString();
    }
    
    public static void main(String... args) {
        TimeClient myTimeClient = new SimpleTimeClient();
        System.out.println(myTimeClient.toString());
    }
}
```java
public interface TimeClient {
    void setTime(int hour, int minute, int second);
    void setDate(int day, int month, int year);
    void setDateAndTime(int day, int month, int year,
                               int hour, int minute, int second);
    LocalDateTime getLocalDateTime();
    
    static ZoneId getZoneId (String zoneString) {
        try {
            return ZoneId.of(zoneString);
        } catch (DateTimeException e) {
            System.err.println("Invalid time zone: " + zoneString +
                "; using default time zone instead.");
            return ZoneId.systemDefault();
        }
    }
    //Add timezone functionality
    default ZonedDateTime getZonedDateTime(String zoneString) {
        return ZonedDateTime.of(getLocalDateTime(), getZoneId(zoneString));
    }
}
```

**Extending Interfaces That Contain Default Methods**

When you extend an interface that contains a default method, you can do the following:
- Not mention the default method at all, which lets your extended interface inherit the default method.
- Redeclare the default method, which makes it abstract.
- Redefine the default method, which overrides it.

What's the difference between the following 3 etensions of TimeClient?

```java
public interface AnotherTimeClient extends TimeClient { }
```

```java
public interface AbstractZoneTimeClient extends TimeClient {
    public ZonedDateTime getZonedDateTime(String zoneString);
}
```
and
```java
public interface HandleInvalidTimeZoneClient extends TimeClient {
    default public ZonedDateTime getZonedDateTime(String zoneString) {
        try {
            return ZonedDateTime.of(getLocalDateTime(),ZoneId.of(zoneString)); 
        } catch (DateTimeException e) {
            System.err.println("Invalid zone ID: " + zoneString +
                "; using the default time zone instead.");
            return ZonedDateTime.of(getLocalDateTime(),ZoneId.systemDefault());
        }
    }
}
```

**Static methods**

In addition to default methods, you can define static methods in interfaces. (A static method is a method that is associated with the class in which it is defined rather than with any object. Every instance of the class shares its static methods.) This makes it easier for you to organize helper methods in your libraries; you can keep static methods specific to an interface in the same interface rather than in a separate class.

See the TimeClient.getZoneId static method for an example.

## Exceptions

**What are exceptions?**

An exception is an event that occurs during the execution of a program that disrupts the normal flow of instructions.

When an error occurs within a method, the method creates an object and hands it off to the runtime system. The object, called an exception object, contains information about the error, including its type and the state of the program when the error occurred. Creating an exception object and handing it to the runtime system is called throwing an exception.

After a method throws an exception, the runtime system attempts to find something to handle it. The set of possible "somethings" to handle the exception is the ordered list of methods that had been called to get to the method where the error occurred. The list of methods is known as the call stack (see the next figure).

The runtime system searches the call stack for a method that contains a block of code that can handle the exception. This block of code is called an exception handler. The search begins with the method in which the error occurred and proceeds through the call stack in the reverse order in which the methods were called. When an appropriate handler is found, the runtime system passes the exception to the handler. An exception handler is considered appropriate if the type of the exception object thrown matches the type that can be handled by the handler.

The exception handler chosen is said to catch the exception. If the runtime system exhaustively searches all the methods on the call stack without finding an appropriate exception handler, as shown in the next figure, the runtime system (and, consequently, the program) terminates.

**The Catch or Specify Requirement**

Code must honor the Catch or Specify Requirement. This means that code that might throw certain exceptions must be enclosed by either of the following:

 - A try statement that catches the exception.
 - A method that specifies that it can throw the exception.

**The Three Kinds of Exceptions**

 - *Checked exceptions* - These are exceptional conditions that a well-written application should anticipate and recover from. Checked exceptions are subject to the Catch or Specify Requirement.
 - *Error* - These are exceptional conditions that are external to the application, and that the application usually cannot anticipate or recover from. Errors are not subject to the Catch or Specify Requirement. Errors are those exceptions indicated by Error and its subclasses.
 - *Runtime exceptions* - These are exceptional conditions that are internal to the application, and that the application usually cannot anticipate or recover from. Runtime exceptions are not subject to the Catch or Specify Requirement. Runtime exceptions are those indicated by RuntimeException and its subclasses.

**Catching and Handling Exceptions**

```java
// Note: This class will not compile yet.
public class ListOfNumbers {

    private List<Integer> list;
    private static final int SIZE = 10;

    public ListOfNumbers () {
        list = new ArrayList<Integer>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            list.add(new Integer(i));
        }
    }

    public void writeList() {
	    // The FileWriter constructor throws IOException, which must be caught.
        PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));

        for (int i = 0; i < SIZE; i++) {
            // The get(int) method throws IndexOutOfBoundsException, which must be caught.
            out.println("Value at: " + i + " = " + list.get(i));
        }
        out.close();
    }
}
```
***The try-catch-finally block***
The first step in constructing an exception handler is to enclose the code that might throw an exception within a try block. In general, a try block looks like the following:
```java
try {
    //code
} catch (ExceptionType name) {
    //exception handler
} catch (ExceptionType name) {
    //exception handler
} finally {
    //always called code
}
```
The segment in the example labeled code contains one or more legal lines of code that could throw an exception. 

You associate exception handlers with a `try block` by providing one or more catch blocks directly after the `try block`. No code can be between the end of the try block and the beginning of the first `catch block`.

Each catch block is an exception handler that handles the type of exception indicated by its argument. The argument type, `ExceptionType`, declares the type of exception that the handler can handle and must be the name of a class that inherits from the `Throwable` class. The handler can refer to the exception with name.

The catch block contains code that is executed if and when the exception handler is invoked. The runtime system invokes the exception handler when the handler is the first one in the call stack whose `ExceptionType` matches the type of the exception thrown. The system considers it a match if the thrown object can legally be assigned to the exception handler's argument.
```java
try {

} catch (IndexOutOfBoundsException e) {
    System.err.println("Caught exception:" + e.getMessage());
} catch (IOException e) {
    System.err.println("Caught exception:" + e.getMessage());
}
```
OR
```java
try {

} catch (IndexOutOfBoundsException | IOException e) {
    System.err.println("Caught exception:" + e.getMessage());
}
```
**The finally block**
The finally block always executes when the try block exits. This ensures that the finally block is executed even if an unexpected exception occurs. But finally is useful for more than just exception handling — it allows the programmer to avoid having cleanup code accidentally bypassed by a return, continue, or break. Putting cleanup code in a finally block is always a good practice, even when no exceptions are anticipated.

<details><summary>The correct writeList() method</summary>
<p>

```java
public void writeList() {
    // The FileWriter constructor throws IOException, which must be caught.
    PrintWriter out = null;
    try {
        out = new PrintWriter(new FileWriter("OutFile.txt"));

        for (int i = 0; i < SIZE; i++) {
            // The get(int) method throws IndexOutOfBoundsException, which must be caught.
            out.println("Value at: " + i + " = " + list.get(i));
        }
    } catch (IndexOutOfBoundsException e) {
        System.err.println("IndexOutOfBoundsException: " + e.getMessage());
    } catch (IOException e) {
        System.err.println("Caught IOException: " + e.getMessage());
    } finally {
    if (out != null) { 
        System.out.println("Closing PrintWriter");
        out.close(); 
    } else { 
        System.out.println("PrintWriter not open");
    } 
}
```
</p></details>

**The try-with-resources Statement**

The `try-with-resources` statement is a try statement that declares one or more resources. A resource is an object that must be closed after the program is finished with it. The try-with-resources statement ensures that each resource is closed at the end of the statement. Any object that implements `java.lang.AutoCloseable`, which includes all objects which implement `java.io.Closeable`, can be used as a resource.

```java
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br =
                   new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```
In this example, the resource declared in the try-with-resources statement is a BufferedReader. The declaration statement appears within parentheses immediately after the `try` keyword. The class `BufferedReader`, in Java SE 7 and later, implements the interface `java.lang.AutoCloseable`. Because the BufferedReader instance is declared in a try-with-resource statement, it will be closed regardless of whether the try statement completes normally or abruptly (as a result of the method BufferedReader.readLine throwing an IOException).

Prior to Java 7 & try-with-resources, the above code would have to be written like this:
```java
static String readFirstLineFromFileWithFinallyBlock(String path)
                                                     throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
        return br.readLine();
    } finally {
        if (br != null) br.close();
    }
}
```

But the 2 code fragments, although they may appear identical, they are not!

If the methods readLine and close both throw exceptions, then the method readFirstLineFromFileWithFinallyBlock throws the exception thrown from the finally block; the exception thrown from the try block is suppressed. In contrast, in the example readFirstLineFromFile, if exceptions are thrown from both the try block and the try-with-resources statement, then the method readFirstLineFromFile throws the exception thrown from the try block; the exception thrown from the try-with-resources block is suppressed. In Java SE 7 and later, you can retrieve suppressed exceptions

Exercise: Let's see suppressed exceptions in action!
<details><summary>More than one resource?</summary>
<p>
```java
public static void writeToFileZipFileContents(String zipFileName,
                                           String outputFileName)
                                           throws java.io.IOException {

    java.nio.charset.Charset charset =
         java.nio.charset.StandardCharsets.US_ASCII;
    java.nio.file.Path outputFilePath =
         java.nio.file.Paths.get(outputFileName);

    // Open zip file and create output file with 
    // try-with-resources statement

    try (
        java.util.zip.ZipFile zf =
             new java.util.zip.ZipFile(zipFileName);
        java.io.BufferedWriter writer = 
            java.nio.file.Files.newBufferedWriter(outputFilePath, charset)
    ) {
        // Enumerate each entry
        for (java.util.Enumeration entries =
                                zf.entries(); entries.hasMoreElements();) {
            // Get the entry name and write it to the output file
            String newLine = System.getProperty("line.separator");
            String zipEntryName =
                 ((java.util.zip.ZipEntry)entries.nextElement()).getName() +
                 newLine;
            writer.write(zipEntryName, 0, zipEntryName.length());
        }
    }
}```
In this example, the try-with-resources statement contains two declarations that are separated by a semicolon: ZipFile and BufferedWriter. When the block of code that directly follows it terminates, either normally or because of an exception, the close methods of the BufferedWriter and ZipFile objects are automatically called in this order. Note that the close methods of resources are called in the opposite order of their creation.

</p></details>

>Note: A try-with-resources statement can have catch and finally blocks just like an ordinary try statement. In a try-with-resources statement, any catch or finally block is run after the resources declared have been closed.

**Suppressed exceptions**
An exception can be thrown from the block of code associated with the try-with-resources statement. In the example writeToFileZipFileContents, an exception can be thrown from the try block, and up to two exceptions can be thrown from the try-with-resources statement when it tries to close the ZipFile and BufferedWriter objects. If an exception is thrown from the try block and one or more exceptions are thrown from the try-with-resources statement, then those exceptions thrown from the try-with-resources statement are suppressed, and the exception thrown by the block is the one that is thrown by the writeToFileZipFileContents method. You can retrieve these suppressed exceptions by calling the Throwable.getSuppressed method from the exception thrown by the try block.

**Specifying the Exceptions Thrown by a Method**

If the writeList method doesn't catch the checked exceptions that can occur within it, the writeList method must specify that it can throw these exceptions.
```java
public void writeList() throws IOException {
    PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));
    for (int i = 0; i < SIZE; i++) {
        out.println("Value at: " + i + " = " + list.get(i));
    }
    out.close();
}
```

**How to throw exceptions**
```java
public Object pop() {
    Object obj;

    if (size == 0) {
        throw new EmptyStackException();
    }

    obj = objectAt(size - 1);
    setObjectAt(size - 1, null);
    size--;
    return obj;
}
```



### Strong on encapsulation

>The single most important factor that distinguishes a well-designed component from a poorly designed one is the degree to which the component hides its internal data and other implementation details from other components. A well-designed component hides all its implementation details, cleanly separating its API from its implementation. Components then communicate only through their APIs and are oblivious to each others’ inner workings. This concept, known as information hiding or encapsulation, is a fundamental tenet of software design

Why is information hiding important:
 - decouples the components from its users, making them easy to develop, test, maintain
 - promotes resuability
 - increases productivity
 - decreases complexity

 Main Rule: **Make each class or member as inaccessible as possible**

Top level classes 
 - package-private or public. If you can make it package-private, do so. This way, it will be part of the implementation and decrease the interfacing area with the package's users.
 - Package-private? Try going one level deeper, to private static inner class if used by a single class in the package

Members:
- private < package-private < protected < public. Strive for private
- Only if another class in same package requires that member, make it package-private
- Protected is a next step but huge. It impacts the exported API! Do not go there unless you plan to support that method forever!

About public instance fields:
- rarely the case for them TODO say why
- become part of the exported API.
- you lose the ability to enforce invariants or do some side-action on access/write
- you lose thread safety if the instance member is mutable and not thread safe itself

Same goes for static fields, with one exception: public static final fields are allowed, as long as they are immutable or primitives. Convention says they should be named with UPPER_CASE

#### Examples/Practice

What's wrong with the following code snippet?
```java
public static final Thing[] VALUES = { ... };
```
<details><summary>FIX?</summary>
<p>

```java
private static final Thing[] PRIVATE_VALUES = { ... };

public static final List<Thing> VALUES =

   Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));
```
</p>
</details>

<details><summary>Another FIX?</summary>
<p>

```java
private static final Thing[] PRIVATE_VALUES = { ... };

public static final Thing[] values() {

    return PRIVATE_VALUES.clone();

}
```
</p></details>

POJOs

Plain Old Java Objects, these are instances of classes that encompass a set of attributes representing a larger entity.

POJOs are most oftenly used to represent the model of an application or the entities used by its API to communicate with clients. This is when POJOs must respect the encapsulation principles discussed so far.

So, what's wrong with the following code?

```java
public class Point {

    public double x;

    public double y;

}
```
If a class is accessible outside its package, exposing its data fields directly is equivalent to throwing all hope of future changes to the internal representation of this class out the window, because it is basically part of the contract of this class.

<details><summary>What's the fix?</summary>
<p>

```java
// Encapsulation of data by accessor methods and mutators

class Point {

    private double x;

    private double y;



    public Point(double x, double y) {

        this.x = x;

        this.y = y;

    }



    public double getX() { return x; }

    public double getY() { return y; }



    public void setX(double x) { this.x = x; }

    public void setY(double y) { this.y = y; }

}
```
</p></details>

Accessor methods (getters and setters) obfuscate the internal representation of the class and permits evolvability, adhering to the principles of encapsulation.

BUT! Don't adhere to this rule blindly! If a class is package-private or is a private nested class, there is nothing inherently wrong with exposing its data fields!

What if the fields exposed are immutable? It is not perfect, you cannot change the inner representation without changing the contract or take auxiliary methods when a field is read, but invariants can be enforced at construction time. Example:

```java
// Public class with exposed immutable fields - questionable

public final class Time {

    private static final int HOURS_PER_DAY    = 24;

    private static final int MINUTES_PER_HOUR = 60;



    public final int hour;

    public final int minute;



    public Time(int hour, int minute) {

        if (hour < 0 || hour >= HOURS_PER_DAY)

           throw new IllegalArgumentException("Hour: " + hour);

        if (minute < 0 || minute >= MINUTES_PER_HOUR)

           throw new IllegalArgumentException("Min: " + minute);

        this.hour = hour;

        this.minute = minute;

    }

    ... // Remainder omitted

}
```

To summarize, _public_ classes should never expose mutable fields. Refrain as much as possible from exposing immutable fields also. It is, however, acceptable and desirable to avoid accessors in package-private or inner classes.

### Strive for immutability

Why immutable objects/classes?
- easier to design
- less error prone
- thread safe by nature
- simpler to understand and use

How to make a class immutable?
1. Don't provide mutators
2. Make sure the class cannot be extended - make it final (Do you know another way?)
3. Make all fields final
4. Make all fields private! Could you do it with public fields? What would be the downside?
5. Ensure exclusive access to any mutable components. Use defensive copies

<details><summary>Example</summary>
<p>

```java
// Immutable complex number class

public final  class Complex {

    private final  double re;

    private final  double im;


    public Complex(double re, double im) {

        this.re = re;

        this.im = im;

    }

    public double realPart()      { return re; }

    public double imaginaryPart() { return im; }

    public Complex plus(Complex c) {

        return new Complex(re + c.re, im + c.im);

    }

    public Complex minus(Complex c) {

        return new Complex(re - c.re, im - c.im);

    }

    public Complex times(Complex c) {

        return new Complex(re * c.re - im * c.im,

                           re * c.im + im * c.re);

    }

    public Complex dividedBy(Complex c) {

        double tmp = c.re * c.re + c.im * c.im;

        return new Complex((re * c.re + im * c.im) / tmp,

                           (im * c.re - re * c.im) / tmp);

    }
}
```
</p></details>

Some more advantages of immutable classes:

- Immutable objects can be shared freely, even between threads!
Ex:
```java
public static final Complex ZERO = new Complex(0, 0);

public static final Complex ONE  = new Complex(1, 0);

public static final Complex I    = new Complex(0, 1);
```
 - Take it further and create static factories that cache frequently requested instances. This promotes sharing, reducing memory footprint and GC costs.
 - No need to make defensive copies of immutable objects!
 - Immutable objects are simple to reuse to form more complex ones. It is far easier to maintain invariants of a complex object when large parts of it are invariant by definition
 - 