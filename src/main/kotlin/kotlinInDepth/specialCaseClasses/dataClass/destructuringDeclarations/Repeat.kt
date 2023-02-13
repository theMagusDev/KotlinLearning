package kotlinInDepth.specialCaseClasses.dataClass.destructuringDeclarations

/*
Вопросы:
1) Что требуют destructuring declarations?
2) Как они работают?
3) На каких классах они работают?
4) Какие классы поддерживают destructuring declarations по умолчанию?

Ответы:
1) Они обязательно требуют метод componentN()

2) Каждая property вызывает нужную componentN() функцию того, откуда мы берём эти значения,
где N - порядковый номер property в этом destructuring declaration. Например:
val person = Person("Yuriy", "Magus", 17)
val (a, b, c) = person
означает
val a = person.component1()
val b = person.component2()
val c = person.component3()

3) Только на тех, у которых имплементирована функция componentN().

4) Data классы, т.к. в них автоматически генерируются componentN() функции.

 */