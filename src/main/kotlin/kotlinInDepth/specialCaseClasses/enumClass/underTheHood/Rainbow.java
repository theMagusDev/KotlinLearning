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

  public static final kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow RED;
  public static final kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow ORANGE;
  public static final kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow YELLOW;
  public static final kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow GREEN;
  public static final kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow BLUE;
  public static final kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow INDIGO;
  public static final kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow VIOLET;

  java.lang.String color;
  java.lang.String rgb;

  public static kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow[] values();
  public static kotlinInDepth.specialCaseClasses.enumClass.underTheHood.Rainbow valueOf(java.lang.String);

  public void printFullInfo();

  static {};
}
 */
