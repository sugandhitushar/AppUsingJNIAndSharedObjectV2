#include"App.h"
#include<stdlib.h>
#include<dlfcn.h>       // functionality for dynamic link loader

JNIEXPORT jint JNICALL Java_App_calcBill(JNIEnv *env, jclass jobj, jintArray arr)
{
	int i, sum=0;
	jsize len = (*env)->GetArrayLength(env, arr);
	
	void *p = NULL; // Pointer for storing address of shared object file
	int (*countTotal)(int[], int) = NULL; // Function ptr of the function to be called from Pure C's .so file
		
	jint *params = (*env)->GetIntArrayElements(env, arr, 0);
	
	// Purposely gave absolute path as it crashed without this. If you have better alternative, use it.
	p = dlopen("/root/SYMCA/JNI/Shopping_app/Shopping_app_v2/calcLogic.so", RTLD_LAZY);
	if(!p)
    {
            printf("Unable to load library: %s\n",dlerror());
    }

	
	// This function acquires the funtion from our Pure C's .so file.
	countTotal = dlsym(p, "countTotal");
	if(countTotal == NULL)
    {
            printf("Unable to get address of function: %s\n",dlerror());
    }
    
    // Call the loaded funtion
    sum = countTotal(params, len);
	
	(*env)->ReleaseIntArrayElements(env, arr, params, 0);
	
	return sum;
}
