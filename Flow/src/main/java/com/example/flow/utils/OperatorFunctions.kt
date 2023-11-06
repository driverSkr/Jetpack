package com.example.flow.utils

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

/**
 * Flow的操作符函数
 */

val flow = flowOf(1, 2, 3, 4, 5)

fun main(){
    runBlocking {
        //mapFun()
        //filterFun()
        //onEachFun()
        //debounceFun()
        //sampleFun()
        //reduceFun()
        //foldFun()
        //flatMapConcatFun()
        //orderConcat()
        //disorderedMerge()
        //flatMapLatestFun()
        //zipFun()
        //zipParallelFun()
        //coastTimeFun()
        //bufferFun()
        conflateFun()
    }
}

/** 1.map 将一个值映射成另一个值，具体映射的规则在map函数中自行定义**/
suspend fun mapFun(){
    flow.map {
        it * it
    }.collect {
        println(it)
    }
}

/** 2.filter 过滤掉一些数据 **/
suspend fun filterFun(){
    flow.filter {
        it % 2 == 0
    }.map {
        it * it
    }.collect {
        println(it)
    }
}

/** 3.onEach 遍历每一条数据的**/
//collect函数中打印出的是最终的结果。如果你想要查看某个中间状态时flow的数据状态，借助onEach就非常有用了
suspend fun onEachFun(){
    runBlocking {
        flow.filter {
            it % 2 == 0
        }.onEach {
            println(it)
        }.map {
            it * it
        }.collect {
        }
    }
}

/** 4.debounce 确保flow的各项数据之间存在一定的时间间隔，如果是时间点过于临近的数据只会保留最后一条**/
@OptIn(FlowPreview::class)
suspend fun debounceFun(){
    runBlocking {
        flow {
            emit(1)
            emit(2)
            delay(600)
            emit(3)
            delay(100)
            emit(4)
            delay(100)
            emit(5)
        }.debounce(500).collect{
            println(it)
        }
    }
}

/** 5.sample 采样的意思，也就是说，它可以从flow的数据流当中按照一定的时间间隔来采样某一条数据**/
suspend fun sampleFun(){
    runBlocking {
        flow {
            while (true) {
                emit("发送一条弹幕")
            }
        }
        .sample(1000)
        //由于flow是通过死循环不断发送的，我们必须调用flowOn函数，让它在IO线程里去执行，否则我们的主线程就一直被卡死了
        .flowOn(Dispatchers.IO)
        .collect {
            println(it)
        }
    }
}
/** 6.reduce **/
suspend fun reduceFun(){
    runBlocking {
        val result = flow {
            for (i in (1..100)) {
                emit(i)
            }
        }.reduce { accumulator, value -> accumulator + value }
        println(result)
    }
}

/** 7.fold 和reduce函数基本上是完全类似的**/
suspend fun foldFun(){
    runBlocking {
        val result = flow {
            for (i in ('A' .. 'Z')) {
                emit(i.toString())
            }
        }.fold("Alphabet：") { acc, value -> acc + value }
        println(result)
    }
}

/** 8.flatMapConcat**/
suspend fun flatMapConcatFun(){
    runBlocking {
        flowOf(1,2,3)
            .flatMapConcat {
                flowOf("a$it","b$it")
            }
            .collect {
                println(it)
            }
    }
}

/** 验证flatMapConcat的循序性**/
@OptIn(FlowPreview::class)
suspend fun orderConcat(){
    runBlocking {
        flowOf(300,200,100)
            .flatMapConcat {
                flow {
                    delay(it.toLong())
                    emit("a$it")
                    emit("b$it")
                }
            }.collect {
                println(it)
            }
    }
}

/** 9.验证flatMapMerge的并发性（无序）**/
suspend fun disorderedMerge(){
    runBlocking {
        flowOf(300,200,100)
            .flatMapMerge {
                flow {
                    delay(it.toLong())
                    emit("a$it")
                    emit("b$it")
                }
            }.collect {
                println(it)
            }
    }
}

/** 10.flatMapLatest **/
@OptIn(ExperimentalCoroutinesApi::class)
suspend fun flatMapLatestFun() {
    flow {
        emit(1)
        delay(150)
        emit(2)
        delay(50)
        emit(3)
    }.flatMapLatest {
        flow {
            delay(100)
            emit("$it")
        }
    }.collect {
        println(it)
    }
}

/** 11.zip**/
suspend fun zipFun() {
    runBlocking {
        val flow1 = flowOf("a","b","c")
        val flow2 = flowOf(1,2,3,4,5)
        flow1.zip(flow2) { a,b ->
            a + b
        }.collect {
            println(it)
        }
    }
}

/** 验证zip是并行运行的**/
suspend fun zipParallelFun() {
    runBlocking {
        val start = System.currentTimeMillis()
        val flow1 = flow {
            delay(3000)
            emit("a")
        }
        val flow2 = flow {
            delay(2000)
            emit(1)
        }
        flow1.zip(flow2) { a, b ->
            a + b
        }.collect {
            val end = System.currentTimeMillis()
            println("Time cost: ${end - start}ms")
        }
    }
}

suspend fun coastTimeFun() {
    runBlocking {
        flow {
            emit(1)
            delay(1000)
            emit(2)
            delay(1000)
            emit(3)
        }.onEach {
            println("$it is ready")
        }.collect {
            delay(1000)
            println("$it is handled")
        }
    }
}

/** 12.buffer **/
suspend fun bufferFun() {
    runBlocking {
        flow {
            emit(1)
            delay(1000)
            emit(2)
            delay(1000)
            emit(3)
        }.onEach {
            println("$it is ready")
        }.buffer()
        .collect {
            delay(1000)
            println("$it is handled")
        }
    }
}

/** 13.conflate**/
suspend fun conflateFun(){
    runBlocking {
        flow {
            var count = 0
            while (true) {
                emit(count)
                delay(1000)
                count ++
            }
        }.conflate()
        .collect{
            println("start handle $it")
            delay(2000)
            println("finish handle $it")
        }
    }
}



