package com.example.corutine

import android.graphics.Paint.Join
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fun main(){
            // here we are calling our supended custom functions
            val time  = measureTimeMillis {
                val mess =ryan();
                val gret =omo();
            }

            //btw before going very far the main function runs on the main thread of the app
            println("main program starts: ${Thread.currentThread().name}")
            // you can put here some other logics but i will come back latter
            GlobalScope.launch { // here we are tyring to build a background thread using this lambda thread expression
                println("we are trying to do some stuff here : ${Thread.currentThread().name}")
//                Thread.sleep(1000)
// instead of a using sleep fuction it's better to use delay since for delay it pauses a corutine but sleep fuction pauses a thread of which a thread might have multiple couritnes
                delay(1000)
                print("this work is finished :${Thread.currentThread().name}")
//here we are going to crete a new courutine but with a runing block function which you can use to block the thred in which you create this courutine from
               runBlocking {
                   delay(100)
                   // that delay is used to block the whole thread in which we create this courutine and in this case it's main thread where we create it feom
               }
                GlobalScope.launch {

                    // here we have created a corotine with a global scope
                    fun jovic(x:Int):Unit{
                        System.out.println("this is a courutine i created using a globalScope.launch function and it's global")
                        System.out.print(("this application is created at a global level or "))
                    }
                val job : Job =launch {
                    System.out.println(" here a creating a courutine with a local scope bro.")
                }
                    job.join()// this one makes sure the coming assigment after a couritne waits for it to finish first for it to be exectudes

                    //N.B the launch function returns a job object and you can use this object to to access methodes like join for the
                    // next job to wait for a given courutine to finish
                    // other methods  which you can use to cancle a courutine and other methodes
                val job1:Deferred<T> = GlobalScope.async {
                    System.out.println("here we have created a courutine with w async function")
                    // here using aysnc function you can return some data which is not the case with launch which doesnot return anything
                    // the Deferred object is a subclass of Job object which means that you can stil acces methodes like jion , cancle
//

                }
                val biralo:Job =launch(start = CoroutineStart.LAZY) {
                    try {
                        print("this is a global courutine ")
                        // to make use of the cancle fuction , the courinte should implement atleast one cancleble fuctions like delay,yeild and others which are cancleble in nature but they belong to kotline couritine library
                        for (i in 1..400)
                        {
                            print("the value is $i")
                            delay(2)
                        }
                    }
                    catch (jovic:CancellationException){
                        print("\n Eexception hanadled very well")
                    }
                    finally {
                        print("this can do anything here ")
                    }

                }
                    biralo.cancel()
                    biralo.join()


                }

           suspend fun ryan():String{
                 delay(100)
               return "hello"
            }
           suspend fun omo():String{
               delay(100)
               return "birlo"
           }


            }

            println("the main program ends: ${Thread.currentThread().name}")


        }
    }
}
