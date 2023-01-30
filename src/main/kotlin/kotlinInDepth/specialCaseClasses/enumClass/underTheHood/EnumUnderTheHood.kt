package kotlinInDepth.specialCaseClasses.enumClass.underTheHood

enum class WeekDay {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}

/* Compiled code (Java):
public final class WeekDay extends java.lang.Enum<WeekDay> {

  public static final WeekDay MONDAY;
  public static final WeekDay TUESDAY;
  public static final WeekDay WEDNESDAY;
  public static final WeekDay THURSDAY;
  public static final WeekDay FRIDAY;
  public static final WeekDay SATURDAY;
  public static final WeekDay SUNDAY;

  public static WeekDay[] values();

  public static WeekDay valueOf(java.lang.String);

  static {};
}
 */