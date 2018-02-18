#include<stdio.h>

extern int countTotal(int[], int);

/*
This function will take an array of integers and return its sum. It is pure C program.
*/
int countTotal(int arr[], int len)
{
	int i, sum=0;
	for(i=0; i<len; i++)
	{
		sum+=arr[i];
	}
	return sum;
}
