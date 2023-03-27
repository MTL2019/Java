package DataStructureAndAlgorithms;

/**
 * generics
 * The key benefit :  to enable errors to be detected at compile time rather than at runtime
 * type : must be reference type, not primitive type
 *
 * raw type ： without a type parameter => allows for backward compatibility with earlier versions of Java,but unsafe
 * Wildcard Generic Types :
 *      unbounded wildcard (?) : ? extends Object => any object type
 *      bounded wildcard : ? extends T => represents T or a subtype of T.
 *      lower bound wildcard : ? super T => T or a supertype of T.
 * type erasure: The information on generics is used by the compiler but is not available at runtime
 *      generic-type information is erased after compiling the code; from generic type to raw type;
 * Restriction:
 *      1. cannot use new E(); because E is not available at runtime, it became raw type and object type
 *      2. cannot use new E[]: their types may be not the same
 *      *3. A Generic Type Parameter of a Class Is Not Allowed in a Static Context
 *      4. Exception class cannot be the generic
 */
public class Chapter19_Generics {
    public static void main(String[] args) {

    }
}
