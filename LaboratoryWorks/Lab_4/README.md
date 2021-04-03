***Руководство по четвертой лабораторной работе***
==================================================
Задание:
--------
**I.** Реализуйте оператор для печати **std::tuple**. Используйте рекурсивные шаблоны.   
```cpp
  template <class Ch, class Tr, class... Args>  
  auto& operator<<(std::basic_ostream<Ch, Tr> &os, const std::tuple<Args...> &t);  
```   

**II.** Напишите свой CSV парсер. Про CSV формат можно прочитать [здесь](https://ru.wikipedia.org/wiki/CSV).  
Особое внимание обратите на примеры.  
  
Работа с парсером должна выглядеть следующим образом:  
```cpp
  std::ifstream file("test.csv");  
  CSVParser<int, std::string> parser(file, 0 /*skip first lines count*/);  
  for (tuple<int, std::string> rs : parser) {  
    std::cout << rs << std::endl;  
```     
*Используйте '\n' в качестве разделителя между строками, ',' в качестве разделителя ячеек внутри строки и '\"' для экранирования.*  
*Позаботьтесь об обработке ошибок: в случае ошибки при парсинге ваш **CSVParser** должен кидать исключение с информацией, в какой строчке произошла ошибка.*  
*Обрабатываемые ошибки - неправильное число ячеек в строке, неправильно использованные символы экранирования.*  

**Ваш парсер должен разбирать следующие типы:**
```cpp  
int, float, double, std::string
```
Потоковая обработка подразумевает lazy (ленивое) чтение строк. Таким образом необходимо реализовать [InputIterator](http://www.enseignement.polytechnique.fr/informatique/INF478/docs/Cpp/en/cpp/concept/InputIterator.html).  
*В качестве примера можете использовать [std::istream_iterator](https://en.cppreference.com/w/cpp/iterator/istream_iterator) - у вашего итератора должно быть схожее поведение.* 
