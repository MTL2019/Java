#Chapter 13
## Abstract Class
### Rules
- An abstract class cannot be used to create objects. 
- An abstract class can contain abstract methods that are implemented in concrete subclasses
- An abstract method is defined without implementation. Its implementation is provided by the subclasses. 
- A class that contains abstract methods must be defined as abstract.
- The constructor in the abstract class is defined as protected because it is used only by subclasses
### pros
- polymorphism
### interesting points
- in a non-abstract subclass extended from an abstract class, all the abstract methods must be implemented. Also note abstract methods
  are nonstatic.
- A subclass can be abstract even if its superclass is concrete
- You cannot create an instance from an abstract class using the new operator, but an abstract class can be used as a data type
  + Right: GeometricObject[] objects = new GeometricObject[10];


## Interface
### rules
- be used to define common behavior for classes (including unrelated classes) => examples: comparable, edible, cloneable.
- be compiled to separate bytecode file
- interface inheritance => using implements keyword
- When a class implements an interface, it implements all the methods defined in the interface.
### generic interface
- Comparable: public int compareTo(E o);
- Cloneable: empty => marker interface : a class possesses certain desirable properties.
  + A class that implements the Cloneable interface is marked cloneable, and its objects can be cloned using the clone() method defined in the Object class.
  + if the class did not implement Cloneable, when the instance invokes clone(), it would return null because super.clone() would throw a CloneNotSupportedException

### comparison  
- Abstract class: single inheritance; invokes constructor by chaining; cannot be instantiated by "new" keyword; strong is-a relationship
- Interface: multiple inheritance; all variables(public static final);now constructor and cannot be instantiated; weak is-a relationship
            hold public abstract instance methods, public default and public static methods


## Class-Design Guidelines  
- Cohesion => a single entity
- Consistency => same format 
- Encapsulation => private variables, use getter and setter
- Clarity 
- Completeness

## Inheritance vs. Aggregation
- Inheritance : is-a relationship => Apple vs Fruit
- Aggregation : has-a relationship => Student vs Name