package kotlinInDepth.specialCaseClasses.enumClass.underTheHood;

public enum Rainbow {
    RED("Red", "#FF0000"),
    ORANGE("Orange", "#FF7F00"),
    YELLOW("Yellow", "#FFFF00"),
    GREEN("Green", "#00FF00"),
    BLUE("Blue", "#0000FF"),
    INDIGO("Indigo", "#4B0082"),
    VIOLET("Violet", "#8B00FF");

    String color;
    String rgb;

    Rainbow(String color, String rgb) {
        this.color = color;
        this.rgb = rgb;
    }

    public void printFullInfo() {
        System.out.println("Color - " + color + ", rgb - " + rgb);
    }
}

/* Compiled code (simplified):
 public final class Rainbow extends java.lang.Enum<Rainbow> {

  public static final Rainbow RED;
  public static final Rainbow ORANGE;
  public static final Rainbow YELLOW;
  public static final Rainbow GREEN;
  public static final Rainbow BLUE;
  public static final Rainbow INDIGO;
  public static final Rainbow VIOLET;

  String color;
  String rgb;

  public static Rainbow[] values();
  public static Rainbow valueOf(String);

  public void printFullInfo();

  static {};
}
 */
