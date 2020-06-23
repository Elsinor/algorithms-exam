import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    System.out.println("Class Name: ");
    System.out.println(args[0]);
    try {

      Class exampleClass = Class.forName(args[0]);
      Method mainMethod = exampleClass.getDeclaredMethod("main", new Class[] {String[].class});
      mainMethod.invoke(null, new Object[] {Arrays.copyOfRange(args, 1, args.length)});

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
