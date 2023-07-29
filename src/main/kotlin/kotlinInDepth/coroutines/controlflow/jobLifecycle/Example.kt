package kotlinInDepth.coroutines.controlflow.jobLifecycle

import kotlinx.coroutines.*

/*
A job is an object which represents a lifecycle of a concurrent task. Using
jobs, you can track task states and cancel them when necessary. Possible
states of a job are shown in `job_lifecycle.png` and below:

                                      wait children
+-----+ start  +--------+  complete  +-------------+  finish  +-----------+
| New | -----> | Active | ---------> | Completing  | -------> | Completed |
+-----+        +--------+            +-------------+          +-----------+
                 |  cancel / fail       |
                 |     +----------------+
                 |     |
                 V     V
             +------------+                           finish  +-----------+
             | Cancelling | --------------------------------> | Cancelled |
             +------------+                                   +-----------+


 An `Active` state means that a job has been started but hasn't yet come to
completion. This state is usually used by default: job is implicitly started
after it’s created.
 Some coroutine builders like launch() and async() allow you to choose
the initial state by specifying an argument of the CoroutineStart type:
 * CoroutineStart.DEFAULT is the default behavior where the job is
started immediately.
 * CoroutineStart.LAZY means that the job has not started
automatically; in this case, it’s placed into a new state and awaits
starting.

 A job in the `New` state can be started by calling its start() or join()
method after which it transitions to the active state:
 */

fun program1() {
    runBlocking {
        val job = launch(start = CoroutineStart.LAZY) {
            println("Job started")
        }
        delay(100)
        println("Preparing to start...")
        job.start()
    }
}

/*
 Preparing to start...
 Job started
 */

/*
 While in the active state, a job can be repeatedly suspended and resumed
by the coroutines machinery. You can determine a list of its non-completed
children jobs by using the children property:
 */

fun program2() {
    runBlocking {
        val job = coroutineContext[Job.Key]!!

        // 2 children running
        launch { println("This is task A") }
        launch { println("This is task B") }
        println("${job.children.count()} children are running")
    }
}

/*
2 children are running
This is task A
This is task B
 */

/*
 When the coroutine finishes the execution of the suspending lambda block,
its job changes its state to `Completing` which basically means "waiting for
children completion". Job retains this state until all of its children
complete after which it transitions to the `Completed` state.

 You can use Job’s `join()` method to suspend the current coroutine until
the job in question is completed.
 The following program ensures that the root coroutine message
is printed after both its children finish their execution:
 */

fun program3() {
    runBlocking {
        val job = coroutineContext[Job.Key]!!
        val jobA = launch { println("This is task A") }
        val jobB = launch { println("This is task B") }
        jobA.join()
        jobB.join()
        println("${job.children.count()} children are running")
    }
}

/*
This is task A
This is task B
0 children are running
 */

/*
 The current state of a job can be tracked by its properties: isActive,
isCancelled, and isComplete. Their meaning can be summarized in the
following table:

State	                        isActive |  isCompleted	|  isCancelled
----------------------------------------------------------------------
New (optional initial state)	false	 |    false	    |    false
Active (default initial state)	true	 |    false	    |    false
Completing (transient state)	true	 |    false	    |    false
Cancelling (transient state)	false	 |    false	    |    true
Cancelled (final state)	        false	 |    true	    |    true
Completed (final state)	        false	 |    true	    |    false
 */

fun main() {
    program1()
    program2()
    program3()
}
