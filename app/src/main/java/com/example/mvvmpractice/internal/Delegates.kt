package com.example.mvvmpractice.internal

import kotlinx.coroutines.*

//High order suspend function
fun<T> lazyDeferred(block: suspend CoroutineScope.() -> T): Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY){
            block.invoke(this)
        }
    }
}