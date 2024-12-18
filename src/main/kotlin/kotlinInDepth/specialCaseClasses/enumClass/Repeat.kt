package kotlinInDepth.specialCaseClasses.enumClass

/* Вопросы

1) Что такое enum?
2) Как такой класс работает под капотом?
3) Какие есть правила для них исходя из определения?
4) Как представлены его members методами, переменными и т.д.?
5) В чём особенность работы when с enum?
6) Что за переменные name, ordinal и declaringJavaClass он содержит?
7) Каков результат сравнения таких констант знаком '=='?
8) А обычным сравнением '>', '<' что сравнивается у них?

Answers:
1) enum - это перечисление глобальных констант определённого типа.
2) Создаётся класс, который автоматически наследует java.lang.enum<НАЗВАНИЕ_ENUM>.
В этом классе есть public static final константы типа этого enum. Подробнее
в файле UnderTheHood.KT
3) Enum класс не может быть вложенным, поскольку его элементы - это всегда
глобальные константы.
4) Это объекты типа объявленного enum, имеющие прописанные методы и переменные.
Если мы пропишем через фигурные скобки метод индивидуально для какой-либо константы,
то будет создан static nested класс, наследующий этот enum.
5) Если проверены ВСЕ константы enum'а, то ветку else можно опустить.
Такой when называется exhausted.
6) name - имя константы, ordinal - позиция в перечислении,
declaringJavaClass - имя объявляющего эту константу enum'а.
7) Результат - false, так как это объекты с разным адресом.
8) Их ordinal, т.е. позиции в перечислении.

 */

/* Практика:

1) Объявите enum WeekDay. Создайте к нему extension функцию, которая
будет определять, рабочий ли день.

 */