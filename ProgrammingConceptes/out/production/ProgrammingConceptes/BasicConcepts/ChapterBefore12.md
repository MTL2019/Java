# Chapter before 11
## heap: is used for dynamic memory allocation, such as objects.
## stack: stores call methods; first in, later out.
## Variable-Length Argument Lists
    typeName... parameterName

# Chapter11&12 The three pillars of object-oriented programming
## encapsulation
- The details of implementation are encapsulated and hidden from the user
## Inheritance
- Single inheritance in Java: A Java class may inherit directly from only one superclass.
- super keyword
    + constructors cannot be inherited by subclass; 
    + use supper() to invoke
    + must be the first statement in subclass's constructor 
    + can be used to invoke superclass's methods
- overriding
    + use same method signature as in its supperclass
    + only if it's accessible, it can be overridden; if so, they are two different methods
    + static methods can be inherited, but not be overridden; if so, supperclass's static method will be hidden.
    + can use an annotation @override to detect mistake
- overloading
    + multiple methods with same name but different signatures; for different functions
    
## Polymorphism
- a variable of a supertype can refer to a subtype object; every instance of a subclass is also an instance of its superclass
    + An object of a subclass can be used wherever its superclass object is used
    
## dynamic Binding
- A method can be implemented in several classes along the inheritance chain. The JVM decides which method is invoked at runtime
- declared type / actual type
    + actual type: among the override methods, Which method is invoked by an object is determined by object’s actual type.
    + declared type: The declared type of the reference variable decides which method to match at compile time
    
## casting object
- One object reference can be typecast into another object reference. 
    + implicit casting : for upcasting, subclass obj can be automatically casted to superclass obj
    + explicit casting : for downcasting, superclass can be casted to subclass with compile check
- function： transform the superclass object to subclass object, to invoke the methods which only exist in subclass    
- instanceOf() : to ensure the object is an instance of another object before attempting a casting   
    + only if instanceOf function returns true，casting would be processed successfully
    
## specific Object class methods
- toString()
- equals(): for Object Class, they use "==" to check whether they pointed to the same object, but will usually override this function.
  > public boolean equals(Object obj) {  
  > return this == obj;//use "==" to check whether they pointed to the same object  
  > //You should override this method in your custom class to test whether two distinct objects have the same content
  > }  
  + == :
    1. be used for comparing the primitive data type values
    2. check two objects pointing to the same object or not
  
## protected keyword
- allow subclass to access the data and methods
- subclass can only expand the supperclass's methods' accessbility, not allow to weaken it(from protected to private)

## final
- final class and method cannot be extended
- final data is a constant
## static
- A static variable or method can be invoked from an instance method, but an instance variable or method cannot be invoked from a static method.
  
## class abstraction
- separates class implementation from how the class is used

# Exception handling
- Exceptions are thrown from a method. The caller of the method can catch and handle the exception
- Three types Exceptions
  + System errors: Error Class: thrown by JVM,describes internal system errors;  
  + Exceptions: Exception class: describes errors caused by your program and by external circumstances; can be caught and handled
  + Runtime exceptions: RuntimeException class, describes programming errors, such as bad casting, accessing an out-of-bounds array, and
    numeric errors. Runtime exceptions normally indicate programming errors 
- Types:
  + unchecked exceptions： RuntimeException, Error; => mostly meaning unrecoverable programming logic errors
  + checked exceptions：all other exception;meaning the compiler forces the programmer to check and deal with them in a try-catch block or declare it in the method header
- Declaring,Throwing and Catching Exceptions
  + only declare the checked exception explicitly => throws keyword; use throw keyword to throw an exception
  + if a method does not declare exceptions in the superclass, you cannot override it to declare exceptions in the subclass.
  + use getMessage() can get the msg from Exceptions' String argument.
- catching an exception: send exception to its caller chain, until find handler; if not, exit program.  
- multicatch feature: catch multiple exceptions at the same time 