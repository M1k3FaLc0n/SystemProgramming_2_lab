#include "lib.h"
#include <stdint.h>

void pxor(uint32_t *ptr1, uint32_t *ptr2)
{
	asm("movdqu (%1), %%xmm0\n"
		"movdqu (%2), %%xmm1\n"
		"pxor %%xmm1, %%xmm0\n"
		"movdqu %%xmm0, (%0)": "=r"(ptr1) : "r"(ptr1),"r"(ptr2) : "xmm0","xmm1");
}

JNIEXPORT void JNICALL Java_lib_process (JNIEnv * e, jclass class, jintArray arr)
{
    jint* array = (*e)->GetIntArrayElements(e, arr, JNI_FALSE);
    for(int i = 0; i + 4 <= (*e)->GetArrayLength(e, arr); i += 4)
	{
		static uint32_t mask[4] = {0x00, 0xFFFFFF, 0x00, 0xFFFFFF};
		pxor(array + i, mask);
	}
    (*e)->ReleaseIntArrayElements(e, arr, array, 0);
	return;
}