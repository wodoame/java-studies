void main() {
    try {
        int myInt = Integer.parseInt("panda");
    } catch (NumberFormatException nfe) {
        // The catch block will catch all exceptions of type NumberFormatException or subclasses of it
        // For example if you use Exception instead of NumberFormatException it will catch all exceptions since
        // all exceptions are subclasses of Exception
        // For NumberFormatException the hierarchy is:
        // Object -> Throwable -> Exception -> RuntimeException -> NumberFormatException (all from java.lang package)
        System.out.println("Error: Unable to convert string to integer.");
    }
    finally{
        // executes regardless of whether an exception occurred or not even if there's a return statement in try or catch block
        System.out.println("Execution completed.");
    }
}
