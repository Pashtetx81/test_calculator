/*
(+++) - Реализованно
Создай консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем строки, числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
+++ Реализуй класс Main с методом public static String calc(String input). Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения. Ты можешь добавлять свои импорты, классы и методы. Добавленные классы не должны иметь модификаторы доступа (public или другие)

Требования:
+++ Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)! Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
+++ Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
+++ Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более. На выходе числа не ограничиваются по величине и могут быть любыми.
+++ Калькулятор умеет работать только с целыми числами.
+++ Калькулятор умеет работать только с арабскими или римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
+++ При вводе римских чисел, ответ должен быть выведен римскими цифрами, соответственно, при вводе арабских - ответ ожидается арабскими.
+++ При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
+++ При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
+++ Результатом операции деления является целое число, остаток отбрасывается.
+++ Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль. Результатом работы калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы, выбрасывается исключение
--- Не успел, пока, доделать более детальные отчеты по ошибкам.
 */
import java.util.Scanner;
final class Members { // создаем класс Member, что бы возвращать из метода separation несколько переменных. Два числа и арифметический знак.
    private final int frt;
    private final int scd;
    private final char sign;
    public Members(int frt, int scd, char sign) { //конструктор класса
        this.frt = frt;
        this.scd = scd;
        this.sign = sign;
    }
    public int getFrt() {
        return frt;
    } // создает внутри класса методы get для возврата переменных

    public int getScd() {
        return scd;
    }
    public char getSign() {
        return sign;
    }
}

class MyException extends Exception { //создаем класс своих исключений который расширяет класс Exception
    public MyException(String mesage)
    {
        super(mesage);
    }
}
public class Main {
    public static void main(String[] args) {
    Scanner scan = new Scanner(System.in); //создаем объект класса сканер, что бы поместить в него введенную строку.
        System.out.println("Введите арифметическое выражение согласно условиям задачи: ");
        String str = scan.nextLine(); //помещаем строку в переменную
        Members spr = separation(str); //создает обьект класса Members и извлекает результаты разделения строки.
        String result = calc(spr.getFrt(),spr.getScd(),spr.getSign()); //Подставляем результаты разделения строки в метод calc для выполнения арифметических оппераций.
        System.out.println("ответ: "+result); //выводим в консоль результат вычисления или же ошибку
           }
          public static String calc(int x, int y, char z) { //основной метод вычисления выражения
           int ii=-1;
           String output=" "; // общий ответ на ошибку, без уточнения
              if (z=='+') { //проверка символа и выполнение соответсвующей операции. Буквенные символы соответсвуют операциям с римскими цифрами
              ii=x+y;
              output = String.valueOf (ii);
              }
                  if (z=='P') {
                    ii=x+y;
                    output = intStrToRim(ii);
                  }
             if (z=='-') {
                  ii=x-y;
                 output = String.valueOf (ii);
             }
                 if (z=='M') {
                     ii=x-y;
                     output = intStrToRim(ii);
                     if (output == "~") return output = "в римской системе нет отрицательных чисел";
                 }

              if (z=='*') {
                  ii = x * y;
                  output = String.valueOf(ii);
                 }
                  if (z=='m') {
                      ii=x*y;
                      output = intStrToRim(ii);

                  }

              if (z=='/') {
                  ii = x / y;
                  output = String.valueOf(ii);
                 }
                  if (z=='d') {
                      ii=x/y;
                      output = intStrToRim(ii);
                      if (output == "~") return output = "в римской системе чисел меньше 1";
                  }


             /*switch (z) { //какой-то глюк. Сам результат в case считает нормально, но на выход выдает ерунду.
                 case '+': ii=x+y;
                 case '-': ii=x-y;
                 case '*': ii=x*y;
                 case '/': ii=x/y;
                 }*/
             return output;
         }


    static int arabStrToInt (String input) {
        int i=-1; //попытка перевести строку в число. Если перевод завершился ошибкой или числом вне диапазона, то
                  //выдается число "-1" - которое, в дальнейше программе интерпретируем как ошибку ввода данных.

        try
        {
            i = Integer.parseInt(input);

            if (i<1 || i>10) {
                throw new MyException("");
                //i=-1;
            }
        }
        catch (NumberFormatException nfe)
        {  //nfe.printStackTrace();
           System.out.println("throws Exception //введенные символы не являются числами или выходят за допустимый диапазон");
            System.exit(1);
        }
        catch (MyException e) {
            //e.printStackTrace();
            //System.out.println(e.getMessage());
            System.out.println("throws Exception //введенные символы не являются числами или выходят за допустимый диапазон");
            System.exit(1);
        }
        return i;
    }

    static int rimStrToInt (String input) { // преобразование римских цифр в int
        int i = -1;                          // Максимально возможно число по условиям программы это 100 (10х10)

        switch (input) {
            case "I":    i = 1;
                break;
            case "II":   i = 2;
                break;
            case "III":  i = 3;
                break;
            case "IV":   i = 4;
                break;
            case "V":    i = 5;
                break;
            case "VI":   i = 6;
                break;
            case "VII":  i = 7;
                break;
            case "VIII": i = 8;
                break;
            case "IX":   i = 9;
                break;
            case "X":    i = 10;
                break;
            default:     i = -1;
                break;
    }
        return i;


    }

    static String intStrToRim (int input) { // преобразование int числа римские цифры Максимально возможно число по условиям программы это 100 (10х10)
        String i = "";   // в случае любой ошибки возврат числа "-1"

        /*try {
            if (z=='|') {
                throw new MyException("//т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            }
        }
        catch (MyException e) {
            e.printStackTrace();
            //System.out.println(e.getMessage());
            System.exit(1);
        }*/
      try {
          if (input < 1) //(input < 1 || input > 100)
              throw new MyException("");
             //return i = "~"; //символ ошибки, для дальнейшей обработки. И конец работы метода.
      }
      catch (MyException e) {
          //e.printStackTrace();
          System.out.println("throws Exception //т.к. в римской системе нет отрицательных чисел");
          System.exit(1);
      }

        if (input==100) return i = "C";

        while (input >= 90) {
            i = i + "XC";
            input = input - 90;
        }
        while (input >= 50) {
            i = i + "L";
            input = input - 50;
        }
        while (input >= 40) {
            i = i + "XL";
            input = input - 40;
        }
        while (input >= 10) {
            i = i + "X";
            input = input - 10;
        }
        while (input >= 9) {
            i = i + "IX";
            input = input - 9;
        }
        while (input >= 5) {
            i = i + "V";
            input = input - 5;
        }
        while (input >= 4) {
            i = i + "IV";
            input = input - 4;
        }
        while (input >= 1) {
            i = i + "I";
            input = input - 1;
        }


        return i;


    }

    static Members separation (String input) { //метод разделения строки использующий методы преобразования строки.
        int x=-1; // переменные возвращаемые методом. Два числа и арифметическая операция.
        int y=-1; // сли возвращаются значения -1 или | - это дает нам понять, что введенные значения
        char z ='|'; // были не корректные по любой возможной причине
     if (input.indexOf("+") > 0) { // Ищем символ в строке. Если нет, то возвращаем -1-1|

               String [] strs = input.split("\\+"); //разделяем строку по символу "+". Две косые нужны, что бы система не воспринимала его как регулярный символ.
                if (strs.length==2) { //после разделения должно быть 2 члена;
                    x = rimStrToInt(strs[0]); //пытаемся преобразовать арабские циры.
                    y = rimStrToInt(strs[1]);
                    z = 'P';
                    

                    //System.out.println("" +" "+x+" "+y+" "+z);
                    if (x == -1 || y == -1) { //если хоть одно число не корректно, то проверяем римские
                        x = arabStrToInt(strs[0]);
                        y = arabStrToInt(strs[1]);
                        z = '+';
                    }
                    if (x == -1 || y == -1) { //проверяем на корректность римские.
                        x = -1;
                        y = -1;
                        z = '|';
                    }
                }
        } else     if (input.indexOf("-") > 0) {
            String [] strs = input.split("-");
            if (strs.length==2) {
                x = rimStrToInt(strs[0]);
                y = rimStrToInt(strs[1]);
                z = 'M';
                if (x == -1 || y == -1) {
                    x = arabStrToInt(strs[0]);
                    y = arabStrToInt(strs[1]);
                    z = '-';
                }
                if (x == -1 || y == -1) {
                    x = -1;
                    y = -1;
                    z = '|';
                }
            }
          } else     if (input.indexOf("*") > 0) {
            String [] strs = input.split("\\*");
            if (strs.length==2) {
                x = rimStrToInt(strs[0]);
                y = rimStrToInt(strs[1]);
                z = 'm';
                if (x == -1 || y == -1) {
                    x = arabStrToInt(strs[0]);
                    y = arabStrToInt(strs[1]);
                    z = '*';
                }
                if (x == -1 || y == -1) {
                    x = -1;
                    y = -1;
                    z = '|';
                }
            }
           } else  if (input.indexOf("/") > 0) {
            String [] strs = input.split("/");
            if (strs.length==2) {
                x = rimStrToInt(strs[0]);
                y = rimStrToInt(strs[1]);
                z = 'd';
                if (x == -1 || y == -1) {
                    x = arabStrToInt(strs[0]);
                    y = arabStrToInt(strs[1]);
                    z = '/';
                }
                if (x == -1 || y == -1) {
                    x = -1;
                    y = -1;
                    z = '|';
                }
            }
            }
     //else {  }

     try {
          if (z=='|') {
              throw new MyException("");
          }
     }
        catch (MyException e) {
             //e.printStackTrace();
             System.out.println("throws Exception //т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
             System.exit(1);
         }

    return new Members(x,y,z);
    }

}
