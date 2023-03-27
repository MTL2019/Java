package AdvancedJavaProgramming.Chapter32MultipleThreading;

/**
 * 1. using Thread ways
 *      - new Class extends Thread
 *      - new Class implements Runnable
 *      - new Class implements Callable
 *
 * 2. Thread Pool:
 *      Executor interface for executing tasks in a thread pool
 *      ExecutorService interface for managing and controlling task; ExecutorService is a subinterface of Executor
 *
 *      Executors class
 *          newFixedThreadPool(int)
 *          newCachedThreadPool() :  Thread numbers are dynamically changed
 *
 * 3. Synchronized Keyword
 *   a lock on the instance method
 *   a lock on the Class's static method
 *   a lock on method's expression ->    a synchronized statement
 *
 * 4. Synchronization Using Locks
 *      - Lock : implements interface  : acquiring and releasing locks explicitly
 *          newCondition() : create any number of Condition objects;
 *          ReentrantLock :  is a concrete implementation of Lock for creating mutually exclusive locks
 *              True fairness policies guarantee that the longest waiting thread will obtain the lock first
 *      - Conditions : create instance to facilitate communications among threads
 *          await() :  causes the current thread to wait until the condition is signaled
 *          signal() : wakes up one waiting thread
 *          signalAll() : wakes all waiting threads
 *      - before Java5, Monitor was used;  wait() / notify() / notifyAll() can be invoked
 *
 * 5. Blocking Queues : causes a thread to block when you try to add an element to a full queue or to remove an element from an empty queue
 *    - synchronization is already implemented
 *    - put() / take() : The put method will never block an unbounded LinkedBlockingQueue or PriorityBlockingQueue.
 *
 *      ArrayBlockingQueue : use array to implement
 *      LinkedBlockingQueue: use Linked list to implement
 *      PriorityBlockingQueue :
 *
 * 6. Semaphores 信号量 ： is an object that controls the access to a common resource.
 *    - A semaphore with just one permit can be used to simulate a mutually exclusive lock互斥锁
 *
 * 7.  Avoiding Deadlocks
 *    *  Deadlocks can be avoided by using a proper 'resource ordering'
 *
 * 8. A thread state : indicates the status of thread
 *       New :  created -> new
 *       Ready : start()
 *       Running : executed;
 *       Blocked
 *       Finished
 * 9. Synchronized Collections
 *    synchronization wrappers : The Collections class provides six static methods for wrapping a collection into a
 *      synchronized version.
 *
 * 10. Parallel Programming
 *    The Fork/Join Framework is used for parallel programming in Java => LIKE  'divide-and-conquer approach'
 *
 **/
public class Chapter32MultipleThreading {

}
