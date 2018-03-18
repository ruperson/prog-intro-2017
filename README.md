# Тесты к курсу «Введение в программирование»

[Условия домашних заданий](http://www.kgeorgiy.info/courses/prog-intro/homeworks.html)

Домашнее задание 9. WebCrawler
----

Условия:
 * Ваш класс должен иметь имя `crawler.SimpleWebCrawler`
    * Класс должен реализовывать интерфейс [WebCrawler](java/crawler/WebCrawler.java)
    * Конструктор класса должен принимать [Downloader](java/crawler/Downloader.java),
      используемый для загрузки страниц и картинок
 * Прохождение боссов является не обязательным, по позволяет получить дополнительные баллы
    * [Тестовый босс](artifacts/crawler/kgeorgiy_cache.zip)

Исходный код тестов:
 * Обычные: [WebCrawlerTest.java](java/crawler/WebCrawlerTest.java)
    * [Кэш](artifacts/crawler/base_cache.zip)
 * Для боссов: [WebCrawlerBossTest.java](java/crawler/WebCrawlerBossTest.java)
 * Для экспериментов: [CachingDownloader.java](java/crawler/CachingDownloader.java)


Домашнее задание 8. Markdown to HTML
----

Модификации
 * *Mark*
    * Добавьте поддержку `~выделения цветом~`: `<mark>выделения цветом</mark>`
    * [Исходный код тестов](java/md2html/Md2HtmlMarkTest.java)
    * [Откомпилированные тесты](artifacts/md2html/Md2HtmlMarkTest.jar)
 * *Underline*
    * Добавьте поддержку `++подчеркивания++`: `<u>подчеркивания</u>`
    * [Исходный код тестов](java/md2html/Md2HtmlUnderlineTest.java)
    * [Откомпилированные тесты](artifacts/md2html/Md2HtmlUnderlineTest.jar)

Исходный код тестов: [Md2HtmlTest.java](java/md2html/Md2HtmlTest.java)

Откомпилированные тесты: [Md2HtmlTest.jar](artifacts/md2html/Md2HtmlTest.jar)


Домашнее задание 7. Разметка
----

Модификации
 * *HTML*
    * Дополнительно реализуйте метод `toHtml`, герерирующий HTML-разметку:
      * выделеный текст окружается тегом `em`;
      * сильно выделеный текст окружается тегом `strong`;
      * зачеркнутый текст окружается тегом `s`.
    * [Исходный код тестов](java/markup/HtmlTest.java)
 * *TeX*
    * Дополнительно реализуйте метод `toTeX`, герерирующий TeX-разметку:
      * выделеный текст заключается в `\emph{` и `}`;
      * сильно выделеный текст заключается в `\textbf{` и `}`;
      * зачеркнутый текст заключается в `\textst{` и `}`.
    * [Исходный код тестов](java/markup/TexTest.java)

Исходный код тестов:

* [Исходный код тестов](java/markup/MarkdownTest.java)


Домашнее задание 6. Подсчет слов++
----

Модификации
 * *LineIndex*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Вместо номеров вхождений во всем файле надо указывать
      `<номер строки>:<номер в строке>`
    * Класс должен иметь имя `WordStatLineIndex`
    * [Исходный код тестов](java/wordStat/WordStatLineIndexTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatLineIndexTest.jar)

Исходный код тестов:

* [WordStatIndexTest.java](java/wordStat/WordStatIndexTest.java)
* [WordStatIndexChecker.java](java/wordStat/WordStatIndexChecker.java)

Откомпилированные тесты: [WordStatIndexTest.jar](artifacts/wordStat/WordStatIndexTest.jar)


Домашнее задание 5. Быстрый реверс
----

Модификации
 * *Минимум*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите минимум из чисел в его столбце и строке.
    * Класс должен иметь имя `ReverseMin`
    * [Исходный код тестов](java/reverse/ReverseMinFastTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseMinFastTest.jar)
 * *Сумма*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите сумму чисел в его столбце и строке.
    * Класс должен иметь имя `ReverseSum`
    * [Исходный код тестов](java/reverse/ReverseSumFastTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseSumFastTest.jar)

Исходный код тестов:

* [ReverseFastTest.java](java/reverse/ReverseFastTest.java)
* [ReverseChecker.java](java/reverse/ReverseChecker.java)

Откомпилированные тесты: [ReverseFastTest.jar](artifacts/reverse/ReverseFastTest.jar)


Домашнее задание 4. Подсчет слов
----

Модификации
 * *Words*
    * В выходном файле слова должны быть упорядочены в лексикографическом порядке
    * Класс должен иметь имя `WordStatWords`
    * [Исходный код тестов](java/wordStat/WordStatWordsTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatWordsTest.jar)
 * *Count*
    * В выходном файле слова должны быть упорядочены по возрастанию числа
      вхождений, а при равном числе вхождений – по порядку первого вхождения
      во входном файле
    * Класс должен иметь имя `WordStatCount`
    * [Исходный код тестов](java/wordStat/WordStatCountTest.java)
    * [Откомпилированные тесты](artifacts/wordStat/WordStatCountTest.jar)

Исходный код тестов:

* [WordStatInputTest.java](java/wordStat/WordStatInputTest.java)
* [WordStatChecker.java](java/wordStat/WordStatChecker.java)

Откомпилированные тесты: [WordStatInputTest.jar](artifacts/wordStat/WordStatInputTest.jar)


Домашнее задание 3. Сумма чисел в файле
----

Модификации
 * *Hex*
    * На вход подаются десятичные и шестнадцатеричные числа
    * Шестнадцатеричные числа имеют префикс `0x`
    * Ввод регистронезависим
    * Класс должен иметь имя `SumHexFile`
    * [Исходный код тестов](java/sum/SumHexFileTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumHexFileTest.jar)
 * *Abc*
    * На вход подаются числа, представленные буквами.
      Нулю соответствует буква `a`, единице – `b` и так далее
    * Ввод регистронезависим
    * Класс должен иметь имя `SumAbcFile`
    * [Исходный код тестов](java/sum/SumAbcFileTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumAbcFileTest.jar)

Исходный код тестов:

* [SumFileTest.java](java/sum/SumFileTest.java)
* [SumFileChecker.java](java/sum/SumFileChecker.java)

Откомпилированные тесты: [SumFileTest.jar](artifacts/sum/SumFileTest.jar)


## Домашнее задание 2. Реверс

Модификации
 * *Транспозиция*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      выведите ее в транспонированном виде
    * Класс должен иметь имя `ReverseTranspose`
    * [Исходный код тестов](java/reverse/ReverseTransposeTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseTransposeTest.jar)
 * *Максимум*
    * Рассмотрим входные данные как (не полностью определенную) матрицу,
      вместо каждого числа выведите максимум из чисел в его столбце и строке.
    * Класс должен иметь имя `ReverseMax`
    * [Исходный код тестов](java/reverse/ReverseMaxTest.java)
    * [Откомпилированные тесты](artifacts/reverse/ReverseMaxTest.jar)

Исходный код тестов:

* [ReverseTest.java](java/reverse/ReverseTest.java)
* [ReverseChecker.java](java/reverse/ReverseChecker.java)

Откомпилированные тесты: [ReverseTest.jar](artifacts/reverse/ReverseTest.jar)


## Домашнее задание 1. Сумма чисел

Модификации
 * *DoubleSpace*
    * Входные данные являются числами с формате с плавающей точкой
    * Класс должен иметь имя `SumDoubleSpace`
    * Числа разделяются [пробелами-разделителями](https://docs.oracle.com/javase/8/docs/api/java/lang/Character.html#SPACE_SEPARATOR)
    * [Исходный код тестов](java/sum/SumDoubleSpaceTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumDoubleSpaceTest.jar)
 * *BigIntegerDigit*
    * Входные данные помещаются в тип [BigInteger](https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html)
    * Класс должен иметь имя `SumBigInteger`
    * Числа имеют вид `[знак]цифры`
    * [Исходный код тестов](java/sum/SumBigIntegerDigitTest.java)
    * [Откомпилированные тесты](artifacts/sum/SumBigIntegerDigitTest.jar)

Для того, чтобы протестировать исходную программу:

 1. Скачайте откомпилированные тесты ([SumTest.jar](artifacts/sum/SumTest.jar))
 * Откомпилируйте `Sum.java`
 * Проверьте, что создался `Sum.class`
 * В каталоге, в котором находится `Sum.class` выполните команду
    ```
       java -jar <путь к SumTest.jar>
    ```
    * Например, если `SumTest.jar` находится в текущем каталоге, выполните команду
    ```
        java -jar SumTest.jar
    ```

Исходный код тестов:

* [SumTest.java](java/sum/SumTest.java)
* [SumChecker.java](java/sum/SumChecker.java)
* [Базовые классы](java/base/)
