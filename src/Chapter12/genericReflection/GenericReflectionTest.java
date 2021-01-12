package Chapter12.genericReflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @version 1.0.0 2021-01-12
 * @author dz
 */
public class GenericReflectionTest {
    public static void main(String[] args) {
        // read class name from command line args or user input
        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Enter class name (e.g. java.util.Collections):");
            name = in.next();
        }

        try {
            // print generic info for class and public methods
            Class<?> cl = Class.forName(name);
            printClass(cl);
            for (Method m : cl.getDeclaredMethods())
                printMethod(m);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void printClass(Class<?> cl) {
        System.out.print(cl);
        printTypes(cl.getTypeParameters(), "<", ", ", ">", true);
        Type sc = cl.getGenericSuperclass();
        if (sc != null) {
            System.out.print(" extends ");
            printType(sc, false);
        }
        printTypes(cl.getGenericInterfaces(), " implents ", ", ", "", false);
        System.out.println();
    }

    private static void printMethod(Method m) {
        String name = m.getName();
        System.out.print(Modifier.toString(m.getModifiers()));
        System.out.print(" ");
        printTypes(m.getTypeParameters(), "<", ", ", "> ", true);

    }

    private static void printTypes(Type[] types, String pre, String sep, String suf, boolean isDefinition) {
        if (pre.equals(" extends ") && Arrays.equals(types, new Type[] { Object.class})) return;
        if (types.length > 0) System.out.print(pre);
        for (int i = 0; i < types.length; i++) {
            if (i > 0) System.out.print(suf);
            printType(types[i], isDefinition);

        }
    }

    private static void printType(Type type, boolean isDefinition) {
        if (type instanceof Class) {
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        }
    }
}
