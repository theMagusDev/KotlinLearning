package kotlinInDepth.properties.introToDelegation

/*
* In the example above, we mainly used delegate to override
* some properties set by the interface and do something simple.
* Let's take a look at a more complex case featuring not one
* but two delegates!
 */

// First interface, specifies contract for callbacks
interface ICallbackReceiver {
    fun onBeforeAction()
    fun onAfterAction()
    fun action(function: () -> Unit) {
        onBeforeAction()
        function()
        onAfterAction()
    }
}

// Second interface, specifies contract for logger
interface ILogger {
    fun getStubDateTime() = "05.11.2022-14:31:04" // stub

    val format: String
        get() = "[${getStubDateTime()}]: "

    fun printIt(s: String)
}

// Simple implementation of ILogger interface
class BasicLogger : ILogger {
    override fun printIt(s: String) = println(format + s)
}

// Implementation of first interface, which defines callback actions,
// setting them to simply print to console each time they are called.
// However! For printing, it utilizes delegated BasicLogger, which
// in this example prints info with date and time markers.
class ConsoleNotifier(logger: ILogger) : ICallbackReceiver, ILogger by logger {
    val onBeforeStr = "OnBefore!"
    val onAfterStr = "OnAfter!"

    // 'printIt' is delegated to 'logger'

    override fun onBeforeAction() = printIt(onBeforeStr)
    override fun onAfterAction() = printIt(onAfterStr)
}

/*
* Above, two interfaces with two corresponding implementations
* are introduced.

* The first one, ICallbackReceiver, can be used in the case when
* we need to "surround" a certain action with function calls doing
* something before and after the execution of a function.
* Such an approach is often used in what is known as serialization –
* the process of saving data structure as a file. For example,
* when we save files, we can prepare the needed data in onBeforeAction,
* and when we load it, we can construct the data back in onAfterAction.
*
* The second one, ILogger, is simply a formatter for output.
* However, when used as a delegate, it makes all output follow
* the same pattern, which can be useful for logging.
*
* ConsoleNotifier, the implementation of ICallbackReceiver,
* uses it as a delegate instead of the usual println().
*
* Below, we construct a class which implements both interfaces via delegation.
* The implementation of ILogger is used twice – in the inner structure
* of notifier and in the class itself.
 */

// Class implementing both interfaces by delegation to implementation to above classes
class ExampleParser(notifier: ICallbackReceiver, logger: BasicLogger) : ICallbackReceiver by notifier, ILogger by logger {
    fun start() = action { parseFiles() }

    fun parseFiles() {
        printIt("Parsing...");
        // do some file parsing
    }
}

fun main() {
    val loggerInstance: BasicLogger = BasicLogger()
    val dateTimeNotifier: ConsoleNotifier = ConsoleNotifier(logger = loggerInstance)

    val simpleParser: ExampleParser = ExampleParser(notifier = dateTimeNotifier, logger = loggerInstance)
    simpleParser.start()
}