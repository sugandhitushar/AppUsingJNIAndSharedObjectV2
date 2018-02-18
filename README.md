- This is a sample shopping application that integrates Pure C library into Java.
- The pure C dynamic library(calcLogic.c) contains the calculation logic.
- Without rewriting the program into JNI understandable format, I have built a wrapper library that is JNI C library. This library internally calls the pure C library.
- Apart from that nothing is modified in java file and is same as earlier version.

