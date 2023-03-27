package AdvancedJavaProgramming;

/**
 *  CGI : Common Gateway Interface;
 *        Web服务器和其他独立进程（应用程序）交互的接口标准；需要用单独的语言来实现，占用独立的进程，即CPU时间和内存，效率低
 *        to generate dynamic Web content; Perl language is the most popular choice
 *
 *      URL characters:
 *             ? : separates the program from the parameters
 *             = : parameter name and value
 *             & : Parameter pairs are separated
 *             + : a space character
 *
 *  Servlet:  Java programs that function like CGI programs;
 *            run inside a servlet container
 *
 *  Servlet container: is a single process that runs in a Java Virtual Machine; was handled by each thread
 *  *         All the threads share the same memory allocated to the JVM; they can share objects created in JVM
 *      Two popular service container:
 *              Tomcat
 *              GlassFish
 */
public class ServletNote {
}
