public class Test {
  public static void main(String[] args) throws Exception {
    int a = 3;

    switch (a) {
    case 1:
      System.out.println("test");
      break;
    case 2:
      System.out.println("test");
      break;
    case 4:
      System.out.println("test");
      break;
    default:
      throw new Exception();
    }

    System.out.println("got here");
  }
}