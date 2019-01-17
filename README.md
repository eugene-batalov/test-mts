# test-mts

Для создания и запуска задачи выполнить POST запрос без параметров
http://ec2-3-82-42-247.compute-1.amazonaws.com:8080/task

В ответ придет id - строка GUID

Для проверки статуса выполнить GET запрос
http://ec2-3-82-42-247.compute-1.amazonaws.com:8080/task/{id}

Например:
http://ec2-3-82-42-247.compute-1.amazonaws.com:8080/task/17c79c46-78a4-4576-9192-2fa400f72c1c

При создании статус устанавливается created, при запуске running, через 2 минуты finished

Использована in-memory БД H2, консоль доступна по адресу
http://ec2-3-82-42-247.compute-1.amazonaws.com:8080/h2-console

Для подключения поменять
JDBC URL: jdbc:h2:mem:testdb
