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

  private static final Ordinals[] $VALUES;

  public static WeekDay[] values() {
      return $VALUES.clone();
  }

  WeekDay(String name, int ordinal) {} // created under the hood

  public static WeekDay valueOf(String name) {
      return (WeekDay) Enum.valueOf(WeekDay.class, name);
  }

  static {
      MONDAY = new WeekDay("MONDAY", 0)
      TUESDAY = new WeekDay("TUESDAY", 0)
      WEDNESDAY = new WeekDay("WEDNESDAY", 0)
      THURSDAY = new WeekDay("THURSDAY", 0)
      FRIDAY = new WeekDay("FRIDAY", 0)
      SATURDAY = new WeekDay("SATURDAY", 0)
      SUNDAY = new WeekDay("SUNDAY", 0)

      WeekDay[] $VALUES = new Ordinals[7];
      $VALUES[0] = MONDAY;
      $VALUES[1] = TUESDAY;
      $VALUES[2] = WEDNESDAY;
      $VALUES[3] = THURSDAY;
      $VALUES[4] = FRIDAY;
      $VALUES[5] = SATURDAY;
      $VALUES[6] = SUNDAY;

      WeekDay.$VALUES = $VALUES;
  };
}
 */