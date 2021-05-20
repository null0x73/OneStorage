package org.demo.onestorage.util.debug;

public class DebugUtil {

    public static void printWithIdentityHashCode(Object object){
        System.out.println(System.identityHashCode(object)+" @ "+object.toString());
    }

}
